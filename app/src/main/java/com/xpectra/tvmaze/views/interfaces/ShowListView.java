package com.xpectra.tvmaze.views.interfaces;

import android.content.Context;

import com.xpectra.tvmaze.views.adapters.ShowsAdapter;

/**
 * Created by xpectra on 6/4/2017.
 */
public interface ShowListView {
    void showProgressDialog();
    void hideProgressDialog();
    void changeIconSort(int mode);
    void changeIconViewShow(int mode);
    void loadListShows(ShowsAdapter adapter);
    void navigateToDetails(int idShow);
    void showHttpError(int code);
    Context getContextView();
}
