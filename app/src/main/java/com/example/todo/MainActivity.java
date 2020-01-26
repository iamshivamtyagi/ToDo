package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EditText etItem;
    Button btnAdd;
    ListView lvItems;
    //Created an arrayList of name items
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etItem = findViewById(R.id.etItem);
        btnAdd = findViewById(R.id.btnAdd);
        lvItems = findViewById(R.id.lvItems);


        items = FileHelper.readData(this); // ReadData returns
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etItem.getText().toString().equals("")) {
                    String data = etItem.getText().toString();
                    adapter.add(data);
                    etItem.setText("");

                    FileHelper.writeData(items, MainActivity.this);
                    Toast.makeText(MainActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lvItems.setOnItemClickListener(MainActivity.this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(items, this);
        Toast.makeText(this, "Task Removed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
