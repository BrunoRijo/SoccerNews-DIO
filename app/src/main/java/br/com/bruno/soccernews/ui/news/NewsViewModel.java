package br.com.bruno.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.bruno.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        List<News> news = new ArrayList<News>();
        news.add(new News("Flamengo vence por 30 a 0", "Vitória arrasadora do Fla"));
        news.add(new News("Palmeiras perde mais uma vez e segue sem mundial", "Palmeiras perde!"));
        news.add(new News("Você conhece? Ninguém conhece esse time mas ele existe!", "O time da camisa azul e branco"));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}