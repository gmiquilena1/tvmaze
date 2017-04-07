package com.xpectra.tvmaze.views.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xpectra.tvmaze.R;
import com.xpectra.tvmaze.helpers.Constants;
import com.xpectra.tvmaze.helpers.EventType;
import com.xpectra.tvmaze.helpers.Utils;
import com.xpectra.tvmaze.schemas.Show;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xpectra on 29/3/2017.
 */
public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ShowsHolder> {


    private List<Show> mLista;
    private Context mContext;
    private int mModeList;
    private EventBus mBus = EventBus.getDefault();

    public ShowsAdapter(Context context,List<Show> lista,int modeList){
        mLista = lista;
        mContext = context;
        mModeList = modeList;
    }

    public void setModeList(int modeList){
        mModeList = modeList;

    }

    @Override
    public ShowsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;

        switch (mModeList){
            case Constants.VIEW_LIST:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_row, parent, false);
                break;
            case Constants.VIEW_GRID:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_grid_row, parent, false);
                break;
        }

        return new ShowsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowsHolder holder, int position) {

        final Show show = mLista.get(position);

        //Image
        Picasso.with(mContext).load(show.getImage().getMedium()).into(holder.imageView);

        //Title
        holder.title.setText(show.getName());

        //Schedule
        holder.schedule.setText(Utils.getListString(show.getSchedule().getDays())+" - "+Utils.parseHours(show.getSchedule().getTime()));

        //Raiting
        if(show.getRating().getAverage()!=null)
            holder.raiting.setRating((float) (show.getRating().getAverage()/2));
        else
            holder.raiting.setRating(0);

        //Duration
        if(show.getRuntime()>=60)
            holder.duration.setText(mContext.getResources().getString(R.string.duration)+ " " + Utils.parseMinHoras(show.getRuntime()));
        else
            holder.duration.setText(mContext.getResources().getString(R.string.duration)+ " " + show.getRuntime()+"min");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBus.post(new Object[] {EventType.LAUNCH_ACTIVITY_DETALLE,show.getId()});
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }

    public class ShowsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.schedule)
        TextView schedule;

        @BindView(R.id.duration)
        TextView duration;

        @BindView(R.id.imageView)
        ImageView imageView;

        @BindView(R.id.rating)
        RatingBar raiting;

        @BindView(R.id.card_view)
        CardView cardView;

        public ShowsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
