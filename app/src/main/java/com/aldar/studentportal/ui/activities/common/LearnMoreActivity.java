package com.aldar.studentportal.ui.activities.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.utilities.FileUtils;
import com.aldar.studentportal.utilities.PermissionUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class LearnMoreActivity extends AppCompatActivity {
    YouTubePlayerView youTubePlayerView;
    Button btnZoom,btnDownload,btnBlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);

        btnZoom = findViewById(R.id.btn_start_zoom);
        btnDownload = findViewById(R.id.btn_download);
        btnBlog = findViewById(R.id.btn_blog);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "BJtMF1s4mCI";
                //youTubePlayer.loadVideo(videoId, 0);
                youTubePlayer.cueVideo(videoId,0);
            }
        });

        btnZoom.setOnClickListener(view -> {
            loadZoom();
        });

        btnBlog.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("link", "https://www.aldar.ac.ae/category/english-content-blog/");
            startActivity(new Intent(this, WebActivity.class).putExtras(bundle));
        });

        btnDownload.setOnClickListener(view -> {
            downloadBroucher();
        });
    }

    private void loadZoom(){
        try {
            Intent intent = getPackageManager()
                    .getLaunchIntentForPackage("us.zoom.videomeetings");

            if (intent != null) {
                startActivity(intent);
            } else {
                Intent go = new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://play.google.com/store/apps/details?id=us.zoom.videomeetings&hl=en"));
                startActivity(go);
            }

        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadBroucher(){
        PermissionUtils.checkPermision(this);
        try {
            new FileUtils(this, "https://www.aldar.ac.ae/wp-content/uploads/2019/06/Academic-Calendar.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        if(youTubePlayerView != null){
            youTubePlayerView = null;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(youTubePlayerView != null){
            youTubePlayerView.release();
            youTubePlayerView = null;
        }
        super.onDestroy();
    }
}