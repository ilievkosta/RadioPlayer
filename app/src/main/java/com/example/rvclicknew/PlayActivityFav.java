package com.example.rvclicknew;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayActivityFav extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    private ArrayList<Radio> radiosList;
    private RecyclerView recyclerView;
    private recycleAdapter.RecycleViewClickListener listener;
    private recycleAdapter.RecycleViewOnLongClickListener listenerlong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_fav);

        recyclerView=findViewById(R.id.recycleView);
        radiosList= new ArrayList<>();
        setRadiosInRecylceView();
        setAdapter();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.PlayMenu:
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(intent);

                return true;

                case R.id.ExitMenu:
                finishAffinity();
                return true;
        }
        return true;
    }



    private void setAdapter() {
        setOnClickListener();
        recycleAdapter adapter = new recycleAdapter(radiosList,listener,listenerlong);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }



    private void setOnClickListener() {
        listenerlong= new recycleAdapter.RecycleViewOnLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {
                Radio RadioDel=new Radio(radiosList.get(position).get_id(),radiosList.get(position).getRadioName(),radiosList.get(position).getRadioUrl(),radiosList.get(position).getRadioPic());
                db.deleteContact(RadioDel);
                Log.d(TAG, "Some logging");
                recreate();
            }
        };
        listener = new recycleAdapter.RecycleViewClickListener() {

            @Override

            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                intent.putExtra("radioName", radiosList.get(position).getRadioName());
                intent.putExtra("radioURL",radiosList.get(position).getRadioUrl());
                intent.putExtra("radioPic",radiosList.get(position).getRadioPic());
                startActivity(intent);
            }
        };
    }

    private void setRadiosInRecylceView() {
        List<Radio> Radio1=db.getAllRadios();
        for(Radio result:Radio1){
            radiosList.add(result);
        }
    }
}