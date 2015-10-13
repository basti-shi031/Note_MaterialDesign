package basti.coryphaei.com.mdtest.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import java.util.Arrays;

import basti.coryphaei.com.mdtest.R;
import basti.coryphaei.com.mdtest.application.App;
import basti.coryphaei.com.mdtest.utils.ColorSelectorAdapter;

/**
 * Created by Bowen on 2015/10/12.
 */
public class ColorDialog extends DialogFragment{

    private LayoutInflater inflater;
    private View view;
    private GridView mColorSelector;
    private ColorSelectorAdapter mAdapter;
    private App myApp;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.colordialog, null, false);

        initView(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setTitle("颜色选择");

        initDates();

        return builder.create();
    }

    private void initDates() {
        myApp = (App) getActivity().getApplicationContext();
        mAdapter = new ColorSelectorAdapter(getActivity(), Arrays.asList(myApp.Colors));
        mColorSelector.setAdapter(mAdapter);

    }

    private void initView(View view) {

        mColorSelector = (GridView) view.findViewById(R.id.color_selector);

    }


}
