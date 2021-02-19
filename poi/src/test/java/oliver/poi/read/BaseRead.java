package oliver.poi.read;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Oliver
 * @date 2021/2/18 16:01
 * @description poi的写入excel
 */
public class BaseRead {

    String filePath = "D:\\utility\\poi\\";

    @Test
    public void read03() throws IOException {

        //获取文件流
        FileInputStream fileInputStream = new FileInputStream(filePath +"testExcel.xls");
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        //得到表
        Sheet sheet = workbook.getSheetAt(0);
        //得到行
        final Row row = sheet.getRow(0);
        //得到单元格
        final Cell cell = row.getCell(0);
        fileInputStream.close();
        System.out.println("cell.getStringCellValue() = " + cell.getStringCellValue());
        //读取时注意类型，否则会报错，所以读的时候要进行类型的判断
        System.out.println("cell.getNumericCellValue() = " + cell.getNumericCellValue());
    }

    @Test
    public void read07() throws IOException {
        //获取文件流
        FileInputStream fileInputStream = new FileInputStream(filePath +"testExcel.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        //得到表
        Sheet sheet = workbook.getSheetAt(0);
        //得到行
        final Row row = sheet.getRow(0);
        //得到单元格
        final Cell cell = row.getCell(0);
        fileInputStream.close();
        System.out.println("cell.getStringCellValue() = " + cell.getStringCellValue());
        //读取时注意类型，否则会报错，所以读的时候要进行类型的判断
        //System.out.println("cell.getNumericCellValue() = " + cell.getNumericCellValue());
    }

    /**
     * 功能描述 : 读取不同类型数据
     * @author Oliver 2021-2-19 9:51
     */
    @Test
    public void read07Different() {

        try(FileInputStream fileInputStream = new FileInputStream(filePath +"differenttype.xlsx")){
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            //获取标题
            Sheet sheet = workbook.getSheetAt(0);
            final Row title = sheet.getRow(0);
            if (title != null) {
                //获取单元格数量
                final int cellsIndex = title.getPhysicalNumberOfCells();
                for (int i = 0; i < cellsIndex; i++) {
                    final Cell cell = title.getCell(i);
                    if (cell != null) {
                        String stringCellValue = cell.getStringCellValue();
                        System.out.print(stringCellValue +"|");
                    }
                }
            }

            //获取除标题外所有内容
            final int rowNumber = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rowNumber; i++) {
                final Row row = sheet.getRow(i);
                if (row != null) {
                    final short lastCellNum = row.getLastCellNum();
                    for (int j = 0; j < lastCellNum; j++) {
                        final Cell cell = row.getCell(j);
                        if (cell != null) {
                            CellType cellTypeEnum = cell.getCellTypeEnum();
                            switch (cellTypeEnum) {
                                case STRING:
                                    System.out.println("String");
                                    final String stringCellValue = cell.getStringCellValue();
                                    System.out.println("stringCellValue = " + stringCellValue);
                                    break;
                                case BOOLEAN:
                                    System.out.println("Boolean");
                                    System.out.println("stringCellValue = " + cell.getBooleanCellValue());
                                    break;
                                case BLANK:
                                    System.out.println("blank");
                                    break;
                                    //数值 （日期、普通数字）
                                case NUMERIC:
                                    System.out.println("NUMERIC");
                                    if(DateUtil.isCellDateFormatted(cell)){
                                        System.out.println("日期");
                                        HSSFDataFormatter hssfDataFormat = new HSSFDataFormatter();
                                        final String s = hssfDataFormat.formatCellValue(cell);
                                        System.out.println("s = " + s);
                                    }else {
                                        final double numericCellValue = cell.getNumericCellValue();
                                        DecimalFormat df = new DecimalFormat("0.##");
                                        String format = df.format(numericCellValue);
                                        System.out.println("numericCellValue = " + format);
                                    }
                                    break;
                                    //当单元格是公式时（如求和）
                                case FORMULA:
                                    final FormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
                                    final String cellFormula = cell.getCellFormula();
                                    System.out.println("公式为 = " + cellFormula);
                                    final CellValue evaluate = formulaEvaluator.evaluate(cell);
                                    final String s = evaluate.formatAsString();
                                    System.out.println("s = " + s);
                                case ERROR:
                                    System.out.println("error");
                                    break;
                            }
                        }
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
