package com.example.deilivry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button testButton;
    ActionMode.Callback actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testButton = findViewById(R.id.testButton);

        testButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setActionModeCallBack();
                startActionMode(actionMode);
                return false;
            }
        });
    }
//////////////////////////////////Options Menu -->>>>>>>/////////////////////////////////
    /*this is all we need to create an option menu from the xml file and add actions to it*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)/*this method like onCreate in activity*/{

        //this two lines to add the menu which is in menu resources
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu_home, menu);

        //the next few lines to make the class in menu has actions like any SearchBox
        /*fist we but the xml view in a variable*/
        SearchView menuSearchView = (SearchView) menu.findItem(R.id.home_menu_search).getActionView();
        /*this the next two lines to receive the changes form the search box*/
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        menuSearchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        /*last thing we put the listener*/
        menuSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO: search submit_text action.
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO: search change_text action.
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu (Menu menu)/*this method is used to change the items of the option menu during activity life cycle*/{
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)/*MenuItem Click Listener*/{
        switch (item.getItemId()){
            case R.id.home_menu_more_help:
                //TODO: help MenuItem
                Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.home_menu_more_settings:
                //TODO: setting menu
                Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.home_menu_more_advanced_1:
                //TODO: advanced options
                Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.home_menu_more_advanced_2:
                Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                //if default should be like this
                super.onOptionsItemSelected(item);
                return false;
        }
    }
    //////////////////////////////////<<<<<<-- Options Menu/////////////////////////////////

//////////////////////////////////Context Menu -->>>>>>>/////////////////////////////////
    /*this type of menus used when we select multiple items in a list view (like selecting photos
    * in galary to move or delete them
    * but to active it we have to call "startActionMode(ActionMode.Callback callback)" inside long press actions
    * eg:
    *   // Called when the user long-clicks on someView
        public boolean onLongClick(View view) {
        if (actionMode != null) {
            return false;
        }

        // Start the CAB using the ActionMode.Callback defined above
        actionMode = getActivity().startActionMode(actionMode);
        view.setSelected(true);
        return true;
    }
    NOTE: we can use the same thing for options menu
    * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu_home, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.home_floating_menu_first:
                //do something using info id as a parameter
                Toast.makeText(this, "Floating menu first item", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.home_floating_menu_second:
                //do something using info is as a parameter
                Toast.makeText(this, "Floating menu second item", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.home_floating_menu_third:
                //do something using info is as a parameter
                Toast.makeText(this, "Floating menu third item", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void setActionModeCallBack(){
        actionMode = new ActionMode.Callback() {

            // Called when the action mode is created; startActionMode() was called
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate a menu resource providing context menu items
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu_home, menu);
                return true;
            }

            // Called each time the action mode is shown. Always called after onCreateActionMode, but
            // may be called multiple times if the mode is invalidated.
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false; // Return false if nothing is done
            }

            // Called when the user selects a contextual menu item
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_floating_menu_first:
                        Toast.makeText(getApplicationContext(), "context menu first", Toast.LENGTH_SHORT).show();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    case R.id.home_floating_menu_second:
                        Toast.makeText(getApplicationContext(), "context menu second", Toast.LENGTH_SHORT).show();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    case R.id.home_floating_menu_third:
                        Toast.makeText(getApplicationContext(), "context menu third", Toast.LENGTH_SHORT).show();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            // Called when the user exits the action mode
            @Override
            public void onDestroyActionMode(ActionMode mode) {
//                actionMode = null;
            }
        };
    }
    //////////////////////////////////<<<<<<-- Context Menu/////////////////////////////////

//////////////////////////////////ListView Selection Menu -->>>>>>>/////////////////////////////////

    private ListView setListViewSelectingMode(ListView listView){
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                //already explained
                return true;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu_home, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an <code><a href="/reference/android/view/ActionMode.html#invalidate()">invalidate()</a></code> request
                return false;
            }
        });
        return listView;
    }

    //////////////////////////////////<<<<<<-- ListView Selection Menu/////////////////////////////////


}