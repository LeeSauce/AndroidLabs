package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText txtEdit = (EditText)findViewById(R.id.nameEntry);
        Button nextButton = (Button)findViewById(R.id.nextButton);


        Intent namePage = new Intent(this, NameActivity.class);


        SharedPreferences pref = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        txtEdit.setText(pref.getString("savedName", ""));

        nextButton.setOnClickListener((next) -> {
            setName(editor, txtEdit);
            namePage.putExtra("name", txtEdit.getText().toString());
            startActivityForResult(namePage, 1);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            finish();
        }
    }

    public void setName(SharedPreferences.Editor editor, EditText txtEdit){
        editor.putString("savedName", txtEdit.getText().toString());
        if(editor.commit()){
            Toast.makeText(this, R.string.savedMessage, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, R.string.errorMessage, Toast.LENGTH_SHORT).show();
        }
    }
}