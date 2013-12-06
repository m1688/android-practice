/**
 * 
 */
package m.ui.android.adapter;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import m.ui.android.R;

import org.apache.http.impl.cookie.DateUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author wei.xinw
 * 
 */
public class WeiboListAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> mData;
	private ImageView img;

	private LayoutInflater mInflater;

	public WeiboListAdapter(Context context) {
		this.mInflater = LayoutInflater.from(context);
		mData = getData();
	}

	// 决定ListView有几行可见
	@Override
	public int getCount() {
		return mData.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.weibo_list_item, null);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		title.setText(mData.get(position).get("title").toString());
		TextView time = (TextView) convertView.findViewById(R.id.time);
		time.setText(mData.get(position).get("time").toString());
		TextView info = (TextView) convertView.findViewById(R.id.info);
		info.setText(mData.get(position).get("info").toString());
		img = (ImageView) convertView.findViewById(R.id.img);
		img.setBackgroundResource((Integer) mData.get(position).get("img"));
		return convertView;
	}

	public void refresh(List<HashMap<String, Object>> mData) {
		this.mData = mData;
		notifyDataSetChanged();
	}

	public List<HashMap<String, Object>> getData() {
		if (mData != null && this.mData.size() > 0) {
			return mData;
		} else {
			List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
			HashMap<String, Object> map = null;
			for (int i = 1; i <= 5; i++) {
				map = new HashMap<String, Object>();
				map.put("title", "人物" + i);
				map.put("time", DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
				map.put("info", "这个不是很简单呀！");
				map.put("img", R.drawable.ic_launcher);
				list.add(map);
				mData = list;
			}
		}

		return mData;
	}

	public void setmData(List<HashMap<String, Object>> mData) {
		this.mData = mData;
	}

}
