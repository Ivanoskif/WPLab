//package mk.ukim.finki.wp.lab.repository.impl;
//
//import jakarta.annotation.PostConstruct;
//import mk.ukim.finki.wp.lab.model.Album;
//import mk.ukim.finki.wp.lab.model.Artist;
//import mk.ukim.finki.wp.lab.model.Song;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class SongRepository {
//
//    private final List<Song> songList = new ArrayList<>();
//    private final AlbumRepository albumRepository;
//
//    public SongRepository(AlbumRepository albumRepository) {
//        this.albumRepository = albumRepository;
//    }
//
//    @PostConstruct
//    public void init() {
//
//        List<Album> albumList = albumRepository.findAll();
//
//        for (int i = 1; i <= 5; i++) {
//            Song song = new Song(String.valueOf(i), "Song"+i, "Genre"+i, i, new ArrayList<>(), 0, 0);
//            song.setAlbum(albumList.get(i-1));
//            songList.add(song);
//        }
//    }
//
//    public List<Song> findAll(){
//        return this.songList;
//    }
//
//    public Song findByTrackId(String trackId){
//        return this.songList.stream()
//                .filter(song -> song.getTrackId().equals(trackId))
//                .findFirst().orElse(null);
//    }
//
//    public Artist addArtistToSong(Artist artist, Song song){
//
//        if (!song.getPerformers().contains(artist)) {
//            song.getPerformers().add(artist);
//        }
//        return artist;
//    }
//
//    public void deleteById(Long id) {
//        songList.removeIf(i -> i.getId().equals(id));
//    }
//
//    public Song save(Song newSong) {
//        Optional<Song> existingSong = songList.stream()
//                .filter(song -> song.getTrackId().equals(newSong.getTrackId()))
//                .findFirst();
//
//        if (existingSong.isPresent()) {
//            Song song = existingSong.get();
//            song.setTitle(newSong.getTitle());
//            song.setGenre(newSong.getGenre());
//            song.setReleaseYear(newSong.getReleaseYear());
//            song.setPerformers(newSong.getPerformers());
//            song.setAlbum(newSong.getAlbum());
//            return song;
//        } else {
//            songList.add(newSong);
//            return newSong;
//        }
//    }
//
//    public List<Song> findByGenre(String genre){
//        return this.songList.stream()
//                .filter(song -> song.getGenre().equals(genre)).toList();
//    }
//
//
//}
