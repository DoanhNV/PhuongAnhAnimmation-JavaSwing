package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Sky extends JPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final Color SKY_COLOR = new Color(107, 146, 185);
	private static final double[] LIMITS = new double[] { 0, 7, 4, -1 };
	private int width;
	private int height;
	private GeneralPath mountain = new GeneralPath();
	private GeneralPath vane = new GeneralPath();
	private int posision;
	private int xSun;
	private int ySun;
	private boolean fly;
	private boolean dark;

	public Sky() {

	}

	public Sky(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	protected void paintComponent(Graphics graphic) {
		initMountain(mountain);
		Graphics2D graphics2D = (Graphics2D) graphic.create();
		if (dark) {
			graphics2D.setColor(Color.BLACK);
			graphics2D.fillRect(0, 0, width, height);
			paintSun(graphics2D, Color.WHITE);
		} else {
			graphics2D.setColor(SKY_COLOR);
			graphics2D.fillRect(0, 0, width, height);
			paintSun(graphics2D, Color.YELLOW);
		}

		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		controllLimit(graphics2D, width, height, LIMITS);
		graphics2D.setColor(Color.GREEN);
		graphics2D.fill(mountain);

		initVane(vane);

		AffineTransform transform = graphics2D.getTransform();
		graphics2D.setTransform(transform);
		graphics2D.translate(0.9, 1);
		graphics2D.scale(0.6, 0.6);
		paintWindMill(graphics2D);

		graphics2D.setTransform(transform);
		graphics2D.translate(0.9, 1);
		graphics2D.scale(2, 0.6);
		paintWindMill(graphics2D);

		graphics2D.setTransform(transform);
		graphics2D.translate(0.9, 1);
		graphics2D.scale(0.3, 0.3);
		paintMonster(graphics2D);

		graphics2D.setTransform(transform);
		graphics2D.translate(10 * (posision % 300) / 300.0, 0);
		graphics2D.scale(0.3, 0.3);
		paint_cars_plane(graphics2D);

	}

	public void initMountain(GeneralPath mountain) {
		mountain.moveTo(0, -1);
		mountain.lineTo(0, 0.7);
		mountain.lineTo(1.5, 1.60);
		mountain.lineTo(1.8, 1.3);
		mountain.lineTo(3, 2.1);
		mountain.lineTo(4.7, 0.7);
		mountain.lineTo(6.1, 1.2);
		mountain.lineTo(9, 0.8);
		mountain.lineTo(9, -1);
		mountain.closePath();
	}

	public void controllLimit(Graphics2D graphics2d, int width, int height, double[] limits) {
		graphics2d.scale(width / (limits[1] - limits[0]), height / (limits[3] - limits[2]));
		graphics2d.translate(-limits[0], -limits[2]);
	}

	public void initVane(GeneralPath vane) {
		vane.moveTo(0, 0);
		vane.lineTo(0.5, 0.1);
		vane.lineTo(1.5, 0);
		vane.lineTo(0.5, -0.1);
		vane.closePath();
	}

	public void paintWindMill(Graphics2D graphics2d) {
		graphics2d.setColor(Color.BLACK);
		graphics2d.fill(new Rectangle2D.Double(-0.05, 0.2, 0.1, 2.8));
		graphics2d.translate(0, 3);
		graphics2d.rotate(posision++);
		graphics2d.setColor(Color.GREEN);
		for (int i = 0; i < 5; i++) {
			graphics2d.rotate(Math.PI / 3);
			graphics2d.fill(vane);
		}
	}

	public void paintSun(Graphics2D graphics2d, Color clor) {
		graphics2d.setColor(clor);
		if (ySun == 139) {
			fly = true;
		} else if (ySun == 0) {
			fly = false;
		}
		if (fly) {
			ySun--;
		} else {
			ySun++;
		}
		if (xSun == width) {
			if (!dark) {
				dark = true;
			} else {
				dark = false;
			}
			xSun = -2;
		}
		graphics2d.fillOval(++xSun, ySun, 100, 100);
		System.out.println(xSun + " - " + ySun);
	}

	public void paintMonster(Graphics2D graphics2d) {
		graphics2d.setColor(Color.RED);
		graphics2d.fillArc(400, 50, 100, 100, 10, 300);
	}

	void paint_cars_plane(Graphics2D g2D) {
		AffineTransform transform = g2D.getTransform();
		g2D.translate(-1.5, -0.1);
		g2D.scale(0.8, 0.8);
		paint_wheel(g2D);
		g2D.setTransform(transform);
		g2D.translate(3.5, -0.1);
		g2D.scale(0.8, 0.8);
		paint_wheel(g2D);
		g2D.setTransform(transform);
		g2D.setColor(Color.RED);
		g2D.fill(new Rectangle2D.Double(-2.5, 0, 7, 3));
		g2D.fill(new Rectangle2D.Double(4, 0, 2, 2));
		g2D.setColor(Color.WHITE);
		g2D.fill(new Rectangle2D.Double(-2.0, 1.5, 2, 1));
		g2D.fill(new Rectangle2D.Double(1.5, 1.5, 2, 1));
		g2D.translate(-8.5, -0.1);
		g2D.scale(0.8, 0.8);
		paint_wheel(g2D);
		g2D.setTransform(transform);
		g2D.translate(-11.5, -0.1);
		g2D.scale(0.8, 0.8);
		paint_wheel(g2D);
		g2D.setTransform(transform);
		g2D.setColor(Color.BLUE);
		g2D.fill(new Rectangle2D.Double(-10.5, 1, 2, 1.5));
		g2D.fill(new Rectangle2D.Double(-12.5, 0, 5, 1.5));
		g2D.setColor(Color.WHITE);
		g2D.fill(new Rectangle2D.Double(-9.7, 1, 0.8, 1));
		g2D.setColor(new Color(0, 0, 128));
		g2D.fill(new Ellipse2D.Double(-10.5, 12, 4, 0.5));
		g2D.fill(new Rectangle2D.Double(-8.4, 11.8, 0.25, 0.7));
		g2D.fill(new Rectangle2D.Double(-10.3, 12.1, 0.25, 0.5));

	}

	private void paint_wheel(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		g2D.fill(new Ellipse2D.Double(-1, -1, 2, 2));
		g2D.setColor(Color.LIGHT_GRAY);
		g2D.fill(new Ellipse2D.Double(-0.8, -0.8, 1.6, 1.6));
		g2D.setColor(Color.BLACK);
		g2D.fill(new Ellipse2D.Double(-0.2, -0.2, 0.4, 0.4));
		g2D.rotate(posision);

		for (int i = 1; i <= 15; i++) {
			g2D.rotate(2 * Math.PI / 15);
			g2D.draw(new Rectangle2D.Double(0, -0.1, 1, 0.2));
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
