package com.example.mysocialmedia.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mysocialmedia.api.ApiUtils;
import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.listener.MessageListener;
import com.example.mysocialmedia.Model.Post.PostRequest;
import com.example.mysocialmedia.R;
import com.example.mysocialmedia.api.APIService;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends DialogFragment {
    private EditText etPostMessage;
    private Button btnSendPost;
    private APIService apiService;
    private User user;
    MessageListener postFragmentListener;
    Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PostFragment(){}

    public PostFragment(Context context,User user) {
        this.context = context;
        this.user = user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
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
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        init(view);

        btnSendPost.setOnClickListener(v -> {
            if (etPostMessage.getText().toString().isEmpty()) {
                postFragmentListener.showMessage("Bir şeyler yazınız");
            } else {
                PostRequest postRequest = new PostRequest();
                postRequest.setMessage(etPostMessage.getText().toString());

                java.util.Date utilDate = new java.util.Date();
                Timestamp ts = new Timestamp(utilDate.getTime());

                postRequest.setCreatedAt(ts);

                apiService.sendPost(user.getUserId(),postRequest).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null){
                                postFragmentListener.showMessage(response.body());
                                dismiss();
                            }
                            System.out.println("send post success");
                        }
                        System.out.println("haydaaaaaaaaaaaaaaaaaaaaaaa"+ response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println("send post error -> " + t.getMessage()) ;
                        dismiss();
                    }
                });
            }
        });

        return view;
    }

    private void init(View view) {
        etPostMessage = view.findViewById(R.id.etPostMessagePostFragment);
        btnSendPost = view.findViewById(R.id.btnSendPostFragment);
        postFragmentListener = (MessageListener) context;
        apiService = ApiUtils.getAPIService();
    }

}