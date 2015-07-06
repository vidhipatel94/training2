package com.example.vidhipatel.myapplication2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vidhi.patel on 7/4/2015.
 */
public class MyRecyclerAdapterImages extends RecyclerView.Adapter<MyRecyclerAdapterImages.ViewHolder> {

    private int itemLayout;
    private GalleryFolder galleryFolder;
    private Context context;
    public MyRecyclerAdapterImages(Context context,GalleryFolder galleryFolder,int itemLayout) {
        this.context=context;
        this.galleryFolder=galleryFolder;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        //Bitmap bitmap= BitmapFactory.decodeFile(galleryFolder.getImagePathAt(i));
        //viewHolder.mImageView.setImageBitmap(bitmap);
        Glide.with(context)
                .load(galleryFolder.getImagePathAt(i))
                .fitCenter()
                .centerCrop()
                .into(viewHolder.mImageView);
        viewHolder.mImageView.setScaleType(ImageView.ScaleType.MATRIX);

    }

    @Override
    public int getItemCount() {
        return galleryFolder.getImageCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView) ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
