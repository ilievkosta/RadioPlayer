package com.example.rvclicknew;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import com.squareup.picasso.Picasso;
import java.io.IOException;


public class PlayActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    MediaPlayer mediaPlayer = new MediaPlayer();
    Button  pauseBtn;
    Button playBtn;

    Button likeBtn;
    ProgressBar progressBar;

    ImageView radioImage;
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        stopRadio();
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_play);


        progressBar=findViewById(R.id.progresbar);
        pauseBtn = findViewById(R.id.idBtnPause);
        playBtn = findViewById(R.id.idBtnPlay);
        pauseBtn.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        radioImage=findViewById(R.id.imageView);
        likeBtn=findViewById(R.id.idBtnLike);




        String radioName = "";
        String radioUrl = "";
        String radioImg= "";
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            radioName=extras.getString("radioName");
            radioUrl= extras.getString("radioURL");
            radioImg = extras.getString("radioPic");
            ImageView ivBasicImage = findViewById(R.id.imageView);
            Picasso.get().load(radioImg).into(ivBasicImage);

        }



        final String finalRadioUrl = radioUrl;
        final String finalRadioName=radioName;
        final String finalRadioPic=radioImg;
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                playRadio(finalRadioUrl);
            }
        });

      pauseBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            pauseRadio();
          }
      });

      likeBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              db.addContact(new Radio(finalRadioName,finalRadioUrl,finalRadioPic));
          }
      });

    }


    private void pauseRadio(){
        mediaPlayer.pause();
        pauseBtn.setVisibility(View.INVISIBLE);
        playBtn.setVisibility(View.VISIBLE);
    }
    private void stopRadio(){
        mediaPlayer.stop();
    }
    private void playRadio(String URL) {

        String url = URL;
        progressBar.setVisibility(View.VISIBLE);
        pauseBtn.setVisibility(View.VISIBLE);
        playBtn.setVisibility(View.INVISIBLE);
        mediaPlayer.reset();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(url);

            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            mediaPlayer.start();
            progressBar.setVisibility(View.GONE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                progressBar.setSecondaryProgress(percent);
            }
        });

    }


}
