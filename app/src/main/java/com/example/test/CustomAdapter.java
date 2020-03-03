package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CustomAdapter  extends BaseAdapter {

    ArrayList<User> listItem;

    Context mContext;
    //constructor
    public CustomAdapter(Context mContext, ArrayList<User> listItem) {
        this.mContext = mContext;
        this.listItem = listItem;
    }

    public int getCount() {
        return listItem.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View arg1, ViewGroup viewGroup)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.chatmenucustomlist, viewGroup, false);

        TextView addresstext = (TextView) row.findViewById(R.id.aNametxt);
        ImageView bodytext = (ImageView) row.findViewById(R.id.appIconIV);

        addresstext.setText(listItem.get(position).getUsername());
        if (listItem.get(position).getImageURL().equals("default")){
            Picasso.get().load(R.drawable.logocolor).into(bodytext);
        }
        else Picasso.get().load(listItem.get(position).getImageURL()).into(bodytext);



        return row;
    }


}
