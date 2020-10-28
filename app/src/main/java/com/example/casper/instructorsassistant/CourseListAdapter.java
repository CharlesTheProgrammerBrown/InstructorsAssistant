package com.example.casper.instructorsassistant;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CASPER on 12/5/2017.
 */

public class CourseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CourseInfo> courseInfos;
    public onItemViewClickListener onitemViewClickListener;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        View view = layoutInflater.inflate(R.layout.courselist_layout,parent,false);
        viewHolder = new CourseViewHolder(view);
        return viewHolder;
    }
    public CourseListAdapter(List<CourseInfo> mcourseInfos,onItemViewClickListener mItemViewClickListener){courseInfos = mcourseInfos;
        onitemViewClickListener=mItemViewClickListener; }

    public void add(CourseInfo courseInfo){
        courseInfos.add(courseInfo);
        notifyItemInserted(courseInfos.size()-1);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        configureMyChatViewHolder((CourseViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        if (courseInfos != null) {
            return courseInfos.size();
        }
        return 0;
    }
    private static class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public CourseViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.showCourseTextListView);
        }
    }
    private void configureMyChatViewHolder(CourseViewHolder holder,int position){
        final CourseInfo courseInfo = courseInfos.get(position);
        holder.textView.setText(courseInfo.getCoursename());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemViewClickListener.onItemCourseClicked(courseInfo.getCoursename());
            }
        });
    }
}
