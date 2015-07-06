package com.example.vidhipatel.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Albums extends AppCompatActivity {

    private static final String TAG="Albums";
    private static final File PATH= Environment.getExternalStorageDirectory();
    List<GalleryFolder> galleryFolderList;
    int folderCount=0;

    @Bind(R.id.list) RecyclerView mRecyclerView;
    MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        ButterKnife.bind(this);

        galleryFolderList=new ArrayList<GalleryFolder>();
        searchDirectory(PATH);

        myRecyclerAdapter=new MyRecyclerAdapter(galleryFolderList ,R.layout.gridlist_layout);
        myRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getApplicationContext(), DisplayImagesActivity.class);
                i.putExtra("GalleryFolder", galleryFolderList.get(position));
                startActivity(i);
            }
        });
        displayFolders();

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
                    if(fimagecount==1) {
                        folderCount++;
                        GalleryFolder galleryFolder=new GalleryFolder(path.getName(), fimagecount);
                        galleryFolderList.add(galleryFolder);
                    }
                    galleryFolderList.get(folderCount-1).addImagePath(f.getAbsolutePath());
                }
            }
            else
                searchDirectory(f);
        }
        if(fimagecount>1) {
            galleryFolderList.get(folderCount-1).setImageCount(fimagecount);
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
