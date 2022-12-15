package com.cyj.easyexcel2.info;

import com.cyj.easyexcel2.pojo.Formula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档注释可用在三个地方，类、字段和方法，用来解释它们是干嘛的。
 *
 * @author 陈毅健
 * @date 2022/12/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormulaInfo {
    private String id;

    private String formulaName;

    private String modifyReason;

    private String date;

    private List<Formula> choiceInfoList = new ArrayList<>();





}
