package br.com.bruno.soccernews.domain;

public class News {
    private String description;
    private String tittle;

    public News(String description, String tittle) {
        this.description = description;
        this.tittle = tittle;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
