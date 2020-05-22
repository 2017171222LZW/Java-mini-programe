package local.plan.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 * 游戏的主窗口
 * @author Administrator
 *
 */
public class MyGameFrame extends Frame {
	public static final int Fwidght = 500;
	public static final int Fheight = 500;
	int shellnum = 20;
	Image im = GameUtil.getImage("images/plane.png");
	Image bg = GameUtil.getImage("images/bg.jpg");
	Plane plane = new Plane(im, 400d, 400d);
	ArrayList<Shell> shells;
	Date startTime = new Date();
	Date endTime;
	int period;
	boolean peng;
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(bg, 0, 0 ,null);
		plane.drawSelf(g);
		if(plane.live == false) {
			g.setColor(Color.white);
			g.drawString("游戏时间："+period+"秒", (int)plane.getX(), (int)plane.getY());
			return ;
		}
		for(Shell s : shells) {
			s.drawSelf(g);
			peng = s.getRect().intersects(plane.getRect());
			if(peng) {
				plane.live = false;
				endTime = new Date();
				period = (int)(endTime.getTime() - startTime.getTime())/1000;
			}
		}
	}
	/**
	 * 绘制图形线程
	 * @author Administrator
	 *
	 */
	class PaintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyPressed(e);
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyReleased(e);
			plane.minusDirection(e);
		}
		
	}
	public void launchFrame() {
		setTitle("飞机游戏");
		setVisible(true);
		setSize(Fwidght, Fheight);
		setLocation(700, 300);
		
		this.addWindowListener(new WindowAdapter() {
			//alt+?	：可以提示复写类
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosed(e);
				System.exit(0);
			}
		});
		new PaintThread().start();
		addKeyListener(new KeyMonitor());
		shells = new ArrayList<Shell>();
		for(int i=0;i < shellnum;++i) {
			shells.add(new Shell());
		}
	}
	
	public static void main(String[] args) {
		MyGameFrame myFrame = new MyGameFrame();
		myFrame.launchFrame();
		
	}
	/**
	 * 双缓冲解决闪烁
	 */
	private Image offSreamImage = null;
	@Override
	public void update(Graphics g) {
		if(offSreamImage == null)
			offSreamImage = createImage(Fwidght, Fheight);
			Graphics gOff = offSreamImage.getGraphics();
			paint(gOff);
			gOff.dispose();//释放图形上下文资源
			g.drawImage(offSreamImage, 0, 0, null);
	}
	
	
}
