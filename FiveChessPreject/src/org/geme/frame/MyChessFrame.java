package org.geme.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyChessFrame extends JFrame implements MouseListener {
	
	public MyChessFrame() {
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		this.setVisible(true);   // 设置窗体为显示状态
		this.setTitle("五子棋！");  // 设置标题
		this.setSize(400, 500);
		this.setLocation((width-200)/2, 100);
		this.setResizable(true);  // 设置窗口大小可不可以改变
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 设置关闭窗口即退出程序

		//System.out.println("Screen's height: " + height);
		//System.out.println("Screen's width: " + width);
		
		//this.addMouseListener(this);

	}
	
	public void paint(Graphics g) {
//		BufferedImage image = null;
//		try {
//			image = ImageIO.read(new File("/Users/dien/Desktop/iShot2021-09-03 16.19.32.png"));
//			//image = ImageIO.read(new File("/Users/dien/Desktop/修图/IMGL7429.jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//Image image = Toolkit.getDefaultToolkit().getImage("/Users/dien/Desktop/五子棋.jpg");
		//g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
		//g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), null);
		//System.out.println("图像打开");
		g.setColor(Color.BLUE);
		g.drawOval(50, 50, 50, 50);
		g.fillRect(100, 50, 66, 99);
		g.drawString("五子棋游戏", 20, 40);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标点击！");
		System.out.println("点击位置：X：" + e.getX());
		System.out.println("点击位置：Y：" + e.getY());	// 以窗口左上角为基准
		//JOptionPane.showMessageDialog(this, "鼠标点击！");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标按下！");
		//JOptionPane.showMessageDialog(this, "鼠标按下！");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标抬起！");
		//JOptionPane.showMessageDialog(this, "鼠标抬起！");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标进入！");
		//JOptionPane.showMessageDialog(this, "鼠标进入！");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标离开！");
		//JOptionPane.showMessageDialog(this, "鼠标离开！");
	}
}
