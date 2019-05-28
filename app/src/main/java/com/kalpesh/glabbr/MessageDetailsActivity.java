package com.kalpesh.glabbr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kalpesh.glabbr.Utills.AlertManager;
import com.kalpesh.glabbr.Utills.Enum;
import com.kalpesh.glabbr.Utills.Typefaces;
import com.kalpesh.glabbr.Utills.Utilities;
import com.kalpesh.glabbr.models.MessageObject;
import com.kalpesh.glabbr.models.MessageStatus;
import com.kalpesh.glabbr.models.StaticData;
import com.kalpesh.glabbr.models.User;
import com.kalpesh.glabbr.service.GlabberService;

import java.util.ArrayList;
import java.util.Locale;

public class MessageDetailsActivity extends AppCompatActivity implements MessageNotifier, ITabSelectListener {

    private Toolbar toolbar;
    private ScrollView rootContainer;
    private GlabberService glabberService;
    private MessageObject currentMessageObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        findViews();
        setData();
        //Steap 1: Set headre for Message
        ArrayList<MessageObject> messageObjects = StaticData.getInstance().getMessageObjects();
        currentMessageObject = messageObjects.get(0);
        setHeader(currentMessageObject.getMessageText(), currentMessageObject.getMessageTime());
        reanderMessageStatusUI(currentMessageObject);

        glabberService =  new GlabberService(this);
        glabberService.start();
    }

    @Override
    public void onTabSelect(Object object) {
        if(object instanceof User) {
            AlertManager.showToastMessage(this, getString(R.string.clicked_on_user, ((User) object).getName()));
        }
    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        rootContainer = findViewById(R.id.root_details_container);
    }

    private void setData() {
        toolbar.setTitle(getString(R.string.message_info));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setHeader(String messageText, long time ) {
        @SuppressLint("InflateParams") View headerView = getLayoutInflater().inflate(R.layout.message_header_layout, null);
        TextView messageTextView = headerView.findViewById(R.id.message_text_view);
        messageTextView.setTypeface(Typefaces.getInstance(this).getTypeface(Typefaces.Style.DANCING_REGULAR));
        messageTextView.setText(messageText);
        TextView timeTextView = headerView.findViewById(R.id.time_text_view);
        timeTextView.setTypeface(Typefaces.getInstance(this).getTypeface(Typefaces.Style.DANCING_REGULAR));
        timeTextView.setText(Utilities.timeString(time, Locale.getDefault()));
        TextView titleTextView = headerView.findViewById(R.id.title_text_view);
        titleTextView.setTypeface(Typefaces.getInstance(this).getTypeface(Typefaces.Style.DANCING_REGULAR));
        toolbar.addView(headerView);
    }

    @Override
    public void updateMessageStatus(final int messageId, final MessageStatus messageStatus) {
        runOnUiThread(() -> {
            MessageObject messageObject = Utilities.getMessageInfoById(messageId);
            //Check if user alrady exist
            if(messageObject != null) {
                for (MessageStatus status : messageObject.getMessageStatus()) {
                    if (status.getUserId() == messageStatus.getUserId()) {
                        status.setTime(messageStatus.getTime());
                        status.setStatus(messageStatus.getStatus());
                        reanderMessageStatusUI(messageObject);
                        return;
                    }
                }
                messageObject.getMessageStatus().add(messageStatus);
                reanderMessageStatusUI(messageObject);
            } else {
                AlertManager.showToastMessage(this, getString(R.string.error_message_id_not_found));
            }
        });
    }

    private void reanderMessageStatusUI(MessageObject messageObject) {
        if(currentMessageObject.getMessageId() == messageObject.getMessageId()) {
            rootContainer.removeAllViews();
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(linearParams);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.text_color_gray_77));
            rootContainer.addView(linearLayout);

            linearLayout.addView(SessionFactory.getInstance().getSession(this, Enum.SESSION_TYPE.READ_BY,
                    messageObject.getMessageStatus()));
            linearLayout.addView(SessionFactory.getInstance().getSession(this, Enum.SESSION_TYPE.DELIVERED_TO,
                    messageObject.getMessageStatus()));
            AlertManager.showToastMessage(this, getString(R.string.alert_message_data_received));
        } else {
            AlertManager.showToastMessage(this, getString(R.string.alert_message_status_receive_for_another_message));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(glabberService != null) {
            glabberService.stopTask();
        }
    }
}
