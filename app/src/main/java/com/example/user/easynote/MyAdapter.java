package com.example.user.easynote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 4/4/2561.
 */

public class MyAdapter extends BaseAdapter {
    private List<Data> mDatas;
    private LayoutInflater mLayoutInflater;
    public MyAdapter(Context context, List<Data> aList){
        mDatas = aList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    static class ViewHolder{
        ImageView Icon;
        TextView Title;
        TextView Description;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = mLayoutInflater.inflate(R.layout.row_layout,viewGroup,false);
            holder = new ViewHolder();
            holder.Icon = (ImageView)view.findViewById(R.id.Icon);
            holder.Title = (TextView)view.findViewById(R.id.title);
            holder.Description = (TextView)view.findViewById(R.id.description);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder)view.getTag();
        }
        String title = mDatas.get(i).getmTitle();
        holder.Title.setText(title);
        holder.Description.setText(mDatas.get(i).getmDescription());
        holder.Icon.setImageResource(mDatas.get(i).getmIcon());

        return view;
    }
}
