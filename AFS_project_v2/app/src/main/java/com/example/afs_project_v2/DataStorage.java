package com.example.afs_project_v2;

public enum DataStorage {
    INSTANCE;

    private Long activeId;
    private ItemBean[] data;
    private ItemVisualBean[] visuals = new ItemVisualBean[]{
            new ItemVisualBean(R.drawable.test_green, R.string.status_open_button, R.string.status_open,
                    R.color.clear_white, R.color.main_text, R.color.main_text),
            new ItemVisualBean(R.drawable.test_yellow, R.string.status_traveling_button, R.string.status_traveling,
                    R.color.travel_bg, R.color.clear_white, R.color.main_text),
            new ItemVisualBean(R.drawable.test_red, R.string.status_working_button, R.string.status_working,
                    R.color.work_bg, R.color.main_text, R.color.clear_white),
            new ItemVisualBean(R.drawable.test_grey, R.string.status_open_button, R.string.status_open,
                    R.color.open_lock_bg, R.color.open_lock_text, R.color.open_lock_text)
    };

    public void setActiveId(Long activeId){
        this.activeId = activeId;
    }

    public void setData(ItemBean[] data){
        this.data = data;
    }

    public Long getActiveId(){
        return activeId;
    }

    public ItemBean[] getData(){
        return data;
    }

    public ItemVisualBean[] getVisuals(){
        return visuals;
    }
}
