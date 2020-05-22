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
 * ��Ϸ��������
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
			g.drawString("��Ϸʱ�䣺"+period+"��", (int)plane.getX(), (int)plane.getY());
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
	 * ����ͼ���߳�
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
		setTitle("�ɻ���Ϸ");
		setVisible(true);
		setSize(Fwidght, Fheight);
		setLocation(700, 300);
		
		this.addWindowListener(new WindowAdapter() {
			//alt+?	��������ʾ��д��
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
	 * ˫��������˸
	 */
	private Image offSreamImage = null;
	@Override
	public void update(Graphics g) {
		if(offSreamImage == null)
			offSreamImage = createImage(Fwidght, Fheight);
			Graphics gOff = offSreamImage.getGraphics();
			paint(gOff);
			gOff.dispose();//�ͷ�ͼ����������Դ
			g.drawImage(offSreamImage, 0, 0, null);
	}
	
	
}
