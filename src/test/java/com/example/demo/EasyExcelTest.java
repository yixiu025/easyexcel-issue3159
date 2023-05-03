package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.demo.excel.DemoReadListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EasyExcelTest {

    @Test
    void normallyQuitTest() {
        // 没有提前退出读取, 执行了 doAfterAllAnalysed 在内部完成了对数据的操作
        DemoReadListener normallyQuitReadListener = new DemoReadListener();
        EasyExcel.read(this.getClass().getResourceAsStream("/测试导入文件-结尾无数据.xlsx"))
                .excelType(ExcelTypeEnum.XLSX)
                .registerReadListener(normallyQuitReadListener)
                .headRowNumber(1)
                .doReadAll();
        Assertions.assertTrue(normallyQuitReadListener.isProcessed());
    }

    @Test
    void earlyQuitTest() {
        // 提前退出读取, 没执行 doAfterAllAnalysed 没有在内部完成对数据的操作
        DemoReadListener earlyQuitReadListener = new DemoReadListener();
        EasyExcel.read(this.getClass().getResourceAsStream("/测试导入文件-结尾有数据.xlsx"))
                .excelType(ExcelTypeEnum.XLSX)
                .registerReadListener(earlyQuitReadListener)
                .headRowNumber(1)
                .doReadAll();
        Assertions.assertTrue(earlyQuitReadListener.isProcessed());
    }

}
