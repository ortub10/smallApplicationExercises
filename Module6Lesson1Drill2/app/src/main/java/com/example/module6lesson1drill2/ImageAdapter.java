package com.example.module6lesson1drill2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    final private List<String> links;

    public ImageAdapter(List<String> links) {
        this.links = links;
    }

    @Override
    public int getCount() {
        return links.size();
    }

    @Override
    public Object getItem(int i) {
        return links.get(i);
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
//        imageView.setImageBitmap(links.get(i));
        Picasso.get().load(links.get(i)).resize(400,400).into(imageView);
        return imageView;
    }
}
