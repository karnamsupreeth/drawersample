package com.demo.supreethks.drawersample;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivityWithDrawer extends AppCompatActivity {

    FrameLayout contentFrame;
    LinearLayout drawerView;
    DrawerLayout leftDrawer;

    public static final String TAG = BaseActivityWithDrawer.class.getSimpleName();
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            Log.d(TAG, "Setting up action bar home button.");
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
        } else {
            Log.d(TAG, "action bar is null");
        }
        setContentView(shouldEnableDrawer() ? R.layout.activity_base_with_drawer : R.layout.activity_base_without_drawer);

        contentFrame = (FrameLayout) findViewById(R.id.content_frame);
        Log.d(TAG, "drawer enabled: " + shouldEnableDrawer());
        if (shouldEnableDrawer()) {
            drawerView = (LinearLayout) findViewById(R.id.drawer);
            leftDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            mDrawerToggle = new ActionBarDrawerToggle(
                    BaseActivityWithDrawer.this,
                    leftDrawer,
                    R.string.app_name,
                    R.string.app_name
            );

            leftDrawer.setDrawerListener(mDrawerToggle);

            //Populate the drawer
            final List<SliderMenuItem> sliderMenuItems = new ArrayList<>();
            SliderMenuItem item1 = new SliderMenuItem();
            item1.setId(1);
            item1.setTitle("Show Screen 1");
            item1.setClickHandler(new SliderMenuItem.MenuItemClickHandler() {
                @Override
                public void handleMenuClick() {
                    startActivity(new Intent(BaseActivityWithDrawer.this, MainActivity.class));
                }
            });

            SliderMenuItem item2 = new SliderMenuItem();
            item2.setId(2);
            item2.setTitle("Show Screen 2");
            item2.setClickHandler(new SliderMenuItem.MenuItemClickHandler() {
                @Override
                public void handleMenuClick() {
                    startActivity(new Intent(BaseActivityWithDrawer.this, MainActivity2.class));
                }
            });

            SliderMenuItem item3 = new SliderMenuItem();
            item3.setId(3);
            item3.setTitle("Show Screen 3");
            item3.setClickHandler(new SliderMenuItem.MenuItemClickHandler() {
                @Override
                public void handleMenuClick() {
                    startActivity(new Intent(BaseActivityWithDrawer.this, MainActivity3.class));
                }
            });

            sliderMenuItems.add(item1);
            sliderMenuItems.add(item2);
            sliderMenuItems.add(item3);
            for (SliderMenuItem item : sliderMenuItems) {
                SliderMenuItemView view = new SliderMenuItemView(this);
                view.setItem(item);
                drawerView.addView(view);
            }
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public abstract boolean shouldEnableDrawer();

    public ViewGroup getFrame() {
        return contentFrame;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
