package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "RatingServlet", value = "/rateSongs")
public class RatingServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final SongServiceImpl songService;

    public RatingServlet(SpringTemplateEngine springTemplateEngine, SongServiceImpl songService) {
        this.springTemplateEngine = springTemplateEngine;
        this.songService = songService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String s = req.getParameter("trackIdForRating");
        String r = req.getParameter("addRating");

        if(s.isEmpty() || r.isEmpty())
        {
            return;
        }

        Integer songID = Integer.valueOf(s);
        Song song = songService.findByTrackId(String.valueOf(songID));
        int rating = Integer.parseInt(r);


        song.addRating(rating);
        song.calculateRating();

        context.setVariable("songs", songService.listSongs());

        springTemplateEngine.process("listSongs.html", context, resp.getWriter());

    }
}
