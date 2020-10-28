package com.example.casper.instructorsassistant;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by CASPER on 12/25/2017.
 */

public class StudentReminderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ReminderInfo> reminderInfoList;

    public StudentReminderListAdapter(List<ReminderInfo> reminderInfoList) {
        this.reminderInfoList = reminderInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        View view = layoutInflater.inflate(R.layout.reminder_row_student,parent,false);
        viewHolder =new ReminderViewHolder(view);
        return viewHolder;
    }

    public void add(ReminderInfo reminderInfo){
        reminderInfoList.add(reminderInfo);
        notifyItemInserted(reminderInfoList.size()-1);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        configureMyReminderHolder((ReminderViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        if (reminderInfoList != null) {
            return reminderInfoList.size();
        }
        return 0;
    }

    private static class ReminderViewHolder extends RecyclerView.ViewHolder{
        TextView reminderName;
        TextView dateText;

        public ReminderViewHolder(View itemView) {
            super(itemView);
            reminderName = (TextView) itemView.findViewById(R.id.stdReminderUploadName);
            dateText = (TextView) itemView.findViewById(R.id.stdReminderUploadDate);
        }
    }

    private void configureMyReminderHolder(ReminderViewHolder holder, final int position){
        final ReminderInfo reminderInfo = reminderInfoList.get(position);
        holder.reminderName.setText(reminderInfo.getMsgInfo());
        Date date = new Date(Long.valueOf(reminderInfo.getMsgDate()));
        holder.dateText.setText(date.toString());
    }
}
