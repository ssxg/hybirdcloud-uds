package com.lenovo.ecr.hybirdcloud.uds.common.task;

public interface Task {

    public void excut();

    /**
     * 定时业务任务实现方法
     * @throws Exception
     */
    public void doExcut() throws Exception;


    /**
     * 是否允许多节点并发运行
     * @return
     */
    public boolean isConcurrent();


    /**
     * 是否是单节中配置了多线程执行任务
     * @return
     */
    public boolean isMulitiThread();


    /**
     * 设置任务数据分片信息，当多节点并发取数时，需通过此方法设置分片信息
     * @param dataSliceInfo  每个节点的取数分片信息
     */
    public void setDataSliceInfo(DataSliceInfo dataSliceInfo);

    /**
     * 获取单节点下，任务的并发数目 。当节点下任务为多线程并发时，返回此值
     * @return
     */
    public int getLocalMulitiThreadNum();
}
