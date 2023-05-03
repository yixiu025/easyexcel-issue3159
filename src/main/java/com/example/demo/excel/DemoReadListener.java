package com.example.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DemoReadListener extends AnalysisEventListener<Map<Integer, String>> {

    private List<Map<Integer, String>> dataList = new ArrayList<>();

    private boolean hasNext = true;

    private boolean processed = false;

    @Override
    public void invoke(Map<Integer, String> dataMap, AnalysisContext analysisContext) {
        if(!StringUtils.hasText(dataMap.get(1))) {
            hasNext = false;
            return;
        }

        dataList.add(dataMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 模拟处理剩余的一批数据
        System.out.println("解析到的数据条数: " + dataList.size());
        processed = true;
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        return hasNext;
    }

    public boolean isProcessed() {
        return processed;
    }

}
