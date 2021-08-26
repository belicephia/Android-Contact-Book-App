package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

/**
 * an class that allow user to update, delete , call or just check the contact
 */
public class updateindc extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText contact_name;
    private EditText contact_phone;
    private EditText contact_email;
    private Contact this_user;
    private AppDatabase db;
    private String name_of_c;
    private String phone_of_c;
    private String email_of_c;
    private String user_id_email;
    private FloatingActionButton update_info;
    private FloatingActionButton delete_info;
    private FloatingActionButton exit_cont;
    private FloatingActionButton call_btn;
    private String contact_id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.updateincon);
        update_info = findViewById(R.id.update_update);
        delete_info = findViewById(R.id.delete_update);
        exit_cont = findViewById(R.id.exit_update);
        call_btn = findViewById(R.id.call_btn);
        contact_name = findViewById(R.id.contact_name_update);
        contact_phone = findViewById(R.id.contact_phone_update);
        contact_email = findViewById(R.id.contact_email_update);
//
        Bundle b = getIntent().getExtras();
            if (b != null){
                contact_id =  b.get("contact_id").toString();
        }
        db = Credb.getInstance(getApplicationContext());
        this_user = db.contactDao().findIdivContact(contact_id);
        name_of_c = this_user.contact_name_sql;
        phone_of_c = this_user.contact_phone_sql;
        email_of_c = this_user.contact_email_sql;
        contact_name.setText(name_of_c);
        contact_phone.setText(phone_of_c);
        contact_email.setText(email_of_c);
//
//
        delete_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.contactDao().delete(this_user);
                Intent intent = new Intent(getApplicationContext(), contactpage.class);
                startActivity(intent);
            }
        });

        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                this_user.contact_email_sql = contact_email.getText().toString();
                this_user.contact_phone_sql = contact_phone.getText().toString();
                this_user.contact_name_sql = contact_name.getText().toString();
                db.contactDao().update(this_user);
                Intent intent = new Intent(getApplicationContext(), contactpage.class);
                startActivity(intent);
            }
        });
//
//
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact_phone = findViewById(R.id.contact_phone_update);
                phone_of_c = contact_phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone_of_c));
                startActivity(intent);
            }
        });

        exit_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), contactpage.class);
                startActivity(intent);
            }


        });
    }
}