package com.example.afs_project_v2.db_tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.afs_project_v2.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class DBReaderWrapper {

    DBReaderHelper dbHelper;

    public DBReaderWrapper(Context context){
        dbHelper = new DBReaderHelper(context);
    }

    public void close(){
        dbHelper.close();
    }

    public Long getTasksSize(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        SQLiteStatement countStatement = db.compileStatement(DBReaderContract.getSqlTasksSize());
        return countStatement.simpleQueryForLong();
    }

    public Long insertNewTask(String taskName){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newRowId = -1;

        ContentValues values = new ContentValues();
        values.put(DBReaderContract.DBEntry.TASK_NAME, taskName);
        values.put(DBReaderContract.DBEntry.TASK_STATUS, 0);

        newRowId = db.insert(DBReaderContract.DBEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public ItemBean[] getStoredData(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ItemBean> storedDataList = new ArrayList<>();
        Cursor cursor;

        String[] projection = {
                BaseColumns._ID,
                DBReaderContract.DBEntry.TASK_NAME,
                DBReaderContract.DBEntry.TASK_STATUS
        };

        cursor = db.query(DBReaderContract.DBEntry.TABLE_NAME, projection,
                null, null, null, null, null);

        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DBReaderContract.DBEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DBReaderContract.DBEntry.TASK_NAME));
            int status = cursor.getInt(cursor.getColumnIndexOrThrow(DBReaderContract.DBEntry.TASK_STATUS));

            storedDataList.add(new ItemBean(id, name, status));
        }

        return storedDataList.toArray(new ItemBean[0]);
    }

    public boolean storeTasks(Long activeId, int newStatus){
        String[] selectArgs = null;
        boolean cleanup = false;
        boolean update = (activeId != null);

        ContentValues values = new ContentValues();
        values.put(DBReaderContract.DBEntry.TASK_STATUS, 0);

        String selection = DBReaderContract.DBEntry.TASK_STATUS + " > 0";

        cleanup = updateTasks(values, selection, null);

        if(update){
            values.put(DBReaderContract.DBEntry.TASK_STATUS, newStatus);
            selection = DBReaderContract.DBEntry._ID + " = ?";
            selectArgs = new String[] {Long.toString(activeId)};

            update = updateTasks(values, selection, selectArgs);
        }

        return (cleanup && update);
    }

    private boolean updateTasks(ContentValues values, String selection, String[]selectArgs){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int count = db.update(DBReaderContract.DBEntry.TABLE_NAME,
                values, selection, selectArgs);

        return count > 0;
    }
}
