package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Contact;
import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.graphics.Color;


import java.util.ArrayList;

/**
 * create a class that is the controller&model for fetching the contact's info
 * based on the create user email(user_email_id)
 * view for this the contactpage.xml
 */
public class contactpage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button logout_button;
    private FloatingActionButton addcontact_button;
    private TextView user_name_info;
    private static final String TAG = "contactpageact";
    private AppDatabase db;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mContact_id = new ArrayList<>();
    private ArrayList<String> mImagesUrls = new ArrayList<>();


    /**
     * @param savedInstanceState
     * the page that would call and build the contactpage.xml when the user_email_id and it's
     * password is matched
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.contactpage);
        logout_button = findViewById(R.id.logoutbutton);
        /**
         * onclicklistener for logout_button in the contact page, it would do both mAuth signout and display the activitylogin page
         */
        logout_button.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                    mAuth.signOut();
                                                    LoginActivityGen();
                                                 }
                                             }

        );
        addcontact_button = findViewById(R.id.addcontact);

        /**
         * display the indivcontact.xml page by running addcontactpage() method
         */
        addcontact_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                addcontactpage();
            }
        });

        FirebaseUser cur_user = mAuth.getCurrentUser();
        user_name_info = findViewById(R.id.username);
        user_name_info.setText(cur_user.getEmail());

        Log.d(TAG,"onCreate:Started.");
        initBitmap();


    }


    /**
     * create an recycler view to display all the cur_user_contact's information
     */
    private void initBitmap()
    {
        ArrayList<Contact> cur_user_contact = new ArrayList<>();
        FirebaseUser cur_user = mAuth.getCurrentUser();
        Log.d(TAG, "preparing recyvler view");
        db = Credb.getInstance(getApplicationContext());
        cur_user_contact = (ArrayList<Contact>) db.contactDao().loadAllByIds(cur_user.getEmail());
        for (int i = 0 ; i < cur_user_contact.size() ; i++)
        {
            mNames.add(cur_user_contact.get(i).contact_name_sql);
            mContact_id.add(cur_user_contact.get(i).contact_id);
        }
        initRecyclerView(cur_user_contact);
    }

    /**
     * @param cur_user_contact: the current contact information
     *                           set the individual information and ready to display
     */
    private void initRecyclerView(ArrayList cur_user_contact)
    {
        Log.d(TAG,"recyclerview init");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,this, mContact_id);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * create an intent that link to loginActivity class
     */
    private void LoginActivityGen(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void addcontactpage(){
        Intent intent = new Intent(getApplicationContext(), indicontact.class);
//        intent.putExtra("the user id", )
        startActivity(intent);
    }


}
