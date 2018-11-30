package com.example.victor.movies.presenter;

import android.util.Log;

import com.example.victor.movies.model.FilmsItem;
import com.example.victor.movies.model.Response;
import com.example.victor.movies.network.MoviesInterface;
import com.example.victor.movies.network.NetworkClient;
import com.example.victor.movies.views.ContractView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements Presenter {

    ContractView contractView;
    private String TAG = "MainPresenter";

    public MainPresenter(ContractView contractView) {
        this.contractView = contractView;
    }

    @Override
    public void getMovies() {
        getObservable().subscribeWith(getObserver());
    }

    @Override
    public void showFragment(int id, FilmsItem item) {
        contractView.showFragment(id, item);
    }

    public Observable<Response> getObservable() {
        return NetworkClient.getRetrofit().create(MoviesInterface.class)
                .getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Response> getObserver() {
        return new DisposableObserver<Response>() {

            @Override
            public void onNext(@NonNull Response movieResponse) {
                Log.d("onNext", "movieResponse = " + movieResponse.getFilms());
                contractView.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                contractView.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }
}
