package local.plan.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	private Image img;
	private double x,y;
	private int speed;
	private int widght,height;
	
	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = 2;
	}

	public GameObject(Image img, double x, double y, int speed, int widght, int height) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.widght = widght;
		this.height = height;
	}

	public GameObject() {
	}
	/**
	 * 绘制图形
	 * @param g
	 */
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
	}
	/**
	 * 获取物体所在的矩形，方便碰撞检测
	 * @return
	 */
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, height, widght);
	}
	
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getWeight() {
		return widght;
	}
	public void setWideght(int widght) {
		this.widght = widght;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
