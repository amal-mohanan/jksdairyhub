package com.jksdairyhub.jksdairyhub.App.Controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

public class MyAutoComplete extends AppCompatAutoCompleteTextView {
    public MyAutoComplete(Context context) {
        super(context);
        init();
    }

    public MyAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyAutoComplete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Muli-Regular.ttf");
            setTypeface(tf);
        }
    }
}
