package com.xpectra.tvmaze.presenters.interfaces;

import android.support.v7.widget.RecyclerView;

import com.xpectra.tvmaze.views.interfaces.ShowListView;

/**
 * Created by xpectra on 6/4/2017.
 */
public interface ShowListPresenter {
    ShowListPresenter init(ShowListView view);
    void loadShows();
    void sortListShow();
    RecyclerView.LayoutManager changeViewShow();
}
