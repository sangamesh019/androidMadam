package com.randroid.madam;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterApp extends ArrayAdapter<Applist> {
    private Activity activity;
    private ArrayList<Applist> lPerson;
    private static LayoutInflater inflater = null;

    public AdapterApp (Activity activity, int textViewResourceId, ArrayList<Applist> _lPerson) {
        super(activity, textViewResourceId, _lPerson);
        try {
            this.activity = activity;
            this.lPerson = _lPerson;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return lPerson.size();
    }

    public Applist getItem(Applist position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_State; 
        public ImageView icon; 

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Applist item = getItem(position);
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.activity_listview1, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.appnamel1);
                holder.display_State = (TextView) vi.findViewById(R.id.state);
                holder.icon = (ImageView) vi.findViewById(R.id.icon);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            holder.display_name.setText(item.getAppName());
            holder.display_State.setText("-->"+item.getGood());
            holder.icon.setImageDrawable(item.getImage());


        } catch (Exception e) {


        }
        return vi;
    }
}