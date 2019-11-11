package annotation;

import java.util.List;

public interface EntityManager <T extends Entity> {
    void save(T object);
    List<T> getEntityListBySql(String sqlQuery);
    List<T> getEntityListByPage(Integer from, Integer limit, Class clazz);
    T gerEntityById(Integer id,Class clazz);
    void delete(T object);
}
