package com.cyj.easyexcel2;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.ListUtils;
import com.cyj.easyexcel2.info.FormulaInfo;
import com.cyj.easyexcel2.listener.ExamListener;
import com.cyj.easyexcel2.pojo.Formula;
import com.cyj.easyexcel2.strategy.MergeStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
class Easyexcel2ApplicationTests {

    @Test
    public void formulaWrite() {
        // 方法1 注解
        String fileName = "H:\\ideaProject\\easyexcel2\\src\\main\\resources\\xlsx\\formulaWrite" + System.currentTimeMillis() + ".xlsx";

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, Formula.class)
                .registerWriteHandler(new MergeStrategy(data().size(),0,1,16,17))
                .sheet("模板")
                .doWrite(data());
    }

    @Test
    public void formulaRead() {
        List<FormulaInfo> formulaInfoList=new ArrayList<>();
        String fileName = "H:\\ideaProject\\easyexcel2\\src\\main\\resources\\xlsx\\配方最终模板.xls";

        EasyExcel.read(fileName, Formula.class, new ExamListener(formulaInfos -> {
            for (FormulaInfo formulaInfo : formulaInfos) {
                formulaInfoList.add(formulaInfo);
            }
        })).sheet().doRead();
        System.out.println(formulaInfoList);
    }

    private List<Formula> data() {
        List<Formula> list = ListUtils.newArrayList();
        String date = new Date().toString();

        Formula formula1 = new Formula();
        formula1.setId(String.valueOf(1));
        formula1.setFormulaName("虾滑测试");
        formula1.setMaterialName("101301-01");
        formula1.setProduct("产品1");
        formula1.setIngredientOne("A原料");
        formula1.setRatioOne("40");
        formula1.setIngredientTwo("B原料");
        formula1.setRatioTwo("20");
        formula1.setIngredientThree("C原料");
        formula1.setRatioThree("30");
        formula1.setModifyReason("创建配方test");
        formula1.setDate(date);

        Formula formula2 = new Formula();
        formula2.setId(String.valueOf(1));
        formula2.setFormulaName("虾滑测试");
        formula2.setMaterialName("101301-02");
        formula2.setProduct("产品2");
        formula2.setIngredientOne("A原料");
        formula2.setRatioOne("40");
        formula2.setIngredientTwo("B原料");
        formula2.setRatioTwo("20");
        formula2.setIngredientThree("C原料");
        formula2.setRatioThree("30");
        formula2.setModifyReason("创建配方test");
        formula2.setDate(date);

        Formula formula3 = new Formula();
        formula3.setId(String.valueOf(1));
        formula3.setFormulaName("虾滑测试");
        formula3.setMaterialName("101301-03");
        formula3.setProduct("产品3");
        formula3.setIngredientOne("A原料");
        formula3.setRatioOne("40");
        formula3.setIngredientTwo("B原料");
        formula3.setRatioTwo("20");
        formula3.setIngredientThree("C原料");
        formula3.setRatioThree("30");
        formula3.setModifyReason("创建配方test");
        formula3.setDate(date);

        Formula formula4 = new Formula();
        formula4.setId(String.valueOf(1));
        formula4.setFormulaName("虾滑测试");
        formula4.setMaterialName("101301-04");
        formula4.setProduct("产品4");
        formula4.setIngredientOne("A原料");
        formula4.setRatioOne("40");
        formula4.setIngredientTwo("B原料");
        formula4.setRatioTwo("20");
        formula4.setIngredientThree("C原料");
        formula4.setRatioThree("30");
        formula4.setModifyReason("创建配方test");
        formula4.setDate(date);

        list.add(formula1);
        list.add(formula2);
        list.add(formula3);
        list.add(formula4);
        return list;
    }

}
