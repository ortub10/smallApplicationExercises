package com.example.module6lesson1drill1;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    final private List<Bitmap> bitmaps;

    public ImageAdapter(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object getItem(int i) {
        return bitmaps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = new ImageView(viewGroup.getContext());
            view.setLayoutParams(new LinearLayout.LayoutParams(400,400));
        }

        ImageView imageView =  (ImageView) view;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(bitmaps.get(i));
        return imageView;
    }
}
