package com.example.ex1login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<Note> notes = new ArrayList<>();
    db dbb;
    public MainActivity(){

    }
    private Button btnLogin;
    private Button btnSignup;
    private EditText txtUsername;
    private EditText txtPassword;
    private TextView txtTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
        dbb = new db(getApplicationContext());
//        dbb.insertNote("abc","abc");
//        dbb.insertNote("ab","ab");
//        dbb.insertNote("cd","cd");
//        dbb.insertNote("dd","dd");
        notes.addAll(dbb.getAllNotes());
        txtTest = findViewById(R.id.txtTest);
        //check get a note
//        long x = 1;
//        Note nt = dbb.getNote(x);
//        txtTest.setText(nt.getUsername());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUsername   = (EditText)findViewById(R.id.id_username);
                txtPassword = (EditText)findViewById(R.id.id_password);
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();


                if(checkLogin(username,password)){
                    Intent nextScreen = new Intent(getApplicationContext(), MainActivity2.class);
                    nextScreen.putExtra("username", username);
                    startActivityForResult(nextScreen, 0);
                }else{
                    TextView txtWrong = (TextView) findViewById(R.id.txtWrongLogin);
                    txtWrong.setText("Something went wrong");
                }
            }
            public boolean checkLogin(String username,String password){
                for(int i=0; i<notes.size(); i++){
                    if(username.equals(notes.get(i).getUsername()) && password.equals(notes.get(i).getPassword())){
                        return  true;
                    }
                }

                return false;
            }
        });

        btnSignup.setOnClickListener((View v) -> {
            Intent nextScreen = new Intent(getApplicationContext(), signup.class);
            startActivityForResult(nextScreen, 0);
        });
    }
}