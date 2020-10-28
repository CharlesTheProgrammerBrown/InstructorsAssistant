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

public class StudentUploadListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<UploadInfo> uploadInfos;
    public onMyDownloadUploadListener downloadUploadListener;

    public StudentUploadListAdapter(List<UploadInfo> uploadInfos, onMyDownloadUploadListener downloadUploadListener) {
        this.uploadInfos = uploadInfos;
        this.downloadUploadListener = downloadUploadListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        View view = layoutInflater.inflate(R.layout.upload_row_student,parent,false);
        viewHolder = new UploadViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          configureMyUploadViewHolder((UploadViewHolder) holder, position);
    }

    public void add(UploadInfo uploadInfo){
        uploadInfos.add(uploadInfo);
        notifyItemInserted(uploadInfos.size()-1);
    }

    @Override
    public int getItemCount() {
        if(uploadInfos != null){
            return uploadInfos.size();
        }
        return 0;
    }

    private static class UploadViewHolder extends RecyclerView.ViewHolder{
        TextView filename;
        Button downloadBtn;
        TextView dateText;
        public UploadViewHolder(View itemView) {
            super(itemView);
            filename = (TextView) itemView.findViewById(R.id.stdUploadName);
            downloadBtn =(Button) itemView.findViewById(R.id.stdDownloadBtn);
            dateText = (TextView) itemView.findViewById(R.id.stdUploadDate);
        }
    }

    private void configureMyUploadViewHolder(UploadViewHolder holder, final int position) {
        final UploadInfo uploadInfo = uploadInfos.get(position);
        holder.filename.setText(uploadInfo.getName());
        Date dateTime = new Date(Long.valueOf(uploadInfo.getDate()));
        holder.dateText.setText(dateTime.toString());
        holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadUploadListener.onUploadDownloadClicked(uploadInfo);
            }
        });
    }
}
