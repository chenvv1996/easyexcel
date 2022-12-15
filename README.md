# 官网

https://easyexcel.opensource.alibaba.com/

学习内容：

- 读Excel
- 写Excel
- 填充Excel
- 文件上传和下载

# 写Excel

**写的对象。待优化，配料和比例可以使用填充。**

```java
@Data
@ContentRowHeight(20)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER, verticalAlignment = VerticalAlignmentEnum.CENTER)
public class Formula {

    @ColumnWidth(14)
    @ExcelProperty(value = "序号",index = 0)
    String id;

    @ColumnWidth(14)
    @ExcelProperty(value = "配方名称",index = 1)
    String formulaName;

    @ColumnWidth(14)
    @ExcelProperty(value = "料号名称",index = 2)
    String materialName;

    @ColumnWidth(35)
    @ExcelProperty(value = "存货名称",index = 3)
    String product;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料一",index = 4)
    String ingredientOne;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 5)
    String ratioOne;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料二",index = 6)
    String ingredientTwo;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 7)
    String ratioTwo;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料三",index = 8)
    String ingredientThree;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 9)
    String ratioThree;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料四",index = 10)
    String ingredientFour;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 11)
    String ratioFour;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料五",index = 12)
    String ingredientFive;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 13)
    String ratioFive;

    @ColumnWidth(35)
    @ExcelProperty(value = "配料六",index = 14)
    String ingredientSix;
    @ColumnWidth(14)
    @ExcelProperty(value = "比例",index = 15)
    String ratioSix;

    @ColumnWidth(20)
    @ExcelProperty(value = "调整原因",index = 16)
    String modifyReason;

    @ColumnWidth(20)
    @ExcelProperty(value = "修改时间",index = 17)
    String date;

}
```

**通用合并策略实现**

```java
public class MergeStrategy extends AbstractMergeStrategy {

    // 合并的列编号，从0开始，指定的index或自己按字段顺序数
    private Set<Integer> mergeCellIndex = new HashSet<>();

    // 数据集大小，用于区别结束行位置
    private Integer maxRow = 0;

    // 禁止无参声明
    private MergeStrategy() {
    }

    public MergeStrategy(Integer maxRow, int... mergeCellIndex) {
        Arrays.stream(mergeCellIndex).forEach(item -> {
            this.mergeCellIndex.add(item);
        });
        this.maxRow = maxRow;
    }

    // 记录上一次合并的信息
    private Map<Integer, MergeRange> lastRow = new HashedMap();

    // 每行每列都会进入，绝对不要在这写循环
    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        int currentCellIndex = cell.getColumnIndex();
        // 判断该列是否需要合并
        if (mergeCellIndex.contains(currentCellIndex)) {
            String currentCellValue = cell.getStringCellValue();
            int currentRowIndex = cell.getRowIndex();
            if (!lastRow.containsKey(currentCellIndex)) {
                // 记录首行起始位置
                lastRow.put(currentCellIndex, new MergeRange(currentCellValue, currentRowIndex, currentRowIndex, currentCellIndex, currentCellIndex));
                return;
            }
            //有上行这列的值了，拿来对比.
            MergeRange mergeRange = lastRow.get(currentCellIndex);
            if (!(mergeRange.lastValue != null && mergeRange.lastValue.equals(currentCellValue))) {
                // 结束的位置触发下合并.
                // 同行同列不能合并，会抛异常
                if (mergeRange.startRow != mergeRange.endRow || mergeRange.startCell != mergeRange.endCell) {
                    sheet.addMergedRegionUnsafe(new CellRangeAddress(mergeRange.startRow, mergeRange.endRow, mergeRange.startCell, mergeRange.endCell));
                }
                // 更新当前列起始位置
                lastRow.put(currentCellIndex, new MergeRange(currentCellValue, currentRowIndex, currentRowIndex, currentCellIndex, currentCellIndex));
            }
            // 合并行 + 1
            mergeRange.endRow += 1;
            // 结束的位置触发下最后一次没完成的合并
            if (relativeRowIndex.equals(maxRow - 1)) {
                MergeRange lastMergeRange = lastRow.get(currentCellIndex);
                // 同行同列不能合并，会抛异常
                if (lastMergeRange.startRow != lastMergeRange.endRow || lastMergeRange.startCell != lastMergeRange.endCell) {
                    sheet.addMergedRegionUnsafe(new CellRangeAddress(lastMergeRange.startRow, lastMergeRange.endRow, lastMergeRange.startCell, lastMergeRange.endCell));
                }
            }
        }
    }
}

class MergeRange {
    public int startRow;
    public int endRow;
    public int startCell;
    public int endCell;
    public String lastValue;

    public MergeRange(String lastValue, int startRow, int endRow, int startCell, int endCell) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCell = startCell;
        this.endCell = endCell;
        this.lastValue = lastValue;
    }
}
```

**测试**

```java
@SpringBootTest
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
```

# 读Excel



![image-20221215153716812](H:\ideaProject\easyexcel2\assets\image-20221215153716812.png)

**目标数据组装类**



# 填充Excel

