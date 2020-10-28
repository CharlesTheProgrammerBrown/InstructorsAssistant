package com.example.casper.instructorsassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by CASPER on 12/5/2017.
 */

public class CourseAddAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CourseInfo> courseInfos;
    Context context;
    public onMyAddCourseListener onAddBtnListener;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        View view = layoutInflater.inflate(R.layout.courserow,parent,false);
        viewHolder =new CourseViewHolder(view);
        return viewHolder;
    }

    public CourseAddAdapter(List<CourseInfo> mcourseInfos,Context mcontext,onMyAddCourseListener mlistener){
        courseInfos = mcourseInfos;
        context = mcontext;
        onAddBtnListener = mlistener;
    }

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
        Button btn;
        public CourseViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.showCourseTextForAllcourses);
            btn = (Button) itemView.findViewById(R.id.addCoursesBtn);
        }
    }
    private void configureMyChatViewHolder(CourseViewHolder holder, final int position){
        final CourseInfo courseInfo = courseInfos.get(position);
        holder.textView.setText(courseInfo.getCoursename());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onAddBtnListener.addBtnClicked(courseInfo);
            }
        });
    }
}
