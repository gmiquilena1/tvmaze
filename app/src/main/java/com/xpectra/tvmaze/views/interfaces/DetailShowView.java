package com.xpectra.tvmaze.views.interfaces;

import com.xpectra.tvmaze.schemas.Show;

/**
 * Created by xpectra on 6/4/2017.
 */
public interface DetailShowView {
    void loadDataShow(Show show);
    void navigateToImage(String url);
    void navigateToBrowser(String url);
    void showHttpError(int code);
}
