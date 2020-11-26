package com.example.potential;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase mAuth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginbutton).setOnClickListener(onClickListener);
        findViewById(R.id.gotologinbutton).setOnClickListener(onClickListener);

    }
    @Override
    public void onBackPressed(){

        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            switch (v.getId()){
                case R.id.loginbutton:
                    Log.e("클릭","클릭");
                    break;
                case R.id.gotologinbutton:
                    startLogin();
                    break;

            }
        }
    };



    private void startToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    private void startLogin(){
        Intent intent = new Intent( this, Login.class);
        startActivity(intent);
    }


}
