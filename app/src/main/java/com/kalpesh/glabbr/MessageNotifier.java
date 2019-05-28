package com.kalpesh.glabbr;

import com.kalpesh.glabbr.models.MessageStatus;

public interface MessageNotifier {
    void updateMessageStatus (int messageId, MessageStatus messageStatus);
}
