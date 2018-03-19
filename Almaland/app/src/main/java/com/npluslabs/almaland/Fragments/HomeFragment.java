package com.npluslabs.almaland.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.npluslabs.almaland.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {


    public HomeFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
//        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);
//        searchView.setVoiceSearch(true);
//        mSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
//        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
//            @Override
//            public void onSearchTextChanged(String oldQuery, final String newQuery) {
//                Log.i("old->" + oldQuery,"new->"+ newQuery);
//                //get suggestions based on newQuery
//
//                //pass them on to the search view
////                mSearchView.swapSuggestions(newSuggestions);
//            }
//        });
//        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
//
//            @Override
//            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
//
//            }
//
//            @Override
//            public void onSearchAction(String currentQuery) {
//                Log.i("Query->" , currentQuery);
//            }
//        });

//        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                //Do some magic
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //Do some magic
//                return false;
//            }
//        });
//
//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//                //Do some magic
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                //Do some magic
//            }
//        });
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.search_menu_items, menu);
//
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
//            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            if (matches != null && matches.size() > 0) {
//                String searchWrd = matches.get(0);
//                if (!TextUtils.isEmpty(searchWrd)) {
//                    searchView.setQuery(searchWrd, false);
//                }
//            }
//
//            return;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//    @Override
//    public void onBackPressed() {
//        if (searchView.isSearchOpen()) {
//            searchView.closeSearch();
//        } else {
//            super.onBackPressed();
//        }
//    }



//    @Override
//    public boolean onActivityBackPress() {
//        //if mSearchView.setSearchFocused(false) causes the focused search
//        //to close, then we don't want to close the activity. if mSearchView.setSearchFocused(false)
//        //returns false, we know that the search was already closed so the call didn't change the focus
//        //state and it makes sense to call supper onBackPressed() and close the activity
//        if (!mSearchView.setSearchFocused(false)) {
//            return false;
//        }
//        return true;
//    }

//    private void setupDrawer() {
//        attachSearchViewActivityDrawer(mSearchView);
//    }
}
