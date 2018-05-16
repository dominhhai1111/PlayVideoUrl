package com.example.admin.playvideourl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> comments;
    private LayoutInflater layoutInflater;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.row_comment, null);
            viewHolder.txtName = convertView.findViewById(R.id.txtName);
            viewHolder.txtContent = convertView.findViewById(R.id.txtContent);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Comment comment = this.comments.get(position);
        viewHolder.txtName.setText(comment.getEmail());
        viewHolder.txtContent.setText(comment.getContent());

        return convertView;
    }

    static class ViewHolder{
        TextView txtName;
        TextView txtContent;
    }
}
