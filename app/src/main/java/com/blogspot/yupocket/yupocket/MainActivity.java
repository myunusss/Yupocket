package com.blogspot.yupocket.yupocket;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText mTanggal, mJenis, mKeterangan, mBiaya;
    Button viewRecords;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        mTanggal = findViewById(R.id.et_newTanggal);
        mTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        mJenis = findViewById(R.id.et_newJenis);
        mKeterangan = findViewById(R.id.et_newKeterangan);
        mBiaya = findViewById(R.id.et_newBiaya);

        viewRecords = findViewById(R.id.btn_viewRecords);

        viewRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRecords();
            }
        });
    }

    public void showDateDialog(){
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                mTanggal.setText(simpleDateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void SaveRecord(){
        String tanggal = mTanggal.getText().toString().trim();
        String jenis = mJenis.getText().toString().trim();
        String keterangan = mKeterangan.getText().toString().trim();
        String aBiaya = mBiaya.getText().toString();
        Float biaya = Float.valueOf(aBiaya);

        DBHelper dbHelper = new DBHelper(this);

        Record record = new Record(tanggal, jenis, keterangan, biaya);
        dbHelper.saveNewRecord(record);
        Toast.makeText(MainActivity.this, "Berhasil tambah data", Toast.LENGTH_SHORT).show();

        //clear field
        ClearField();
    }

    public void ClearField(){
        mTanggal.setText("");
        mJenis.setText("");
        mKeterangan.setText("");
        mBiaya.setText("");
    }

    public void ShowRecords(){
        Intent intent = new Intent(MainActivity.this, PopulateRecords.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        if (itemSelected == R.id.action_save){
            SaveRecord();
        }
        return super.onOptionsItemSelected(item);
    }
}
