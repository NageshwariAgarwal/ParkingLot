package com.example.nageshwari.parking_lot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NAGESHWARI on 21-06-2018.
 */

public class BookingAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> uuser;
    ArrayList<String> bbid;
    ArrayList<String> ddate;
    ArrayList<String> ttime;
    ArrayList<String> ddura;
    ArrayList<String> pplot;
    ArrayList<String> sslot;
    ArrayList<String> aarea;

    public BookingAdapter(Context context2, ArrayList<String> bid, ArrayList<String> date,ArrayList<String> time,
                          ArrayList<String> dura,ArrayList<String> user,ArrayList<String> plot,ArrayList<String> slot,
                          ArrayList<String> area) {
        this.context = context2;
        this.uuser = user;
        this.bbid = bid;
        this.ddate = date;
        this.ttime = time;
        this.ddura = dura;
        this.pplot = plot;
        this.sslot = slot;
        this.aarea = area;
    }
    @Override
    public int getCount() {
        return uuser.size();
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
        BookingAdapter.Holder holder;
        LayoutInflater layoutInflater;
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.viewbooking, null);
            holder = new Holder();

            holder.textView2=(TextView)child.findViewById(R.id.textView2);
            holder.textView4=(TextView)child.findViewById(R.id.textView4);
            holder.textView6=(TextView)child.findViewById(R.id.textView6);
            holder.textView8=(TextView)child.findViewById(R.id.textView8);
            holder.textView10=(TextView)child.findViewById(R.id.textView10);
            holder.textView12=(TextView)child.findViewById(R.id.textView12);
            holder.textView14=(TextView)child.findViewById(R.id.textView14);
            holder.textView16=(TextView)child.findViewById(R.id.textView16);
            child.setTag(holder);
        }else{
            holder = (BookingAdapter.Holder)child.getTag();
        }
        holder.textView2.setText(uuser.get(position));
        holder.textView4.setText(bbid.get(position));
        holder.textView6.setText(ddate.get(position));
        holder.textView8.setText(ttime.get(position));
        holder.textView10.setText(ddura.get(position));
        holder.textView12.setText(pplot.get(position));
        holder.textView14.setText(sslot.get(position));
        holder.textView16.setText(aarea.get(position));
        return child;
    }
    public class Holder
    {
        TextView textView2;
        TextView textView4;
        TextView textView6;
        TextView textView8;
        TextView textView10;
        TextView textView12;
        TextView textView14;
        TextView textView16;
    }
}
