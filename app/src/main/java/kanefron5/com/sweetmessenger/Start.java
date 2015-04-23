package kanefron5.com.sweetmessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;



/**
 * Created by Роман on 02.04.2015.
 */
public class Start extends ActionBarActivity {
    private int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_romik);
        Toolbar toolbar = (Toolbar) findViewById(R.id.start);
        setSupportActionBar(toolbar);
        getSupportActionBar();
    }
public void onBtnClick(View v){
    x++;
    if (x==5) {
        x=0;
        Intent i = new Intent(this, easter_egg.class);
        startActivity(i);
         }
}
    public void Login(View v){
        Intent z = new Intent(this, TwoLog.class);
        startActivity(z);
    }
}
