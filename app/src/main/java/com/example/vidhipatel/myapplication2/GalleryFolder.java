package com.example.vidhipatel.myapplication2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vidhi.patel on 7/6/2015.
 */
public class GalleryFolder implements Serializable{
    private String folderName;
    private List<String> imagePath;

    public GalleryFolder(String folderName) {
        this.folderName = folderName;
        imagePath=new ArrayList<String>();
    }

    public void addImagePath(String imagePath) {
        this.imagePath.add(imagePath);
    }

    public String getImagePathAt(int position) {
        return imagePath.get(position);
    }

    public String getFolderName() {
        return folderName;
    }

    public int getImageCount() {
        return imagePath.size();
    }

}
