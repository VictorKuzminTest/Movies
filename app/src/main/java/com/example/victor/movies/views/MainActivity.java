package com.example.victor.movies.views;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.victor.movies.R;
import com.example.victor.movies.model.FilmsItem;
import com.example.victor.movies.model.Response;
import com.example.victor.movies.presenter.MainPresenter;
import com.example.victor.movies.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements ContractView {

    public static final int FRAGMENT_DESCRIPTION = 1;

    private Presenter presenter;
    private Fragment moviesFragment;
    private Fragment descriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(moviesFragment == null){
                moviesFragment = MoviesFragment.newInstance(presenter);
            }
            fragmentTransaction.add(R.id.fragmentContainer, moviesFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(Response movieResponse) {
        ((ContractView)moviesFragment).displayMovies(movieResponse);
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }


    @Override
    public void showFragment(int id, FilmsItem item) {
        goToDescriptionFragment(item);
    }

    @Override
    public void setFilm(FilmsItem film) {

    }

    private void goToDescriptionFragment(FilmsItem item) {
        if(descriptionFragment == null){
            descriptionFragment = DescriptionFragment.newInstance(presenter);
        }
        ((DescriptionFragment)descriptionFragment).setFilm(item);
        setFragment(descriptionFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
