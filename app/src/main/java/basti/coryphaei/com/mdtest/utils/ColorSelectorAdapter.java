package basti.coryphaei.com.mdtest.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import basti.coryphaei.com.mdtest.R;
import basti.coryphaei.com.mdtest.application.App;

/**
 * Created by Bowen on 2015/10/14.
 */
public class ColorSelectorAdapter extends BaseAdapter{

    public List<String> mList;
    private Context mContext;
    private App myApp;

    public ColorSelectorAdapter(Context context,List<String> list){
        mList = list;
        mContext = context;
        myApp = (App) mContext.getApplicationContext();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_color_selector,parent,false);
            viewHolder = new ViewHolder();

            viewHolder.mColorButton = (AppCompatButton) convertView.findViewById(R.id.selector_item);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffffcc00});
        //viewHolder.mColorButton.setText(123+"");
        return convertView;
    }

    class ViewHolder{
         AppCompatButton mColorButton;
    }
}
