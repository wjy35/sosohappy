package com.ssafy.chat.api.service;

import com.ssafy.chat.api.request.ChatPublish;

public interface ChatSendService {
    void sendForDetail(ChatPublish chatPublish);
    void sendForList(ChatPublish chatPublish);

}
