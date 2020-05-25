package com.krokoteam.kroko.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.krokoteam.kroko.R;
import com.krokoteam.kroko.data.model.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<ChatMessage> messages;

    ChatAdapter(Context context, List<ChatMessage> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        // holder.imageView.setImageResource(message.getmImageUrl());
        holder.nameView.setText(message.getmSenderID());
        holder.textView.setText(message.getmMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // final ImageView imageView;
        final TextView nameView, textView;
        ViewHolder(View view){
            super(view);
            // imageView = (ImageView)view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.chat_name);
            textView = (TextView) view.findViewById(R.id.chat_text);
        }
    }
}
