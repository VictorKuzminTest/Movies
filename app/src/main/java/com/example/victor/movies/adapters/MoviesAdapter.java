package com.example.victor.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.victor.movies.R;
import com.example.victor.movies.model.FilmsItem;
import com.example.victor.movies.presenter.Presenter;
import com.example.victor.movies.utils.Sort;
import com.example.victor.movies.views.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<FilmsItem> movieList;
    Context context;
    private Presenter presenter;

    public MoviesAdapter(Presenter presenter, List<FilmsItem> movieList, Context context) {
        this.movieList = movieList;
        Sort.sort(this.movieList);
        addYearItems();
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FilmsItem.TYPE_MOVIE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
            return new MoviesHolder(view);
        } else if (viewType == FilmsItem.TYPE_YEAR) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_year, parent, false);
            return new YearHolder(view);
        } else {
            throw new RuntimeException("The type has to be ZERO or ONE");
        }
    }

    /**
     * Determine which layout to use for the row
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        FilmsItem item = movieList.get(position);
        if (item.getType() == FilmsItem.TYPE_MOVIE) {
            return FilmsItem.TYPE_MOVIE;
        } else if (item.getType() == FilmsItem.TYPE_YEAR) {
            return FilmsItem.TYPE_YEAR;
        } else {
            return -1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case FilmsItem.TYPE_MOVIE:
                initLayoutOne((MoviesHolder) holder, position);
                break;
            case FilmsItem.TYPE_YEAR:
                initLayoutTwo((YearHolder) holder, position);
                break;
            default:
                break;
        }
    }

    private void initLayoutOne(MoviesHolder holder, int position) {
        holder.textViewName.setText(movieList.get(position).getName());
        holder.textViewLocalizedName.setText(movieList.get(position).getLocalizedName());
        holder.textViewRating.setText(Double.toString(movieList.get(position).getRating()));
    }

    private void initLayoutTwo(YearHolder holder, int position) {
        holder.textViewYear.setText(Integer.toString(movieList.get(position).getYear()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    private void addYearItems() {
        int year = 0;
        int increment = 0;
        int size = movieList.size();
        for(int i = 0; i < size; i++){
            if(movieList.get(i + increment).getYear() > year){
                year = movieList.get(i + increment).getYear();
                FilmsItem filmsItem = new FilmsItem();
                filmsItem.setType(FilmsItem.TYPE_YEAR);
                filmsItem.setYear(year);
                movieList.add(i + increment, filmsItem);
                increment++;
            }
        }
    }



    public class MoviesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textView_name)
        TextView textViewName;
        @BindView(R.id.textView_localized_name)
        TextView textViewLocalizedName;
        @BindView(R.id.textView_rating)
        TextView textViewRating;

        public MoviesHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            presenter.showFragment(MainActivity.FRAGMENT_DESCRIPTION, movieList.get(getAdapterPosition()));
        }
    }

    public class YearHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_year)
        TextView textViewYear;

        public YearHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
