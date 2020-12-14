package com.example.potential;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Board extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    CustomAdapter recyclerViewadapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private EditText searchview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        findViewById(R.id.tv_name).setOnClickListener(onClickListener);
        findViewById(R.id.tv_recommend).setOnClickListener(onClickListener);

        searchview = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recyclerView); //아이디 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰기존성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트(어댑터쪽으로)
        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("User"); //DB테이블연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); //기존 배열리스트가 존재하지않게 초기화

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {//반복문으로 데이터리스트추출
                    User user = snapshot.getValue(User.class); //만들어뒀던 User 객체에 데이터를 담는다
                    arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                recyclerViewadapter.notifyDataSetChanged(); //리스트 저장 및 새로 고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비를 가져오던 중 에러 발생 시
                Log.e("",String.valueOf(databaseError.toException())); //에러문출력
            }
        });

        recyclerViewadapter = new CustomAdapter(arrayList,this);
        recyclerView.setAdapter(recyclerViewadapter); //리사이클러뷰에 어댑터연결

        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            switch (v.getId()){
                case R.id.tv_recommend:
                    Log.e("클릭","클릭");
                    Collections.sort(arrayList);
                    recyclerViewadapter.notifyDataSetChanged();


                    break;

                case R.id.tv_name:
                    Log.e("클릭","클릭");
                    Collections.sort(arrayList, new Comparator<User>() {
                        @Override
                        public int compare(User u1, User u2) {
                            return u1.getName().compareTo(u2.getName());
                        }
                    });
                    recyclerViewadapter.notifyDataSetChanged();


                    break;
            }
        }
    };

    private void filter (String text){
        ArrayList<User> filteredList = new ArrayList<>();
        for (User item: arrayList){
            if(item.getName().toLowerCase().contains(text.toLowerCase()) || item.getPro().toLowerCase().contains(text.toLowerCase()) || item.getTerri().toLowerCase().contains(text.toLowerCase()) ){
                filteredList.add(item);
            }
        }
        recyclerViewadapter.filterList(filteredList);
    }


}