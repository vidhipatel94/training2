package com.example.vidhipatel.myapplication2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vidhi.patel on 7/4/2015.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<GalleryFolder> galleryFolderList;
    private int itemLayout;
    OnItemClickListener mOnItemClickListener;

    public MyRecyclerAdapter(List<GalleryFolder> galleryFolderList,int itemLayout) {
        this.itemLayout = itemLayout;
        this.galleryFolderList = galleryFolderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        GalleryFolder galleryFolder=galleryFolderList.get(i);

        viewHolder.mDirName.setText(galleryFolder.getFolderName());
        viewHolder.mImageCount.setText(galleryFolder.getImageCount()+"");

        Bitmap bitmap= BitmapFactory.decodeFile(galleryFolder.getImagePathAt(0));
        viewHolder.mImageView.setImageBitmap(bitmap);
        viewHolder.mImageView.setScaleType(ImageView.ScaleType.MATRIX);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null)
                    mOnItemClickListener.onItemClick(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryFolderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView) ImageView mImageView;
        @Bind(R.id.dirname) TextView mDirName;
        @Bind(R.id.fcount) TextView mImageCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    interface OnItemClickListener{
        public void onItemClick(View v,int position);
    }

    void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }
}
