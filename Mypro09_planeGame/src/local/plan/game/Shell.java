package local.plan.game;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends GameObject{
	double degree;
	public Shell() {
		setX(200);
		setY(200);
		setHeight(10);
		setWideght(10);
		setSpeed(5);
		degree = Math.random()*Math.PI*2;
	}
	@Override
	public void drawSelf(Graphics g) {
		// TODO Auto-generated method stub
		super.drawSelf(g);
		Color c = g.getColor();
		
		g.setColor(Color.YELLOW);
		g.fillOval((int)getX(), (int)getY(), getWeight(), getHeight());
		
		setX(getX()+getSpeed()*Math.cos(degree));
		setY(getY()+getSpeed()*Math.sin(degree));
		
		if(getX()<=10 || getX()>=MyGameFrame.Fwidght-10) {
			degree=Math.PI-degree;
		}
		if(getY()<=40 || getY()>=MyGameFrame.Fheight-30) {
			degree=-degree;
		}
		g.setColor(c);
	}
	
}
