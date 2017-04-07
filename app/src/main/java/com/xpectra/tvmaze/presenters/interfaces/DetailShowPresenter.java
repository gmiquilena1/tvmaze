package com.xpectra.tvmaze.presenters.interfaces;

import com.xpectra.tvmaze.views.interfaces.DetailShowView;

/**
 * Created by xpectra on 6/4/2017.
 */
public interface DetailShowPresenter {
    DetailShowPresenter init(DetailShowView view);
    void openImage();
    void openBrowser();
    void loadShow(int id);
}
