package kanefron5.com.sweetmessenger;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.media.Image;
import android.os.Bundle;
import android.renderscript.Long2;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.perm.kate.api.Api;
import com.perm.kate.api.KException;
import com.perm.kate.api.Message;
import com.perm.kate.api.Photo;
import com.perm.kate.api.User;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 22.04.15.
 */
public class SweetDialogsActivity extends Fragment {

    Account account = new Account();
    Api api;


    public SweetDialogsActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialogs, container, false);

        final ListView ListView = (ListView) rootView.findViewById(R.id.ListView);





        account.restore(getActivity());
        // Создаем API, Если есть токен
        if (account.access_token != null) {
            api = new Api(account.access_token, Constants.API_ID);
        } else {
            Toast.makeText(getActivity(), "Вход не выполнен", Toast.LENGTH_LONG).show();
        }

        // Усе! Дальше можно отправлять запросы на сервер
        // Получим список диалогов
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    final ArrayList<DialogsItem> items = new ArrayList<>();

                    ArrayList<Message> apiDialogs = api.getMessagesDialogs(0, 200, null, null);
/* Желательно читать доки. Тут мы получаем 100 сообщений, начиная от начала
 Но прикол в том, что так мы не можем получить имя и фамилию (Читаем документацию)
 Но можем по ID */

                    ArrayList<Long> uidsList = new ArrayList<>();
                    for (Message message : apiDialogs) {
                        uidsList.add(message.uid);
                    }

                   /* ArrayList<Long> Date = new ArrayList<>();
                    for (Message message : apiDialogs) {
                        uidsList.add(message.date);
                    }*/




/* Думаю тут понятно, нам нужен ID, Заносим в лист,
 что бы потом вытащить инфу и пользователе.*/

                    ArrayList<User> apiProfiles = api.getProfiles(uidsList, null, "nickname, photo_200" , null, null, null);
/* Получаем информацию и пользователях по их ID
 Ну и все почти */


                    for (int i = 0; i < apiProfiles.size(); i++) {

                        User user = apiProfiles.get(i);
                        Message message = apiDialogs.get(i);

                        items.add(new DialogsItem(user.first_name + " " + user.last_name,  message.body, user.photo_200, message.title, message.date));



                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DialogsAdapter adapter = new DialogsAdapter(getActivity().getApplicationContext(), items);
                            ListView.setAdapter(adapter);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    // Как-то так!

return rootView;
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
                view = inflater.inflate(R.layout.dialog_list_item, parent, false);
            }





            // Вот тут самое интересное
            // узнаем View по их ID и заполняем данными
            TextView tvName = (TextView) view.findViewById(R.id.fullName_dialog);
            tvName.setText(dialogsItems.get(position).fullName);

            TextView tvBody = (TextView) view.findViewById(R.id.tvBody_dialog);
            tvBody.setText(dialogsItems.get(position).body);

            ImageView ivImgAvatar_dialog = (ImageView) view.findViewById(R.id.ivImgAvatar_dialog);
            ivImgAvatar_dialog.setImageResource(R.drawable.ic);

            Picasso.with(context)
                        .load(dialogsItems.get(position).ava)
                        .placeholder(R.drawable.ic)
                    .into(ivImgAvatar_dialog);

            TextView tvDate_dialog = (TextView) view.findViewById(R.id.tvDate_dialog);
            tvDate_dialog.setText(dialogsItems.get(position).date + " ");





            return view; // Возвращаем View

        }


    }

    public class DialogsItem {

        String fullName;
        String body;
        String ava;
        String title;
        Long date;

        //String date;

        public DialogsItem(String fullName, String body, String photo_200, String title, Long date ) {
            this.fullName = fullName;
            this.body = body;
            this.ava = photo_200;
            this.date = date;
            if (! title.equalsIgnoreCase("..."))this.fullName = title;


        }
    }



}

