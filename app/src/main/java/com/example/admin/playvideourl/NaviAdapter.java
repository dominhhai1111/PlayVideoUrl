package com.example.admin.playvideourl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 24/3/2018.
 */

public class NaviAdapter extends BaseAdapter{
    private NavigationDrawerFragment context;
    private List<String> categories;
    private LayoutInflater layoutInflater;

    public NaviAdapter(NavigationDrawerFragment context, List<String> categories, LayoutInflater layoutInflater) {
        this.context = context;
        this.categories = categories;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NaviViewHolder naviViewHolder;
        if (convertView == null){
            naviViewHolder = new NaviViewHolder();
            convertView = layoutInflater.inflate(R.layout.row_fragment, null);
            naviViewHolder.txtCategory = convertView.findViewById(R.id.txtCategory);
            convertView.setTag(naviViewHolder);
        }else{
            naviViewHolder = (NaviViewHolder) convertView.getTag();
        }

        naviViewHolder.txtCategory.setText(categories.get(position));
        return convertView;
    }
    
    static class NaviViewHolder{
        TextView txtCategory;
    }
}
