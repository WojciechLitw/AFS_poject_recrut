package com.example.afs_project_v2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.widget.TextView;

public class TextViewExt extends androidx.appcompat.widget.AppCompatTextView {

    private long itemId;

    public TextViewExt(Context context) {
        super(context);
        setItemId(0l);
    }

    public TextViewExt(Context context, AttributeSet attrs) {
        super(context, attrs);
        setItemId(0l);
    }

    public TextViewExt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemId(0l);
    }


    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getItemId() {
        return itemId;
    }
}