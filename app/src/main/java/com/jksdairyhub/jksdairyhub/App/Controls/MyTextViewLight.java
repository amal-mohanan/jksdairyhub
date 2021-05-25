package com.jksdairyhub.jksdairyhub.App.Controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class MyTextViewLight extends AppCompatTextView {

    public MyTextViewLight(Context context) {
        super(context);
        init();
    }

    public MyTextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextViewLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Muli-Light.ttf");
            setTypeface(tf);
        }
    }
}
