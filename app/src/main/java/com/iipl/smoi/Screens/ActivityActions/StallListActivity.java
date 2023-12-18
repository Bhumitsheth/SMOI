package com.iipl.smoi.Screens.ActivityActions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.iipl.smoi.Adapter.BookedStallListAdapter;
import com.iipl.smoi.Model.OtherModel.BookStallRentModel;
import com.iipl.smoi.R;

import java.util.ArrayList;

public class StallListActivity extends AppCompatActivity {

    BookedStallListAdapter bookStallRentAdapter;

    ArrayList<BookStallRentModel> bookStallRentArrayList = new ArrayList<>();

    ImageView img_back_stall_list;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_list);


        img_back_stall_list = findViewById(R.id.img_back_stall_list);
        recyclerView = findViewById(R.id.rv_view_stall_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(StallListActivity.this, LinearLayoutManager.VERTICAL, true));

//        ArrayList<BookStallRentModel> bookStallRentArrayList = (ArrayList<BookStallRentModel>)getArguments().getSerializable("key");

        Bundle b = getIntent().getExtras();
        if (b != null) {
            bookStallRentArrayList = (ArrayList<BookStallRentModel>) b.getSerializable("key");
        }


//        Log.d("List::>>>", String.valueOf(bookStallRentArrayList));

        img_back_stall_list.setOnClickListener(view1 -> {
            finish();
        });

        bookStallRentAdapter = new BookedStallListAdapter(StallListActivity.this, bookStallRentArrayList, position -> {
            bookStallRentArrayList.remove(bookStallRentArrayList.get(position));
            bookStallRentAdapter.notifyDataSetChanged();
            Log.d("StallList:::>>", String.valueOf(bookStallRentArrayList));

        });
        recyclerView.setAdapter(bookStallRentAdapter);
        bookStallRentAdapter.updateList(bookStallRentArrayList);


    }

    /*
    public ArrayList<BookStallRentModel> getUpdateStallArrayList() {
        return bookStallRentArrayList;
    }*/


}