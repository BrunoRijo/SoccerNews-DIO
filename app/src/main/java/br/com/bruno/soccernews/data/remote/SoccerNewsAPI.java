package br.com.bruno.soccernews.data.remote;

import java.util.List;

import br.com.bruno.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsAPI {

    @GET("news.json")
    Call<List<News>> getNews();
}
