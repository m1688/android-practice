package m.ui.android;

import android.app.ActionBar;
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
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

public class PostActivity extends Activity {



    private EditText postContentEditText = null;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		//预先设置允许改变的窗口状态，需在 setContentView 之前调用，否则设置标题时抛运行时错误。
		  
		  setContentView(R.layout.activity_post);
		  
		  ActionBar actionBar = getActionBar();
		  actionBar.setDisplayHomeAsUpEnabled(true);
		  
		  Bundle extras = getIntent().getExtras();
		  
		  if (extras != null) {
			  String title = extras.getString("username");
			  setTitle(title);
		  }
//		  设置输入框焦点
		  postContentEditText= (EditText)findViewById(R.id.postcontent);
		  postContentEditText.setFocusableInTouchMode(true);  
		  postContentEditText.requestFocus(); 
//		  自动载入键盘，用timer延时加载，避免在界面生成前弹出
		 Timer timer = new Timer();  
	     timer.schedule(new TimerTask(){  
	         public void run()  
	         {  
	             InputMethodManager inputManager =  
	                 (InputMethodManager)postContentEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);  
	             inputManager.showSoftInput(postContentEditText, 0);  
	         }  
	           
	     },300);   
		  
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
            	Intent i = new Intent(this, MyHomeActivity.class);  
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
                startActivity(i);   
                return true;  
            case R.id.post_btn:
            	 EditText postContent = (EditText) findViewById(R.id.postcontent);
	        	 Intent mIntent = new Intent();  
	             mIntent.putExtra("postContent", postContent.getText().toString());  
	             // 设置结果，并进行传送  
	             this.setResult(MyHomeActivity.POSTTWEET, mIntent); 
	             this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
