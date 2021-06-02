package indi.rennnhong.staterkit.common.web.support;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Dt查詢條件
 *
 * @param <T> entity type
 */
public interface DtSpecification<T, E> extends Specification<T> {
    void setDataTablesInput(DataTablesInput input);

    List<E> setDataResults(List<T> results);
}
