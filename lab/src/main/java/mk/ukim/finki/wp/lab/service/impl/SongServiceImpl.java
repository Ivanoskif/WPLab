package mk.ukim.finki.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepositoryJpa;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepositoryJpa songRepository;
    private final ArtistRepositoryJpa artistRepository;

    public SongServiceImpl(SongRepositoryJpa songRepository, ArtistRepositoryJpa artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional
    public Artist addArtistToSong(Artist artist, Song song) {

        if (!song.getPerformers().contains(artist)) {
            song.getPerformers().add(artist);
            songRepository.save(song);
        }

        if (!artist.getSongs().contains(song)) {
            artist.getSongs().add(song);
            artistRepository.save(artist);
        }
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

//    @Override
//    public void addRatingToSong(String trackId, int rating) {
//        Song song = findByTrackId(trackId);
//        song.setNumRatings(song.getNumRatings() + 1);
//        song.setRatings(song.getRatings() + rating);
//    }

    public void addRating(Long songId, int ratingNew) {

        Song song = songRepository.findById(songId).orElse(null);

        if (song != null) {

            song.addRating(ratingNew);

            float newRating = song.calculateRating();
            song.setRating(newRating);

            songRepository.save(song);
        } else {
            System.out.println("Song not found");
        }
    }

//    @Override
//    public float calculateSongRating(String trackId) {
//        Song song = findByTrackId(trackId);
//
//        if(song.getNumRatings() == 0){
//            return  0;
//        }
//        else {
//            song.setRating(song.getRatings() / (float)song.getNumRatings());
//            return song.getRating();
//        }
//    }

    @Override
    @Transactional
    public Song getSongById(Long songId)
    {
        return songRepository.findAll().stream()
                .filter(s -> s.getId().equals(songId))
                .findFirst()
                .orElse(null);
    }
    public List<String> listGenres()
    {
        return songRepository.findAll().stream().map(Song::getGenre).distinct().toList();
    }

    public List<Album> listAlbums()
    {
        return songRepository.findAll().stream().map(Song::getAlbum).distinct().toList();
    }

    public List<Song> findSongsByGenre(String genre) {
        return songRepository.findByGenreOrderByIdAsc(genre);
    }
    public List<Song> findAllByAlbumId(Long albumId) {
        return songRepository.findAllByAlbum_IdOrderByIdAsc(albumId);
    }

    public void deleteSongById(Long id)
    {
        songRepository.deleteById(id);
    }

    @Override
    public Song save(Song newSong) {
        return songRepository.save(newSong);
    }

}
