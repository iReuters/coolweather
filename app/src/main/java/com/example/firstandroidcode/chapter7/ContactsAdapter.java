package com.example.firstandroidcode.chapter7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidcode.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter <ContactsAdapter.ViewHolder> {

    private List<Contacts> mContactsList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View contactsView;
        TextView contactsName;
        TextView contactsNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactsView = itemView;
            contactsName = itemView.findViewById(R.id.contacts_tv1);
            contactsNumber = itemView.findViewById(R.id.contacts_tv2);
        }
    }

    public ContactsAdapter(List<Contacts> mContactsList) {
        this.mContactsList = mContactsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contacts contacts = mContactsList.get(position);
        holder.contactsName.setText(contacts.getName());
        holder.contactsNumber.setText(contacts.getNumber());
    }


    @Override
    public int getItemCount() {
        return mContactsList.size();
    }
}
