package com.example.mysocialmedia.listener;

import java.util.UUID;

public interface MessageListener {
    void showMessage(String msg);
    void onClickShowComments(UUID postId);
}
