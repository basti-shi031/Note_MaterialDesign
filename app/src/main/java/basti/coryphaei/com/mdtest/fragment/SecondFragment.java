package basti.coryphaei.com.mdtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import basti.coryphaei.com.mdtest.R;

/**
 * Created by Bowen on 2015/10/11.
 */
public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null)
        {
            // Currently in a layout without a contaifirstpage.xmlner, so no
            // reason to create our view.
            return null;
        }

        View view = inflater.inflate(R.layout.hearderlayout,container,false);
        return view;    }
}
