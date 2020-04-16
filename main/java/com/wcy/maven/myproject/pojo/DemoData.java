package com.wcy.maven.myproject.pojo;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.wcy.maven.myproject.enums.DemoEnum;
import com.wcy.maven.myproject.util.DemoEnumConvert;
import lombok.Data;

import java.util.Date;

/**
 * 基础数据类：DemoData 就是一个普通的 POJO 类，上面使用 ExayExcel 相关注解，ExayExcel 将会通过反射读取字段类型以及相关注解，然后直接生成 Excel 。
 *
 * @author Jiaju Zhuang
 **/
@ContentRowHeight(30)// 表体行高
@HeadRowHeight(20)// 表头行高
@ColumnWidth(35)// 列宽
@Data
public class DemoData {
    /**
     * 单独设置该列宽度
     */
    @ColumnWidth(50)
    @ExcelProperty("字符串标题")//指定当前字段对应excel中的那一列，内部 value 属性指定表头列的名称
    private String string;
    /**
     * 年月日时分秒格式
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "日期标题")
    private Date date;
    /**
     * 格式化百分比
     */
    @NumberFormat("#.##%")//另外 ExayExcel 还提供几个注解，自定义日期以及数字的格式化转化。 @DateTimeFormat @NumberFormat
    @ExcelProperty("数字标题")
    private Double doubleData;
    @ExcelProperty(value = "枚举类",converter = DemoEnumConvert.class)//另外我们可以自定义格式化转换方案，需要实现 Converter 类相关方法即可。//最后我们还需要在 @ExcelProperty 注解上使用 converter 指定自定义格式转换方案。
    private DemoEnum demoEnum;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore//默认所有字段都会和excel去匹配，加了这个注解会忽略该字段默认所有字段都会和excel去匹配，加了这个注解会忽略该字段
    private String ignore;
}
