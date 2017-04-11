package com.example.elina.githubtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.app.Activity;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView txt_result;
    Button btn_start_another_activity;
    MainActivity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();


            Intent intent = new Intent();
            intent.setClass(MainActivity.this , Main2Activity.class);
            startActivity(intent);

        if (itemThatWasClickedId == R.id.m1) {
            Context context = MainActivity.this;
            Toast.makeText(context,"another_activity", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}