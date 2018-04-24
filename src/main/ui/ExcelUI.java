package main.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jxl.write.WriteException;

import main.write.Writer;

public class ExcelUI extends JFrame implements ActionListener {


  private static final long serialVersionUID = 1L;
  public JPanel row1 = new JPanel();
  public JPanel numRow = new JPanel();
  public JLabel xStart, xEnd, xCount, yStart, yEnd, yCount, xFormat, yFormat;
  public JTextField xStartVal, xEndVal, xCountVal, yStartVal, yEndVal, yCountVal, xFormatVal, yFormatVal;
  public JPanel buttonRow = new JPanel();
  public JButton play, reset;
  public static double[] xArr;
  public static double[] yArr;


  public ExcelUI() throws HeadlessException {
    super("water.Wang");

    xStart = new JLabel("X初始值=", JLabel.RIGHT);
    xStartVal = new JTextField("0");
    xEnd = new JLabel("X末尾值=", JLabel.RIGHT);
    xEndVal = new JTextField("0");
    xCount = new JLabel("X个数=", JLabel.RIGHT);
    xCountVal = new JTextField("0");
    xFormat = new JLabel("X保留位数=", JLabel.RIGHT);
    xFormatVal = new JTextField("10");
    yStart = new JLabel("Y初始值=", JLabel.RIGHT);
    yStartVal = new JTextField("0");
    yEnd = new JLabel("Y末尾值=", JLabel.RIGHT);
    yEndVal = new JTextField("0");
    yCount = new JLabel("Y个数=", JLabel.RIGHT);
    yCountVal = new JTextField("0");
    yFormat = new JLabel("Y保留位数=", JLabel.RIGHT);
    yFormatVal = new JTextField("10");

    play = new JButton("执行");
    play.addActionListener(this);
    reset = new JButton("重置");
    reset.addActionListener(this);

    this.setSize(700, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // this.setResizable(false);

    int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 700) / 2;
    int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2;
    setLocation(w, h);
    GridLayout gridLayout = new GridLayout(4, 1, 10, 10);
    setLayout(gridLayout);

    FlowLayout flowLayout1 = new FlowLayout(FlowLayout.CENTER, 10, 10);
    row1.setLayout(flowLayout1);
    add(row1);

    GridLayout gridLayout2 = new GridLayout(2, 1, 10, 10);
    numRow.setLayout(gridLayout2);
    numRow.add(xStart);
    numRow.add(xStartVal);
    numRow.add(xEnd);
    numRow.add(xEndVal);
    numRow.add(xCount);
    numRow.add(xCountVal);
    numRow.add(xFormat);
    numRow.add(xFormatVal);
    numRow.add(yStart);
    numRow.add(yStartVal);
    numRow.add(yEnd);
    numRow.add(yEndVal);
    numRow.add(yCount);
    numRow.add(yCountVal);
    numRow.add(yFormat);
    numRow.add(yFormatVal);
    add(numRow);
    setVisible(true);

    FlowLayout flowLayout2 = new FlowLayout(FlowLayout.CENTER);
    buttonRow.setLayout(flowLayout2);
    buttonRow.add(play);
    buttonRow.add(reset);
    add(buttonRow);
    //System.out.println(xCountVal.getText());
  }


  public static void main(String[] args) {
    new ExcelUI();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == play) {
      //获取界面上xy的各个值
      double x0 = Double.valueOf(xStartVal.getText());
      double x1 = Double.valueOf(xEndVal.getText());
      int xcount = Integer.parseInt(xCountVal.getText());
      Integer xForm = Integer.parseInt(xFormatVal.getText());
      double y0 = Double.valueOf(yStartVal.getText());
      double y1 = Double.valueOf(yEndVal.getText());
      int ycount = Integer.parseInt(yCountVal.getText());
      Integer yForm = Integer.parseInt(yFormatVal.getText());

      double temX = (x1 - x0) / (xcount - 1);
      double temY = (y1 - y0) / (ycount - 1);
      xArr = new double[xcount * ycount];
      yArr = new double[xcount * ycount];
      double sumX = x0;
      double sumY = y0;

      //存储xy
      for (int i = 0; i < xcount * ycount; i++) {
        if (i < xcount) {
          xArr[i] = sumX;
          sumX += temX;
          yArr[i] = sumY;
//			    		System.out.println(yArr[21]);

        } else {
          xArr[i] = xArr[i - xcount];
          yArr[i] = yArr[i - xcount] + temY;
        }
      }
      JFileChooser jf = new JFileChooser("d:/");
      //jf.setFileFilter(new FileNameExtensionFilter("Excel", new String[]{".xls"}));
      jf.setSelectedFile(new File(".xls"));
      int value = jf.showSaveDialog(null);

      if (value == JFileChooser.APPROVE_OPTION) {    //判断窗口是否点的是打开或保存

        File getPath = jf.getSelectedFile();       //取得路径
        String path = getPath.toString();
        String filename = getPath.getName();
        if (filename.indexOf(".xls") == -1 || filename.indexOf(".xlsx") == -1) {  //不包含
//					JOptionPane.showMessageDialog(this,"请保存成excel格式！");
//					return;
          path += ".xls";
        }
        try {
          saveFile(path, xArr, yArr, xForm, yForm);
        } catch (WriteException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

      } else {
        // 没有选择，即点了窗口的取消
        //.......   //点了取消后有要做些什么
      }
    } else if (e.getSource() == reset) {
      this.xStartVal.setText("0");
      this.xEndVal.setText("0");
      this.xCountVal.setText("0");
      this.yStartVal.setText("0");
      this.yEndVal.setText("0");
      this.yCountVal.setText("0");
    }

  }


  private void saveFile(String getPath, double[] xArr, double[] yArr, Integer lenX, Integer lenY)
      throws WriteException, IOException {
    OutputStream out = new FileOutputStream(getPath); //是否追加;
    Writer we = new Writer();
    we.createExcel(out, xArr, yArr, lenX, lenY);
    JOptionPane.showMessageDialog(this, "成功保存在" + getPath);
    //System.exit(0);
  }


}