package local.plan.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.ObjectInputStream.GetField;

public class Explode {
	double x,y;
	static Image[] im = new Image[16];
	static {
		for(int i=0;i < im.length;++i) {
			im[i] = GameUtil.getImage("images/explode/e"+(i+1)+".gif");
			im[i].getWidth(null);
		}
	}
	int count;
	public void draw(Graphics g) {
		if(count>=im.length)
			return ;
			//count=0;
		g.drawImage(im[count],(int)x,(int)y,null);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count++;
	}
	public Explode(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
}
