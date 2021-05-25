package com.jksdairyhub.jksdairyhub.App.Controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class MyEditTextBold extends AppCompatEditText {
    public MyEditTextBold(Context context) {
        super(context);
        init();
    }

    public MyEditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditTextBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Muli-Bold.ttf");
            setTypeface(tf);
        }
    }
}
