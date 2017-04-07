package com.xpectra.tvmaze.views.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xpectra.tvmaze.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends Activity {

    @BindView(R.id.imageView)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        //variable pasada por parametro
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");

        Picasso.with(getApplicationContext()).load(url).into(mImageView);
    }
}
