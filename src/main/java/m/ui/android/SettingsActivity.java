package m.ui.android;

import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * 设置界面
 * 
 * @author ticmy
 */
public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
    /**
     * 是否推送消息
     */
    public static final String KEY_RECEIVE_PUSH_MSG = "key_push_msg";
    /**
     * 是否响铃
     */
    public static final String KEY_RECEIVE_BELL = "key_receive_bell";
    /**
     * 铃声
     */
    public static final String KEY_RINGTONE = "key_ringtone";
    /**
     * 是否震动
     */
    public static final String KEY_RECEIVE_VIBRATE = "key_receive_vibrate";
    /**
     * @我提醒
     */
    public static final String KEY_AT_REMIND = "key_at_remind";
    /**
     * 评论提醒
     */
    public static final String KEY_COMMENT_REMIND = "key_comment_remind";
    /**
     * 私信提醒
     */
    public static final String KEY_PRIVATE_MSG_REMIND = "key_private_msg_remind";
    /**
     * 新关注提醒
     */
    public static final String KEY_NEW_FANS_REMIND = "key_new_fans_remind";
    /**
     * 消息获取频率
     */
    public static final String KEY_MSG_PULL_FREQUENCY = "key_msg_pull_frequency";
    /**
     * 阅读模式：有图、无图
     */
    public static final String KEY_READING_MODE = "key_reading_mode";
    /**
     * 字号
     */
    public static final String KEY_FONT_SIZE = "key_font_size";
    /**
     * 是否自动加载更多
     */
    public static final String KEY_LOAD_MORE = "key_load_more";
    
    /**
     * 更改配置后需要更新summary显示的那些preference
     */
    private Map<String, Summary> needUpdatePreferenceMap = new HashMap<String, SettingsActivity.Summary>();
    
    {
        needUpdatePreferenceMap.put(KEY_MSG_PULL_FREQUENCY, new Summary("每隔", "分钟刷新一次"));
        needUpdatePreferenceMap.put(KEY_RINGTONE, new Summary("", ""){
            public String getSummary(String value) {
                int lastSlashIndex = -1;
                if(value != null && (lastSlashIndex = value.lastIndexOf("/")) != -1) {
                    return value.substring(lastSlashIndex + 1);
                }
                return "";
            }
        });
        needUpdatePreferenceMap.put(KEY_READING_MODE, new Summary("", "") {
            public String getSummary(String value) {
                if("true".equalsIgnoreCase(value)) {
                    return "有图模式";
                }
                return "无图模式";
            }
        });
        needUpdatePreferenceMap.put(KEY_FONT_SIZE, new Summary("", "") {
            public String getSummary(String value) {
                if("small".equalsIgnoreCase(value)) {
                    return "小";
                } else if("large".equalsIgnoreCase(value)) {
                    return "大";
                } else {
                    return "中";
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        for(String key : needUpdatePreferenceMap.keySet()) {
            setSummary(sharedPreferences, key);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Summary summary = needUpdatePreferenceMap.get(key);
        if (summary != null) {
            setSummary(sharedPreferences, key);
        }
    }

    private void setSummary(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        String value = sharedPreferences.getString(key, null);
        Summary summary = needUpdatePreferenceMap.get(key);
        if(preference == null || value == null || summary == null) {
            return;
        }
        preference.setSummary(summary.getSummary(value));
    }
    
    private static class Summary {
        private String prefix;
        private String suffix;
        
        public Summary(String prefix, String suffix){
            this.prefix = prefix;
            this.suffix = suffix;
        }
        
        /**
         * 默认是 prefix + value + suffix
         * @param value
         * @return
         */
        public String getSummary(String value) {
            return prefix + value + suffix;
        }
    }
}
