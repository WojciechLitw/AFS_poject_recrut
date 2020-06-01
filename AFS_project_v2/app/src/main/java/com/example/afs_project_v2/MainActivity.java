package com.example.afs_project_v2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.afs_project_v2.db_tools.DBReaderWrapper;

public class MainActivity extends AppCompatActivity {

    private ListView itemList;
    private DBReaderWrapper dbWrapper;
    private DataStorage storage = DataStorage.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbWrapper = new DBReaderWrapper(getApplicationContext());

        itemList = (ListView) findViewById(R.id.task_list);
        itemList.setClickable(false);

        prepareStorage();
        listAdapterSetup();
    }

    @Override
    protected void onPause(){
        super.onPause();
        int newStatus = 0;

        if(storage.getActiveId() != null){
            for(ItemBean item: storage.getData()){
                if(item.id == storage.getActiveId()){
                    newStatus = item.status;
                    break;
                }
            }
        }

        dbWrapper.storeTasks(storage.getActiveId(), newStatus);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        dbWrapper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void perform_action(View v){
        TextViewExt tv = (TextViewExt) v;

        ListView mainList = (ListView) findViewById(R.id.task_list);
        Parcelable listState = mainList.onSaveInstanceState();

        long focusedId = tv.getItemId();

        for(ItemBean item: storage.getData()) {
            if (item.id == focusedId) {
                int focusedStatus = item.status;
                focusedStatus = ++focusedStatus % 3;

                item.status = focusedStatus;
                if(storage.getActiveId() == null){
                    storage.setActiveId(item.id);
                }
                if(focusedStatus == 0){
                    storage.setActiveId(null);
                }

                break;
            }
        }

        listAdapterSetup();
        mainList.onRestoreInstanceState(listState);
    }

    private void listAdapterSetup(){
        ItemAdapter adapter = new ItemAdapter(this, R.layout.list_item, storage.getData());
        itemList.setAdapter(adapter);
    }

    private void prepareStorage(){
        ItemBean[] dbData = new ItemBean[0];

        Long storedDataSize = dbWrapper.getTasksSize();
        storage.setActiveId(null);

        if(storedDataSize == 0){
            dbData = initData();
        }
        else {
            dbData = dbWrapper.getStoredData();
            for(ItemBean item: dbData){
                if(item.status > 0){
                    storage.setActiveId(item.id);
                    break;
                }
            }
        }

        storage.setData(dbData);
    }

    private ItemBean[] initData(){
        Long tmpId = -1l;
        ItemBean[] newData = new ItemBean[]{
                new ItemBean(tmpId, "Make cake", 0),
                new ItemBean(tmpId, "Create a new religion", 0),
                new ItemBean(tmpId, "Buy new shoes", 0),
                new ItemBean(tmpId, "Finish this project", 0),
                new ItemBean(tmpId, "Prepare presentation for boss", 0),
                new ItemBean(tmpId, "Learn japanese", 0),
                new ItemBean(tmpId, "Go on strike", 0),
                new ItemBean(tmpId, "Have fun", 0),
                new ItemBean(tmpId, "Lose something", 0),
                new ItemBean(tmpId, "Call mum", 0),
                new ItemBean(tmpId, "Visit real mum", 0),
                new ItemBean(tmpId, "Just do", 0)
        };

        for(ItemBean item: newData){
            tmpId = dbWrapper.insertNewTask(item.name);
            item.id = tmpId;
        }

        return newData;
    }
}
