package br.com.bruno.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import br.com.bruno.soccernews.MainActivity;
import br.com.bruno.soccernews.databinding.FragmentNewsBinding;
import br.com.bruno.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel NewsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding =  FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, updatedNews -> {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null ) mainActivity.getDb().newsDAO().save(updatedNews);
            }));
        });
        NewsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state){
                case DOING:
                    break; //TODO incluir swiperefreshLayout (loading)
                case DONE:
                    break; //TODO finalizar swiperefreshLayout (leading)
                case ERROR:
                    //TODO finalizar swiperefreshLayout (leading)
                    //TODO mensagem de erro genérica
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}