package com.xpectra.tvmaze.views.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xpectra.tvmaze.R;
import com.xpectra.tvmaze.helpers.Utils;
import com.xpectra.tvmaze.presenters.DetailShowPresenterImpl;
import com.xpectra.tvmaze.presenters.interfaces.DetailShowPresenter;
import com.xpectra.tvmaze.schemas.Show;
import com.xpectra.tvmaze.service.WebService;
import com.xpectra.tvmaze.views.interfaces.DetailShowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalleActivity extends AppCompatActivity implements DetailShowView {

    private static final String TAG = DetalleActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.imageView)
    ImageView mImageView;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.summary)
    TextView mSummary;

    @BindView(R.id.schedule)
    TextView mSchedule;

    @BindView(R.id.duration)
    TextView mDuration;

    @BindView(R.id.genres)
    TextView mGenres;

    @BindView(R.id.status)
    TextView mStatus;

    @BindView(R.id.rating)
    RatingBar mRating;

    @BindView(R.id.app_bar)
    AppBarLayout mApp_bar;

    private Show mShow;
    private boolean mFullDescription = false;
    private DetailShowPresenter mDetailShowPresenter;
    private String mTitleShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(mTitleShow!=null ? mTitleShow:"");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        //variable pasada por parametro
        Bundle bundle = getIntent().getExtras();
        int idShow = bundle.getInt("idShow");

        mDetailShowPresenter = new DetailShowPresenterImpl().init(this);
        mDetailShowPresenter.loadShow(idShow);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    @OnClick(R.id.fab)
    void clickFab(){
       mDetailShowPresenter.openBrowser();
    }

    @OnClick(R.id.summary)
    void clickSummary(){
        mFullDescription = !mFullDescription;
        mSummary.setMaxLines(mFullDescription?1000:3);
        if(!mFullDescription)
            mApp_bar.setExpanded(true);
    }

    @Override
    public void loadDataShow(Show show) {

        mTitleShow = show.getName();

        //Imagen
        Picasso.with(getApplicationContext()).load(show.getImage().getOriginal()).into(mImageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDetailShowPresenter.openImage();
            }
        });

        //Title
        mTitle.setText(show.getName());

        //Summary
        mSummary.setText(Html.fromHtml(show.getSummary()));

        //Schedule
        mSchedule.setText(Utils.getListString(show.getSchedule().getDays())+" - "+Utils.parseHours(show.getSchedule().getTime()));

        //Raiting
        if(show.getRating().getAverage()!=null)
            mRating.setRating((float) (show.getRating().getAverage()/2));
        else
            mRating.setRating(0);

        //Duration
        if(show.getRuntime()>=60)
            mDuration.setText(Utils.parseMinHoras(show.getRuntime()));
        else
            mDuration.setText(show.getRuntime()+"min");

        //genres
        mGenres.setText(Utils.getListString(show.getGenres()));

        //state
        mStatus.setText(show.getStatus());
    }

    @Override
    public void navigateToImage(String url) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void navigateToBrowser(String url) {
        if(url==null)
            return;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void showHttpError(int code) {
        WebService.handlerRequestError(getApplicationContext(),code);
    }
}
