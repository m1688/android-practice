package m.ui.android.adapter;

import java.util.List;

import m.domain.user.User;
import m.ui.android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FollowerArrayAdapter extends ArrayAdapter<User>{
	private int resource;
	public FollowerArrayAdapter(Context context, int resource,
			List<User> objects) {
		super(context, resource, objects);
		this.resource = resource;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout view;
		final User user = getItem(position);
		if(convertView == null) {
			view = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li = (LayoutInflater)getContext().getSystemService(inflater);
			li.inflate(resource, view, true);
		} else {
			view = (LinearLayout)convertView;
		}
		
		ImageView img = (ImageView)view.findViewById(R.id.follower_img);
		img.setImageResource(R.drawable.follower1);//从user中取，下载到本地，再用本地地址
		
		TextView nameView = (TextView)view.findViewById(R.id.name);
		nameView.setText(user.getName());
		
		TextView nickNameView = (TextView)view.findViewById(R.id.nickname);
		nickNameView.setText(user.getNickName());
		
		Button focusButton = (Button)view.findViewById(R.id.focusButton);
		focusButton.setText(R.string.follower_button_cancel_focus);
		focusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button fb = (Button)v;
				String currentAction = fb.getText().toString();
				String cancelFocus = getContext().getResources().getString(R.string.follower_button_cancel_focus);
				if(cancelFocus.equalsIgnoreCase(currentAction)) {
					Toast.makeText(getContext(), R.string.msg_cancle_focus, Toast.LENGTH_SHORT).show();
					remove(user);
					notifyDataSetChanged();
				}
			}
		});
		return view;
	}
}
