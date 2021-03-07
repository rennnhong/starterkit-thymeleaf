package indi.rennnhong.staterkit.common.service;

import org.springframework.lang.NonNull;

import java.util.Collection;

public interface Deletable<T,ID> {
    void delete(@NonNull T entity);

    void deleteById(@NonNull ID id);

    void deleteBatch(@NonNull Collection<T> entities);

    void deleteBatchById(@NonNull Collection<ID> ids);
}
