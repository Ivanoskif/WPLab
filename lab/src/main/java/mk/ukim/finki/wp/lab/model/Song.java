package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackId;

    private String title;

    private String genre;

    private int releaseYear;

    @ManyToMany(mappedBy = "songs", fetch = FetchType.EAGER)
    private   List<Artist> performers;

    private float rating;

    @Column(columnDefinition = "int default 0")
    int ratings;

    @Column(columnDefinition = "int default 0")
    private   int numRatings;

    @ManyToOne
    Album album;


    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers, int rating, int numRatings, Album album) {
       this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
        this.ratings= rating;
        this.numRatings = numRatings;
        this.rating = 0;
        //this.id = (long) (Math.random() * 1000);
        this.album = album;

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
       // this.id = (long) (Math.random() * 1000);
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
