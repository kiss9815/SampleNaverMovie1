package com.example.tacademy.samplenavermovie1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    //    ArrayAdapter<MovieItem> mAdapter;
    MovieAdapter mAdapter;
    EditText keywordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        keywordView = (EditText)findViewById(R.id.edit_keyword);
        listView = (ListView)findViewById(R.id.listView);
//        mAdapter = new ArrayAdapter<MovieItem>(this, android.R.layout.simple_list_item_1);
        mAdapter = new MovieAdapter();
        listView.setAdapter(mAdapter);
        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = keywordView.getText().toString();
                NaverMovieRequest request = new NaverMovieRequest(keyword);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NaverMovies>() {
                    @Override
                    public void onSuccess(NetworkRequest<NaverMovies> request, NaverMovies result) {
                        //                            for (MovieItem item : result.items) {
//                                mAdapter.add(item);
//                            }
                        mAdapter.addAll(result.items);
                    }

                    @Override
                    public void onFailure(NetworkRequest<NaverMovies> request, int errorCode, int responseCode, String message, Throwable exception) {
                        Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        Log.i("MainActivity", "responseCode : " + responseCode);

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
