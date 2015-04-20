package kanefron5.com.sweetmessenger;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;



/**
 * Created by Роман on 02.04.2015.
 */
public class TwoLog extends ActionBarActivity {
    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout_sweet);
    }
    public void LoginTwo(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
