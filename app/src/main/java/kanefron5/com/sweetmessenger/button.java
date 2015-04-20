package kanefron5.com.sweetmessenger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by Роман on 16.04.2015.
 */
public class button extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView =
                inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
}
