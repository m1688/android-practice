package m.ui.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import m.domain.user.User;
import m.domain.user.UserRepository;
import m.infrastructure.SampleData;
import m.infrastructure.impl.DefaultUserRepository;
import m.ui.android.adapter.UserListAdapter;

public class SearchActivity extends Activity {
    private boolean isSearchInputBoxCleared = false;
    private UserRepository userRepository = new DefaultUserRepository();
    private User currentUser = SampleData.currentUser;

    private List<User> searchedUsers;

    private static final int pageSize = 10;
    private int pageNum = 1;
    private int lastUserIndex = 0;

    //views
    private ListView userListView;
    private UserListAdapter userListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        View searchBoxView = findViewById(R.id.search_keyword);
        EditText editText = (EditText) searchBoxView;
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //handle search
                    handled = true;
                }
                return handled;
            }
        });

        AutoCompleteTextView searchBoxAutoComplete = (AutoCompleteTextView) searchBoxView;

        //clear keyword input box on click
        searchBoxAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AutoCompleteTextView searchBoxAutoComplete = (AutoCompleteTextView) view;
                if (!isSearchInputBoxCleared) {
                    searchBoxAutoComplete.setText("");
                    isSearchInputBoxCleared = true;
                }
            }
        });
        String[] searchArray = getResources().getStringArray(R.array.search_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchArray);
        searchBoxAutoComplete.setAdapter(adapter);

        userListView = (ListView) findViewById(R.id.userListView);

        userListView.setOnScrollListener(new LoadMoreListener(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSearchTextClicked(View view) {
        AutoCompleteTextView editText = (AutoCompleteTextView) view;
        editText.setText("");
    }

    public void followOrUnfollowUser(View view) {
        Integer index = userListView.getPositionForView(view);
        User selectUser = (User) userListView.getAdapter().getItem(index);

        Button followButton = (Button) view;

        if (selectUser.isMyFollower(currentUser)) {
            selectUser.unfollow(currentUser);
            followButton.setText(R.string.follow_button);
        } else {
            selectUser.addFollower(currentUser);
            followButton.setText(R.string.unfollow);
        }
    }

    public void searchUsers(View view) {
        EditText editText = (EditText) findViewById(R.id.search_keyword);
        List<User> searchedUserList = userRepository.fuzzySearchByUserName(editText.getText().toString());
        setSearchedUsers(searchedUserList);

        TextView noSearchResult = (TextView) findViewById(R.id.no_user_search_result);
        noSearchResult.setText("");
        noSearchResult.setVisibility(View.INVISIBLE);
        if (searchedUserList == null || searchedUserList.isEmpty()) {
            noSearchResult.setVisibility(View.VISIBLE);
            noSearchResult.setText(R.string.no_search_result);
        }
        userListAdapter = new UserListAdapter(this, android.R.layout.simple_list_item_2, loadOneMorePageUsers());
        userListView.setAdapter(userListAdapter);
    }

    //TODO remove side effects
    public List<User> loadOneMorePageUsers() {
        int from = (pageNum - 1) * pageSize;
        int to = from + pageSize;
        to = to > searchedUsers.size() ? searchedUsers.size() : to;
        lastUserIndex = to;
        return searchedUsers.subList(0, to);
    }

    public List<User> getSearchedUsers() {
        return searchedUsers;
    }

    public void setSearchedUsers(List<User> searchedUsers) {
        this.searchedUsers = searchedUsers;
        pageNum = 1;
        lastUserIndex = 0;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_search, container, false);
            return rootView;
        }
    }

    public class LoadMoreListener implements AbsListView.OnScrollListener {

        private Context context;

        public LoadMoreListener(Context context) {
            this.context = context;
        }

        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {

        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {

            boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;
            if (searchedUsers != null && lastUserIndex < searchedUsers.size() && loadMore) {
                pageNum++;
                userListAdapter.setUserList(loadOneMorePageUsers());
            }

        }
    }

}
