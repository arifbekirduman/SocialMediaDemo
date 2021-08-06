package com.example.mysocialmedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysocialmedia.Model.Post.PostResponse;
import com.example.mysocialmedia.api.ApiUtils;
import com.example.mysocialmedia.Model.Comment.CommentRequest;
import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.R;
import com.example.mysocialmedia.api.APIService;
import com.example.mysocialmedia.listener.MessageListener;
import com.example.mysocialmedia.utils.Utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    List<PostResponse> mPostList;
    LayoutInflater inflater;
    Context context;
    MessageListener messageListener;
    User user;

    public PostAdapter(Context context, List<PostResponse> mPostList, User user) {
        this.mPostList = mPostList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        messageListener = (MessageListener) context;
        this.user = user;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_item_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
        holder.setupPost(mPostList.get(position));
        holder.sendComment(mPostList.get(position));
        holder.showComments(mPostList.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName, tvPostDate, tvPostMessage, tvLastComment, tvShowComment;
        private EditText etCommentMessage;
        private ImageView imgSendComment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserNamePostItemCard);
            tvPostDate = itemView.findViewById(R.id.tvPostTimePostItemCard);
            tvPostMessage = itemView.findViewById(R.id.tvPostMessagePostItemCard);
            tvLastComment = itemView.findViewById(R.id.tvLastCommentUserPostItemCard);
            etCommentMessage = itemView.findViewById(R.id.etSendCommentPostItemCard);
            imgSendComment= itemView.findViewById(R.id.imgSendCommentPostItemCard);
            tvShowComment = itemView.findViewById(R.id.tvShowCommentPostItemCard);

        }

        void showComments(UUID postId){
            tvShowComment.setOnClickListener(v -> {
                messageListener.onClickShowComments(postId);
            });
        }
        void sendComment(PostResponse postResponse){
            imgSendComment.setOnClickListener(v -> {
                if (etCommentMessage.getText().toString().isEmpty()){
                    messageListener.showMessage("Yorum alanını boş bırakmayınız!");
                }
                else {
                    String commentMessage = etCommentMessage.getText().toString();
                    etCommentMessage.setText("");
                    sendCommentApi(postResponse.getId(),user,commentMessage);
                }
            });
        }


        void setupPost(PostResponse postResponse) {
            tvUserName.setText(String.valueOf(postResponse.getUser().getUserName()));
            tvPostMessage.setText(postResponse.getMessage());
            tvPostDate.setText(Utils.formatDate(postResponse.getCreatedAt()));
            if (postResponse.getLastComment() != null) {
                String userName = "";
                String message = "";
                userName = postResponse.getLastComment().getUser().getUserName();
                message = postResponse.getLastComment().getCommentMessage();
                tvLastComment.setText(userName + ": " + message);
            }
            else {
                tvLastComment.setText("Henüz yorum yapılmamış, ilk yorum yapan sen ol.");
            }
        }
    }

    public static void sendCommentApi(UUID postId, User user, String s){
        APIService apiService = ApiUtils.getAPIService();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentMessage(s);
        commentRequest.setPostId(postId);

        java.util.Date utilDate = new java.util.Date();
        Timestamp ts = new Timestamp(utilDate.getTime());

        commentRequest.setCommentCreatedAt(ts);

        apiService.sendComment(user.getUserId(),commentRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null){

                }
                else {
                    System.out.println("Error");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("send Comment Error->" + t.getMessage());
            }
        });
    }
}
