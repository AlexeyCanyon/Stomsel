package com.example.aleks.wordgame.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleks.wordgame.Models.Chat;
import com.example.aleks.wordgame.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private Context mContext;
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private List<Chat> mChats;

    public MessageAdapter(Context mContext, List<Chat> mChats) {
        this.mContext = mContext;
        this.mChats = mChats;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if(i == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, viewGroup, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, viewGroup, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Chat chat = mChats.get(position);

        viewHolder.show_message.setText(chat.getMessage());
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView show_message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);

        }
    }


    @Override
    public int getItemViewType(int position) {
        if(mChats.get(position).getQueue()) {
            return MSG_TYPE_RIGHT;
        } else
            return MSG_TYPE_LEFT;
    }
}
