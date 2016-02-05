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

    URLImageView iconView;
    TextView titleView, directorView;
    private void init(){
        inflate(getContext(), R.layout.view_item, this);
        iconView = (URLImageView)findViewById(R.id.image_icon);
        titleView = (TextView)findViewById(R.id.text_title);
        directorView = (TextView)findViewById(R.id.text_director);
    }

    MovieItem item;
    //    ImageRequest mRequest;
    //
    public void setMovieItem(MovieItem item) {
        this.item = item;
        titleView.setText(Html.fromHtml(item.title));
        directorView.setText(item.director);

        // iconView item.image....
        iconView.setImageURL(item.image);
//        if (mRequest != null) {
//            mRequest.cancel();
//            mRequest = null;
//        }
//        if (!TextUtils.isEmpty(item.image)) {
//            iconView.setImageResource(R.drawable.ic_stub);
//            ImageRequest request = new ImageRequest(item.image);
//            mRequest = request;
//            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<Bitmap>() {
//                @Override
//                public void onSuccess(NetworkRequest<Bitmap> request, Bitmap result) {
//                    iconView.setImageBitmap(result);
//                    mRequest = null;
//                }
//
//                @Override
//                public void onFailure(NetworkRequest<Bitmap> request, int errorCode, int responseCode, String message, Throwable excepton) {
//                    iconView.setImageResource(R.drawable.ic_error);
//                    mRequest = null;
//                }
//            });
//        } else {
//            iconView.setImageResource(R.drawable.ic_empty);
//        }
         //수동으로 itemView 마다 이미지를 가져오는 경우
    }
}
