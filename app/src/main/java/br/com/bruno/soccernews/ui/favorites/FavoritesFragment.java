package br.com.bruno.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import br.com.bruno.soccernews.MainActivity;
import br.com.bruno.soccernews.databinding.FragmentFavoritesBinding;
import br.com.bruno.soccernews.domain.News;
import br.com.bruno.soccernews.ui.adapter.NewsAdapter;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            List<News> favoriteNews = mainActivity.getDb().newsDAO().loadFavoriteNews();
            binding.rvFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvFavoritos.setAdapter(new NewsAdapter(favoriteNews, updateNews -> {
                mainActivity.getDb().newsDAO().save(updateNews);
                loadFavoriteNews();
               }
            ));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}