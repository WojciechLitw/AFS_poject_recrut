package com.example.afs_project_v2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<ItemBean> {
    Context context;
    int layoutResourceId;
    ItemBean data[] = null;
    DataStorage storage = DataStorage.INSTANCE;

    static class ItemBeanHolder{
        RelativeLayout itemCore;
        FrameLayout buttonFrame;
        ImageView fakeButtonBG;
        TextViewExt fakeButtonTxt;
        TextView taskName;
        TextView statusName;
    }

    public ItemAdapter(Context context, int layoutResourceId, ItemBean[] data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup parent){
        View item = convertView;
        ItemBeanHolder holder = null;

        if(item == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            item = inflater.inflate(layoutResourceId, parent, false);

            holder = new ItemBeanHolder();
            holder.itemCore = (RelativeLayout) item.findViewById(R.id.item_core);
            holder.buttonFrame = (FrameLayout) item.findViewById(R.id.fake_button);
            holder.taskName = (TextView) item.findViewById(R.id.task_name);
            holder.fakeButtonBG = (ImageView) item.findViewById(R.id.button_background);
            holder.statusName = (TextView) item.findViewById(R.id.status_name);
            holder.fakeButtonTxt = (TextViewExt) item.findViewById(R.id.button_text);

            item.setTag(holder);
        }
        else {
            holder = (ItemBeanHolder) item.getTag();
        }
        modifyHolder(holder, data[positon]);

        return item;
    }

    private void modifyHolder(ItemBeanHolder holder, ItemBean srcData){
        ItemVisualBean visuals = null;
        holder.taskName.setText(srcData.name);
        holder.fakeButtonTxt.setItemId(srcData.id);
        holder.fakeButtonTxt.setSelected(true);

        if(srcData.status == 0 && storage.getActiveId() != null){
            visuals = storage.getVisuals()[0];
            holder.buttonFrame.setVisibility(View.INVISIBLE);
        }
        else {
            visuals = storage.getVisuals()[srcData.status];
            holder.buttonFrame.setVisibility(View.VISIBLE);
        }

        holder.fakeButtonBG.setImageResource(visuals.getButtonImage());
        holder.fakeButtonTxt.setText(visuals.getButtonText());
        holder.statusName.setText(visuals.getStatusText());

        holder.itemCore.setBackgroundColor(convertToColor(visuals.getBackgroundColor()));
        holder.taskName.setTextColor(convertToColor(visuals.getMainTextColor()));
        holder.statusName.setTextColor(convertToColor(visuals.getMainTextColor()));
        holder.fakeButtonTxt.setTextColor(convertToColor(visuals.getButtonTextColor()));
    }
    
    private int convertToColor(int resourceColor){
        return getContext().getResources().getColor(resourceColor);
    }
}
