package com.example.afs_project_v2;

public class ItemVisualBean {
    
    private int buttonImage;
    private int buttonText;
    private int statusText;
    private int backgroundColor;
    private int mainTextColor;
    private int buttonTextColor;
    
    public ItemVisualBean(int buttonImage, int buttonText, int statusText, 
                          int backgroundColor, int mainTextColor, int buttonTextColor){
        this.buttonImage = buttonImage;
        this.buttonText = buttonText;
        this.statusText = statusText;
        this.backgroundColor = backgroundColor;
        this.mainTextColor = mainTextColor;
        this.buttonTextColor = buttonTextColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getButtonImage() {
        return buttonImage;
    }

    public int getButtonText() {
        return buttonText;
    }

    public int getButtonTextColor() {
        return buttonTextColor;
    }

    public int getMainTextColor() {
        return mainTextColor;
    }

    public int getStatusText() {
        return statusText;
    }
}
