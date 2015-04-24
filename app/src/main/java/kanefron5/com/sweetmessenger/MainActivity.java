package kanefron5.com.sweetmessenger;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.ColorRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;



public class MainActivity extends ActionBarActivity {
    private static final int NOTIFY_ID = 101;

    private Drawer.Result drawerResult = null;
    private android.support.v7.widget.Toolbar mRelativeLayout;

    SharedPreferences colors;
    final String SAVED_TEXT = "colors";

    void saveColor() {
        colors = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = colors.edit();
        ed.putString(SAVED_TEXT, "Нилазь сюда");
        ed.commit();
        //Toast.makeText(this, "Color saved", Toast.LENGTH_SHORT).show();
    }


//Смена цветов
    public void onRedButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.redColor));

        setTheme(R.style.Name);

    }

    public void onIndigoButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.indigo));

    }
    public void onGreenButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.greenColor));
    }
    public void onBlueButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.blueColor));
    }
    public void oTealButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.Teal));
    }
    public void onPurpleButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.Purple));
    }
    public void onPinkButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.Pink));
    }
    public void onLimeButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.Lime));
    }
    public void onYellowButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.Yellow));

    }
    public void onDeep_OrangeButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.Deep_Orange));
    }
    public void onBrownButtonClick(View view) {

        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.Brown));

    }
//Конец смены цветов



    public void Reply(View v) {
        onStop();

    }

    public void onNotClick(View view){
        Context context = getApplicationContext();
        Intent not = new Intent(context, Notify.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, not, PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentIntent(contentIntent)
        .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setTicker("Тестовое уведомление")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Тест")
                .setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setDefaults(Notification.COLOR_DEFAULT)

                .setContentText("Тестовое уведомление, показывающее, что кодер данного приложения - Роман " +
                        " Заболотских - прям охереть какой четки программист, все понили дыа??!!");
                Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
        long[] vibro = new long[] {1000, 500, 1000};
        notification.vibrate = vibro;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);



        // Инициализируем Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Инициализируем Navigation Drawer
        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.mes).withIcon(R.drawable.ic_messages),
                        new PrimaryDrawerItem().withName(R.string.friends).withIcon(R.drawable.ic_friends),
                        new SectionDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.settings).withIcon(R.drawable.ic_settings),
                        new SecondaryDrawerItem().withName(R.string.contact).withIcon(R.drawable.ic_talkback),
                        new SecondaryDrawerItem().withName("Визуализация").withIcon(R.drawable.ic_color),
                        new SecondaryDrawerItem().withName("Пост")

                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Скрываем клавиатуру при открытии Navigation Drawer
                        InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    // Обработка клика
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {


                            Fragment fragment = null;

                            FragmentManager fragmentManager = getFragmentManager(); // For AppCompat use getSupportFragmentManager

                            switch (position) {
                                default:
                                case 0:
                                    fragment = new SweetDialogsActivity();
                                    break;
                                case 1:
                                    fragment = new Fri();

                                    break;
                                case 2:
                                    fragment = new Fri();
                                    break;
                                case 3:
                                    fragment = new Settings();
                                    break;
                                case 4:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.vk.com/im?sel=273646145")));
                                    break;
                                case 5:
                                    fragment = new Color();
                                    break;
                                case 6:
                                    fragment = new post();
                                    break;

                            }
                            fragmentManager.beginTransaction()
                                    .replace(R.id.content_frame, fragment)

                                    .commit();
                        }
                    }
                })
                .build();
    }

    @Override
    public void onBackPressed() {
        // Закрываем Navigation Drawer по нажатию системной кнопки "Назад" если он открыт
        if (drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }

    }

    // Заглушка, работа с меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Заглушка меню
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.action_settings);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Здесь переход на преференс
        switch(item.getItemId())
        {
            case R.id.action_settings:
                Intent settings = new Intent(this, Chaing.class);
                startActivity(settings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
