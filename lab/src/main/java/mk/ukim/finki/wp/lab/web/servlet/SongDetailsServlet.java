package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongDetailsServlet", value = "/servlet/songDetails")
public class SongDetailsServlet extends HttpServlet {

    private final SongServiceImpl songService;
    private final ArtistService artistService;
    private final SpringTemplateEngine springTemplateEngine;

    public SongDetailsServlet(SongServiceImpl songService, ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String trackId = req.getParameter("trackId");
        String artistId = req.getParameter("artistId");

        if (trackId == null || artistId == null) {
            resp.getWriter().write("Missing trackId or artistId.");
            return;
        }

        Song song = songService.findByTrackId(trackId);
        if (song == null) {
            resp.getWriter().write("Song not found.");
            return;
        }

        Artist artist = artistService.ArtistfindById(Long.valueOf(artistId));
        if (artist != null) {
            songService.addArtistToSong(artist, song);
        } else {
            resp.getWriter().write("Artist not found.");
            return;
        }

        context.setVariable("song", song);
        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }


}
