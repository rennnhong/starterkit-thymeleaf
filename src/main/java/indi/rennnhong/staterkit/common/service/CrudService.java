package indi.rennnhong.staterkit.common.service;

import org.springframework.lang.NonNull;

public interface CrudService<T, ID> extends Creatable<T, ID>, Deletable<T, ID>, Updatable<T, ID>, Queryable<T> {
    T findOneById(@NonNull ID id);
}
