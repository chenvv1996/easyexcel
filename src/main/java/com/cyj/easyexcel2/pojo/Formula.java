package com.cyj.easyexcel2.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ContentRowHeight(20)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER, verticalAlignment = VerticalAlignmentEnum.CENTER)
public class Formula {

    @ColumnWidth(14)
    @ExcelProperty(value = "序号",index = 0)
    private String id;

    @ColumnWidth(14)
    @ExcelProperty(value = "配方名称",index = 1)
    private String formulaName;

    @ColumnWidth(14)
    @ExcelProperty(value = "料号名称",index = 2)
    private String materialName;

    @ColumnWidth(35)
    @ExcelProperty(value = "存货名称",index = 3)
    private String product;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料一",index = 4)
    private String ingredientOne;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 5)
    private String ratioOne;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料二",index = 6)
    private String ingredientTwo;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 7)
    private String ratioTwo;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料三",index = 8)
    private String ingredientThree;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 9)
    private String ratioThree;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料四",index = 10)
    private String ingredientFour;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 11)
    private String ratioFour;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料五",index = 12)
    private String ingredientFive;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 13)
    private String ratioFive;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料六",index = 14)
    private String ingredientSix;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 15)
    private String ratioSix;

    @ColumnWidth(20)
    @ExcelProperty(value = "调整原因",index = 16)
    private String modifyReason;

    @ColumnWidth(20)
    @ExcelProperty(value = "修改时间",index = 17)
    private String date;

}
