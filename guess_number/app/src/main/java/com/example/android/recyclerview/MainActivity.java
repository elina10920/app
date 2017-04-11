package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements myAdapter.ListItemClickListener {
    public static int NUM_LIST_ITEMS;
    private myAdapter mAdapter;
    private RecyclerView mNumbersList;
    private Toast mToast;

    //更改
    private EditText eText;
    public static  int key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //宣告recycleView
        //更改
        eText = (EditText) findViewById(R.id.editText);
        NUM_LIST_ITEMS = Integer.parseInt(eText.getText().toString());
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);  //垂直列下來
        mNumbersList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); //item之間的間隔
        mNumbersList.setHasFixedSize(true); //固定item大小
        mAdapter = new myAdapter(NUM_LIST_ITEMS, this);
        mNumbersList.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //傳入一個menu
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_refresh://點擊reset可重新開始

                NUM_LIST_ITEMS = Integer.parseInt(eText.getText().toString());
                Toast.makeText(this, "新回合開始!", Toast.LENGTH_SHORT).show();

                mAdapter = new myAdapter(NUM_LIST_ITEMS, this);
                mNumbersList.setAdapter(mAdapter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {  //點擊item會提示
        if (mToast != null) {
            mToast.cancel();
        }
        int click = clickedItemIndex+1;
        if(clickedItemIndex+1 == key) {
            String toastMessage2 = "答對了～～答案就是" + click;
            mToast = Toast.makeText(this, toastMessage2, Toast.LENGTH_SHORT);
        if(clickedItemIndex+1<key){
            mAdapter.notifyItemRangeChanged(0,click);
        }
        }
        if (mToast != null) {
            mToast.show();
        }
    }


}

