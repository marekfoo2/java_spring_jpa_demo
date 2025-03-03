package pl.mfconsulting.java.demo.springjpa.repository.common;

import java.util.List;

public interface HibernateRepository<T> {
    /**
     * Too danger to be used. Performance.
     *
     * @return Throws UnsupportedOperationException
     */
    @Deprecated
    List<T> findAll();

    /**
     * Too danger to be used. Performance.
     *
     * @return Throws UnsupportedOperationException
     */
    @Deprecated
    <S extends T> S save(S entity);

    /**
     * Too danger to be used. Performance.
     *
     * @return Throws UnsupportedOperationException
     */
    @Deprecated
    <S extends T> List<S> saveAll(Iterable<S> entities);

    /**
     * Too danger to be used. Performance.
     *
     * @return Throws UnsupportedOperationException
     */
    @Deprecated
    <S extends T> S saveAndFlush(S entity);

    /**
     * Too danger to be used. Performance.
     *
     * @return Throws UnsupportedOperationException
     */
    @Deprecated
    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);

    // Methods to save only newly created entities

    /**
     * Saves newly created entity, which does not exist in db yet.
     * In Transaction it is not necessary to call it, all done automatically.
     */
    <S extends T> S persist(S entity);

    <S extends T> S persistAndFlush(S entity);

    <S extends T> List<S> persistAll(Iterable<S> entities);

    <S extends T> List<S> peristAllAndFlush(Iterable<S> entities);

    // Methods to propagate detached entity state changes if they (changes) really
    // exists

    /**
     * Should be called when merge the state of the given (detached) entity into the
     * current persistence context. Entity already exists in db.
     * In Transaction it is not necessary to call it, all done automatically.
     */
    <S extends T> S merge(S entity);

    <S extends T> S mergeAndFlush(S entity);

    <S extends T> List<S> mergeAll(Iterable<S> entities);

    <S extends T> List<S> mergeAllAndFlush(Iterable<S> entities);

    // Update methods are meant to force the detached entity state changes

    /**
     * Should be called on detached instance containing updated state. It force
     * update not merges!
     * The current state of entity in persistence context will be overwirten by
     * state of detached instance.
     * In Transaction it is not necessary to call it, all done automatically.
     */
    <S extends T> S update(S entity);

    <S extends T> S updateAndFlush(S entity);

    <S extends T> List<S> updateAll(Iterable<S> entities);

    <S extends T> List<S> updateAllAndFlush(Iterable<S> entities);
}
