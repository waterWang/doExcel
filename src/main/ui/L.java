package main.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;
public class L extends JFrame {
/**
* @param args
*/
public static void main(String[] args) throws Exception {
new ConfigFrame("�����ļ�");
}
}
class ConfigFrame extends JFrame {
private static final long serialVersionUID = 1L;
public ConfigFrame(String title) {
// ���ô�������
final int width = 300;
final int height = 200;
final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
final int left = (screen.width - width) / 2;
final int top = (screen.height - height) / 2;
this.setLocation(left, top);
this.setSize(width, height);
this.setTitle(title);
// ������
JPanel panel = new JPanel();
this.add(panel);
JButton b = new JButton("��");
panel.add(b);
b.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
JFileChooser chooser = new JFileChooser(".");
TxtFileFilter txtFilter = new TxtFileFilter();
JavaFileFilter javaFilter = new JavaFileFilter();
chooser.addChoosableFileFilter(txtFilter);
chooser.addChoosableFileFilter(javaFilter);
// ����Ĭ�ϵ��ļ������������������,�������ӵ��ļ�������ΪĬ�Ϲ�����,������ΪjavaFilter
chooser.setFileFilter(txtFilter);
int rs = chooser.showSaveDialog(ConfigFrame.this);
}
});
this.setVisible(true);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
class JavaFileFilter extends FileFilter {
public String getDescription() {
return "*.java(javaԴ�ļ�)";
}
public boolean accept(File file) {
String name = file.getName();
return name.toLowerCase().endsWith(".java");
}
}
class TxtFileFilter extends FileFilter {
public String getDescription() {
return "*.txt(�ı��ļ�)";
}
public boolean accept(File file) {
String name = file.getName();
return name.toLowerCase().endsWith(".txt");
}
}