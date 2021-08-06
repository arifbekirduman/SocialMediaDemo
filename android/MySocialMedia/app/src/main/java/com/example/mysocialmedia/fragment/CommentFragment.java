package com.example.mysocialmedia.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysocialmedia.adapter.CommentAdapter;
import com.example.mysocialmedia.api.ApiUtils;
import com.example.mysocialmedia.Model.Comment.CommentResponse;
import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.R;
import com.example.mysocialmedia.adapter.PostAdapter;
import com.example.mysocialmedia.api.APIService;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentFragment extends DialogFragment {
    RecyclerView recyclerView;
    TextView btnCancel;
    EditText etCommentMessage;
    ImageView btnSendComment;
    private User user;
    private UUID postId;
    private Context context;
    private APIService apiService;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CommentFragment() {
        // Required empty public constructor
    }
    public CommentFragment(Context context, UUID postId,User user) {
        this.context = context;
        this.postId = postId;
        this.user = user;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommentFragment newInstance(String param1, String param2) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        init(view);
        getComments();

        btnCancel.setOnClickListener(v->{
            dismiss();
        });

        btnSendComment.setOnClickListener(v->{
            if (etCommentMessage.getText().toString().isEmpty()){
                System.out.println("Boş alan bırakmayınız.");
            }
            else {
                PostAdapter.sendCommentApi(postId, user, etCommentMessage.getText().toString());
                etCommentMessage.setText("");
                dismiss();
            }

        });

        return view;
    }

    private void getComments() {
        apiService.getAllComments(postId).enqueue(new Callback<List<CommentResponse>>() {
            @Override
            public void onResponse(Call<List<CommentResponse>> call, Response<List<CommentResponse>> response) {
                if (response.isSuccessful() && response.body() != null){
                    setupRecyclerView(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CommentResponse>> call, Throwable t) {

            }
        });
    }

    private void init(View view){
        recyclerView = view.findViewById(R.id.recyclerViewCommentFragment);
        btnCancel = view.findViewById(R.id.btnCancelFragmentComment);
        etCommentMessage = view.findViewById(R.id.etSendCommentCommentItemCard);
        btnSendComment = view.findViewById(R.id.imgSendCommentCommentItemCard);
        apiService = ApiUtils.getAPIService();
    }

    private void setupRecyclerView(List<CommentResponse> commentResponses){
        CommentAdapter commentAdapter = new CommentAdapter(commentResponses,getContext());
        recyclerView.setAdapter(commentAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}