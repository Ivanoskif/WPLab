package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private   List<Artist> performers;
    private   float rating;
    int ratings;
    private   int numRatings;
    private Long id;
    Album album;


    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers, int rating, int numRatings) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
        this.ratings= rating;
        this.numRatings = numRatings;
        this.rating = 0;
        this.id = (long) (Math.random() * 1000);
        this.album = null;

    }

    public Song(String title, String trackId, String genre, int releaseYear, Album album){
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.ratings= 0;
        this.numRatings = 0;
        this.rating = 0;
        this.id = (long) (Math.random() * 1000);
        this.album = album;

    }

    public void addRating(int ratingNew) {
        this.numRatings = this.numRatings + 1;
        this.ratings = this.ratings + ratingNew;
    }


    public float calculateRating() {
        if(this.numRatings == 0){
            return  0;
        }
        else {
            this.rating = this.ratings / (float)this.numRatings;
            return this.rating;
        }
    }

}
