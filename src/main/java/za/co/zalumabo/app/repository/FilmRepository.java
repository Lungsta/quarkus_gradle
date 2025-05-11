package za.co.zalumabo.app.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import za.co.zalumabo.app.model.Film;

import java.util.Optional;

@ApplicationScoped
public class FilmRepository {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Film> getFilm(short filmId) {
        return jpaStreamer.stream(Film.class)
                .filter(f -> f.getId().equals(filmId))
                .findFirst();
    }
}
