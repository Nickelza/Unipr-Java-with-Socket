package it.unipr.ingegneria.api;

public interface IObservable  <T> {
    void addObserver(T t);
    void removeObserver(T t);

}
