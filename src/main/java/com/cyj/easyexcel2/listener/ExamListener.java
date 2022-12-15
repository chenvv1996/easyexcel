package com.cyj.easyexcel2.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.cyj.easyexcel2.info.FormulaChoiceInfo;
import com.cyj.easyexcel2.info.FormulaInfo;
import com.cyj.easyexcel2.pojo.Formula;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ExamListener implements ReadListener<Formula> {

  // 自定义消费者函数接口用于自定义监听器中数据组装
    private final Consumer<List<FormulaInfo>> consumer;

    public ExamListener(Consumer<List<FormulaInfo>> consumer) {
        this.consumer = consumer;
    }

    // easy excel读取参数
    private List<Formula> formulaList=new ArrayList<>();
    // 封装读取对象
    private List<FormulaInfo> formulaInfoList=new ArrayList<>();
    // 每行读取完成之后会执行
    @Override
    public void invoke(Formula data, AnalysisContext context) {

        // 按照格式组装数据
        if(data.getFormulaName() != null){
            FormulaInfo formulaInfo = new FormulaInfo();
            BeanUtils.copyProperties(data,formulaInfo);
            formulaInfo.getChoiceInfoList().add(new FormulaChoiceInfo(
                    data.getMaterialName(),
                    data.getProduct(),
                    data.getIngredientOne(),
                    data.getRatioOne(),
                    data.getIngredientTwo(),
                    data.getRatioTwo(),
                    data.getIngredientThree(),
                    data.getRatioThree(),
                    data.getIngredientFour(),
                    data.getRatioFour(),
                    data.getIngredientFive(),
                    data.getRatioFive(),
                    data.getIngredientSix(),
                    data.getRatioSix()

                    ));
            formulaInfoList.add(formulaInfo);
            formulaInfoList.toString();
        }
//        else {
//            // 倒序添加选择题信息,只对最后一个进行添加选项数据信息
//                examInfoList.get(examInfoList.size() - 1).getEaxmChoiceInfoList().add(new EaxmChoiceInfo(data.getChoiceType(),data.getOptionContent()));
//        }
    }
	// 每行读取完成之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
         if (CollectionUtils.isNotEmpty(formulaInfoList)) {
            consumer.accept(formulaInfoList);
        }
    }
}
