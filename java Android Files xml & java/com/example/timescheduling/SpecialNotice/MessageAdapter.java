package com.example.timescheduling.SpecialNotice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timescheduling.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private Context mCtx; // to create layout inflater
    private List<MessageGetter> messageList;

    public MessageAdapter(Context mCtx, List<MessageGetter> timeTableList) {
        this.mCtx = mCtx;
        this.messageList = timeTableList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.message_layout,null);
        MessageViewHolder holder=new MessageViewHolder(view);
        return holder;
        //return new TimeTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder timeTableViewHolder, int i) {
        MessageGetter msg=messageList.get(i);

        //timeTableViewHolder.date.setText(msg.getMessage());
        timeTableViewHolder.message.setText(msg.getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        TextView date,message;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            message=itemView.findViewById(R.id.message);

        }
    }
}
