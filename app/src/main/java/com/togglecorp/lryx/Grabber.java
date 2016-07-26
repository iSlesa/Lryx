package com.togglecorp.lryx;

import android.os.AsyncTask;
import android.telecom.Call;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;

public class Grabber extends AsyncTask<Void, Void, Void>{

    private String query;
    private String lyrix;
    private String track;
    private String error;
    private Callback callback;

    public Grabber(String q, Callback cb){
        this.query = q;
        this.callback = cb;
    }

    protected Void doInBackground(Void... params){
        // public static String grab_lyrics(String arg) throws IOException{
        try {
            error = "";
            String arg = query;
            arg = arg.replaceAll(" ","+");
            Log.d("query", arg);
            String Url = "http://google.com/search?hl=en&q="+arg+"+lyrics&btnI=I\'m+Feeling+Lucky";
//        Log.d("url", Url);
//        URL url = null;
//        try {
//            url = new URL(Url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        HttpURLConnection ucon = null;
//        try {
//            ucon = (HttpURLConnection) url.openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.d("Here","vayena ni ta");
//        }
//        ucon.setInstanceFollowRedirects(false);
//        String redirectedUrl = ucon.getHeaderField("Location");

            Document page = null;
            page = Jsoup.connect(Url).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").timeout(30000)
                    .referrer("http://www.google.com")
                    .get();
            Log.d("Title: ", page.title());
            track = page.title();
            int a = track.indexOf("-");
            track = track.substring(a+2,track.length());
            page.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
            page.select("br").append("\\n");
            Elements lyrics = page.select("b + br + br + div"); //Search for lyrics <div> tag, that after <b> and 2 <br> tags
            String s = lyrics.html().replaceAll("\\\\n", "\n"); //Google again
            lyrix = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
            lyrix = lyrix.replace("\n\n", "\n");
            lyrix = lyrix.substring(4); //Remove first enters
            //Log.d("lyrics", lyrix );
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error msg", "Exception aaaaayoooo!");
            error = "Some error which I have no idea about. Peace.";
        }
        return null;
    }

    protected void onPostExecute(Void param) {
        if (error.length()==0) {
            if (lyrix.length() != 0)
                callback.gotSomething(lyrix, track);
            else
                callback.gotSomething("Couldn't find the lryx.", "Sorry :(");
        }
        else{
            callback.gotSomething(error,"Sorry :(");
        }
    }
}