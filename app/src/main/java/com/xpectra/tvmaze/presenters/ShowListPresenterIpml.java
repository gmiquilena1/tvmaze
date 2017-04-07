package com.xpectra.tvmaze.presenters;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xpectra.tvmaze.helpers.Constants;
import com.xpectra.tvmaze.models.api.TvMazeImpl;
import com.xpectra.tvmaze.models.interfaces.ApiListener;
import com.xpectra.tvmaze.models.interfaces.TvMaze;
import com.xpectra.tvmaze.presenters.interfaces.ShowListPresenter;
import com.xpectra.tvmaze.schemas.Show;
import com.xpectra.tvmaze.views.adapters.ShowsAdapter;
import com.xpectra.tvmaze.views.interfaces.ShowListView;

import java.util.Collections;
import java.util.List;

/**
 * Created by xpectra on 6/4/2017.
 */
public class ShowListPresenterIpml implements ShowListPresenter,ApiListener {

    private ShowListView mView;
    private List<Show> mListShows;
    private ShowsAdapter mAdapter;
    private int mViewMode = Constants.VIEW_LIST;
    private int mSortMode = Constants.SORT_ASC;

    private TvMaze mTvMaze;

    @Override
    public ShowListPresenter init(ShowListView view) {
        mView = view;
        mTvMaze = new TvMazeImpl().init(this);
        return this;
    }

    @Override
    public void loadShows() {
        mView.showProgressDialog();
        mTvMaze.getAllShows();
    }

    @Override
    public void sortListShow() {

        mSortMode = mSortMode == Constants.SORT_ASC ? Constants.SORT_DES:Constants.SORT_ASC;

        switch (mSortMode){
            case Constants.SORT_ASC:
                Collections.sort(mListShows);
                break;
            case Constants.SORT_DES:
                Collections.reverse(mListShows);
                break;
        }

        mAdapter.notifyDataSetChanged();
        mView.changeIconSort(mSortMode);

    }

    @Override
    public RecyclerView.LayoutManager changeViewShow() {
        mViewMode = mViewMode == Constants.VIEW_LIST ? Constants.VIEW_GRID:Constants.VIEW_LIST;
        mView.changeIconViewShow(mViewMode);
        mAdapter.setModeList(mViewMode);
        return mViewMode == Constants.VIEW_LIST ? new LinearLayoutManager(mView.getContextView()):new GridLayoutManager(mView.getContextView(),2);
    }

    @Override
    public void successApi(Object result) {
        mView.hideProgressDialog();
        mListShows = (List<Show>) result;
        Collections.sort(mListShows);
        mAdapter = new ShowsAdapter(mView.getContextView(),mListShows,mViewMode);
        mView.loadListShows(mAdapter);
    }

    @Override
    public void errorApi(int code) {
        mView.hideProgressDialog();
        mView.showHttpError(code);
    }
}
