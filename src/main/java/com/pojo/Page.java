package com.pojo;


import java.util.List;

/**
 * @Description Page是分页的对象模型, 存储一页数据
 * @Author IceCube
 * @Date 2020/12/12 20:46
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4; //默认页大小
    public static final Integer MAX_PAGE_SIZE = 30; //最大页大小
    private Integer pageTotal; //总页数
    private Integer pageTotalCount; //总记录数
    private Integer pageNo; //当前页码
    private Integer pageSize = PAGE_SIZE; //当前页的记录数
    private List<T> items; //当前页的数据
    private String url; //分页栏的请求地址 (相对于工程路径)


    @Override
    public String toString() {
        return "Page{" +
                "pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageTotal == null){
            throw new RuntimeException("请先设置pageTotal");
        }
        //必须先设置pageTotal, 再设置pageNo, 避免空指针异常
        if (pageNo > 0 && pageNo <= pageTotal) {
            this.pageNo = pageNo;
        } else {
            this.pageNo = 1;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize > 0 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = PAGE_SIZE;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
