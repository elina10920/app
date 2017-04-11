/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

// 需建立 Adapter 來控管每個 item 的顯示設定

public class myAdapter extends RecyclerView.Adapter<myAdapter.NumberViewHolder> {  //myAdapter繼承RecyclerView.Adapter
    private static final String TAG = myAdapter.class.getSimpleName();
    private ListItemClickListener mOnClickListener;
    private int mNumberItems;
    MainActivity mainActivity = new MainActivity();
    private SparseBooleanArray mSelectedItems =new SparseBooleanArray();;
    int[] check = new int[100];

    public interface ListItemClickListener {  //interface會定義沒有實作內容的方法，無法直接產生物件，必須先定義一個將介面中所有方法實作出來的類別，由此類別產生物件
        void onListItemClick(int clickedItemIndex);
    }

    public myAdapter(int numberOfItems, ListItemClickListener listener) {  //建構子
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        mainActivity.key = (int) (Math.random() * numberOfItems+1);

    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) { //現有的viewholder不夠用 要求adapter建新的
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }  //回傳item數量

    class NumberViewHolder extends RecyclerView.ViewHolder
         implements OnClickListener {

        TextView listItemNumberView;
        View bgView;

        public NumberViewHolder(View itemView) {
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            bgView = itemView.findViewById(R.id.background);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);



            int click = clickedPosition+1;
            if(click == mainActivity.key){
                check[clickedPosition] = 2;
                notifyItemChanged(clickedPosition);
            }
            else if(clickedPosition+1<mainActivity.key ){
                for(int i =0;i<click;i++){
                    check[i] =1;
                }
                notifyItemRangeChanged(0,click);
            }
            else if(clickedPosition+1>mainActivity.key){
                for(int i =clickedPosition;i< mNumberItems;i++){
                    check[i] =1;
                }
                notifyItemRangeChanged(clickedPosition,mNumberItems-clickedPosition);
            }

            notifyItemChanged(clickedPosition);


        }
    }
        @Override
        public void onBindViewHolder(NumberViewHolder holder, int position) { //透過 position 參數指定每個 item 所用到的資料
            holder.listItemNumberView.setText(String.valueOf(position + 1));
            holder.itemView.setBackgroundColor(Color.WHITE);


                if(check[position] == 2){
                    holder.itemView.setBackgroundColor(Color.parseColor("#ffFFCCCC"));
                }
                else if(check[position] == 1){
                    holder.itemView.setBackgroundColor(Color.parseColor("#ffDDDDDD"));
                }
                else{
                    holder.itemView.setBackgroundColor(Color.parseColor("#ffffffff"));
                }

            Log.d(TAG, "#" + position);

            }

}

