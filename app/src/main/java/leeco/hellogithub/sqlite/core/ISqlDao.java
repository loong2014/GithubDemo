package leeco.hellogithub.sqlite.core;

public interface ISqlDao<T> {

    boolean addDef();

    boolean add(T info);

    boolean delete(T info);

    boolean delete(int id);

    boolean update(T info);

    T query(int id);

    T query(long id);

    void close();
}
