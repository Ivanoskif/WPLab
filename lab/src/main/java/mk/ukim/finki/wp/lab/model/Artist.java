package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    private String firstName;

    private String lastName;

    private  String bio;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Song> songs;

    public Artist(String firstName, String lastName, String bio) {
        //this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }
}
