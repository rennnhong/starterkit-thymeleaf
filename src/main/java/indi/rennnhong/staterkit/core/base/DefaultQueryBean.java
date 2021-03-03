package indi.rennnhong.staterkit.core.base;

import java.util.Objects;

/*
    todo 設計查詢參數父類
 */
public class DefaultQueryBean implements QueryBean {
    private Integer page;
    private Integer size;
    private Integer sort;

    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer DEFAULT_SIZE = 20;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public int page() {

        return Objects.isNull(page) ? DEFAULT_PAGE : page;
    }

    @Override
    public int size() {
        return Objects.isNull(page) ? DEFAULT_SIZE : page;
    }

}
