package com.example.user.popularmoviesstageone.utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.popularmoviesstageone.Movie;
import com.example.user.popularmoviesstageone.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedActivity extends AppCompatActivity {
    public static final String URL_BASE = "http://image.tmdb.org/t/p/";
    public static final String SIZE = "w154";
    private Movie mMoviesList;
    private int mItemNumber;

    @BindView(R.id.tv_tittle)
    TextView mItemTittle;
    @BindView(R.id.tv_plot_synopsis)
    TextView mItemPlotSynopsis;
    @BindView(R.id.tv_user_rating)
    TextView mUserRating;
    @BindView(R.id.tv_relase_date)
    TextView mReleaseDate;
    @BindView(R.id.iv_thumbnail)
    ImageView mDetailedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent it = getIntent();
        Movie.ResultsBean viewData = it.getExtras().getParcelable("value");
        ButterKnife.bind(this);

        mItemTittle.setText(viewData.getOriginal_title());
        mUserRating.setText("User Rating \n" + String.valueOf(viewData.getVote_average()));
        mReleaseDate.setText("Release date \n" + String.valueOf(viewData.getRelease_date()));
        mItemPlotSynopsis.setText(viewData.getOverview());
        String httpRequestAddress = URL_BASE + SIZE + viewData.getPoster_path();
        Picasso.with(this).load(httpRequestAddress).into(mDetailedItem);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(item));
    }

}
