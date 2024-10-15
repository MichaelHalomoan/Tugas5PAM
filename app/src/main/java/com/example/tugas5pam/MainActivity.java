package com.example.tugas5pam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etStudentName;
    private Button btnAddStudent;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private ArrayList<String> studentList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etStudentName = findViewById(R.id.etStudentName);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        studentList = databaseHelper.getAllStudents();
        studentAdapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etStudentName.getText().toString().trim();
                if (!name.isEmpty()) {
                    databaseHelper.addStudent(name);
                    studentList.add(name);
                    studentAdapter.notifyDataSetChanged();
                    etStudentName.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}