package com.example.todaysdinner;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    private static final String TAG = "RecipesNames";
    private RecyclerView mRecyclerView;
    private RecipeAdapter adapter;
    Context context = this;
    String newurl="";
    private TextView emptyview;
    private Button backbtn;
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mRecyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        emptyview = (TextView)findViewById(R.id.empty_view);

        GridLayoutManager manager = new GridLayoutManager(DisplayActivity.this,2);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();


    //Extract the data…
        if(bundle!=null) {
             user = bundle.getString("userinput");
        }
        if (!isNetworkAvailable(this) == false) {
            DownloadRecipes task = new DownloadRecipes();
            task.execute("http://www.recipepuppy.com/api/?i=" + user);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Please Connect to Internet",Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public class DownloadRecipes extends AsyncTask<String, Void, List<ListItem>> {

  private List<ListItem> recipeList;
  private ArrayList<String> u;

  protected List<ListItem> doInBackground(String... urls) {
      String result = "";

      URL url;
      HttpURLConnection urlConnection = null;
      try {
          url = new URL(urls[0]);

          urlConnection = (HttpURLConnection) url.openConnection();

          InputStream in = urlConnection.getInputStream();

          InputStreamReader reader = new InputStreamReader(in);

          int data = reader.read();

          while (data != -1) {

              char current = (char) data;

              result += current;

              data = reader.read();

          }

      } catch (MalformedURLException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }

      try {
          JSONObject response = new JSONObject(result);
          JSONArray posts = response.optJSONArray("results");
          recipeList = new ArrayList<>();
          u = new ArrayList<String>();

          for (int i = 0; i < posts.length(); i++) {
              JSONObject post = posts.optJSONObject(i);
              ListItem item = new ListItem();
              newurl = post.optString("href");
              boolean check =  newurl.matches(".*\\b(kraftfoods)\\b.*");
                if(!check) {
                    u.add(newurl);
                    item.setTitle(post.optString("title"));
                    Log.i("checking", post.optString("title"));
                    item.setImage(post.optString("thumbnail"));
                    Log.i("Imageeeeeeee", post.optString("thumbnail"));
                    Log.i("URl of website", post.optString("href"));
                    recipeList.add(item);
                    Log.d("recipe", recipeList.toString());
                }
          }
      } catch (JSONException e) {
          e.printStackTrace();
      }

      return recipeList;
  }


        protected void onPostExecute(List<ListItem> result) {

            if (result.isEmpty()) {
                mRecyclerView.setVisibility(View.GONE);
                emptyview.setVisibility(View.VISIBLE);
            }
            else {
                mRecyclerView.setVisibility(View.VISIBLE);
                emptyview.setVisibility(View.GONE);
            }

            adapter = new RecipeAdapter(DisplayActivity.this, result)  ;
            mRecyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(ListItem item,int position) {

                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                  Uri.parse(u.get(position)));
                    startActivity(viewIntent);
                }
            });
        }
    }
  public Boolean isNetworkAvailable(Context context) {

        Boolean resultValue = false; // Initial Value

        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            resultValue = true;
        }
        return resultValue;
    }
}
