package com.example.orderts.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orderts.Adapter.ListAdapter;
import com.example.orderts.Model.List;
import com.example.orderts.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentI6 extends Fragment {
    RecyclerView recyclerView;
    ArrayList<List> list;
    ListAdapter listAdapter;
    DatabaseReference reference;
    public FragmentI6() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_i6, container, false);
        recyclerView = view.findViewById(R.id.recycler_ip6);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       list = new ArrayList<List>();
       reference = FirebaseDatabase.getInstance().getReference().child("Fragment");
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                   List l = dataSnapshot1.getValue(List.class);
                   if (l.getCategory().equals("iphone6")){
                   list.add(l);}
               }
               listAdapter = new ListAdapter(list);
               recyclerView.setAdapter(listAdapter);
           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) { }
       });
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
