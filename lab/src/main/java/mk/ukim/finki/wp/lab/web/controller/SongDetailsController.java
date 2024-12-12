package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {
    private final SongServiceImpl songService;
    private final ArtistService artistService;

    public SongDetailsController(SongServiceImpl songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getSongDetailPage(@RequestParam(required = false) String error,
                                    @RequestParam() String trackId,
                                    @RequestParam() String artistId,
                                    Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        Song song = songService.findByTrackId(trackId.split(",")[0]);

        if (song == null) {
            return "redirect:/songDetails?error=SongNotFound";
        }

        Artist artist = artistService.ArtistfindById(Long.valueOf(artistId));

        if (artist != null) {
            songService.addArtistToSong(artist, song);
        } else {
            return "redirect:/songDetails?error=SongNotFound";
        }

        model.addAttribute("song", song);


        return "songDetails";
    }

}
