package com.example.asignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asignment.R;
import com.example.asignment.database.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DBHelper db;
    private EditText edName;
    private EditText edDes;
    private EditText edInfo;
    private EditText edPrice;
    private EditText edDate;
    private Button btAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView(){
        edName = (EditText) findViewById(R.id.edName);
        edDes = (EditText) findViewById(R.id.edDes);
        edInfo = (EditText) findViewById(R.id.edInfo);
        edPrice = (EditText) findViewById(R.id.edPrice);
        edDate = (EditText) findViewById(R.id.edDate);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btAdd){
            onAddExpenses();
        }
    }
    private void onAddExpenses() {
        if (edName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edDes.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the description", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edInfo.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the infor", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edPrice.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the amount", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edDate.getText().toString().isEmpty()){
            Toast.makeText(this, "Please endter the date", Toast.LENGTH_SHORT).show();
            return;
        }

        String isAdd = db.addExpense(edName.getText().toString(),edInfo.getText().toString(),edDes.getText().toString()
                ,edPrice.getText().toString(),edDate.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();

    }
}