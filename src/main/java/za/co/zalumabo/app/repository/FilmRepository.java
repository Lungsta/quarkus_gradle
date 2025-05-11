package za.co.zalumabo.app.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import za.co.zalumabo.app.model.Film;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepository {

    @Inject
    JPAStreamer jpaStreamer;

    public static final int PAGE_SIZE = 20;

    public Optional<Film> getFilm(short filmId) {
        return jpaStreamer.stream(Film.class)
                .filter(f -> f.getId().equals(filmId))
                .findFirst();
    }

    public Stream<Film> paged(long page, int minLength) {
        return jpaStreamer.stream(Film.class)
                .filter(film -> film.getLength() >= minLength)
                .sorted((o1, o2) -> o2.getLength() - o1.getLength())
                .skip(page * PAGE_SIZE)
                .limit(PAGE_SIZE);
    }
}
