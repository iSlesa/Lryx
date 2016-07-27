//package com.togglecorp.lryx;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.method.ScrollingMovementMethod;
//import android.widget.TextView;
//
//public class LyricsActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lyrics);
//
//        Intent intent = getIntent();
//        String query = intent.getStringExtra(MetadataActivity.EXTRA_MESSAGE);
//        final ProgressDialog dialog = ProgressDialog.show(this,"Hold on", "fetching lyrics..", true);
//        Grabber g = new Grabber(query, new Callback(){
//            @Override
//            public void gotSomething(String lyrics, String track){
//                dialog.dismiss();
//                TextView vw = (TextView) findViewById(R.id.lyrics);
//                vw.setText(lyrics);
//                vw.setMovementMethod(new ScrollingMovementMethod());
//                TextView title = (TextView) findViewById(R.id.lyrics_title);
//                title.setText(track);
//            }
//        });
//
//        g.execute();
//    }
//}
