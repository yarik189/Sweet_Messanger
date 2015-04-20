package kanefron5.com.sweetmessenger;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.View;



/**
 * Created by Роман on 13.04.2015.
 */
public class Settings extends PreferenceFragment {

    private Settings t;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

    }
}



