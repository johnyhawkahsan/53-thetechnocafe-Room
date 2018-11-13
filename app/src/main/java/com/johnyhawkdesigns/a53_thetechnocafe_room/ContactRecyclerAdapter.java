package com.johnyhawkdesigns.a53_thetechnocafe_room;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johnyhawkdesigns.a53_thetechnocafe_room.models.Contact;

import java.util.List;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ViewHolder> {

    //Interface for callbacks
    interface ActionCallback {
        void onLongClickListener(Contact contact);
    }

    private Context context;
    private List<Contact> contactList;
    private int[] colors;
    private ActionCallback mActionCallbacks;

    ContactRecyclerAdapter(Context context, List<Contact> contactList, int[] colors){
        this.context = context;
        this.contactList = contactList;
        this.colors = colors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    void updateData(List<Contact> contacts) {
        this.contactList = contacts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        private TextView mNameTextView;
        private TextView mInitialsTextView;
        private GradientDrawable mInitialsBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnLongClickListener(this);

            mInitialsTextView = itemView.findViewById(R.id.initialsTextView);
            mNameTextView = itemView.findViewById(R.id.nameTextView);
            mInitialsBackground = (GradientDrawable) mInitialsTextView.getBackground();

        }

        void bindData(int position){
            Contact contact = contactList.get(position);

            String fullName = contact.getFirstName() + " " + contact.getLastName();
            mNameTextView.setText(fullName);

            String initial = contact.getFirstName().toUpperCase().substring(0, 1); // Get first character of name
            mInitialsTextView.setText(initial);

            mInitialsBackground.setColor(colors[position % colors.length]);

        }

        @Override
        public boolean onLongClick(View v) {
            if (mActionCallbacks != null){
                mActionCallbacks.onLongClickListener(contactList.get(getAdapterPosition()));
            }
            return true;
        }

        void addActionCallback(ActionCallback actionCallbacks) {
            mActionCallbacks = actionCallbacks;
        }

    }
}
