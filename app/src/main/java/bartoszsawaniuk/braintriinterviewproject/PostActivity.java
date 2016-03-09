package bartoszsawaniuk.braintriinterviewproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import bartoszsawaniuk.braintriinterviewproject.models.TumblrPost;

/**
 * Created by bartoszsawaniuk on 08/03/16.
 */
public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        TumblrPost post = (TumblrPost) intent.getSerializableExtra("post");

        new DownloadImageTask(
                (ImageView) findViewById(R.id.image)).execute(post.getPhotoURL1280());

        TextView text = (TextView) findViewById(R.id.text);
        text.setText(post.getSlug()+"\n"+post.getLinkDescription()+"\n"+post.getLinkText());

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView image;

        public DownloadImageTask(ImageView image) {
            this.image = image;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;

            try {
                URL url = new URL(urldisplay);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                Log.e("log", "_" + e.toString());
            }

            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
        }
    }
}
