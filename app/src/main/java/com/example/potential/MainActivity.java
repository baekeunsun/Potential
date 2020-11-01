package com.example.potential;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    TextView jin_ju, chang_won;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jin_ju = findViewById(R.id.jin_ju);
        chang_won = findViewById(R.id.chang_won);

//Changwon Jinju intent
        jin_ju.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        chang_won.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startSignUpActivity();
        }
        findViewById(R.id.logout).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    startSignUpActivity();
                    finish();
                    break;
            }
        }
    };

    private void startSignUpActivity(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}