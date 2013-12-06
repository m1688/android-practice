package m.ui.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class DetailActivity extends Activity {
	private ListView commentListView;
	private List<Map<String, Object>> commentContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		commentListView = (ListView)this.findViewById(R.id.comment_list_view);
		commentContent = getData();
		commentListView.setAdapter(new CommnetAdapter(this));
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comment_user_img", R.drawable.comment_user_img);
		map.put("comment_user_name", "淘宝");
		map.put("comment_info", "我是淘宝");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("comment_user_img", R.drawable.comment_user_img);
		map.put("comment_user_name", "支付宝");
		map.put("comment_info", "我是支付宝");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("comment_user_img", R.drawable.comment_user_img);
		map.put("comment_user_name", "天猫");
		map.put("comment_info", "我是天猫");
		list.add(map);
		
		return list;
	}
	
	private class CommnetAdapter extends BaseAdapter{

		private Context context;
		
		public CommnetAdapter(Context context){
			this.context = context;
		}
		
		@Override
		public int getCount() {
			return commentContent.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View view, ViewGroup viewGroup) {
			
			ItemViewCache viewCache = null;
			
			if(view == null){
				view = LayoutInflater.from(context).inflate(R.layout.comment_item, null);
				viewCache = new ItemViewCache();  
                viewCache.commentUserImg = (ImageView)view.findViewById(R.id.comment_user_img);
                viewCache.commentUserName = (TextView)view.findViewById(R.id.comment_user_name);
                viewCache.commentInfo = (TextView)view.findViewById(R.id.comment_info);
                view.setTag(viewCache);
			} else {
				viewCache = (ItemViewCache)view.getTag();
			}
			
			viewCache.commentUserImg.setBackgroundResource((Integer)commentContent.get(position).get("comment_user_img"));
			viewCache.commentUserName.setText((String)commentContent.get(position).get("comment_user_name"));
			viewCache.commentInfo.setText((String)commentContent.get(position).get("comment_info"));
			
			return view;
		}
	}
	
	private final class ItemViewCache{
		public ImageView commentUserImg;
		public TextView commentUserName;
		public TextView commentInfo;
	}
}
