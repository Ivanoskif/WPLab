package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.impl.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "АrtistServlet", value = "/artist")
public class АrtistServlet  extends HttpServlet {

    private final ArtistServiceImpl artistService;
    private final SongServiceImpl songService;
    private final SpringTemplateEngine springTemplateEngine;

    public АrtistServlet(ArtistServiceImpl artistService, SongServiceImpl songService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.songService = songService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("artists", artistService.listArtists() );
        String songid = req.getParameter("trackId");
        context.setVariable("songid", songid);

//        Integer songidRate = Integer.valueOf(req.getParameter("trackIdForRating"));
//        Integer rating = Integer.valueOf(req.getParameter("addRating"));

//        if(songidRate != null && rating != null) {
//            Song song = songService.findByTrackId(String.valueOf(songidRate));
//
//            song.addRating(rating);
//            song.calculateRating();
//        }

        springTemplateEngine.process("artistsList.html", context, resp.getWriter());


    }
}
