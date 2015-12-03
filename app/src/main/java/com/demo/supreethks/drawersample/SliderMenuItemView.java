package com.demo.supreethks.drawersample;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Supreeth.K.S on 9/8/15.
 */
public class SliderMenuItemView extends AbstractSliderMenuItemView {

    public SliderMenuItemView(Context context) {
        super(context);
    }

    public SliderMenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.view_drawer_item;
    }
}
