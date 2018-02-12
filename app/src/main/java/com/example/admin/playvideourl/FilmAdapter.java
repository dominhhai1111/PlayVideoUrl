package com.example.admin.playvideourl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 10/2/2018.
 */

public class FilmAdapter extends BaseAdapter{
    private Context context;
    private List<Film> films;
    private LayoutInflater layoutInflater;

    public FilmAdapter(Context context, List<Film> films) {
        this.context = context;
        this.films = films;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Object getItem(int position) {
        return films.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.row_menu, null);
            viewHolder.txtName = convertView.findViewById(R.id.txtName);
            viewHolder.txtTime = convertView.findViewById(R.id.txtTime);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Film film = this.films.get(position);
        viewHolder.txtName.setText(film.getName());
        viewHolder.txtTime.setText(String.valueOf(film.getTime()));
        viewHolder.imageView.setImageResource(R.mipmap.poster);

        return convertView;
    }

    static class ViewHolder{
        TextView txtName;
        TextView txtTime;
        ImageView imageView;
    }
}
