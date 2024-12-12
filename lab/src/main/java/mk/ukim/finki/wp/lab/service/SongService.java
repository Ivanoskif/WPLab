package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);

//    void addRating(String trackId, int ratingNew);
//    float calculateSongRating(String trackId);

     void addRating(Long songId, int ratingNew);
    Song getSongById(Long songId);
    List<String> listGenres();
    List<Song> findSongsByGenre(String genre);
    void deleteSongById(Long id);

    Song save(Song newSong);
    List<Song> findAllByAlbumId(Long albumId);
}
