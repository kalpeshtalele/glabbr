package com.kalpesh.glabbr;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalpesh.glabbr.Utills.Enum;
import com.kalpesh.glabbr.Utills.Typefaces;
import com.kalpesh.glabbr.models.MessageStatus;

import java.util.ArrayList;

class SessionFactory {

    private static SessionFactory instance;

    private SessionFactory() {}

    public static SessionFactory getInstance() {
        if(instance == null) {
            instance = new SessionFactory();
        }
        return instance;
    }

    public View getSession(Activity activity, Enum.SESSION_TYPE type, ArrayList<MessageStatus> statuses) {
        switch (type){
            case READ_BY:
                return getSection(activity, activity.getString(R.string.read_by), R.drawable.msg_read, getStatusOfType(2, statuses));
            case DELIVERED_TO:
                return getSection(activity, activity.getString(R.string.delivered_to), R.drawable.msg_delieverd, getStatusOfType(1, statuses));
            default:
                return null;
        }

    }

    private ArrayList<MessageStatus> getStatusOfType(int staus, ArrayList<MessageStatus> statuses) {
        ArrayList<MessageStatus> sortedStatus = new ArrayList<>();
        for(MessageStatus statObj : statuses) {
            if(statObj.getStatus() == staus) {
                sortedStatus.add(statObj);
            }
        }
        return sortedStatus;
    }

    private View getSection(Activity activity, String title, int imageDrawable, ArrayList<MessageStatus> statuses) {
        View sectionView  = activity.getLayoutInflater().inflate(R.layout.message_section_layout, null);
        TextView sectionTitle = sectionView.findViewById(R.id.section_title_text_view);
        sectionTitle.setText(title);
        sectionTitle.setTypeface(Typefaces.getInstance(activity).getTypeface(Typefaces.Style.SEMI_BOLD_ITALIC));

        ImageView statusImageView = sectionView.findViewById(R.id.status_image_view);
        statusImageView.setImageDrawable(activity.getResources().getDrawable(imageDrawable));

        RecyclerView sectionRecyclerView = sectionView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        sectionRecyclerView.setLayoutManager(layoutManager);
        sectionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SectionAdapter sectionAdapter = new SectionAdapter(activity, statuses, (ITabSelectListener) activity);
        sectionRecyclerView.setNestedScrollingEnabled(false);
        sectionRecyclerView.setAdapter(sectionAdapter);
        return sectionView;
    }
}
