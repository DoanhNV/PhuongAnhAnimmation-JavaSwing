package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Sky extends JPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final int NUMBER_OF_SNOW = 100;
	private static final Color SNOW_COLOR = Color.WHITE;
	private static final Color SKY_COLOR = new Color(107, 146, 185);

	private Color color;
	private List<Snow> listSnows;
	private static List<Integer> sizes;

	public Sky() {

	}

	public Sky(Color color) {
		this.color = color;
		this.listSnows = new ArrayList<Snow>();
	}

	public Sky(Color color, List<Snow> listSnows) {
		this.color = color;
		this.listSnows = listSnows;
	}

	public void init(Graphics graphic) {
		for (int i = 0; i < NUMBER_OF_SNOW; i++) {
			int x = getRandomValue(1, 1000);
			int y = getRandomValue(1, 700);
			listSnows.add(new Snow(x, y, 10, 10));
		}
	}

	@Override
	protected void paintComponent(Graphics graphic) {
		graphic.setColor(SKY_COLOR);
		graphic.fillRect(0, 0, 1000, 700);
		this.listSnows = new ArrayList<Snow>();
		this.sizes = new ArrayList<Integer>();
		for (int i = 0; i < NUMBER_OF_SNOW; i++) {
			Snow snow = listSnows.get(i);
			snow.setX(snow.getX()+1);
			snow.setY(snow.getY()+1);
			graphic.fillOval((int)snow.getY(), (int)snow.getX(), 10, 10);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	public static int getRandomValue(int minValue, int maxValue) {
		Random random = new Random();
		int result = random.nextInt(maxValue);
		while (result < minValue) {
			result = random.nextInt(maxValue);
		}
		return result;
	}

	public void dropSnow() {
		repaint();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Snow> getListSnows() {
		return listSnows;
	}

	public void setListSnows(List<Snow> listSnows) {
		this.listSnows = listSnows;
	}

}
