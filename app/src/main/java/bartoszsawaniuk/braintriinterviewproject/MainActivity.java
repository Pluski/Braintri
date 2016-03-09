package bartoszsawaniuk.braintriinterviewproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import bartoszsawaniuk.braintriinterviewproject.adapters.TumblrListAdapter;
import bartoszsawaniuk.braintriinterviewproject.models.TumblrPost;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TumblrPost> tumblrList = new ArrayList<>();
    private TumblrListAdapter mAdapter;
    private ListView listView;
    private String tumblrName = "drift-swag";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialog();
        new DataDownloader(tumblrName).execute();

        mAdapter = new TumblrListAdapter(this, tumblrList);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                intent.putExtra("post", tumblrList.get(position));
                startActivity(intent);
            }
        });

        EditText editText = (EditText) findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tumblrName = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                new DataDownloader(tumblrName).execute();
            }
        });

    }

    private void showDialog(){
        dialog = ProgressDialog.show(this,"Loading...","", true, true);
    }

    public class DataDownloader extends AsyncTask<Void, Void, Void> {

        URL url;
        String logText = "";
        String blogName;

        public DataDownloader(String blogName) {
            this.blogName = blogName;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO C'mon do something funny

            try {
                url = new URL("http://"+blogName+".tumblr.com/api/read/json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader r = new BufferedReader(new InputStreamReader(in));
                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        total.append(line);
                    }

                    String tumblrJSON = total.toString().subSequence(21,
                            total.toString().length()).toString();

                    JSONObject mainObject = new JSONObject(tumblrJSON);
                    String tumblrTitle = mainObject.getJSONObject("tumblelog").getString("title");
                    String tumblrName = mainObject.getJSONObject("tumblelog").getString("name");

                    JSONArray postArray = mainObject.getJSONArray("posts");
                    tumblrList.clear();
                    for (int i = 0; i <postArray.length(); i++) {
                        JSONObject postObject = (JSONObject) postArray.get(i);

                        Log.e("log",""+postArray.get(i));

                        tumblrList.add(new TumblrPost(postObject.optString("link-text"),
                                postObject.optString("url"), postObject.optString("url-with-slug"),
                                postObject.optString("type"), postObject.optString("format"),
                                postObject.optString("slug"),postObject.optString("link-url"),
                                postObject.optString("link-description"), postObject.optString("id"),
                                postObject.optString("photo-link-url"), postObject.optString("photo-url-1280"),
                                postObject.optString("photo-url-500"), postObject.optString("photo-url-400"),
                                postObject.optString("photo-url-250"), postObject.optString("photo-url-100"),
                                postObject.optString("photo-url-75")));
                    }


                } catch (JSONException e) {
                    Log.e("log","JSONExeption_"+e.toString());
                    logText = "Error - Downloaded data is malformed";
                } finally {
                    urlConnection.disconnect();
                }

            } catch (MalformedURLException e) {
                Log.e("log", "MalformedURLException_" + e.toString());
                logText = "Error - Tumblr WWW adres is malformed";
            } catch (IOException e) {
                Log.e("log", "IOException_"+e.toString());
                if(e.toString().contains("FileNotFoundException")){
                    logText = "Error - Given Tumblr name is invalid";
                } else {
                    logText = "Error - Connection error";
                }
            }

            return null;
        }

            @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                if(dialog != null)
                    dialog.cancel();

                if(logText.length() > 1) {
                    Toast.makeText(getApplicationContext(),
                            logText, Toast.LENGTH_LONG).show();
                }
        }
    }
}
