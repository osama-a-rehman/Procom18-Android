package com.example.osamaabdulrehman.procom18.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.osamaabdulrehman.procom18.R;

public class MainActivity extends AppCompatActivity {

    private ImageView mainActivityLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMain = (Button) findViewById(R.id.btnNextMainActivity);
        mainActivityLogo = (ImageView) findViewById(R.id.mainActivityLogo);

        Glide.with(this).load(R.drawable.procom).into(mainActivityLogo);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
}
