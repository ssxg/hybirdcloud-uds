package com.lenovo.ecr.hybirdcloud.uds.common.task;

public class DataSliceInfo {
    /**
     * 总分片数
     */
    private Integer size;

    /**
     * 分片下标
     */
    private Integer index;


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
