package com.practice.filmorate.storage;

import com.practice.filmorate.exceptions.NotFoundException;
import com.practice.filmorate.exceptions.ValidationException;
import com.practice.filmorate.model.Film;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    Map<Integer, Film> films = new HashMap<>();
    private int uniqueId = 1;

    @Override
    public List<Film> findAll() {
        log.debug("Получение списка фильмов");
        return new ArrayList<>(films.values());
    }

    @Override
    public Optional<Film> findById(int id) {
        if (!films.containsKey(id)) {
            throw new NotFoundException("Пользователь не найден");
        }
        log.debug("Получение фильма");
        return Optional.of(films.get(id));
    }

    @Override
    public Film create(Film film) {
        film.setId(uniqueId++);
        films.put(film.getId(), film);
        log.debug("Фильм добавлен");
        return film;
    }

    @Override
    public Film update(Film film) {

        films.put(film.getId(), film);
        log.debug("Фильм обновлен");
        return film;
    }

    @Override
    public Film remove(int id) {
        if (!films.containsKey(id)) {
            throw new NotFoundException("Пользователь не найден");
        }
        log.debug("Удаление фильма");
        return films.remove(id);
    }

    @Override
    public Map<Integer, Film> getFilms() {
        return films;
    }
}
