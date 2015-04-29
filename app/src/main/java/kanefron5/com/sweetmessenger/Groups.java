package kanefron5.com.sweetmessenger;

/**
 * Created by Роман on 29.04.2015.
 */

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.perm.kate.api.Group;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import com.perm.kate.api.Api;

public class Groups extends Fragment {

    Account account = new Account();
    Api api;
    Long user_id = account.user_id;

    public Groups() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.friends, container, false);

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

                    ArrayList<Group> Groups = api.getUserGroups(user_id);




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
                view = inflater.inflate(R.layout.friends_fragment, parent, false);
            }





            // Вот тут самое интересное
            // узнаем View по их ID и заполняем данными
            TextView tvName = (TextView) view.findViewById(R.id.fullName_dialog);
            tvName.setText(dialogsItems.get(position).fullName);



            ImageView ivImgAvatar_dialog = (ImageView) view.findViewById(R.id.ivImgAvatar_dialog);
            ivImgAvatar_dialog.setImageResource(R.drawable.ic);

            Picasso.with(context)
                    .load(dialogsItems.get(position).ava)
                    .placeholder(R.drawable.ic)
                    .into(ivImgAvatar_dialog);







            return view; // Возвращаем View

        }


    }

    public class DialogsItem {

        String fullName;
        String ava;

        //String date;

        public DialogsItem(String fullName, String photo_200 ) {
            this.fullName = fullName;

            this.ava = photo_200;



        }
    }



}


