package com.example.equal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.equal.Chat.Chat;
import com.example.equal.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{
    private ArrayList<Chat> listChat;
    private Context context;

    public ChatAdapter(ArrayList<Chat> list){
        this.listChat = list;
    }

    @Override
    public int getItemViewType(int position) {
        if(listChat.get(position).getType() == 0){
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item_message_user, parent, false);
            return new ViewHolderUser(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item_message_bot, parent, false);
            return new ViewHolderBot(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat chat = listChat.get(position);

        if(chat.getType() == 0){
            //holder user
            ViewHolderUser holderUser = (ViewHolderUser) holder;
            holderUser.tvMessage.setText(chat.getMessage());

        }else{
            //holder bot
            ViewHolderBot holderBot = (ViewHolderBot) holder;
            holderBot.tvMessage.setText(chat.getMessage());

        }
    }

    @Override
    public int getItemCount() {
        return listChat.size();
    }

    class ViewHolderUser extends RecyclerView.ViewHolder{
        TextView tvMessage;

        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvUserMessage);
        }
    }

    class ViewHolderBot extends RecyclerView.ViewHolder{
        TextView tvMessage;

        public ViewHolderBot(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvBotMessage);
        }
    }
}
