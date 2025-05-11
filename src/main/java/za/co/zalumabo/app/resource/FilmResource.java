package za.co.zalumabo.app.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import za.co.zalumabo.app.repository.FilmRepository;

import java.util.stream.Collectors;

@Path("/")
public class FilmResource {

    @Inject
    FilmRepository filmRepository;

    @GET
    @Path("/helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World";
    }

    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(@PathParam("filmId") short filmId) {
        return filmRepository.getFilm(filmId).isPresent()
                ? filmRepository.getFilm(filmId).get().getTitle() : "Film Not Found";
    }

    @GET
    @Path("/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPagedFilms(@PathParam("page") int page, @PathParam("minLength") int minLength) {
        return filmRepository.paged(page, minLength)
                .map(film -> String.format("%s (%d min)", film.getTitle(), film.getLength()))
                .collect(Collectors.joining("\n"));
    }
}
