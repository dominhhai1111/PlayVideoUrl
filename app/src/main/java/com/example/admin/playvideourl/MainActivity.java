package com.example.admin.playvideourl;

import android.media.MediaPlayer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("http://www.html5videoplayer.net/videos/toystory.mp4");
        if (mediaController == null) {
            mediaController = new MediaController(MainActivity.this);
            // set the video view that acts as the anchor for media controller
            mediaController.setAnchorView(videoView);
            // set media controller for video view
            videoView.setMediaController(mediaController);
        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                }

                // when video Screen change size
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        // re-set video view that acts as the anchor for controller
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });
    }

    // when you change direction of the phone, this method will be called.
    // it store state of the video (current position)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // store current position
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        outState.putInt("currentPosition", videoView.getCurrentPosition());
        videoView.pause();
    }

    // after rotating the phone, this method will be called.
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get saved position
        position = savedInstanceState.getInt("currentPosition");
        videoView.seekTo(position);
    }
}
