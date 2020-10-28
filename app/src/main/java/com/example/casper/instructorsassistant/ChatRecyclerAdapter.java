package com.example.casper.instructorsassistant;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

/**
 * Created by CASPER on 12/26/2017.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int VIEW_TYPE_STUDENT = 1;
    private static final int VIEW_TYPE_INSTRUCTOR = 2;
    List<Chat> chats;

    public ChatRecyclerAdapter(List<Chat> chats) {
        this.chats = chats;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case VIEW_TYPE_STUDENT:
                View viewChatMine = layoutInflater.inflate(R.layout.student_chat_row, parent, false);
                viewHolder = new MyChatViewHolder(viewChatMine);
                break;
            case VIEW_TYPE_INSTRUCTOR:
                View viewChatOther = layoutInflater.inflate(R.layout.instructor_chat_row, parent, false);
                viewHolder = new OtherChatViewHolder(viewChatOther);
                break;
        }
        return viewHolder;
    }

    public void add(Chat chat) {
        chats.add(chat);
        notifyItemInserted(chats.size() - 1);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(TextUtils.equals(chats.get(position).sId, "ST")){
            configureMyChatViewHolder((MyChatViewHolder) holder, position);
        }
        else {
            configureOtherChatViewHolder((OtherChatViewHolder) holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals(chats.get(position).sId, "ST")) {
            return VIEW_TYPE_STUDENT;
        } else {
            return VIEW_TYPE_INSTRUCTOR;
        }

    }

    @Override
    public int getItemCount() {
        if (chats != null) {
            return chats.size();
        }
        return 0;
    }
    private static class MyChatViewHolder extends RecyclerView.ViewHolder {
         TextView txtChatMessage, txtUserAlphabet;
         TextView txtDate;
         TextView txtName;

        public MyChatViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.std_text_view_chat_name);
            txtChatMessage = (TextView) itemView.findViewById(R.id.std_text_view_chat_message);
            txtUserAlphabet = (TextView) itemView.findViewById(R.id.std_text_view_user_alphabet);
            txtDate = (TextView) itemView.findViewById(R.id.std_text_view_chat_date);
        }
    }
    private static class OtherChatViewHolder extends RecyclerView.ViewHolder {
         TextView txtChatMessage, txtUserAlphabet;
         TextView txtDate;
         TextView txtName;

        public OtherChatViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.text_view_chat_name);
            txtChatMessage = (TextView) itemView.findViewById(R.id.text_view_chat_message);
            txtUserAlphabet = (TextView) itemView.findViewById(R.id.text_view_user_alphabet);
            txtDate = (TextView) itemView.findViewById(R.id.std_text_view_chat_date);
        }
    }
    private void configureMyChatViewHolder(MyChatViewHolder myChatViewHolder, int position) {
        Chat chat = chats.get(position);

        String alphabet = chat.sId.substring(0, 1);
        myChatViewHolder.txtChatMessage.setText(chat.message);
        myChatViewHolder.txtUserAlphabet.setText(alphabet);
        myChatViewHolder.txtName.setText(chat.sender);
        Long dat = Long.valueOf(chat.getTimeStamp());
        Date dateTime = new Date(dat);
        //myChatViewHolder.txtDate.setText(dateTime.toString());
    }

    private void configureOtherChatViewHolder(OtherChatViewHolder otherChatViewHolder, int position) {
        Chat chat = chats.get(position);

        String alphabet = chat.sId.substring(0, 1);

        otherChatViewHolder.txtChatMessage.setText(chat.message);
        otherChatViewHolder.txtUserAlphabet.setText(alphabet);
        otherChatViewHolder.txtName.setText(chat.sender);
        Date date = new Date(Long.valueOf(chat.getTimeStamp()));
        Log.d("DATE_LOG",date.toString());
        //otherChatViewHolder.txtDate.setText(date.toString());
    }
}
