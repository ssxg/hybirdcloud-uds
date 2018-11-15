package com.lenovo.ecr.hybirdcloud.uds.db.util;

import java.util.List;

public class PageModel<E> {
    //结果集
    private List<E> list;
    //查询记录数
    private int totalRecords;
    //每页多少条数据
    private int pageSize;
    //第几页
    private int pageNo;
    /**
     * 总页数
     * @return
     */
    public int getTotalPages(){
        return(totalRecords+pageSize-1)/pageSize;
    }
    /**
     * 取得首页
     * @return
     */
    public int getTopPageNo(){
        return 1;
    }
    public List<E> getList() {
        return list;
    }
    /**
     * 上一页
     * @return
     */
    public int getPreviousPageNo(){
        if(pageNo<=1){
            return 1;
        }
        return pageNo-1;
    }
    /**
     * 下一页
     * @return
     */
    public int getNextPageNo(){
        if(pageNo>=getBottomPageNo()){
            return getBottomPageNo();
        }
        return pageNo+1;
    }
    /**
     * 取得尾页
     * @return
     */
    public int getFirstResult(){ return pageNo*pageSize;}
    public int getBottomPageNo(){
        return getTotalPages();
    }
    public void setList(List<E> list) {
        this.list = list;
    }
    public int getTotalRecords() {
        return totalRecords;
    }
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
