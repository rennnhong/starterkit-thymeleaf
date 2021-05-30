package indi.rennnhong.staterkit.common.query;

import java.util.Collection;

public interface PageableResult<T> {

    int getSize();

    int getNumber();

    int getTotalPages();

    long getTotalElements();

    Collection<T> getResult();
}
