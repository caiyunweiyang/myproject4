package com.wcy.maven.myproject.handle.handler;

import com.alibaba.excel.write.handler.WorkbookWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;

/**
 * Abstract workbook write handler
 *
 * @author Jiaju Zhuang
 **/
public abstract class AbstractWorkbookWriteHandler implements WorkbookWriteHandler {

    @Override
    public void beforeWorkbookCreate() {

    }

    @Override
    public void afterWorkbookCreate(WriteWorkbookHolder writeWorkbookHolder) {

    }

    @Override
    public void afterWorkbookDispose(WriteWorkbookHolder writeWorkbookHolder) {

    }
}
