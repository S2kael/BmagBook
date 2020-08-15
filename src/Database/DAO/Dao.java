package Database.DAO;

import java.util.Optional;

public interface Dao<T>{
    Optional<T> get(int id);
    int insert(T t);
    int update(T t);
}
