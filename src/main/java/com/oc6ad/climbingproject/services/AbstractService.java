package com.oc6ad.climbingproject.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public abstract class AbstractService<T, ID extends Long> implements CrudService<T, ID> {
    protected abstract CrudRepository<T, ID> getRepository();

    @Override
    public <S extends T> S save(S var1) {
        return getRepository().save(var1);
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> var1) {
        return getRepository().saveAll(var1);
    }

    @Override
    public Optional<T> findById(ID var1) {
        return getRepository().findById(var1);
    }

    @Override
    public boolean existsById(ID var1) {
        return getRepository().existsById(var1);
    }

    @Override
    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> var1) {
        return getRepository().findAllById(var1);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public void deleteById(ID var1) {
        getRepository().deleteById(var1);
    }

    @Override
    public void delete(T var1) {
        getRepository().delete(var1);
    }

    @Override
    public void deleteAll(Iterable<? extends T> var1) {
        getRepository().deleteAll(var1);
    }

    @Override
    public void deleteAll() {
        getRepository().deleteAll();
    }
}
