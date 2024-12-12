package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepositoryJpa extends JpaRepository<Song, Long>  {
    List<Song> findAllByAlbum_Id(Long albumId);
    Song findByTrackId(String trackId);
    List<Song> findByGenre(String genre);
    List<Song> findByGenreOrderByIdAsc(String genre);
    List<Song> findAllByOrderByIdAsc();

    void deleteById(Long id);

    List<Song> findAllByAlbum_IdOrderByIdAsc(Long albumId);

}
