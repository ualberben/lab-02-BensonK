package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> datalist;
    EditText inputText;
    int selected_position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);
        Button confirm = findViewById(R.id.confirm_button);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton","Vancouver","Moscow","Sydney","Berlin","Vienna","Tokyo","Beijing","Osaka","New Delhi"};

        datalist = new ArrayList<>();
        datalist.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, datalist);
        cityList.setAdapter(cityAdapter); //connect cityList with adapter, above connect adapter with datalist

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        inputText = findViewById(R.id.input_bar);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_position = position;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout input = findViewById(R.id.input);
                input.setVisibility(View.VISIBLE);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected_position != -1) {
                    datalist.remove(selected_position);
                    cityAdapter.notifyDataSetChanged();
                    selected_position = -1;
                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout input = findViewById(R.id.input);
                String text = inputText.getText().toString();
                text = text.trim();
                if(text.isEmpty()){
                    Toast.makeText(MainActivity.this, "No Input", Toast.LENGTH_SHORT).show();
                }
                else if(!datalist.contains(text)) {
                    datalist.add(text);
                    cityAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "City Existed", Toast.LENGTH_SHORT).show();
                }
                inputText.setText("");
                input.setVisibility(View.GONE);
            }
        });
    }


}