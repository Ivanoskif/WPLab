package mk.ukim.finki.wp.lab.Bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final AlbumRepositoryJpa albumRepository;
    private final SongRepositoryJpa songRepository;
    private final ArtistRepositoryJpa artistRepository;

    @Autowired
    public DataInitializer(AlbumRepositoryJpa albumRepository, SongRepositoryJpa songRepository, ArtistRepositoryJpa artistRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 5; i++) {
            albumRepository.save(new Album("Album" + i, "Genre" + i, "Year" + i));
        }

        for (int i = 1; i <= 5; i++) {
            artistRepository.save(new Artist( "Name" + i, "Surname" + i,  "Bio for Artist " + i));
        }


        List<Album> albums = albumRepository.findAll();
        for (int i = 1; i <= 5; i++) {
            Song song = new Song(String.valueOf(i), "Song" + i, "Genre" + i, i, new ArrayList<>(), 0, 0, albums.get(i - 1));
            songRepository.save(song);
        }
    }
}

