package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, @RequestParam(required = false) String albumId, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("albums", albumService.findAll());

//        List<Album> albumi = albumService.findAll();
//
//        for (Album album:
//             albumi){
//            System.out.println("Name: " + album.getName());
//            System.out.println("Id: " + album.getId());
//             }
//
//        System.out.println("Album Id: " + albumId);


        if (albumId!=null && !albumId.equals("All songs"))//&& !albumId.equals("All songs")
        {
            model.addAttribute("songs", songService.findAllByAlbumId(Long.valueOf(albumId)));
        }
        else
        {
            model.addAttribute("songs", songService.listSongs());
        }

        return "listSongs";
    }

    @GetMapping("/edit/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {

        Song song = songService.getSongById(id);

        if (song == null) {
            return "redirect:/songs?error=SongNotFound";
        }

        model.addAttribute("song", song);
        model.addAttribute("albums", albumService.findAll());
        return "editSong";
    }

    @PostMapping("/edit/{songId}")
    public String updateSong(@PathVariable Long songId,
                             @RequestParam String title,
                             @RequestParam String trackId,
                             @RequestParam String genre,
                             @RequestParam int releaseYear,
                             @RequestParam Long albumId) {

        //System.out.println("Received albumId: " + albumId);

        Song song = songService.getSongById(songId);

        if (song == null) {
            return "redirect:/songs?error=SongNotFound";
        }

        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);

        Album album = albumService.getAlbumById(albumId);

        if (album == null) {
            return "redirect:/songs?error=AlbumNotFound";
        }

        song.setAlbum(album);
        songService.save(song);

        return "redirect:/songs";
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);
        return "redirect:/songs";
    }

    @GetMapping("/add")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumService.findAll());
        return "addSong";
    }


    @PostMapping("/add")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam Long albumId) {

        Album album = albumService.getAlbumById(albumId);

        if (album == null) {
            return "redirect:/songs?error=AlbumNotFound";
        }

        Song newSong = new Song(title, trackId, genre, Integer.parseInt(releaseYear), album);
        songService.save(newSong);

        return "redirect:/songs";
    }


    @PostMapping("/addRating")
    public String addRating(@RequestParam Long trackIdForRating, @RequestParam int addRating, Model model) {

        Song song = songService.getSongById(trackIdForRating);

        if (song == null) {
            return "redirect:/songs?error=SongNotFound";
        }

        songService.addRating(trackIdForRating, addRating);

        return "redirect:/songs";
    }

}

