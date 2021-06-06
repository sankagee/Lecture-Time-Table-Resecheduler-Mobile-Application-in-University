package com.example.timescheduling.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timescheduling.R;

import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder> {

    private Context mCtx; // to create layout inflater
    private List<TimeTableGetter> timeTableList;

    public TimeTableAdapter(Context mCtx, List<TimeTableGetter> timeTableList) {
        this.mCtx = mCtx;
        this.timeTableList = timeTableList;
    }

    @NonNull
    @Override
    public TimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.layout,null);
        TimeTableViewHolder holder=new TimeTableViewHolder(view);
        return holder;
        //return new TimeTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableViewHolder timeTableViewHolder, int i) {
        TimeTableGetter product=timeTableList.get(i);

        timeTableViewHolder.day.setText(product.getDay());
        timeTableViewHolder.tv1.setText(product.getT1());
        timeTableViewHolder.tv2.setText(product.getT2());
        timeTableViewHolder.tv3.setText(product.getT3());
        timeTableViewHolder.tv4.setText(product.getT4());
        timeTableViewHolder.tv5.setText(product.getT5());
        timeTableViewHolder.tv6.setText(product.getT6());
        timeTableViewHolder.tv7.setText(product.getT7());
        timeTableViewHolder.tv8.setText(product.getT8());
        timeTableViewHolder.tv9.setText(product.getT9());
        timeTableViewHolder.tv10.setText(product.getT10());
    }

    @Override
    public int getItemCount() {
        return timeTableList.size();
    }

    class TimeTableViewHolder extends RecyclerView.ViewHolder{

        TextView day,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
        public TimeTableViewHolder(@NonNull View itemView) {
            super(itemView);

            day=itemView.findViewById(R.id.day);
            tv1=itemView.findViewById(R.id.t1);
            tv2=itemView.findViewById(R.id.t2);
            tv3=itemView.findViewById(R.id.t3);
            tv4=itemView.findViewById(R.id.t4);
            tv5=itemView.findViewById(R.id.t5);
            tv6=itemView.findViewById(R.id.t6);
            tv7=itemView.findViewById(R.id.t7);
            tv8=itemView.findViewById(R.id.t8);
            tv9=itemView.findViewById(R.id.t9);
            tv10=itemView.findViewById(R.id.t10);
        }
    }
}
