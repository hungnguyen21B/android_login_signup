package com.example.ex1login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class signup extends AppCompatActivity {
    List<Note> notes = new ArrayList<>();
    private Button btnSignup;
    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtConfirm;
    db dbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignup = findViewById(R.id.btn_signup_form);
        dbb= new db(getApplicationContext());
        notes.addAll(dbb.getAllNotes());
        btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtUsername   = (EditText)findViewById(R.id.txt_username_form);
                txtPassword = (EditText)findViewById(R.id.txt_password_form);
                txtConfirm = (EditText)findViewById(R.id.txt_confirm);
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String confirm = txtConfirm.getText().toString();


                if(checkSignup(username,password,confirm)){
                    dbb.insertNote(username,password);
                    Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityForResult(nextScreen, 0);
                }else{
                    Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
            public boolean checkSignup(String username,String password,String confirm){

                //check sign up
                //if confirm password pass move to check exist username
                // if have same username return false
                //
                if(password.equals(confirm)){
                    for(int i=0; i<notes.size(); i++){
                        if(username.equals(notes.get(i).getUsername())){
                            return  false;
                        }
                    }
                    // after all check cases return true
                    return true;
                }
                return false;
            }
        });
    }
}