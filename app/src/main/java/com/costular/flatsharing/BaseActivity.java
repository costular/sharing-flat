package com.costular.flatsharing;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by diego on 7/12/15.
 */
public class BaseActivity extends AppCompatActivity {

    public static final int SEMI_TRANSPARENT = 1;
    public static final int TRANSPARENT = 2;
    public static final int OPAQUE = 3;

    private Toolbar toolbar;
    private Handler handler;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }

    protected void setUpToolbar(boolean homeEnabled) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        status = OPAQUE;

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        if(homeEnabled) {
            if (getSupportActionBar() != null) {

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }
    }

    public int getType() {
        return status;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbarType(int type) {
        switch (type) {
            case TRANSPARENT:
                status = TRANSPARENT;
                toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                break;
            case OPAQUE:
                status = OPAQUE;
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case SEMI_TRANSPARENT:
                status = SEMI_TRANSPARENT;
                toolbar.setBackgroundColor(Color.BLACK);
                toolbar.getBackground().setAlpha(20);
                break;
        }
    }

    public int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    public int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }

    public void setToolbarTitle(final CharSequence title) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(title);
            }
        });
    }

    public void setToolbarColor(int color) {
        toolbar.setBackgroundColor(color);
    }
}