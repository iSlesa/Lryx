package com.togglecorp.lryx;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button myButton = (Button) findViewById(R.id.click_me);
//        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MetadataActivity.class);
//                startActivity(intent);
//            }
//        });
        IntentFilter iF = new IntentFilter();
        iF.addAction("com.android.music.metachanged");
        iF.addAction("com.android.music.playstatechanged");
        iF.addAction("com.android.music.playbackcomplete");
        iF.addAction("com.android.music.queuechanged");

        registerReceiver(mReceiver, iF);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String cmd = intent.getStringExtra("command");
            Log.d("khai k ho ", action + " / " + cmd);
            String artist = intent.getStringExtra("artist");
            String album = intent.getStringExtra("album");
            String track = intent.getStringExtra("track");
            Log.d("Music",artist+":"+album+":"+track);

//            TextView vw = (TextView) findViewById(R.id.disptext);
//            vw.setText("Artist: " + artist + "\n Album: " +  album +"\nTrack :" + track);

            final ProgressDialog dialog = ProgressDialog.show(MainActivity.this ,"Hold on", "fetching lyrics..", true);
            Grabber g = new Grabber(track, new Callback(){
                @Override
                public void gotSomething(String lyrics, String track){
                    dialog.dismiss();
                    TextView vw = (TextView) findViewById(R.id.lyrics);
                    vw.setText(lyrics);
                    vw.setMovementMethod(new ScrollingMovementMethod());
                    TextView title = (TextView) findViewById(R.id.lyrics_title);
                    title.setText(track);
                }
            });

            g.execute();
        }
    };

    @Override
    protected void onPause()
    {
        try {
            unregisterReceiver(mReceiver);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        super.onPause();
    }
}
