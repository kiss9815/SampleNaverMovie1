package com.example.tacademy.samplenavermovie1;

import android.content.Context;
import android.text.Html;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-02-05.
 */
public class ItemView extends FrameLayout{
    public ItemView(Context context) {
        super(context);
        init();
    }

    ImageView iconView;
    TextView titleView, directorView;
    private void init(){
        inflate(getContext(), R.layout.view_item, this);
        iconView = (ImageView)findViewById(R.id.image_icon);
        titleView = (TextView)findViewById(R.id.text_title);
        directorView = (TextView)findViewById(R.id.text_director);
    }

    MovieItem item;
    public void setMovieItem(MovieItem item) {
        this.item = item;
        titleView.setText(Html.fromHtml(item.title));
        directorView.setText(item.director);

        // iconView item.image....
    }
}
