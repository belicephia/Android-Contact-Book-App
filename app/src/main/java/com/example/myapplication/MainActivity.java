package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Contact;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * a class that check if the user is already login or not and display contactpage or loginactivity accordingly
 */
public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }else{
            mainpage();
        }
    }


    /**
     * if not login, display the login page
     */
    private void mainpage(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    /**
     * if login already, display the contactpage
     */
    private void reload() {
        Intent intent = new Intent(getApplicationContext(), contactpage.class);
        startActivity(intent);
    }


    private void updateUI(FirebaseUser user) {}
}