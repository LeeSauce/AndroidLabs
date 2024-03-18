package com.example.androidlabs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
        setContentView(R.layout.activity_main_contraint);

        Button button = (Button)findViewById(R.id.myButton);
        TextView txt = (TextView)findViewById(R.id.loveAndroid);
        EditText editTxt = (EditText)findViewById(R.id.myEditText);
        CheckBox checkBox = (CheckBox)findViewById(R.id.myCheckBox);

        button.setOnClickListener((btn) -> {
                txt.setText(editTxt.getText());
                Toast.makeText(this,getResources().getString(R.string.toastMessage)
                        ,Toast.LENGTH_SHORT ).show();
            });

        checkBox.setOnCheckedChangeListener((check, isChecked) ->{
            String status = checkBox.isChecked() == true ?
                    getResources().getString(R.string.on):getResources().getString(R.string.off);
            Snackbar.make(checkBox, getResources().getString(R.string.snackMessage)
                            +" " + status, Snackbar.LENGTH_SHORT)
                    .setAction(getResources().getString(R.string.undo), click ->
                            checkBox.setChecked(!isChecked)).show();

        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}