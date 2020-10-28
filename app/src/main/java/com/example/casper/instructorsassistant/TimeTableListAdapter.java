package com.example.casper.instructorsassistant;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CASPER on 12/20/2017.
 */

public class TimeTableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TimeTableInfo> timeTableInfos;

    public TimeTableListAdapter(List<TimeTableInfo> timeTableInfos) {
        this.timeTableInfos = timeTableInfos;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        View view = layoutInflater.inflate(R.layout.timetablerow,parent,false);
        viewHolder = new TimeTableViewHolder(view);
        return viewHolder;
    }

    public void add(TimeTableInfo timeTableInfo){
        timeTableInfos.add(timeTableInfo);
        notifyItemInserted(timeTableInfos.size()-1);
    }

    private static class TimeTableViewHolder extends RecyclerView.ViewHolder{
        TextView courseTextView;
        TextView coursePeriodTextview;
        TextView courseClassNumberTextView;
        TextView courseTypeTextView;
        public TimeTableViewHolder(View itemView) {
            super(itemView);
            courseTextView = (TextView) itemView.findViewById(R.id.timetableCourseName);
            coursePeriodTextview =(TextView) itemView.findViewById(R.id.timetableDate);
            courseClassNumberTextView = (TextView) itemView.findViewById(R.id.timetableClassNumber);
            courseTypeTextView =(TextView) itemView.findViewById(R.id.timetableClassType);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        configureMyTimeTableViewHolder((TimeTableViewHolder) holder,position);
    }

    @Override
    public int getItemCount() {
        if (timeTableInfos != null) {
            return timeTableInfos.size();
        }
        return 0;
    }

    private void configureMyTimeTableViewHolder(TimeTableViewHolder holder, int position){
        final TimeTableInfo timeTableInfo = timeTableInfos.get(position);
        holder.courseTextView.setText(timeTableInfo.getCoursename());
        holder.coursePeriodTextview.setText(timeTableInfo.getPeriod());
        holder.courseClassNumberTextView.setText(timeTableInfo.getClassName());
        holder.courseTypeTextView.setText(timeTableInfo.getClassType());
    }


}
