package com.blogspot.yupocket.yupocket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class PopulateRecords extends AppCompatActivity {

    DBHelper dbHelper;
    AdapterRecords adapterRecords;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    TextView tvTotal;
    Button shTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_records);

        mRecyclerView = findViewById(R.id.record_recycler);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        showAllRecords();

        shTotal = findViewById(R.id.btn_total);
        shTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTotal();
            }
        });
    }

    private void showAllRecords() {
        dbHelper = new DBHelper(this);
        adapterRecords = new AdapterRecords(dbHelper.recordList(), this, mRecyclerView);
        mRecyclerView.setAdapter(adapterRecords);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterRecords.notifyDataSetChanged();
    }

    public void showTotal(){
        dbHelper = new DBHelper(this);

        tvTotal =findViewById(R.id.tv_TotalBiaya);

        tvTotal.setText("Total Biaya : " + dbHelper.getSum().toString());
    }
}
