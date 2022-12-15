package com.cyj.easyexcel2.info;

import com.cyj.easyexcel2.pojo.Formula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文档注释可用在三个地方，类、字段和方法，用来解释它们是干嘛的。
 *
 * @author 陈毅健
 * @date 2022/12/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormulaChoiceInfo extends Formula {
    private String materialName;

    private String product;

    private String ingredientOne;

    private String ratioOne;

    private String ingredientTwo;

    private String ratioTwo;

    private String ingredientThree;

    private String ratioThree;

    private String ingredientFour;

    private String ratioFour;

    private String ingredientFive;

    private String ratioFive;

    private String ingredientSix;

    private String ratioSix;


}
