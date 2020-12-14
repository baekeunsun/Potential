package com.example.potential;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;


public class Rating extends AppCompatActivity {
    private DatabaseReference Firebasedatabase,Counseilingdatabase;
    private FirebaseDatabase FirebaseInstance;
    private String post_num, email,Counseiling;
    float rating_;
    EditText user,content;
    RatingBar ratingBar;
    Button storebutton;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        content = findViewById(R.id.contentsEditText);
        ratingBar = findViewById(R.id.ratingBar);
        FirebaseInstance = FirebaseDatabase.getInstance();
        Firebasedatabase = FirebaseInstance.getReference("post");
        Counseilingdatabase = FirebaseInstance.getReference("User");
        post_num = Firebasedatabase.push().getKey();
        email = PreferenceManager.getString(Rating.this,"email");
        rating_ = (Float.valueOf(getIntent().getStringExtra("rating")) + ratingBar.getRating());
        Counseiling = getIntent().getStringExtra("name");
        position = getIntent().getStringExtra("position");



        findViewById(R.id.storebutton).setOnClickListener(onClickListener);
    }

    public void addUser(String username, String content,String counseiling,String rating){
        Post post = new Post(username, content,counseiling,rating);
        Firebasedatabase.child(post_num).setValue(post);
    }

    public  void updateUser(String rating){
        Counseilingdatabase.child("user_00").child("rating").setValue(rating);

    }
    public void insertData()
    {
        addUser(email,content.getText().toString().trim(),Counseiling,Float.toString(ratingBar.getRating()));
    }

    public void updateData()
    {
        updateUser(String.format("%.1f",( Float.valueOf(getIntent().getStringExtra("rating")) + ratingBar.getRating()) / 2) );
    }

    public void  readData(View view){
        Firebasedatabase.child("post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot noteDataSnapshot: snapshot.getChildren() ){
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String dbuser = ds.child("username").getValue(String.class);
                        String dbcontent = ds.child("email").getValue(String.class);
                        Log.d("TAG", dbuser+"/"+dbcontent);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.storebutton:
                    insertData();
                    updateData();
                    Intent intent=new Intent(Rating.this, Board.class);
                    Log.e("클릭", "클릭"); //클릭이 잘 작동하는 지 로그 작성
                    startActivity(intent);
                    break;
            }
        }
    };

}