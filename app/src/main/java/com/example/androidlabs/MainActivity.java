package com.example.androidlabs;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
        private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter = new Adapter());
        Button accept = (Button) findViewById(R.id.myButton);
        EditText editText = (EditText)findViewById(R.id.textEdit);
        Switch mySwitch = (Switch)findViewById(R.id.mySwitch);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);



        accept.setOnClickListener((click) ->{
            adapter.isUrgent.add(mySwitch.isChecked());
            adapter.elements.add(editText.getText().toString());
            adapter.notifyDataSetChanged();
            editText.setText(null);
            mySwitch.setChecked(false);
        });

        list.setOnItemClickListener( (par, v, pos, id)->{
                alert.setTitle("Alert!")
                        .setPositiveButton("Yes", (click, arg)->{
                            adapter.elements.remove(pos);
                            adapter.isUrgent.remove(pos);
                            adapter.notifyDataSetChanged();
                        }).setNegativeButton("No", (click, arg) ->{})
                        .create().show();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private class Adapter extends BaseAdapter{
        ArrayList<String> elements = new ArrayList<>();
        ArrayList<Boolean> isUrgent = new ArrayList<>();
        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return elements.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View newView = inflater.inflate(R.layout.todo_layout, parent, false);

            TextView row = newView.findViewById(R.id.row);

            if(isUrgent.get(position)){
                row.setTextColor(Color.WHITE);
                row.setBackgroundColor(Color.RED);
            }

            row.setText(elements.get(position));

            return newView;
        }
    }
}