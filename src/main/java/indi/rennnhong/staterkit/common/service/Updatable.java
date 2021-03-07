package indi.rennnhong.staterkit.common.service;

import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;

public interface Updatable<T, ID> {

    ID update(@NonNull T entity);

    List<ID> update(@NonNull Collection<T> entities);
}
