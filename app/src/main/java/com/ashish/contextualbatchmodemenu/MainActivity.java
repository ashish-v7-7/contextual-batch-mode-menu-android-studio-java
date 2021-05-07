package com.ashish.contextualbatchmodemenu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

// App such as Gmail or a file browser, you've probably seen Contextual Mode when selecting multiple items
// Three key elements -
//1. Creating a Contextual Menu to inflate
//2. Defining MultiChoiceModeListener to pass to setMultiChoiceModeListener()
//3. Set ChoiceMode of the ListView to CHOICE_MODE_MULTIPLE_MODAL.

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create a ListView populated with multiple country names to demonstrate multiple selections or batch mode.
        String[] countries = new String[]{ "France",
                 "India", "Russia", "China","United Kingdom",
                "United States"};
        ListAdapter countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, countries);
        setListAdapter(countryAdapter);

        //define MultiChoiceModeListener to handle batch mode with
        //the ListView. We then set up the ListView to allow multiple selections and pass in the
        //MultiChoiceModeListener

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        getListView().setMultiChoiceModeListener(mMultiChoiceModeListener);
        getListView().setOnItemClickListener((parent, view, position, id) -> ((ListView) parent).setItemChecked(position,
                true));
    }


    //Create a MultiChoiceModeListener to handle the Contextual Action Bar events
    AbsListView.MultiChoiceModeListener
            mMultiChoiceModeListener = new
            AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                }
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    // Inflate the menu for the CAB
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.contextual_menu, menu);
                    return true;
                }
                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }
                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    // Handle menu selections
                    switch (item.getItemId()) {
                        case R.id.menu_move:
                            Toast.makeText(MainActivity.this, "Move", Toast.LENGTH_SHORT).show();
                            mode.finish();
                            return true;
                        case R.id.menu_delete:
                            Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                            mode.finish();
                            return true;
                        default:
                            return false;
                    }
                }
                @Override
                public void onDestroyActionMode(ActionMode mode) {
                }
            };
}