package main.ui;
import java.awt.*;      //为了使用布局管理器   
import java.awt.event.*;//用来处理事件   
import javax.swing.*;   //最新的GUI组件   
import java.io.*;       //读写文件用   
  
public class filechooser   
{   
  
    private JFrame frm;   
    private JButton open;   
    private JButton read;   
    private JPanel p;   
    private File f;   
    private JFileChooser fc;   
    private int flag;   
  
   public filechooser()   
    {   
      frm=new JFrame("java");   
      open=new JButton("open");   
      read=new JButton("read");   
      p=new JPanel();   
      fc=new JFileChooser();   
  
  
      Container c=frm.getContentPane();   
      c.setLayout(new FlowLayout());   
  
      c.add(p);   
      p.add(open);   
      p.add(read);   
  
    //注册按钮事件   
      open.addActionListener(new action());   
      read.addActionListener(new action());   
  
      frm.setSize(300,300);   
      frm.setVisible(true);   
    //设置默认的关闭操作   
      frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    }   
  
  
  private void openFile()  //打开文件   
           {   
              //设置打开文件对话框的标题   
                fc.setDialogTitle("Open File");   
  
              //这里显示打开文件的对话框   
           try{    
                       flag=fc.showOpenDialog(frm);    
                }   
           catch(HeadlessException head){    
  
                       System.out.println("Open File Dialog ERROR!");   
                }   
                 
              //如果按下确定按钮，则获得该文件。   
              if(flag==JFileChooser.APPROVE_OPTION)   
                {   
                     //获得该文件   
                       f=fc.getSelectedFile();   
                       System.out.println("open file----"+f.getName());   
                 }   
           }   
  
  private void readFile() //保存文件   
         {   
             String fileName;   
  
           //设置保存文件对话框的标题   
             fc.setDialogTitle("Save File");    
  
           //这里将显示保存文件的对话框   
        try{    
                  flag=fc.showSaveDialog(frm);   
             }   
        catch(HeadlessException he){   
                  System.out.println("Save File Dialog ERROR!");    
             }   
  
           //如果按下确定按钮，则获得该文件。   
           if(flag==JFileChooser.APPROVE_OPTION)   
             {   
                 //获得你输入要保存的文件   
                   f=fc.getSelectedFile();   
                 //获得文件名   
                   fileName=fc.getName(f);   
                 //也可以使用fileName=f.getName();   
                   System.out.println(fileName);   
             }   
         }   
  
  //按钮监听器类内部类   
  class action implements ActionListener   
    {   
      public void actionPerformed(ActionEvent e)   
        {   
  
          //判断是哪个按纽被点击了   
          if(e.getSource()==open)   
               openFile();   
          else  
          if(e.getSource()==read)   
               readFile();   
        }   
    }   
  
  public static void main(String[] args)   
    {   
    new filechooser();   
     }   
}