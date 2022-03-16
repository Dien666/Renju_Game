package org.geme.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//import com.sun.tools.javap.TryBlockWriter;

public class FiveChessFrame extends JFrame implements MouseListener, Runnable {
	// 取得屏幕宽高
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	// 初始化背景图片
	BufferedImage bgImage = null;
	// 定义下棋的坐标
	int p_x = 0;
	int p_y = 0;
	// 定义两个栈用于保存下棋的记录
	int[][] allChess = new int[21][21];
	// 定义黑白棋的下棋标识
	boolean isBlack = true;
	// 定义游戏继续的标识
	boolean flag = true;
	// 游戏提示信息
	String message = "黑方先行";
	int maxTime = 999;
	int time = 0;
	int Blacktime, Whitetime = 0;
	String BlackTimeMessage = "无限制";
	String WhiteTimeMessage = "无限制";
	// 倒计时线程操作
	Thread t = new Thread(this);

	
	public FiveChessFrame() {
		//t.start();
		this.setTitle("五子棋");
		this.setSize(500, 520);
		this.setLocation((width-500)/2, (height-500)/2);
		// 设置窗口大小不可变
		this.setResizable(false);
		// 设置关闭窗口即退出程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		// 加载图片
		try {
			bgImage = ImageIO.read(new File("/Users/dien/Desktop/Java Study/FiveChess/Background.jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		this.setVisible(true);
		// 加入鼠标监听
		this.addMouseListener(this);
	}
	
	@SuppressWarnings("removal")
	public void paint(Graphics g) {
		// 双缓冲技术
		BufferedImage bi = new BufferedImage(500, 520, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = bi.createGraphics();
		// 背景图片
		g2.drawImage(bgImage, 0, 30, this);
		// 标题信息
		g2.setColor(Color.black);
		g2.setFont(new Font("黑体", Font.BOLD, 20));
		g2.drawString("游戏信息：", 125, 60);
		g2.drawString(message, 220, 60);
		// 输出时间信息
		g2.setFont(new Font("宋体", 0, 15));
		g2.drawString("黑方时间：" + BlackTimeMessage, 20, 495);
		g2.drawString("白方时间：" + WhiteTimeMessage, 255, 495);
		//t.resume();
		// 绘制棋盘格子
		for (int i = 0; i < 19; i++) {
			// 竖着的边
			g2.drawLine(10+i*20, 85, 10+i*20, 445);
			// 横着的边
			g2.drawLine(10, 85+i*20, 370, 85+i*20);
		}
		// 绘制星和天元
		g2.fillOval(87, 162, 6, 6);
		g2.fillOval(287, 162, 6, 6);
		g2.fillOval(87, 362, 6, 6);
		g2.fillOval(287, 362, 6, 6);
		g2.fillOval(187, 262, 6, 6);
		// 绘制棋子
		for(int i = 0; i < 19; ++i) {
			for(int j = 0; j < 19; ++j) {
				// 绘制黑棋
				if(allChess[i][j] == 1) {
					g2.setColor(Color.BLACK);
					g2.fillOval(20*i+1, 20*j+76, 18, 18);
				}
				// 绘制白棋
				if(allChess[i][j] == 2) {
					g2.setColor(Color.WHITE);
					g2.fillOval(20*i+2, 20*j+77, 16, 16);
					g2.setColor(Color.BLACK);
					g2.drawOval(20*i+1, 20*j+76, 18, 18);
				}
			}
		}
		g.drawImage(bi, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		// 进入游戏开始菜单
		if (x >= 411 && x <= 485 && y >= 82 && y <= 113) {
			JOptionPane.showMessageDialog(this, "游戏开始");
			isBlack = true;
			message = "黑棋请落子";
			flag = true;
			allChess = new int[21][21];
			Blacktime = Whitetime = maxTime;
			t.start();
			this.repaint();
		}
		// 认输菜单
		if (x >= 411 && x <= 485 && y >= 294 && y <= 324) {
			JOptionPane.showMessageDialog(this, isBlack == true ? "黑棋认输，白棋获胜！":"白棋认输，黑棋获胜！");
		}
		// 游戏信息菜单
		if (x >= 411 && x <= 485 && y >= 345 && y <= 375) {
			JOptionPane.showMessageDialog(this, "作者：桂宝寒" + "\n" + "2021.10" + "\n" + "Sheffield");
		}
		// 游戏结束菜单
		if (x >= 411 && x <= 485 && y >= 398 && y <= 428) {
			System.exit(0);
		}
		// 游戏设置菜单
		if (x >= 411 && x <= 485 && y >= 133 && y <= 155) {
			String input = JOptionPane.showInputDialog("输入落子时间限制（秒）：");
			try {
				maxTime = Integer.parseInt(input);
				if (maxTime <= 0)
				JOptionPane.showMessageDialog(this, "请输入正确信息！");

				if (maxTime > 0) {
					int mark = JOptionPane.showConfirmDialog(this, "设置完成！是否开始游戏？");
					if (mark == 0) {
						JOptionPane.showMessageDialog(this, "游戏开始");
						isBlack = true;
						Blacktime = maxTime;
						Whitetime = maxTime;
						
						flag = true;
						allChess = new int[21][21];
						t.start();
						this.repaint();
					}
				}
			} catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "请输入正确信息！");
			}			
		}
		if (flag) {
			if (x >= 10 && x <= 370 && y >= 85 && y <= 445){
				p_x = getPosition_x(x);
				p_y = getPosition_y(y);
				playChess();
				
				flag = checkWin(p_x, p_y);
				if (!flag) {
					JOptionPane.showMessageDialog(this, allChess[p_x][p_y] == 1 ? "黑棋获胜！":"白棋获胜！");
					this.repaint();
				}
			}	
		} else {
			JOptionPane.showMessageDialog(this, "游戏结束！请重新开始！");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public int getPosition_x (int x) {
		Double number = ((double) x-10) / 20.0 ;
		int p_x = (int)Math.round(number);
		return p_x;
	}
	
	public int getPosition_y (int y) {
		Double number = ((double) y-85) / 20.0 ;
		int p_y = (int)Math.round(number);
		return p_y;
	}
	
	public void playChess() {
		if (allChess[p_x][p_y] == 0) {
			if(isBlack) {
				allChess[p_x][p_y] = 1;
				isBlack = false;
				message = "白棋请落子";
			} else {
				allChess[p_x][p_y] = 2;
				isBlack = true;
				message = "黑棋请落子";
			}
		}else
			JOptionPane.showMessageDialog(this, "已有棋子，请重新落子！");
	}
	
	private boolean checkWin(int p_x, int p_y) {
		boolean flag = true;
		int color = allChess[p_x][p_y];
		// 判断棋子
		
		flag = check1(color, p_x, p_y, flag);
		flag = check2(color, p_x, p_y, flag);
		flag = check3(color, p_x, p_y, flag);
		flag = check4(color, p_x, p_y, flag);
		this.repaint();
		return flag;
	}
	
	private boolean check1 (int color, int p_x, int p_y, boolean flag) {
		int i = p_x+1, j = p_x-1, count = 1; 
		int end1 = 0, end2 = 0;
		while (count < 5) {
			// 判断右边的棋子
			if (allChess[i][p_y] == color) {
				count++;
				i++;
			}else end1 = 1;
			
			// 判断左边的棋子
			if (j>0 && allChess[j][p_y] == color) {
				count++;
				j--;
			}else end2 = 1;
		
			if (count == 5) {
				flag = false;
				//JOptionPane.showMessageDialog(this,color == 1 ? "黑棋获胜！":"白棋获胜！");
			}
			if (end1 == 1 && end2 == 1) 
				break;
		}
		
		return flag;
	}
	
	private boolean check2 (int color, int p_x, int p_y, boolean flag) {
		int i = p_y+1, j = p_y-1, count = 1; 
		int end1 = 0, end2 = 0;
		while (count < 5) {
			// 判断上边的棋子
			if (allChess[p_x][i] == color) {
				count++;
				i++;
			}else end1 = 1;
			
			// 判断下边的棋子
			if (j>0 && allChess[p_x][j] == color) {
				count++;
				j--;
			}else end2 = 1;
		
			if (count == 5) {
				flag = false;
				//JOptionPane.showMessageDialog(this,color == 1 ? "黑棋获胜！":"白棋获胜！");
			}
			if (end1 == 1 && end2 == 1) 
				break;
		}
		
		return flag;
	}
	
	private boolean check3 (int color, int p_x, int p_y, boolean flag) {
		int i = 1, j = 1, count = 1; 
		int end1 = 0, end2 = 0;
		while (count < 5 ) {
			// 判断右下边的棋子
			if (p_x - i > 0 && p_y - i > 0 && allChess[p_x-i][p_y-i] == color) {
				count++;
				i++;
				//System.out.println("右下+1");
			}else end1 = 1;
			
			// 判断左上边的棋子
			if (allChess[p_x+j][p_y+j] == color) {
				count++;
				j++;
				//System.out.println("左上+1");
			}else end2 = 1;
		
			if (count == 5) {
				flag = false;
				//JOptionPane.showMessageDialog(this,color == 1 ? "黑棋获胜！":"白棋获胜！");
			}
			if (end1 == 1 && end2 == 1) 
				break;
		}
		return flag;
	}
	
	private boolean check4 (int color, int p_x, int p_y, boolean flag) {
		int i = 1, j = 1, count = 1; 
		int end1 = 0, end2 = 0;
		while (count < 5) {
			// 判断右上边的棋子
			if (p_y-i>0 && allChess[p_x+i][p_y-i] == color) {
				count++;
				i++;
				//System.out.println("右上+1");
			}else end1 = 1;
			
			// 判断左下边的棋子
			if (p_x-j>0 && allChess[p_x-j][p_y+j] == color) {
				count++;
				j++;
				//System.out.println("左下+1");
			}else end2 = 1;
		
			if (count == 5) {
				flag = false;
				//JOptionPane.showMessageDialog(this,color == 1 ? "黑棋获胜！":"白棋获胜！");
			}
			if (end1 == 1 && end2 == 1) 
				break;
		}
		
		return flag;
	}

	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		if (maxTime > 0) {
			while(true) {
				while(!flag)
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(isBlack && Blacktime > 0) {
					Whitetime = maxTime;
					Blacktime--;
					WhiteTimeMessage = String.valueOf(Whitetime) + " s";
					BlackTimeMessage = String.valueOf(Blacktime) + " s";
					this.repaint();
				}
				if (!isBlack && Whitetime > 0) {
					Blacktime = maxTime;
					Whitetime--;
					BlackTimeMessage = String.valueOf(Blacktime) + " s";
					WhiteTimeMessage = String.valueOf(Whitetime) + " s";
					this.repaint();
				}
				this.repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(Blacktime == 0 || Whitetime == 0) {
					flag = false;
					JOptionPane.showMessageDialog(this, Whitetime == 0 ? "黑棋获胜！":"白棋获胜！");
					this.repaint();
				}
			}
		}
	}
}
