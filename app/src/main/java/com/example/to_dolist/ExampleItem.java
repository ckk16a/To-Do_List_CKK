package com.example.to_dolist;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private boolean done = false;
    public ExampleItem(int imageResource, String text1, String text2) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }
    public void changeText2(String text) {
        mText2 = text;
    }
    public int getImageResource() {
        return mImageResource;
    }

    public void setDone(){
        if(!done)
            done = true;
        else
            done = false;
    }

    public boolean getDone(){
        return done;
    }

    public void changeImage(){
        if(done)
            mImageResource = R.drawable.ic_check;
        else
            mImageResource = R.drawable.ic_x;
    }
    public String getText1() {
        return mText1;
    }
    public String getText2() {
        return mText2;
    }
}