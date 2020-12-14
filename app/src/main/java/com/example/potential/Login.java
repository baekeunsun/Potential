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

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance(); //파이어베이스 정보 얻기

        findViewById(R.id.loginbutton).setOnClickListener(onClickListener); //로그인버튼
    }

    //시작할 때 로그인이 되있는지 체크
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    //로그인 버튼
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            switch (v.getId()){
                case R.id.loginbutton:
                    Log.e("클릭","클릭");
                    Login();
                    break;
            }
        }
    };
    //데이터베이스에서 로그인 정보를 가져와서 확인한다
    private void Login() {

        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passEditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0 ){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                PreferenceManager.setString(Login.this,"email",email);
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인에 성공하셨습니다.");
                                startMainactivity();
                            } else {
                                if(task.getException()!=null){
                                    startToast(task.getException().toString());

                                }
                            }

                            // ...
                        }
                    });

        }else{
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }

    }
    //로그인 성공하였을때 토스트 메세지를 보여주는 함수
    private void startToast(String msg){
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }
    //Board로 이동하는 함수
    private void startMainactivity (){
        Intent intent = new Intent(this, Board.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

}
