package com.example.vidhipatel.myapplication2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Albums extends AppCompatActivity {

    private static final String TAG="Albums";
    private static final File PATH= Environment.getExternalStorageDirectory();
    List<String> folders;
    public static List<Integer> foldersCount;
    public static List<String> fpath;

    @Bind(R.id.list) RecyclerView mRecyclerView;
    MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        ButterKnife.bind(this);

        folders=new ArrayList<String>();
        foldersCount=new ArrayList<Integer>();
        fpath=new ArrayList<String>();
        searchDirectory(PATH);

        myRecyclerAdapter=new MyRecyclerAdapter(folders,foldersCount,fpath,R.layout.gridlist_layout);
        myRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), DisplayImagesActivity.class);
                i.putExtra("position",position);
               // i.putExtra("position", (Serializable) fpath);
                startActivity(i);
            }
        });
        displayFolders();
        for(String s:folders)
            Log.d(TAG,s);
    }

    private void displayFolders() {
        mRecyclerView.setAdapter(myRecyclerAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void searchDirectory(File path) {
        File[] files=path.listFiles();
        int fimagecount=0;
        for(File f:files){
            if(f.isFile()) {
                String fname=f.getName();
                String type=fname.substring((fname.lastIndexOf(".") + 1), fname.length());
                if(type.equals("jpg")) {
                    fimagecount++;
                    fpath.add(f.getAbsolutePath());
                    //Log.d(TAG,"File: " + f.getName() + " Dir: "+ f.getParentFile().getName());
                }
            }
            else
                searchDirectory(f);
        }
        if(fimagecount>0) {
            folders.add(path.getName());
            foldersCount.add(fimagecount);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_albums, menu);
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
