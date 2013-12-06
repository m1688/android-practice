package m.ui.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyProfileActivity extends Activity {
	private static SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
	private static SimpleDateFormat yearBeforeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
		
		listView = (ListView)findViewById(R.id.message_list);
		
		SimpleAdapter adapter = new SimpleAdapter(this, getData(), 
				R.layout.list_row, 
				new String[]{"title", "postTime", "content"}, 
				new int[]{R.id.title, R.id.post_time, R.id.content});
		
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_profile, menu);
		return true;
	}
	
	
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		list.add(createMessage("测试1", new Date(), "但是，当我无意间翻阅-current版的邮件列表时，偶然发现了一些关于BSD grep与GNU grep性能的讨论，你可能也注意到了那些讨论。不管怎么说，仅供参考吧，下面是一些简单的总结，关于为什么GNU grep如此之快。或许你能借鉴其中的一些思想运用到BSD grep中去。"));
		return list;
	}
	
	private Map<String, Object> createMessage(String title, Date postTime, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("postTime", currentYear(postTime) ? format.format(postTime) : yearBeforeFormat.format(postTime));
		map.put("content", content);
		return map;
	}
	
	private boolean currentYear(Date date){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		cal.setTime(date);
		return year == cal.get(Calendar.YEAR); 
	}

}
