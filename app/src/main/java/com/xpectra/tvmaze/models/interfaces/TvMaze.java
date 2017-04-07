package com.xpectra.tvmaze.models.interfaces;

/**
 * Created by xpectra on 6/4/2017.
 */
public interface TvMaze {
    TvMaze init(ApiListener listener);
    void getAllShows();
    void getShow(int id);
}
