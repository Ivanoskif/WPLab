package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;


    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String listArtists(@RequestParam Long trackId,@RequestParam(required = false) String rating, Model model) {

//        //rating = rating.split(",")[0];
//
//        if(rating != null && !rating.isEmpty())
//        {
//            Song song = songService.findByTrackId(trackId); // .split(",")[0]
//
//            if (song == null) {
//                return "redirect:/songs?error=SongNotFound";
//            }
//
//            song.addRating(Integer.parseInt(rating));
//            song.calculateRating();
//
//            return "redirect:/songs";
//        }
//        else {
//            List<Artist> artists = artistService.listArtists();
//            model.addAttribute("artists", artists);
//
//            model.addAttribute("songid", trackId);
//
//            return "artistsList";
//        }

        List<Artist> artists = artistService.listArtists();

        model.addAttribute("artists", artists);

        model.addAttribute("songid", trackId);

        return "artistsList";

    }

    @PostMapping("/add")
    public String addArtistToSong(@RequestParam Long trackId, @RequestParam Long artistId) {
        Song song = songService.getSongById(trackId);
        Artist artist = artistService.ArtistfindById(artistId);

        if (song != null && artist != null) {
            songService.addArtistToSong(artist, song);
        }

        return "redirect:/artist?trackId=" + trackId;
    }
}

