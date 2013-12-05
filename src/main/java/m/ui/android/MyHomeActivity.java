package m.ui.android;

/*
 * 个人微博首页
 * 需要依赖框架  https://github.com/bauerca/drag-sort-listview
 * @author chunterg
 */
import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

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

    private GestureDetector mGesture;
    TweetListAdapter adapter;
    DragSortListView lv;
    private List<Tweet> TweetData = SampleData.twList;
    private User currentUser = SampleData.currentUser;
    protected static final int POSTTWEET = 1;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_myhome);
		  
		  lv= (DragSortListView) findViewById(R.id.my_timeline);
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
            	Intent it = new Intent(this, PostActivity.class);
            	Bundle bundle=new Bundle();
            	bundle.putString("username", currentUser.getName());
            	it.putExtras(bundle);       
            	startActivityForResult(it, POSTTWEET); 
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        // 根据上面发送过去的请求吗来区别  
        switch (requestCode) {  
        case POSTTWEET:  
        	String postContent = data.getStringExtra("postContent"); 
        	Tweet newTweet = new Tweet();
            newTweet.setContent(postContent);
            newTweet.setUserName(currentUser.getName());
            TweetData.add(0,newTweet);
            adapter.notifyDataSetChanged();
            break;  
        default:  
            break;  
        }  
    } 


        private DragSortListView.DragSortListener onGoing =
        		new DragSortListView.DragSortListener() {
					
					@Override
					public void remove(int which) {
						// TODO Auto-generated method stub
						Log.d("App-twitter", "delete:"+TweetData.get(which).getTid());
	                    adapter.remove(adapter.getItem(which));
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
