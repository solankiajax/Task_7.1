package com.example.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.data.DatabaseHelper;
import com.example.lostandfound.model.Item;

public class NewAdvertActivity extends AppCompatActivity {
    Button save;
    TextView name,phone,description,date,location;
    boolean postTypeClicked=false;
    RadioButton lost,found;
    Item item;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_add);

        item = new Item();
        lost = findViewById(R.id.lost);
        found = findViewById(R.id.found);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone1);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        location = findViewById(R.id.location);
        save = findViewById(R.id.save);
        db = new DatabaseHelper(this);



        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(postTypeClicked==false){
                    postTypeClicked=true;
                    item.setPost_type("Lost");

                }
                else if(postTypeClicked&&item.getPost_type().equals("Found")){
                    lost.setChecked(false);
                    Toast.makeText(NewAdvertActivity.this,"Found already checked",Toast.LENGTH_LONG).show();
                    item.setPost_type("Found");
                }
                else if(postTypeClicked&&item.getPost_type().equals("Lost")){
                    lost.setChecked(false);
                    postTypeClicked = false;
                    item.setPost_type("");
                }
            }
        });

        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(postTypeClicked==false){
                    postTypeClicked=true;
                    item.setPost_type("Found");
                }
                else if(postTypeClicked&&item.getPost_type().equals("Lost")){
                    found.setChecked(false);
                    Toast.makeText(NewAdvertActivity.this,"Lost already checked",Toast.LENGTH_LONG).show();
                    item.setPost_type("Lost");
                }
                else if(postTypeClicked&&item.getPost_type().equals("Found")){
                    found.setChecked(false);
                    postTypeClicked = false;
                    item.setPost_type("");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(NewAdvertActivity.this,"Please enter your name",Toast.LENGTH_SHORT).show();
                }else{item.setName(name.getText().toString());}

                if(phone.getText().toString().isEmpty()){
                    Toast.makeText(NewAdvertActivity.this,"Please enter your phone number",Toast.LENGTH_SHORT).show();
                }else{item.setPhone(Integer.parseInt(phone.getText().toString()));}

                if(description.getText().toString().isEmpty()){
                    Toast.makeText(NewAdvertActivity.this," please enter a description",Toast.LENGTH_SHORT).show();
                }else{item.setDescription(description.getText().toString());}

                if(date.getText().toString().isEmpty()){
                    Toast.makeText(NewAdvertActivity.this,"Please enter date",Toast.LENGTH_SHORT).show();
                }else{item.setDate(date.getText().toString());}

                if(location.getText().toString().isEmpty()){
                    Toast.makeText(NewAdvertActivity.this,"Please enter the location where item was found or lost at",Toast.LENGTH_SHORT).show();
                }else{item.setLocation(location.getText().toString());}
                if(postTypeClicked==false){
                    Toast.makeText(NewAdvertActivity.this,"Please select Post Type",Toast.LENGTH_SHORT).show();
                }



                boolean allTrue = !name.getText().toString().isEmpty()&&!phone.getText().toString().isEmpty()&&!description.getText().toString().isEmpty()&&!date.getText().toString().isEmpty()&&!location.getText().toString().isEmpty()&&!(item.getPost_type().isEmpty()||item.getPost_type().equals(""));
                if(postTypeClicked&&allTrue){

                    postTypeClicked=false;
                    long result  = db.insertItem(item);

                    if(result > 0){

                        Intent intent = new Intent(NewAdvertActivity.this,MainActivity.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(NewAdvertActivity.this,"didn't get saved",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


    }
}