package com.example.realestate.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realestate.R;
import com.example.realestate.chatmodule.AllUsersActivity;
import com.example.realestate.chatmodule.ConversationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatFragment extends Fragment {


    private static final String TAG = "ChatFragment";
    private ArrayList<ChatModel> chatsArrayList = new ArrayList<>();
    private RecyclerView conversationRecyclerView;
    private RecyclerViewAdapterMessages adapter;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private static class ChatModel {
        private String name, imageUrl, lastMcg, uid;
        public String getUid() {
            return uid;
        }
        public void setUid(String uid) {
            this.uid = uid;
        }
        public ChatModel(String name, String imageUrl, String lastMcg, String uid) {
            this.name = name;
            this.imageUrl = imageUrl;
            this.lastMcg = lastMcg;
            this.uid = uid;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getImageUrl() {
            return imageUrl;
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
        public String getLastMcg() {
            return lastMcg;
        }
        public void setLastMcg(String lastMcg) {
            this.lastMcg = lastMcg;
        }
        public ChatModel(String name, String imageUrl, String lastMcg) {
            this.name = name;
            this.imageUrl = imageUrl;
            this.lastMcg = lastMcg;
        }

        ChatModel() {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview= inflater.inflate(R.layout.fragment_chat2, container, false);
        conversationRecyclerView = mview.findViewById(R.id.chats_recyclerview);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        if (mAuth.getCurrentUser() == null){
//            Toast.makeText(getContext(), "Pleas login first!", Toast.LENGTH_SHORT).show();
//
//        }

//        mview.findViewById(R.id.newBtn).setOnClickListener(view ->{
//            startActivity(new Intent(getContext(), AllUsersActivity.class));
//        });

//        else {
            databaseReference.child("chats").child("LDDUxkvJc2OrS8lQNTmX7kPrZ343")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (!snapshot.exists()) {
                                return;
                            }

                            chatsArrayList.clear();

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                ChatModel model = dataSnapshot.getValue(ChatModel.class);
                                model.setUid(dataSnapshot.getKey());
                                chatsArrayList.add(model);
                            }

                            initRecyclerView();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//        }

        return mview;
    }
    private void initRecyclerView() {


        adapter = new RecyclerViewAdapterMessages();
        //        LinearLayoutManager layoutManagerUserFriends = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        //    int numberOfColumns = 3;
        //int mNoOfColumns = calculateNoOfColumns(getApplicationContext(), 50);
        //  recyclerView.setLayoutManager(new GridLayoutManager(this, mNoOfColumns));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        conversationRecyclerView.setLayoutManager(linearLayoutManager);
        conversationRecyclerView.setHasFixedSize(true);
        conversationRecyclerView.setNestedScrollingEnabled(false);

        conversationRecyclerView.setAdapter(adapter);

        if (adapter.getItemCount() != 0) {

            //        noChatsLayout.setVisibility(View.GONE);
            //        chatsRecyclerView.setVisibility(View.VISIBLE);

        }

    }

    public class RecyclerViewAdapterMessages extends RecyclerView.Adapter
            <RecyclerViewAdapterMessages.ViewHolderRightMessage> {

        @NonNull
        @Override
        public ViewHolderRightMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chats_fragment, parent, false);
            return new ViewHolderRightMessage(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolderRightMessage holder, int position) {
            ChatModel chatModel = (ChatModel) chatsArrayList.get(position);
            Log.e(TAG, "onBindViewHolder: 1234: name: " + chatModel.getName());
            Log.e(TAG, "onBindViewHolder: 1234: url: " + chatModel.getImageUrl());
            holder.chatName.setText(chatModel.getName());
            holder.lastMessage.setText(chatModel.getLastMcg());

            String url = chatModel.getImageUrl().equals("Error") ? "https://cdn.pixabay.com/photo/2019/08/06/00/46/black-and-white-4387130_960_720.jpg"
                    : chatModel.getImageUrl();

            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ConversationActivity.class);
                    intent.putExtra("name", chatModel.getName());
                    intent.putExtra("url", chatModel.getImageUrl());
                    intent.putExtra("uid", chatModel.getUid());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (chatsArrayList == null)
                return 0;
            return chatsArrayList.size();
        }
        public class ViewHolderRightMessage extends RecyclerView.ViewHolder {

            TextView chatName, lastMessage;
            CircleImageView profileImage;
            CardView parentLayout;

            public ViewHolderRightMessage(@NonNull View v) {
                super(v);
                chatName = v.findViewById(R.id.username_textview_chats);
                lastMessage = v.findViewById(R.id.last_message_chats);
                profileImage = v.findViewById(R.id.profile_image_chats);
                parentLayout = v.findViewById(R.id.parent_layout);

            }
        }

    }

}