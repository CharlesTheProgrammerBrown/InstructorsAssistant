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
 * Created by CASPER on 12/22/2017.
 */

public class UploadListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<UploadInfo> uploadInfos;
    public onMyDownloadAndDeleteUploadListener deleteUploadListener;

    public UploadListAdapter(List<UploadInfo> uploadInfos, onMyDownloadAndDeleteUploadListener myDownloadAndDeleteUploadListener) {
        this.uploadInfos = uploadInfos;
        this.deleteUploadListener = myDownloadAndDeleteUploadListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        View view = layoutInflater.inflate(R.layout.uploadsrow,parent,false);
        viewHolder = new UploadViewHolder(view);
        return viewHolder;
    }

    public void add(UploadInfo uploadInfo){
        uploadInfos.add(uploadInfo);
        notifyItemInserted(uploadInfos.size()-1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          configureMyUploadViewHolder((UploadViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        if(uploadInfos != null){
            return uploadInfos.size();
        }
        return 0;
    }



    private void configureMyUploadViewHolder(UploadViewHolder holder, final int position) {
        final UploadInfo uploadInfo = uploadInfos.get(position);
        holder.filename.setText(uploadInfo.getName());
        Date date = new Date(Long.valueOf(uploadInfo.getDate()));
        holder.dateText.setText(date.toString());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUploadListener.onUploadDeleteClicked(uploadInfo);
                uploadInfos.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position,uploadInfos.size());
            }
        });
    }

    private static class UploadViewHolder extends RecyclerView.ViewHolder{
        TextView filename;
        Button downloadBtn;
        Button deleteBtn;
        TextView dateText;
        public UploadViewHolder(View itemView) {
            super(itemView);
            filename = (TextView) itemView.findViewById(R.id.uploadName);
            downloadBtn =(Button) itemView.findViewById(R.id.uploadBtn);
            deleteBtn = (Button) itemView.findViewById(R.id.uploadDeleteBtn);
            dateText = (TextView) itemView.findViewById(R.id.uploadDate);
        }
    }
}
