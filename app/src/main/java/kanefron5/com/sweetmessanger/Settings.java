package kanefron5.com.sweetmessanger;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.View;

/**
 * Created by Роман on 13.04.2015.
 */
public class Settings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
public void onBackPressed(){
    Intent i = new Intent(this, MainActivity.class);
    startActivity(i);

}

}
