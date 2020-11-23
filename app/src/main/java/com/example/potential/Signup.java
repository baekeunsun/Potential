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
        mAuth = FirebaseAuth.getInstance(); //파이어베이스 정보얻기

        findViewById(R.id.loginbutton).setOnClickListener(onClickListener); //회원가입버튼
        findViewById(R.id.gotologinbutton).setOnClickListener(onClickListener);//로그인버튼

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
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            switch (v.getId()){
                case R.id.loginbutton:
                    Log.e("클릭","클릭");
                    signUp();
                    break;
                case R.id.gotologinbutton:
                    startLogin();
                    break;

            }
        }
    };
    //회원가입 함수
    private void signUp() {

        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passEditText)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passCheckEditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){

            if(password.equals(passwordCheck)){

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("회원가입이 되었습니다.");
                                    Intent intent = new Intent(Signup.this, Login.class);
                                    startActivity(intent);

                                    //UI
                                } else {
                                    if(task.getException() != null){
                                        startToast(task.getException().toString());
                                    }
                                    // UI
                                }
                            }
                        });
            }else{
                startToast("비밀번호가 일치하지 않습니다.");
            }

        }else{
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }

    }
    //로그인 성공 토스트 메세지
    private void startToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }
    //Login화면으로 이동하는 함수
    private void startLogin(){
        Intent intent = new Intent( this, Login.class);
        startActivity(intent);
    }


}
