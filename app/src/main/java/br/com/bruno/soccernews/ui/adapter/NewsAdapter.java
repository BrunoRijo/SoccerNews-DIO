package br.com.bruno.soccernews.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.bruno.soccernews.databinding.NewsItemBinding;
import br.com.bruno.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favoriteListener;

    public NewsAdapter(List<News> news , FavoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTittle.setText(news.title);
        holder.binding.tvDesc.setText(news.description);

        //Utilizando a biblioteca Picasso para consumir a imagem da API.
        Picasso.get()
                .load(news.image)
                .fit()
                .into(holder.binding.imageView );

        //Implementação da funcionalidade de abrir link
        holder.binding.btnOpenLink.setOnClickListener(view ->{
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(news.link));
                    holder.itemView.getContext().startActivity(i);
                });

        //Implementação da funcionalidade de compatilhar
        holder.binding.btnShareNews.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, news.link);
            holder.itemView.getContext().startActivity(Intent.createChooser(i,"Share via"));
        });

        //Implementação da funcionalidade de favoritar, o evento será instanciado pelo fragment
        holder.binding.btnFavoriteNews.setOnClickListener(view -> {
            news.favorited = !news.favorited;
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
        });

        //Alternando a cor do icone de favorito quando ele for clicado.
        int favoriteColor = news.favorited ?  android.R.color.holo_blue_light : android.R.color.black;
        holder.binding.btnFavoriteNews.setColorFilter(
                holder.itemView.getContext().getResources().getColor(favoriteColor)
        );
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener {
        void onFavorite(News news);
    }
}
