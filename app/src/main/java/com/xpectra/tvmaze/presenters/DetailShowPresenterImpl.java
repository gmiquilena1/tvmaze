package com.xpectra.tvmaze.presenters;

import com.xpectra.tvmaze.models.api.TvMazeImpl;
import com.xpectra.tvmaze.models.interfaces.ApiListener;
import com.xpectra.tvmaze.models.interfaces.TvMaze;
import com.xpectra.tvmaze.presenters.interfaces.DetailShowPresenter;
import com.xpectra.tvmaze.schemas.Show;
import com.xpectra.tvmaze.views.interfaces.DetailShowView;

/**
 * Created by xpectra on 6/4/2017.
 */
public class DetailShowPresenterImpl implements DetailShowPresenter,ApiListener {

    private DetailShowView mView;
    private Show mShow;
    private TvMaze mTvMaze;

    @Override
    public DetailShowPresenter init(DetailShowView view) {
        mView = view;
        mTvMaze = new TvMazeImpl().init(this);
        return this;
    }

    @Override
    public void openImage() {
        mView.navigateToImage(mShow.getImage().getOriginal());
    }

    @Override
    public void openBrowser() {
        mView.navigateToBrowser(mShow.getUrl());
    }

    @Override
    public void loadShow(int id) {
        mTvMaze.getShow(id);
    }

    @Override
    public void successApi(Object result) {
        mShow = (Show) result;
        mView.loadDataShow(mShow);
    }

    @Override
    public void errorApi(int code) {
        mView.showHttpError(code);
    }
}
