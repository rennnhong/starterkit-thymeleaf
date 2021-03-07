package indi.rennnhong.staterkit.common.web.support;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.domain.Specification;

/**
 * Dt查詢條件
 * @param <T> entity type
 */
public interface DtSpecification<T> extends Specification<T> {
    void setDataTablesInput(DataTablesInput input);
}
