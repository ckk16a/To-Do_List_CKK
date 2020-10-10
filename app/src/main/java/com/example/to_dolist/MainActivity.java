package com.example.to_dolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createExampleList();
        buildRecyclerView();
        setButtons();
    }
    public void insertItem(int position, String task) {
        mExampleList.add(position, new ExampleItem(R.drawable.ic_x, task, "Not Done"));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
    public void changeItem(int position, String text) {
        mExampleList.get(position).changeText2(text);
        mAdapter.notifyItemChanged(position);
    }
    public void changeImage(int position) {
        mExampleList.get(position).changeImage();
        mAdapter.notifyItemChanged(position);
    }
    public void changeDone(int position) {
        mExampleList.get(position).setDone();
        mAdapter.notifyItemChanged(position);
    }
    public boolean getDone(int position) {
        return mExampleList.get(position).getDone();
    }
    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_x, "Example Task", "Not Done"));
    }
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(!getDone(position)) {
                    changeDone(position);
                    changeImage(position);
                    changeItem(position, "Done!");
                }
                else{
                    changeDone(position);
                    changeImage(position);
                    changeItem(position, "Not Done");
                }
            }
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }
    public void setButtons() {
        buttonInsert = findViewById(R.id.button_insert);
        editTextInsert = findViewById(R.id.edittext_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mAdapter.getItemCount();
                String task = editTextInsert.getText().toString();
                insertItem(position, task);
                editTextInsert.setText("");
            }
        });
    }
}