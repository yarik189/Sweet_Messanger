package kanefron5.com.sweetmessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.perm.kate.api.Api;


/**
 * Created by Роман on 02.04.2015.
 */
public class Start extends ActionBarActivity {
    private int x;
    private final int REQUEST_LOGIN=1;

    Button button3;
    Button button4;


    Account account=new Account();
    Api api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_romik);

        setupUI();

        //Восстановление сохранённой сессии
        account.restore(this);

        //Если сессия есть создаём API для обращения к серверу
        if(account.access_token!=null)
            api=new Api(account.access_token, Constants.API_ID);


        showActivitys();
    }

    private void setupUI() {
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);

        button3.setOnClickListener(authorizeClick);

    }

    private View.OnClickListener authorizeClick=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startLoginActivity();
        }
    };

    private void startLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                //авторизовались успешно
                account.access_token=data.getStringExtra("token");
                account.user_id=data.getLongExtra("user_id", 0);
                // Создаем API
                api = new Api(account.access_token, Constants.API_ID);
                account.save(Start.this);
                Toast.makeText(this, "Авторизовались успешно...", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                api=new Api(account.access_token, Constants.API_ID);
                showActivitys();



            }
        }
    }

    void showActivitys(){
        if(api!=null){
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.VISIBLE);

        }else{
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.VISIBLE);


        }
    }
}
