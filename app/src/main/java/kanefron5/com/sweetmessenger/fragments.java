package kanefron5.com.sweetmessenger;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;



/**
 * Created by Роман on 14.04.2015.
 */

class Mes extends Fragment {

    public Mes() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialogs, container,
                false);

        return rootView;
    }

}
class Fri extends Fragment {

    public Fri() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.two, container,
                false);

        return rootView;
    }

}

class Set extends Fragment {

    public Set() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.set, container,
                false);

        return rootView;
    }

}


class TalkBack extends Fragment {


    public TalkBack() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.talkback, container,
                false);




        return rootView;

    }


}
class Color extends Fragment {

    public Color() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.color, container,
                false);

        return rootView;

    }


}



