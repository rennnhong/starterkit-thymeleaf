package indi.rennnhong.staterkit.core.base.service;

import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;

public interface Creatable<T, ID> {
    ID create(T entity);

    List<ID> createBatch(@NonNull Collection<T> entities);
}
