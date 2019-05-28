package com.kalpesh.glabbr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalpesh.glabbr.Utills.AlertManager;
import com.kalpesh.glabbr.Utills.Typefaces;
import com.kalpesh.glabbr.Utills.Utilities;
import com.kalpesh.glabbr.models.MessageStatus;
import com.kalpesh.glabbr.models.User;

import java.util.List;
import java.util.Locale;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder>  {
    private List<MessageStatus> items;
    private Context context;
    private ITabSelectListener tabSelectListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        TextView dateTextView;
        ViewGroup userDetailsContainer;

        ViewHolder(ViewGroup v) {
            super(v);
            userNameTextView = v.findViewById(R.id.user_name_text_view);
            dateTextView = v.findViewById(R.id.date_text_view);
            userDetailsContainer = v.findViewById(R.id.user_details_container);
        }
    }

    SectionAdapter(Context context, List<MessageStatus> items, ITabSelectListener tabSelectListener) {
        this.context = context;
        this.items = items;
        this.tabSelectListener = tabSelectListener;
    }


    @NonNull
    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_message_details_tem_layout, parent, false);

        return new SectionAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final MessageStatus messageStatus = items.get(i);
        final User user = Utilities.getUserById(messageStatus.getUserId());
        if(user != null) {
            viewHolder.userNameTextView.setText(user.getName());
            viewHolder.userNameTextView.setTypeface(Typefaces.getInstance(context).getTypeface(Typefaces.Style.BOLD));

            viewHolder.dateTextView.setText(Utilities.dateFormatMonthAndTime(messageStatus.getTime(), Locale.getDefault()));
            viewHolder.dateTextView.setTypeface(Typefaces.getInstance(context).getTypeface(Typefaces.Style.DANCING_REGULAR));

            //Add listener for getting callback to activity on tab select
            viewHolder.userDetailsContainer.setOnClickListener(v -> {
                if (tabSelectListener != null) {
                    tabSelectListener.onTabSelect(user);
                }
            });
        } else {
            AlertManager.showToastMessage(context, context.getString(R.string.error_user_not_found));
        }


    }
    @Override
    public int getItemCount() {
        return items.size();
    }

}
