package com.e.customheaderrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.e.customheaderrecyclerview.events.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ListItem> items =  new ArrayList<>();
    private DataAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessageEvent messageEvent=new MessageEvent("Recieve eventbus data");
        EventBus.getDefault().postSticky(messageEvent);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rvItems);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DataAdapter(items);
        recyclerView.setAdapter(adapter);

//GRID LAYOUT MANAGER
        GridLayoutManager gd = new GridLayoutManager(this, 2);

        gd.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position) == ListItem.TYPE_HEADER ? 2 : 1;
            }
        });

        recyclerView.setLayoutManager(gd);

        items.add(new Header("Header 1"));
        items.add(new Person("Item 1"));
        items.add(new Person("Item 2"));
        items.add(new Person("Item 3"));
        items.add(new Header("Header 2"));
        items.add(new Person("Item 4"));
        items.add(new Person("Item 5"));
        items.add(new Person("Item 6"));
        items.add(new Person("Item 7"));
        items.add(new Header("Header 3"));
        items.add(new Person("Item 8"));
        items.add(new Person("Item 9"));
        items.add(new Person("Item 10"));
        items.add(new Person("Item 11"));
        adapter.notifyDataSetChanged();


        Button btnEvent=findViewById(R.id.btnEvent);
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,RecieveEventActivity.class));
            }
        });
    }
}