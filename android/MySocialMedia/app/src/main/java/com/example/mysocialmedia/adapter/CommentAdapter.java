package com.example.mysocialmedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysocialmedia.Model.Comment.CommentResponse;
import com.example.mysocialmedia.R;
import com.example.mysocialmedia.utils.Utils;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    List<CommentResponse> mCommentList;
    LayoutInflater inflater;

    public CommentAdapter(List<CommentResponse> mCommentList , Context context) {
        this.mCommentList = mCommentList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.comment_item_card, parent, false);
        CommentAdapter.MyViewHolder holder = new CommentAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, int position) {
        holder.setupComment(mCommentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCommentMessage,tvCommentDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCommentMessage = itemView.findViewById(R.id.tvCommentMessageCommentItemCard);
            tvCommentDate = itemView.findViewById(R.id.tvCommentDateCommentItemCard);
        }


        void setupComment(CommentResponse commentResponse){
            String userName = commentResponse.getUser().getUserName();
            String commentMessage = commentResponse.getCommentMessage();
            tvCommentMessage.setText(userName + ": " + commentMessage);
            tvCommentDate.setText(Utils.formatDate(commentResponse.getCommentCreatedAt()));
        }
    }
}
