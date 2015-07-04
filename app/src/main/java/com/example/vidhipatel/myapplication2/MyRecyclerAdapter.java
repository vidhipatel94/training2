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
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<String> mDirList;
    private List<Integer> mImageCount;
    private List<String> mfPath;
    private int itemLayout;
    private String mDir;
    OnItemClickListener mOnItemClickListener;

    public MyRecyclerAdapter(List<String> mDirList, List<Integer> mImageCount, List<String> mfPath,int itemLayout) {
        this.mDirList = mDirList;
        this.mImageCount = mImageCount;
        this.itemLayout = itemLayout;
        this.mfPath=mfPath;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        mDir = mDirList.get(i);
        viewHolder.mDirName.setText(mDir);
        viewHolder.mImageCount.setText(mImageCount.get(i).toString());

        int iFile = 0;
        for(int j=0;j<i;j++)
            iFile+=mImageCount.get(j);
        Bitmap bitmap= BitmapFactory.decodeFile(mfPath.get(iFile));
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
        return mDirList.size();
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
