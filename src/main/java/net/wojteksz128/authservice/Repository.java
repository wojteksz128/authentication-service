package net.wojteksz128.authservice;

import java.util.Optional;

public interface Repository<T> {

    Optional<T> findById(Long id);

    T save(T obj);

    void update(T app);

    void delete(T app);
}
