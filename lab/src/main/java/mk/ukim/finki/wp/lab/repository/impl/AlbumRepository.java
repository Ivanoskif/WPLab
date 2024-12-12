//package mk.ukim.finki.wp.lab.repository.impl;
//
//import jakarta.annotation.PostConstruct;
//import mk.ukim.finki.wp.lab.model.Album;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class AlbumRepository {
//
//    private final List<Album> albumList = new ArrayList<>();
//
//
//    @PostConstruct
//    public void init() {
//        for (int i = 1; i <= 5; i++) {
//            albumList.add(new Album("Album" + i, "Genre" + i,  "Year" + i));
//        }
//    }
//
//    public List<Album> findAll(){
//        return this.albumList;
//    }
//
//    public Optional<Album> findById(Long id){
//        return this.albumList.stream()
//                .filter(artist -> artist.getId().equals(id))
//                .findFirst();
//    }
//
//    public Optional<Album> findByName(String name){
//        return this.albumList.stream()
//                .filter(artist -> artist.getName().equals(name))
//                .findFirst();
//    }
//}
