package oliver.poi.write;

import cn.hutool.core.date.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Oliver
 * @date 2021/2/18 16:20
 */
public class BaseWrite {

    String filePath = "D:\\utility\\poi\\";

    /*------------普通写入-------------*/
    @Test
    public void write03() {
        //创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建sheet
        Sheet sheet = workbook.createSheet("sheet01");
        //创建行
        Row row01 = sheet.createRow(0);
        //创建单元格 到现在坐标(0,0)表示表中第一个单元格（1,1）
        Cell cell1_1 = row01.createCell(0);
        //往第一个单元格内设值
        cell1_1.setCellValue("我是第一个单元格");

        Cell cell1_2 = row01.createCell(1);
        cell1_2.setCellValue("我是第二个单元格");

        //创建行
        Row row02 = sheet.createRow(1);
        Cell cell2_1 = row02.createCell(0);
        cell2_1.setCellValue("我是第二行第一个单元格11111");

        Cell cell2_2 = row02.createCell(1);

        String now = DateUtil.now();
        cell2_2.setCellValue(now);
        try(
                //生成一个表 03版本就要使用xls结尾
                FileOutputStream fileOutputStream = new FileOutputStream(filePath + "testExcel.xls")
        ){
            workbook.write(fileOutputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("03文件生成完毕");
    }

    @Test
    public void write07() {
        //创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建sheet
        Sheet sheet = workbook.createSheet("sheet01");
        //创建行
        Row row01 = sheet.createRow(0);
        //创建单元格 到现在坐标(0,0)表示表中第一个单元格（1,1）
        Cell cell1_1 = row01.createCell(0);
        //往第一个单元格内设值
        cell1_1.setCellValue("我是第一个单元格");

        Cell cell1_2 = row01.createCell(1);
        cell1_2.setCellValue("我是第二个单元格");

        //创建行
        Row row02 = sheet.createRow(1);
        Cell cell2_1 = row02.createCell(0);
        cell2_1.setCellValue("我是第二行第一个单元格11111");

        Cell cell2_2 = row02.createCell(1);

        String now = DateUtil.now();
        cell2_2.setCellValue(now);
        try(
                //生成一个表 07版本就要使用xlsx结尾
                FileOutputStream fileOutputStream = new FileOutputStream(filePath + "testExcel.xlsx")
        ){
            workbook.write(fileOutputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("07文件生成完毕");
    }

    /*------------大数据普通写入-------------*/

    //03速度，但超过65536行会报错
    @Test
    public void write03BigData() {
        long start = System.currentTimeMillis();

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("我是03第一个sheet");
        for (int rows = 0; rows < 40000; rows++) {
            Row row = sheet.createRow(rows);
            for (int cell = 0; cell < 10; cell++) {
                Cell cell1 = row.createCell(cell);
                cell1.setCellValue(cell);
            }
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(filePath+"03bigData.xls")){
            workbook.write(fileOutputStream);
        }catch(IOException e){e.printStackTrace();}
        long end = System.currentTimeMillis();
        //733
        System.out.println("用时："+(end-start));
    }

    @Test
    public void write07BigData() {

        long start = System.currentTimeMillis();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("我是07第一个sheet");
        for (int rows = 0; rows < 40000; rows++) {
            Row row = sheet.createRow(rows);
            for (int cell = 0; cell < 10; cell++) {
                Cell cell1 = row.createCell(cell);
                cell1.setCellValue(cell);
            }
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(filePath+"07bigData.xlsx")){
            workbook.write(fileOutputStream);
        }catch(IOException e){e.printStackTrace();}
        long end = System.currentTimeMillis();
        //5024
        System.out.println("用时："+(end-start));

    }

    //XSSF优化版，既能存很多数据又很快，占用内存还少
    //原理内存中默认只写入100条数据，超过这个数量就会写入一个临时文件，再把临时文件转成实际文件
    //默认数量可以自定义
    //此版本的poi会自动删除临时文件
    @Test
    public void write07BigDataSXSSF() {

        long start = System.currentTimeMillis();

        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("我是07第一个sheet");
        for (int rows = 0; rows < 40000; rows++) {
            Row row = sheet.createRow(rows);
            for (int cell = 0; cell < 10; cell++) {
                Cell cell1 = row.createCell(cell);
                cell1.setCellValue(cell);
            }
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(filePath+"07bigDataSXSS.xlsx")){
            workbook.write(fileOutputStream);
        }catch(IOException e){e.printStackTrace();}
        long end = System.currentTimeMillis();
        //1168
        System.out.println("用时："+(end-start));

    }
}
