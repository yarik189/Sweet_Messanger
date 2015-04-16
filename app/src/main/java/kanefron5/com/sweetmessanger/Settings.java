package kanefron5.com.sweetmessanger;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import java.util.prefs.Preferences;

/**
 * Created by Роман on 13.04.2015.
 */
public class Settings extends PreferenceFragment {

    private Settings t;

      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

          /**  Preference version = (Preference) findPreference("Версия");
        version
                .setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    public boolean onPreferenceClick(Preference p) {
                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);

                        return true;
                    }

                });
           */
    }


}


