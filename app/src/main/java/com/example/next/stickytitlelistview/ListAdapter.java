package com.example.next.stickytitlelistview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by NeXT on 15/4/6.
 */
public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_MENU = 1;
    private static final int TYPE_NORMAL = 2;

    private int itemCount = 18;
    private boolean reset = false;

    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //Inflater 使用如下方法，否则宽度变为 wrap_content
        //http://stackoverflow.com/questions/24503760/cardview-layout-width-match-parent-does-not-match-parent-recyclerview-width
        if(viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_top, viewGroup, false);
            return new HeaderViewHolder(view);
        } else if(viewType == TYPE_MENU) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_header_menu, viewGroup, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
            return new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position) == TYPE_HEADER){
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
        } else if (getItemViewType(position) == TYPE_MENU) {
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
        } else {
            MyHolder holder = (MyHolder) viewHolder;
            switch (position % 3){
                case 0:
                    if(reset){
                        holder.tvComment.setText("转发 ：啦啦啦啦啦德玛西亚");
                    } else {
                        holder.tvComment.setText("啦啦啦啦啦德玛西亚");
                    }
                    break;
                case 1:
                    if(reset){
                        holder.tvComment.setText("转发：德玛西亚万岁");
                    } else {
                        holder.tvComment.setText("德玛西亚万岁");
                    }
                    break;
                case 2:
                    if(reset){
                        holder.tvComment.setText("转发：敌军还有30秒到达战场");
                    } else {
                        holder.tvComment.setText("敌军还有30秒到达战场");
                    }
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return TYPE_HEADER;
        } else if(position == 1){
            return TYPE_MENU;
        } else {
            return TYPE_NORMAL;
        }
    }


    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void resetItems() {
        itemCount = itemCount + 2;
        if(reset){
            reset = false;
        } else {
            reset = true;
        }
        notifyDataSetChanged();
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        TextView tvComment;

        public MyHolder(View itemView) {
            super(itemView);
            tvComment = (TextView) itemView.findViewById(R.id.tv_comment);
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

}
