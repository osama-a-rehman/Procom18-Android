package com.example.osamaabdulrehman.procom18.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.osamaabdulrehman.procom18.R;

public class ContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText subjectEditText;
    private EditText messageEditText;

    private LinearLayout linearLayout;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().setTitle("Contact Us");

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        subjectEditText = (EditText) findViewById(R.id.subjectEditText);
        messageEditText = (EditText) findViewById(R.id.messageEditText);

        linearLayout = (LinearLayout) findViewById(R.id.activity_contact);

        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String subject = subjectEditText.getText().toString();
                String message = messageEditText.getText().toString();

                if(name.isEmpty()){
                    Snackbar.make(linearLayout, "Please enter your Name", Snackbar.LENGTH_LONG).show();
                    return;
                }else if(email.isEmpty()){
                    Snackbar.make(linearLayout, "Please enter your Email", Snackbar.LENGTH_LONG).show();
                    return;
                }else if(subject.isEmpty()){
                    Snackbar.make(linearLayout, "Please enter a Subject", Snackbar.LENGTH_LONG).show();
                    return;
                }else if(message.isEmpty()){
                    Snackbar.make(linearLayout, "Please enter your Message", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(!name.matches("[a-zA-Z]+")){
                    Snackbar.make(linearLayout, "Name should only contain letters", Snackbar.LENGTH_LONG).show();
                    return;
                }

                sendEmail();
            }
        });


    }

    public void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"procom@nu.edu.pk"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectEditText.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, messageEditText.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "Email Sent");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
