package com.example.potential;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    Button rating;
    private static final String TAG = "GalleryActivity";
    private RecyclerView recyclerView;
    ReviewAdapter reviewAdapter;
    private RecyclerView.LayoutManager layoutManager;
//    private ArrayList<Review> arrayList, arrayList1;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        recyclerView = findViewById(R.id.review);
        recyclerView.setHasFixedSize(true); //리사이클러뷰기존성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Review> arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트(어댑터쪽으로)
        ArrayList<Review> arrayList1 = new ArrayList<>(); // User 객체를 담을 어레이 리스트(어댑터쪽으로)

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("post"); //DB테이블연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); //기존 배열리스트가 존재하지않게 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {//반복문으로 데이터리스트추출
                    Review review = snapshot.getValue(Review.class); //만들어뒀던 User 객체에 데이터를 담는다
                    arrayList.add(review); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
//                    Toast.makeText(getApplicationContext(),review.+"",Toast.LENGTH_SHORT).show();


                }

                for(int i = 0; i < arrayList.size(); i++){
                    if(arrayList.get(i).getcounseiling().equals(getIntent().getStringExtra("image_name"))){
                        arrayList1.add(arrayList.get(i));
                    }
                }

                    reviewAdapter.notifyDataSetChanged(); //리스트 저장 및 새로 고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비를 가져오던 중 에러 발생 시
                Log.e("",String.valueOf(databaseError.toException())); //에러문출력
            }
        });
        reviewAdapter = new ReviewAdapter(arrayList1,this);
        recyclerView.setAdapter(reviewAdapter); //리사이클러뷰에 어댑터연결




        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();
        rating = findViewById(R.id.rating);
        rating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GalleryActivity.this, Rating.class);
                intent.putExtra("name",getIntent().getStringExtra("image_name"));
                intent.putExtra("rating",getIntent().getStringExtra("rating"));
                startActivity(intent);
            }
        });




    }


    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            //사진
            String imageUrl = getIntent().getStringExtra("image_url");
            //이름
            String imageName = getIntent().getStringExtra("image_name");
            //주소
            String addr = getIntent().getStringExtra("addr");
            //번호
            String number = getIntent().getStringExtra("number");
            //별점
            String rating = getIntent().getStringExtra("rating");
            //전문
            String pro = getIntent().getStringExtra("pro");
            //영역
            String terri = getIntent().getStringExtra("terri");

            setImage(imageUrl, imageName,addr,number,rating, terri, pro);
        }
    }


    private void setImage(String imageUrl, String imageName,String imageaddr, String imagenumber,String imagerating,String imageterri, String imagepro){
        Log.d(TAG, "setImage: setting te image and name to widgets.");
        //이름
        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);
        //주소
        TextView addr = findViewById(R.id.image_addr);
        addr.setText(imageaddr);

        //번호
        TextView number = findViewById(R.id.image_number);
        number.setText(imagenumber);
        //별점
        TextView rating = findViewById(R.id.image_rating);
        rating.setText(imagerating);
        //전문영역
        TextView pro = findViewById(R.id.image_pro);
        pro.setText(imagepro);
        //영역
        TextView terri = findViewById(R.id.image_terri);
        terri.setText(imageterri);
        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }

}

