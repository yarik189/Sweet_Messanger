package kanefron5.com.sweetmessenger;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.perm.kate.api.Api;
import com.perm.kate.api.KException;
import com.perm.kate.api.Message;
import com.perm.kate.api.User;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONException;

import kanefron5.com.sweetmessenger.Account;
import kanefron5.com.sweetmessenger.Constants;
import kanefron5.com.sweetmessenger.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22.04.15.
 */
public class SweetDialogsActivity extends ActionBarActivity {

    Account account = new Account();
    Api api;


    private final List<SweetDialogsActivity> messages = new ArrayList<SweetDialogsActivity>();
    private ArrayAdapter<SweetDialogsActivity> listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dab);
        setSupportActionBar(toolbar);
        getSupportActionBar();

        account.restore(this);



        // Создаем API, Если есть токен
        if (account.access_token != null) {
            api = new Api(account.access_token, Constants.API_ID);
        } else {
            Toast.makeText(this, "Access token == null", Toast.LENGTH_LONG).show();
        }

        // Усе! Дальше можно отправлять запросы на сервер
        // Получим список диалогов

        try {

            ArrayList<DialogsItem> items = new ArrayList<>();

            ArrayList<Message> apiDialogs = api.getMessagesDialogs(0, 100, null, null);
            // Желательно читать доки. Тут мы получаем 100 сообщений, начиная от начала
            // Но прикол в том, что так мы не можем получить имя и фамилию (Читаем документацию)
            // Но можем по ID

            ArrayList<Long> uidsList = new ArrayList<>();
            for (Message message : apiDialogs) {
                uidsList.add(message.uid);
            }
            // Думаю тут понятно, нам нужен ID, Заносим в лист,
            // что бы потом вытащить инфу и пользователе.

            ArrayList<User> apiProfiles = api.getProfiles(uidsList, null, "nickname", null, null, null);
            // Получаем информацию и пользователях по их ID
            // Ну и все почти

            for (int i = 0; i < apiProfiles.size(); i++) {

                User user = apiProfiles.get(i);
                Message message = apiDialogs.get(i);

                items.add(new DialogsItem(user.first_name + " " + user.last_name, message.body));


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Как-то так!


    }


    private class DialogsAdapter extends BaseAdapter {

        Context context;
        ArrayList<DialogsItem> dialogsItems;

        public DialogsAdapter(Context context, ArrayList<DialogsItem> dialogsItems) {
            this.context = context;
            this.dialogsItems = dialogsItems;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    public class DialogsItem {
        String fullName;
        String body;

        public DialogsItem(String fullName, String body) {
            this.fullName = fullName;
            this.body = body;
        }


        private class DialogsAdapter extends BaseAdapter {

            Context context;
            ArrayList<DialogsItem> dialogsItems;

            LayoutInflater inflater; // Он нам нужен, что бы конвертировать разметку в элемент

            public DialogsAdapter(Context context, ArrayList<DialogsItem> dialogsItems) {
                this.context = context;
                this.dialogsItems = dialogsItems;

                inflater = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            }

            @Override
            public int getCount() {
// Должен возвращать кол-во элементов
                return dialogsItems.size();
            }

            @Override
            public Object getItem(int position) {
// Должен возвращать элемент по позиции
                return dialogsItems.get(position);
            }

            @Override
            public long getItemId(int position) {
// Должен возвращать ID, position == id
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    view = inflater.inflate(R.layout.li, parent, false);
                }
// Вот тут самое интересное
// узнаем View по их ID и заполняем данными
                TextView tvName = (TextView) view.findViewById(R.id.fullName_dialog);
                tvName.setText(dialogsItems.get(position).fullName);

                TextView tvBody = (TextView) view.findViewById(R.id.tvBody_dialog);
                tvBody.setText(dialogsItems.get(position).fullName);


                return view; // Возвращаем View

            }
        }
    }
}

