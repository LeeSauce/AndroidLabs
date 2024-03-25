package com.example.androidlabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_name);


        TextView welcome = (TextView)findViewById(R.id.welcomeView);
        Button reject = (Button)findViewById(R.id.rejectButton);
        Button accept = (Button)findViewById(R.id.acceptButton);

        Intent data = getIntent();

        StringBuilder welcomeMessage = new StringBuilder();
        welcomeMessage.append(welcome.getText());

        String name = data.getStringExtra("name");
        if(name != null){
            welcomeMessage.append(" ");
            welcomeMessage.append(name);
        }
        welcomeMessage.append("!");
        welcome.setText(welcomeMessage);


        reject.setOnClickListener((click) ->{
            setResult(0, data);
            finish();
        });

        accept.setOnClickListener((click) ->{
            setResult(1, data);
            finish();
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}