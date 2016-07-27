//package com.togglecorp.lryx;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class MetadataActivity extends AppCompatActivity {
//
//    public String track_name;
//    public final static String EXTRA_MESSAGE = "com.togglecorp.lryx.MESSAGE";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_metadata);
//
//        Button show_lyrics = (Button) findViewById(R.id.lyrics_button);
//        show_lyrics.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MetadataActivity.this, LyricsActivity.class);
//                intent.putExtra(EXTRA_MESSAGE, track_name);
//                startActivity(intent);
//            }
//        });
//
//        IntentFilter iF = new IntentFilter();
//        iF.addAction("com.android.music.metachanged");
//        iF.addAction("com.android.music.playstatechanged");
//        iF.addAction("com.android.music.playbackcomplete");
//        iF.addAction("com.android.music.queuechanged");
//
//        registerReceiver(mReceiver, iF);
//    }
//
//    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            String cmd = intent.getStringExtra("command");
//            Log.d("Command: ", action + " / " + cmd);
//            String artist = intent.getStringExtra("artist");
//            String album = intent.getStringExtra("album");
//            String track = intent.getStringExtra("track");
//            Log.d("Music",artist+":"+album+":"+track);
//            track_name = track;
//            TextView vw = (TextView) findViewById(R.id.disptext);
//            String info = "Artist: " + artist + "\nAlbum: " +  album +"\nTrack :" + track;
//            vw.setText(info);
//        }
//    };
//
//    @Override
//    protected void onPause()
//    {
//        try {
//            unregisterReceiver(mReceiver);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        super.onPause();
//    }
//}
