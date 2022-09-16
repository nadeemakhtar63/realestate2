package com.example.realestate.chatmodule;

import static android.view.LayoutInflater.from;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.realestate.R;
import com.example.realestate.modelclasses.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllUsersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        DatabaseReference mDatabaseUsers;
        FirebaseAuth mAuth;

        mDatabaseUsers = FirebaseDatabase.getInstance().getReference();
        mDatabaseUsers.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();

        DatabaseReference mRef = mDatabaseUsers.child("profile").child(mAuth.getCurrentUser().getUid());
//        mRef.child("Name").setValue(fullName);
//        mRef.child("Email").setValue(emailStr);
//        mRef.child("Password").setValue(passwordStr);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("profile")
//                .child(mAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            tasksArrayList.clear();

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                UserModel userModel = new UserModel();
                                String name = snapshot.child(dataSnapshot.getKey()).child("name").getValue(String.class);
                                userModel.Name = name;
                                userModel.key = dataSnapshot.getKey();

                                tasksArrayList.add(userModel);
                            }

                            initRecyclerView();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AllUsersActivity.this, error.toException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private ArrayList<UserModel> tasksArrayList = new ArrayList<>();

    private RecyclerView conversationRecyclerView;
    private RecyclerViewAdapterMessages adapter;

    private void initRecyclerView() {
        conversationRecyclerView = findViewById(R.id.alluserrecyclerview);
        conversationRecyclerView.addItemDecoration(new DividerItemDecoration(conversationRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewAdapterMessages();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        conversationRecyclerView.setLayoutManager(linearLayoutManager);
        conversationRecyclerView.setHasFixedSize(true);
        conversationRecyclerView.setNestedScrollingEnabled(false);
        conversationRecyclerView.setAdapter(adapter);
    }

    private class RecyclerViewAdapterMessages extends Adapter
            <RecyclerViewAdapterMessages.ViewHolderRightMessage> {

        @NonNull
        @Override
        public ViewHolderRightMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = from(parent.getContext()).inflate(R.layout.layout_users_items, parent, false);
            return new ViewHolderRightMessage(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolderRightMessage holder, int position) {
            holder.title.setText(tasksArrayList.get(position).Name);

            holder.parent.setOnClickListener(view -> {
                Intent intent = new Intent(AllUsersActivity.this, ConversationActivity.class);
                intent.putExtra("first", true);
                intent.putExtra("name", tasksArrayList.get(position).Name);
                intent.putExtra("url", "null");
                intent.putExtra("uid", tasksArrayList.get(position).key);

                startActivity(intent);
            });


        }

        @Override
        public int getItemCount() {
            if (tasksArrayList == null)
                return 0;
            return tasksArrayList.size();
        }

        public class ViewHolderRightMessage extends ViewHolder {
            TextView title;
            RelativeLayout parent;

            public ViewHolderRightMessage(@NonNull View v) {
                super(v);
                title = v.findViewById(R.id.nameTv);
                parent = v.findViewById(R.id.parent);

            }
        }
    }
}