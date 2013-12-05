package m.ui.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
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

import javax.security.auth.PrivateCredentialPermission;

import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.DragSortController;

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
    private GestureDetector mGesture;
    TweetListAdapter adapter;
    private List<Tweet> TweetData = SampleData.twList;


    @Override
	protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_myhome);
		  
		  DragSortListView lv = (DragSortListView) findViewById(R.id.my_timeline);
	      lv.setDragSortListener(onGoing);
//	      lv.setRemoveListener(onRemove);

		  adapter = new TweetListAdapter(this,R.layout.tweet_item,TweetData);
		  Log.d("App-twitter","Adapter created");
		  lv.setAdapter(adapter);
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


        private DragSortListView.RemoveListener onRemove = 
            new DragSortListView.RemoveListener() {
                @Override
                public void remove(int which) {
//                	remove callback
                    Log.d("App-twitter", "delete:"+TweetData.get(which).getTid());
                    adapter.remove(adapter.getItem(which));
                }
            };
            
        private DragSortListView.DragSortListener onGoing =
        		new DragSortListView.DragSortListener() {
					
					@Override
					public void remove(int which) {
						// TODO Auto-generated method stub
						Log.d("App-twitter", "delete:"+TweetData.get(which).getTid());
//	                    adapter.remove(adapter.getItem(which));
					}
					
					@Override
					public void drag(int from, int to) {
						// TODO Auto-generated method stub
						Log.d("App-twitter", "drag from:"+from+" to:"+to);
					}
					
					@Override
					public void drop(int from, int to) {
						// TODO Auto-generated method stub
						Log.d("App-twitter", "drop from:"+from+" to:"+to);
						
					}
				};
}
