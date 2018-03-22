package leeco.hellogithub.sqlite.core;

/**
 * Created by zhangxin17 on 2018/3/20.
 */
public class BaseSqlDao<T> implements ISqlDao<T> {
    @Override
    public boolean addDef() {
        return false;
    }

    @Override
    public boolean add(T info) {
        return false;
    }

    @Override
    public boolean delete(T info) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(T info) {
        return false;
    }

    @Override
    public T query(int id) {
        return null;
    }

    @Override
    public T query(long id) {
        return null;
    }

    @Override
    public void close() {

    }
}
