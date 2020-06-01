package com.example.afs_project_v2.db_tools;

import android.provider.BaseColumns;

public final class DBReaderContract {

    private static final String SQL_TASKS_CREATE = "CREATE TABLE " + DBEntry.TABLE_NAME + " ("
                                                + DBEntry._ID + " INTEGER PRIMARY KEY,"
                                                + DBEntry.TASK_NAME + " TEXT,"
                                                + DBEntry.TASK_STATUS + " INTEGER)";
    private static final String SQL_TASKS_DELETE = "DROP TABLE IF EXISTS " + DBEntry.TABLE_NAME;
    private static final String SQL_TASKS_SIZE = "SELECT COUNT(*) FROM " + DBEntry.TABLE_NAME;

    private DBReaderContract(){
    }

    public static class DBEntry implements BaseColumns{
        public static final String TABLE_NAME = "TASKS";
        public static final String TASK_NAME = "NAME";
        public static final String TASK_STATUS = "STATUS";
    }

    public static String getSqlTasksCreate() {
        return SQL_TASKS_CREATE;
    }

    public static String getSqlTasksDelete() {
        return SQL_TASKS_DELETE;
    }

    public static String getSqlTasksSize() {
        return SQL_TASKS_SIZE;
    }
}
