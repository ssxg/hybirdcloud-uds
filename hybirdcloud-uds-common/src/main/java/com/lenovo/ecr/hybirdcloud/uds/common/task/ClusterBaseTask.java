package com.lenovo.ecr.hybirdcloud.uds.common.task;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClusterBaseTask implements Task {

    @Autowired
    ClusterTaskExcutor excutor;

    /**
     * 暴露在外的方法
     * @throws Exception
     */
    @Override
    public void excut() {
        try {
            excutor.excute(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void setDataSliceInfo(DataSliceInfo dataSliceInfo) {

    }

    @Override
    public int getLocalMulitiThreadNum() {
        return 0;
    }
}
