package local.plan.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	boolean left,right,up,down;
	boolean live=true;
	Explode explode = null;
	
	public Plane(Image im, double d, double e) {
		super(im, d, e);
		setWideght(im.getWidth(null));
		setHeight(im.getHeight(null));
		setSpeed(4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawSelf(Graphics g) {
		// TODO Auto-generated method stub
		if(!live) {
			if(explode == null)
				explode=new Explode(this.getX()-this.getWeight(), this.getY()-this.getHeight());
			explode.draw(g);
			return ;
		}
		super.drawSelf(g);
		g.drawImage(getImg(), (int)getX(), (int)getY(), null);
		if(left) {
			setX(getX()-getSpeed());
		}
		if(right) {
			setX(getX()+getSpeed());
		}
		if(up) {
			setY(getY()-getSpeed());
		}
		if(down) {
			setY(getY()+getSpeed());
		}
	}
	//添加方向
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;
		default:
			break;
		}
	}
	//取消方向
		public void minusDirection(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left=false;
				break;
			case KeyEvent.VK_UP:
				up=false;
				break;
			case KeyEvent.VK_RIGHT:
				right=false;
				break;
			case KeyEvent.VK_DOWN:
				down=false;
				break;
			default:
				break;
			}
		}
}
