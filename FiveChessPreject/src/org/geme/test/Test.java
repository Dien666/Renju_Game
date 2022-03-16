package org.geme.test;

import javax.swing.JOptionPane;

import org.geme.frame.FiveChessFrame;
import org.geme.frame.MyChessFrame;

public class Test {
	public static void main(String[] args) {
		//MyChessFrame mf = new MyChessFrame();
		//JOptionPane.showMessageDialog(mf, "My message");  // 在已有的窗口上打开信息窗口（提示信息）
//		int option = JOptionPane.showConfirmDialog(mf, "是否开始游戏？");
//		JOptionPane.showMessageDialog(mf, option == 0 ? "游戏开始":"游戏结束");
//		
//		String username = null;
//		while (username == null) {
//			username = JOptionPane.showInputDialog("请输入你的姓名：");
//		}
//		JOptionPane.showMessageDialog(mf, "输入姓名为： " + username);
		FiveChessFrame ff = new FiveChessFrame();
	}
}
