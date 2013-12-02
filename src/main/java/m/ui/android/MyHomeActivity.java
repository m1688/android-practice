package m.ui.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import m.domain.tweet.Tweet;
import m.domain.user.User;
import m.domain.user.UserRepository;
import m.infrastructure.SampleData;
import m.infrastructure.impl.DefaultUserRepository;
import m.ui.android.adapter.TweetListAdapter;
import m.ui.android.adapter.UserListAdapter;

public class MyHomeActivity extends Activity {


    private List<Tweet> Tweets;
//
//    private static final int pageSize = 10;
//    private int pageNum = 1;
//    private int lastUserIndex = 0;
    
    private List<Tweet> TweetData = SampleData.twList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhome);
        
        ListView lv = (ListView)findViewById(R.id.my_timeline);
        Log.d("App-twitter","ListView created");
        TweetListAdapter adapter = new TweetListAdapter(this,R.layout.tweet_item,TweetData);
        Log.d("App-twitter","Adapter created");
        lv.setAdapter(adapter);
//        ListView userListView = (ListView)findViewById(R.id.userListView);
//        UserListAdapter userListAdapter = new UserListAdapter(this, android.R.layout.simple_list_item_2, SampleData.list);
//        userListView.setAdapter(userListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.myhome, menu);
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
            case R.id.post_twitter:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
