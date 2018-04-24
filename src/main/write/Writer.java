package main.write;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Writer {

  /**
   * 格式化输出 浮点数 test
   *
   * @param d 双精度浮点数
   * @param max 小数点后-最大保留位数
   * @param min 小数点后-最小保留位数(默认为 2 ,不足补0)
   */
  public static String format(Double d, Integer max, Integer min) {
    if (null == d) {
      return "";
    }
    Integer _min = (null == min || min < 0) ? 2 : min;
    String pattern = "0";
    DecimalFormat formatter = new DecimalFormat(pattern);
    if (null != _min) {
      formatter.setMinimumFractionDigits(_min);
    }
    if (null != max) {
      formatter.setMaximumFractionDigits(max);
    }
    return formatter.format(d);
  }

  //写入excel
  public void createExcel(OutputStream os, double[] xArr, double[] yArr, Integer lenX, Integer lenY)
      throws WriteException, IOException {

    //创建工作薄
    WritableWorkbook workbook = Workbook.createWorkbook(os);
    //创建新的一页
    WritableSheet sheet = workbook.createSheet("1", 0);
    //创建要显示的具体内容
    Label lab, lx, ly, la, x, y;
    int i = 0;
    Number a;

    for (int hang = 0; hang < xArr.length * 2; hang++) {
      if (hang % 2 == 1) {  //奇数行
        lx = new Label(1, hang, "x");
        //x = new Number(2,hang,xArr[i] ,wcfx);
        x = new Label(2, hang, format(xArr[i], lenX, 10));
        ly = new Label(3, hang, "y");
        //y = new Number(4,hang,yArr[i] ,wcfy);
        y = new Label(4, hang, format(yArr[i], lenY, 10));
        la = new Label(5, hang, "a");
        a = new Number(6, hang, 0.25);
        sheet.addCell(lx);
        sheet.addCell(x);
        sheet.addCell(ly);
        sheet.addCell(y);
        sheet.addCell(la);
        sheet.addCell(a);
        i++;
      } else {
        lab = new Label(0, hang, "m98p1");
        sheet.addCell(lab);
      }
    }
    //把创建的内容写入到输出流中，并关闭输出流
    workbook.write();
    workbook.close();
    os.close();
  }
}
