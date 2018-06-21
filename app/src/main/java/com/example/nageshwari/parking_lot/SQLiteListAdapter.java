package com.example.nageshwari.parking_lot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NAGESHWARI on 13-06-2018.
 */

public class SQLiteListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> NName;
    ArrayList<String> PPhnno;
    ArrayList<String> EEmail;

    public SQLiteListAdapter(Context context2, ArrayList<String> name, ArrayList<String> phn,ArrayList<String> em) {
        this.context = context2;
        this.PPhnno = phn;
        this.NName = name;
        this.EEmail = em;
    }
    @Override
    public int getCount() {
        return NName.size();
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
    public View getView(int position, View child, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutInflater;
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.activity_listviewdatalayout, null);
            holder = new Holder();

            holder.name=(TextView)child.findViewById(R.id.textView2);
            holder.phn=(TextView)child.findViewById(R.id.textView4);
            holder.email=(TextView)child.findViewById(R.id.textView6);
            child.setTag(holder);
        }else{
            holder = (Holder)child.getTag();
        }
        holder.name.setText(NName.get(position));
        holder.phn.setText(PPhnno.get(position));
        holder.email.setText(EEmail.get(position));
        return child;
    }
    public class Holder
    {
        TextView name;
        TextView phn;
        TextView email;
    }
}
