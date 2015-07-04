package com.example.vidhipatel.myapplication2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vidhi.patel on 7/4/2015.
 */
public class MyRecyclerAdapterImages extends RecyclerView.Adapter<MyRecyclerAdapterImages.ViewHolder> {

    private List<String> mfPath;
    private int itemLayout;
    private int startFrom;
    private int count;

    public MyRecyclerAdapterImages(List<String> mfPath,int startFrom, int count,int itemLayout) {
        this.itemLayout = itemLayout;
        this.mfPath=mfPath;
        this.startFrom=startFrom;
        this.count=count;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Bitmap bitmap= BitmapFactory.decodeFile(mfPath.get(startFrom+i));
        viewHolder.mImageView.setImageBitmap(bitmap);
        viewHolder.mImageView.setScaleType(ImageView.ScaleType.MATRIX);

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView) ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
