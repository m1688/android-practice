package m.ui.android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View.OnTouchListener; 
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import m.domain.tweet.Tweet;
import m.domain.user.User;
import m.infrastructure.SampleData;
import m.ui.android.R;

/**
 * chunterg on 12/2/13.
 */
public class TweetListAdapter extends ArrayAdapter<Tweet> {

    private final Context context;   
    private List<Tweet> tweetList;
    private int resource;  
    int startX = 0;
    int endX = 0;
    private GestureDetector mGesture = null;

    public TweetListAdapter(Context context, int resource, List<Tweet> tweetList) {

        super(context, resource, tweetList);
        this.context = context;
        this.tweetList = tweetList;
        this.resource = resource;
    }
    
    public final class ListItemView{  
        public TextView twitterUserName;     
        public TextView twitterContent; 
     }

    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	ListItemView  listItemView = null;
    	
    	if (convertView == null) {
    		listItemView = new ListItemView();
//    		获取布局直接调用，不用实例化
            convertView = LayoutInflater.from(context).inflate(resource, null);
            listItemView.twitterUserName = (TextView) convertView.findViewById(R.id.twitter_username);
            listItemView.twitterContent = (TextView) convertView.findViewById(R.id.twitter_content);
            convertView.setTag(listItemView); 
            
        }else {   
            listItemView = (ListItemView)convertView.getTag();   
        }
    	
		// 为每一个view项设置触控监听
//    	convertView.setOnTouchListener(new OnTouchListener() {
//    		  
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
////				Listview touch事件
//				 switch(event.getAction())//根据动作来执行代码     
//                 {    
//                 case MotionEvent.ACTION_MOVE://滑动     
//                     Toast.makeText(context, "move...", Toast.LENGTH_SHORT).show();  
//                     break;    
//                 case MotionEvent.ACTION_DOWN://按下     
//                     startX = (int) event.getX(); 
//                     Log.d("app-twitter", "start:"+startX+"");
//                     break;    
//                 case MotionEvent.ACTION_UP://松开     
//                	 endX = (int) event.getX();  
//                	 Log.d("app-twitter", "end:"+endX+"");
//                	 Log.d("app-twitter", "move:"+(Math.abs(endX-startX))+"");
//                	 if(Math.abs(endX-startX) > 20){  
////                         holder.cBox.setVisibility(View.VISIBLE);  
//                     }  
//                     break;    
//                 default:    
//                 }    
//                 return true;   
//			}
//			
//		});  
    	listItemView.twitterUserName.setText(tweetList.get(position).getUserName());
    	listItemView.twitterContent.setText(tweetList.get(position).getContent());
        return convertView;
    }


}
