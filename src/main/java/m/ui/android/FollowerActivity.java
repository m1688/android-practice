package m.ui.android;

import java.util.ArrayList;
import java.util.List;

import m.domain.user.User;
import m.infrastructure.SampleData;
import m.ui.android.adapter.FollowerArrayAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FollowerActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_follower);
		
		ListView followerView = (ListView)findViewById(R.id.myfollowerList);
		
		User user = SampleData.currentUser;
		List<Long> followerIds = user.getFollowees();
		List<User> followers = new ArrayList<User>();
		for(Long followerId : followerIds) {
//		    followers.add();
		}
		followers = SampleData.list;//for test
		ArrayAdapter<User> adapter = new FollowerArrayAdapter(this, R.layout.follower_item, followers);
		followerView.setAdapter(adapter);
	}
}
