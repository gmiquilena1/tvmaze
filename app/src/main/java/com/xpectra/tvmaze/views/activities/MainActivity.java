package com.xpectra.tvmaze.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xpectra.tvmaze.R;
import com.xpectra.tvmaze.helpers.Constants;
import com.xpectra.tvmaze.helpers.EventType;
import com.xpectra.tvmaze.presenters.ShowListPresenterIpml;
import com.xpectra.tvmaze.presenters.interfaces.ShowListPresenter;
import com.xpectra.tvmaze.views.adapters.ShowsAdapter;
import com.xpectra.tvmaze.helpers.CustomMessage;
import com.xpectra.tvmaze.service.WebService;
import com.xpectra.tvmaze.views.interfaces.ShowListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ShowListView {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Menu mMenu;
    private MaterialDialog mDialog;

    private ShowListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        EventBus.getDefault().register(this);

        mDialog = CustomMessage.get(this, CustomMessage.TYPE_PROGRESSBAR, "Cargando Shows").build();

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new ShowListPresenterIpml().init(this);

        mPresenter.loadShows();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_list_sort:
                mPresenter.sortListShow();
                break;
            case R.id.action_list_view:
                mRecyclerView.setLayoutManager(mPresenter.changeViewShow());
                mRecyclerView.setAdapter(mRecyclerView.getAdapter());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(Object[] args) {

        int event = (int) args[0];
        Intent intent = null;

        switch (event) {
            case EventType.LAUNCH_ACTIVITY_DETALLE:
                navigateToDetails((int) args[1]);
                break;
        }
    }


    @Override
    public void showProgressDialog() {
        mDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mDialog.hide();
    }

    @Override
    public void changeIconSort(int mode) {
        switch (mode){
            case Constants.SORT_ASC:
                mMenu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_action_descending_sorting));
                break;
            case Constants.SORT_DES:
                mMenu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_action_ascending_sorting));
                break;
        }
    }

    @Override
    public void changeIconViewShow(int mode) {
        switch (mode){
            case Constants.VIEW_GRID:
                mMenu.getItem(1).setIcon(getResources().getDrawable(R.drawable.ic_action_list));
                break;
            case Constants.VIEW_LIST:
                mMenu.getItem(1).setIcon(getResources().getDrawable(R.drawable.ic_action_grid));
                break;
        }
    }

    @Override
    public void loadListShows(ShowsAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void navigateToDetails(int idShow) {
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("idShow", idShow);
        startActivity(intent);
    }

    @Override
    public void showHttpError(int code) {
        WebService.handlerRequestError(getApplicationContext(),code);
    }

    @Override
    public Context getContextView() {
        return this;
    }
}
