package com.example.vidhipatel.myapplication2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DisplayImagesActivity extends AppCompatActivity {

    @Bind(R.id.images) RecyclerView mRecyclerView;
    MyRecyclerAdapterImages myRecyclerAdapterImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GalleryFolder galleryFolder= (GalleryFolder) getIntent().getSerializableExtra("GalleryFolder");
        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);

        myRecyclerAdapterImages=new MyRecyclerAdapterImages(galleryFolder,R.layout.images_gridlayout);
        displayImages();

    }

    private void displayImages() {
        mRecyclerView.setAdapter(myRecyclerAdapterImages);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_images, menu);
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
