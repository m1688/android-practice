package m.ui.android;

import java.util.HashMap;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import static m.constant.SettingsKey.KEY_MSG_PULL_FREQUENCY;
import static m.constant.SettingsKey.KEY_RINGTONE;
import static m.constant.SettingsKey.KEY_READING_MODE;
import static m.constant.SettingsKey.KEY_FONT_SIZE;
/**
 * 设置界面
 * 
 * @author ticmy
 */
public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

    /**
     * 更改配置后需要更新summary显示的那些preference
     */
    private Map<String, Handler> needUpdatePreferenceMap = new HashMap<String, Handler>();

    {
        needUpdatePreferenceMap.put(KEY_MSG_PULL_FREQUENCY, null);
        needUpdatePreferenceMap.put(KEY_RINGTONE, new Handler() {

            public String handle(String value) {
                if (value == null || value.trim().length() == 0) {
                    return "";
                }
                int lastSlashIndex = value.lastIndexOf("/");
                if (lastSlashIndex != -1) {
                    return value.substring(lastSlashIndex + 1);
                }
                return value;
            }
        });
        needUpdatePreferenceMap.put(KEY_READING_MODE, null);
        needUpdatePreferenceMap.put(KEY_FONT_SIZE, null);
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
        for (String key : needUpdatePreferenceMap.keySet()) {
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
        if (needUpdatePreferenceMap.containsKey(key)) {
            setSummary(sharedPreferences, key);
        }
    }

    private void setSummary(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference == null) {
            return;
        }
        String value = sharedPreferences.getString(key, null);
        Handler tip = needUpdatePreferenceMap.get(key);
        if (tip == null) {
            if (preference instanceof ListPreference) {
                value = ((ListPreference) preference).getEntry().toString();
            }
        } else {
            if (value == null) {
                value = "";
            } else {
                value = tip.handle(value);
            }
        }
        preference.setSummary(value);
    }

    /**
     * 字符串处理器，默认什么也不做
     * 
     * @author ticmy
     */
    private static class Handler {

        /**
         * 默认返回传入的参数value
         * 
         * @param value
         * @return
         */
        public String handle(String value) {
            return value;
        }
    }
}
