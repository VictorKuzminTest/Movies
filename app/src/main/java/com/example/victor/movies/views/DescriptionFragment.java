package com.example.victor.movies.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.victor.movies.R;
import com.example.victor.movies.model.FilmsItem;
import com.example.victor.movies.model.Response;
import com.example.victor.movies.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DescriptionFragment extends Fragment implements ContractView {

    private static Presenter presenter;
    private Unbinder unbinder;
    private FilmsItem filmsItem;
    @BindView(R.id.imageView)
    ImageView imageViewPoster;
    @BindView(R.id.textView_name)
    TextView textViewName;
    @BindView(R.id.textView_year)
    TextView textViewYear;
    @BindView(R.id.textView_rating)
    TextView textViewRating;
    @BindView(R.id.textView_description)
    TextView textViewDescription;
    @BindView(R.id.toolbar)
    Toolbar toolBarBack;
    @BindView(R.id.toolbar_localized_name)
    TextView toolBarLocalizedName;

    public static DescriptionFragment newInstance(Presenter curPresenter) {
        DescriptionFragment descriptionFragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putString("title", "descriptionFragment");
        descriptionFragment.setArguments(args);
        presenter = curPresenter;
        return descriptionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description,
                container, false);

        setHasOptionsMenu(true);

        unbinder = ButterKnife.bind(this, view);

        setViews();

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setViews(){
        if(filmsItem != null){
            Glide.with(getContext()).load(filmsItem.getImageUrl()).into(imageViewPoster);
            textViewName.setText(filmsItem.getName());
            textViewYear.setText(Integer.toString(filmsItem.getYear()));
            textViewRating.setText(Double.toString(filmsItem.getRating()));
            textViewDescription.setText(filmsItem.getDescription());

            toolBarLocalizedName.setText(filmsItem.getLocalizedName());
            toolBarBack.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            toolBarBack.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });

        }
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void displayMovies(Response response) {

    }

    @Override
    public void displayError(String s) {

    }

    @Override
    public void setFilm(FilmsItem film) {
        this.filmsItem = film;
    }

    @Override
    public void showFragment(int id, FilmsItem item) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
