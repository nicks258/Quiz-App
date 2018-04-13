package com.npluslabs.almaland;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.npluslabs.almaland.Fragments.ConnectionsFragment;
import com.npluslabs.almaland.Fragments.HomeFragment;
import com.npluslabs.almaland.Fragments.MessageFragment;
import com.npluslabs.almaland.Fragments.NotificationFragment;

public class MainActivity extends AppCompatActivity {
    private FloatingSearchView mSearchView;
    Fragment selectedFragment;
    FragmentTransaction fragmentTransaction;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_connections:
                    selectedFragment = new ConnectionsFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_message:
                    selectedFragment = new MessageFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    selectedFragment = new NotificationFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_jobs:
                    Log.i("Wowo Messa","true");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        HomeFragment selectedFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,selectedFragment);
        fragmentTransaction.commit();
        mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                Log.i("old->" + oldQuery,"new->"+ newQuery);
                //get suggestions based on newQuery

                //pass them on to the search view
//                mSearchView.swapSuggestions(newSuggestions);
            }
        });
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {

            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                Log.i("Query->" , currentQuery);
            }
        });
    }

}
