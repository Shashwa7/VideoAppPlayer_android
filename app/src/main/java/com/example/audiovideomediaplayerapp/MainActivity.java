package com.example.audiovideomediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player; //for playing mp3 song
    VideoView vw;   //video container
    MediaController mc; //for video controls
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vw = findViewById(R.id.vw_box);
        playVideo();

    }

    public void playVideo(){

        try {
            String vp = "android.resource://" + getPackageName() + "/" + R.raw.lucas;
            vw.setVideoPath(vp);
            mc = new MediaController(this);
            vw.setMediaController(mc);
            mc.setAnchorView(vw);
            vw.start();
            vw.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(MainActivity.this, "Completed!", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception ex) {
            Log.d("Playtime Error:", ex.getMessage());
        }
    }

    public void playSong(View view){
            if(player == null) {
                player = MediaPlayer.create(this, R.raw.cupcakesong);
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopPlayer();
                    }
                });
            }
            player.start();
        }
    public void pauseSong(View v){
            if(player != null){
                player.pause();
            }
        }
    public void stopSong(View v){
            stopPlayer();
        }

    public void stopPlayer(){
            if(player != null){
                player.stop();
                player.release();
                player = null;
                Toast.makeText(getApplicationContext(),"Player Stopped!",Toast.LENGTH_LONG).show();
            }
        }


    //as soon as we leave the app, the media player will release all the resources
    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}