package com.dariuszdeoniziak.charades.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dariuszdeoniziak.charades.R;
import com.dariuszdeoniziak.charades.activities.fragments.BaseFragment;
import com.dariuszdeoniziak.charades.activities.fragments.CategoriesFragment;
import com.dariuszdeoniziak.charades.models.TestClass;

import javax.inject.Inject;

@Layout(R.layout.activity_categories)
public class CategoriesActivity extends BaseActivity {

    private static final String KEY_FRAGMENT = "key_fragment";
    BaseFragment fragment;

    @Inject SharedPreferences sharedPreferences;
    @Inject TestClass testClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragment = getSavedFragment(savedInstanceState, KEY_FRAGMENT);
        if (fragment == null) {
            fragment = new CategoriesFragment();
            replaceFragment(null, fragment, R.id.fragment_container, fragment.TAG);
        }

        Log.i(TAG, "onCreate: " + testClass.say());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveFragment(fragment, outState, KEY_FRAGMENT);
    }
}
