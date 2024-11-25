package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongController(SongService songService, SongRepository songRepository, AlbumRepository albumRepository) {
        this.songService = songService;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("songs", songService.listSongs());
        return "listSongs";
    }

    @GetMapping("/edit/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        Song song = songRepository.findAll().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (song == null) {
            return "redirect:/songs?error=SongNotFound";
        }

        model.addAttribute("song", song);
        model.addAttribute("albums", albumRepository.findAll());
        return "editSong";
    }

    @PostMapping("/edit/{songId}")
    public String updateSong(@PathVariable Long songId,
                             @RequestParam String title,
                             @RequestParam String trackId,
                             @RequestParam String genre,
                             @RequestParam int releaseYear,
                             @RequestParam Long albumId) {

        Song song = songRepository.findAll().stream()
                .filter(s -> s.getId().equals(songId))
                .findFirst()
                .orElse(null);

        if (song == null) {
            return "redirect:/songs?error=SongNotFound";
        }

        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);

        Album album = albumRepository.findAll().stream()
                .filter(a -> a.getId().equals(albumId))
                .findFirst()
                .orElse(null);

        if (album != null) {
            song.setAlbum(album);
        }

        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songRepository.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/add")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumRepository.findAll()); // Сите албуми за избор
        return "addSong"; // HTML страница за додавање песна
    }


    @PostMapping("/add")
    public String saveSong(@RequestParam String title,
                           @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam Long albumId) {

        Album album = albumRepository.findAll().stream()
                .filter(a -> a.getId().equals(albumId))
                .findFirst()
                .orElse(null);

        if (album == null) {
            return "redirect:/songs?error=AlbumNotFound";
        }

        Song newSong = new Song(title, trackId, genre, Integer.parseInt(releaseYear), album);
        songRepository.save(newSong);

        return "redirect:/songs";
    }
}

