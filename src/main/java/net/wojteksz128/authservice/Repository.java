package net.wojteksz128.authservice;

import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;

public interface Repository<T> {

    T findById(Long id) throws InvalidRequestException, ObjectNotFoundException;

    T save(T obj);

    void update(T app);

    void delete(T app);
}
