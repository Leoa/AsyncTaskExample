package edu.pace.pcd.simpleasynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.InputStream;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ImageView imageview = (ImageView)findViewById(R.id.imageView);
        DownloadImageTask myTask = new DownloadImageTask(imageview);

        myTask.execute("http://lorempixel.com/400/200/sports/");
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            super();
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap myBitmap = null;
            try {

                InputStream in = new java.net.URL(urldisplay).openStream();
                myBitmap = BitmapFactory.decodeStream(in);

            } catch (Exception e) {

                Log.e("Error", e.getMessage());
                e.printStackTrace();

            }
            return myBitmap;
        }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            bmImage.setImageBitmap(bitmap);
        }

    }
}
