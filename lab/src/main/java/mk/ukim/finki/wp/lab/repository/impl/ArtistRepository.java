package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    private final List<Artist> artistList = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 5; i++) {
            artistList.add(new Artist((long) i, "Name" + i, "Surname" + i,  "Bio for Artist " + i));
        }
    }

    public List<Artist> findAll(){
        return this.artistList;
    }

    public Optional<Artist> findById(Long id){
        return this.artistList.stream()
                .filter(artist -> artist.getId().equals(id))
                .findFirst();
    }

}
