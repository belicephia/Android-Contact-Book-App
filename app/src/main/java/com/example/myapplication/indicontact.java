package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * create an class that is the controller&model for the view indivcontact.xml
 * this class add new contact's to the current user
 */
public class indicontact extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView contact_name;
    private TextView contact_phone;
    private TextView contact_email;
    private AppDatabase db;
    private String name_of_c;
    private String phone_of_c;
    private String email_of_c;
    private String user_id_email;
    private FloatingActionButton save_info;
    private FloatingActionButton exit_cont;
    private FloatingActionButton call_btn;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.indivcontact);
        save_info = findViewById(R.id.save_button);
        exit_cont = findViewById(R.id.exit_buttom);
        call_btn = findViewById(R.id.call_button);
        /**
         * create a call button that can be used to call the number as string "phone_of_c"
         */
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact_phone = findViewById(R.id.Phoneinput);
                phone_of_c = contact_phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone_of_c));
                startActivity(intent);
            }
        });

        /**
         * exit the current page and go back to contactpage
         */
        exit_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), contactpage.class);
                startActivity(intent);
            }


        });

        /**
         * store all the user input into strings that will be stored into database as sqlite query
         */
        save_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                contact_name = findViewById(R.id.nameinput);
                name_of_c = contact_name.getText().toString();
                contact_phone = findViewById(R.id.Phoneinput);
                phone_of_c = contact_phone.getText().toString();
                contact_email = findViewById(R.id.emailinput);
                email_of_c = contact_email.getText().toString();
                FirebaseUser cur_user = mAuth.getCurrentUser();
                user_id_email = cur_user.getEmail().toString();

//              create a new contact in the app that is associated with the current user_email_id
                Contact new_con = new Contact();
                new_con.contact_email_sql = email_of_c;
                new_con.contact_name_sql = name_of_c;
                new_con.contact_phone_sql= phone_of_c;
                new_con.user_id_email_sql = user_id_email;

//              fetch the database
                db = Credb.getInstance(getApplicationContext());
                System.out.println("db");

//              insert the new contact into the database
                db.contactDao().insert(new_con);
                }


        });
    }
}
