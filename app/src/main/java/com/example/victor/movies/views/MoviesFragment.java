package com.example.victor.movies.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.victor.movies.R;
import com.example.victor.movies.adapters.MoviesAdapter;
import com.example.victor.movies.model.FilmsItem;
import com.example.victor.movies.model.Response;
import com.example.victor.movies.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoviesFragment extends Fragment implements ContractView{

    private String TAG = "MoviesFragment";

    private static Presenter presenter;
    private Unbinder unbinder;
    RecyclerView.Adapter adapter;
    @BindView(R.id.recycler_view_movies)
    RecyclerView recyclerViewMovies;

    public static MoviesFragment newInstance(Presenter curPresenter){
        MoviesFragment moviesFragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString("title", "moviesFragment");
        moviesFragment.setArguments(args);
        presenter = curPresenter;
        return moviesFragment;
    }

    /**
     * When creating view we ask for movies from server. If the device has no internet connection,
     * activity only shows "error" message. In this app we don't listen and handle for internet connection.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies,
                container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerViewMovies.setHasFixedSize(true);
        setRecyclerView();
        presenter.getMovies();

        return view;
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void displayMovies(Response response) {
        if(response != null) {
            adapter = new MoviesAdapter(presenter, response.getFilms(), getContext());
            recyclerViewMovies.setAdapter(adapter);
        }else {
            Log.d(TAG,"Movies response null");
        }
    }

    @Override
    public void displayError(String s) {

    }

    public void setRecyclerView(){
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showFragment(int id, FilmsItem item) {

    }

    @Override
    public void setFilm(FilmsItem film) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
