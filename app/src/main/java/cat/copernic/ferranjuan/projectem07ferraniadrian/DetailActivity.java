package cat.copernic.ferranjuan.projectem07ferraniadrian;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    SeekBar sBar;
    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView AlbumesTitle = findViewById(R.id.titleDetail);
        ImageView sportsImage = findViewById(R.id.albumImageDetail);
        TextView sportsSubtitle = findViewById(R.id.subTitleDetail);

        AlbumesTitle.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(sportsImage);
        sportsSubtitle.setOnClickListener(new View.OnClickListener() {
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
                        media = MediaPlayer.create(getApplicationContext(),R.raw.head_down );
                        media.start();
                       sBar = (SeekBar)findViewById(R.id.musicSeekBar);
                        sBar.setMax(media.getDuration());
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

        });


    }
}
