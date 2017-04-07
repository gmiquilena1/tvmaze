package com.xpectra.tvmaze.models.api;

import android.util.Log;

import com.xpectra.tvmaze.models.interfaces.ApiListener;
import com.xpectra.tvmaze.models.interfaces.TvMaze;
import com.xpectra.tvmaze.schemas.Show;
import com.xpectra.tvmaze.service.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xpectra on 6/4/2017.
 */
public class TvMazeImpl implements TvMaze {

    private static final String TAG = TvMazeImpl.class.getSimpleName();
    ApiListener mListener;

    @Override
    public TvMaze init(ApiListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public void getAllShows() {
        WebService.getAllShows().enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                switch (response.code()) {
                    case WebService.CODE_200_SUCCESSFUL:
                    case WebService.CODE_201_SUCCESSFUL:
                        mListener.successApi(response.body());
                        break;
                    default:
                        mListener.errorApi(response.code());
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                Log.e(TAG, t.toString());
                mListener.errorApi(0);
            }
        });
    }

    @Override
    public void getShow(int id) {
        WebService.getShow(id).enqueue(new Callback<Show>() {
            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {

                switch (response.code()) {
                    case WebService.CODE_200_SUCCESSFUL:
                    case WebService.CODE_201_SUCCESSFUL:
                        mListener.successApi(response.body());
                        break;
                    default:
                        mListener.errorApi(response.code());
                        break;
                }
            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {
                Log.e(TAG, t.toString());
                mListener.errorApi(0);
            }
        });
    }
}
