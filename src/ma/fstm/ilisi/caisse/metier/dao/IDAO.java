package ma.fstm.ilisi.caisse.metier.dao;

import java.util.Collection;

public interface IDAO<T> {
    boolean create(T obj);
    Collection<T> retreive();
    boolean update(T obj);
    boolean delete(T obj);
}
