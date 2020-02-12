package cat.copernic.ferranjuan.projectem07ferraniadrian;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    SeekBar sBar;
    MediaPlayer media;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView AlbumesTitle = findViewById(R.id.titleDetail);
        ImageView albumImage = findViewById(R.id.albumImageDetail);
        TextView albumSubtitle = findViewById(R.id.subTitleDetail);


        AlbumesTitle.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(albumImage);
        albumSubtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if (media != null && media.isPlaying())
                    {

                        media.stop();
                    }
                    else

                    {
                        handler = new Handler();
                        media = MediaPlayer.create(getApplicationContext(),R.raw.head_down);
                        media.start();
                       sBar = (SeekBar)findViewById(R.id.musicSeekBar);
                        sBar.setMax(media.getDuration());
                        int mediaPos = media.getCurrentPosition();
                        sBar.setProgress(mediaPos);// set current progress to song's
                        handler.removeCallbacks(moveSeekBarThread);
                        handler.postDelayed(moveSeekBarThread, 100);
                        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                            boolean userTouch;
                            @Override
                            public void onStopTrackingTouch(SeekBar arg0) {
                                userTouch = false;
                            }
                            @Override
                            public void onStartTrackingTouch(SeekBar arg0) {
                                userTouch = true;
                            }
                            @Override
                            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                                if(media.isPlaying() && arg2)
                                    media.seekTo(arg1);
                            }
                        });
                    }
                } catch(Exception e) {

                }

            }
            private Runnable moveSeekBarThread = new Runnable() {

                public void run() {
                    if(media.isPlaying()){

                        int mediaPos_new = media.getCurrentPosition();
                        int mediaMax_new = media.getDuration();
                        sBar.setMax(mediaMax_new);
                        sBar.setProgress(mediaPos_new);

                        handler.postDelayed(this, 100); //Looping the thread after 0.1 second
                        // seconds
                    }
                }
            };

        });


    }
}
