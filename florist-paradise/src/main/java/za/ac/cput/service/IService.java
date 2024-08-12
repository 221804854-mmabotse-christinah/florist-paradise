package za.ac.cput.service;

public interface IService<T, Id> {

    T save(T t);

    T read(Id id);

    T update(T t);

    boolean deleteById(Id id);
}

