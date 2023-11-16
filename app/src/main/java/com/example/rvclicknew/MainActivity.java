package com.example.rvclicknew;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Radio> radiosList;
    private RecyclerView recyclerView;
    private recycleAdapter.RecycleViewClickListener listener;
    private recycleAdapter.RecycleViewOnLongClickListener listenerlong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleView);
        radiosList= new ArrayList<>();
        setUserInfo();
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
            //  Toast.makeText(this,"Play Selected",Toast.LENGTH_SHORT).show();
              return true;
          case R.id.ExitMenu:
              finishAffinity();
              return true;

          case R.id.FavMenu:
              Intent intent1 = new Intent(getApplicationContext(), PlayActivityFav.class);
              startActivity(intent1);
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
                Log.d(TAG, "Some logging");
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

    private void setUserInfo() {
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));
        radiosList.add(new Radio("Darik Nostalgie","https://darikradio.by.host.bg:8000/Nostalgie","https://darikradio.bg/media/165/nostalgie.m.jpg"));
        radiosList.add(new Radio("Darik","https://darikradio.by.host.bg:8000/S2-128","https://darikradio.bg/media/232/25godinidariklogo.m.l-1-.m.jpg"));


    }
}