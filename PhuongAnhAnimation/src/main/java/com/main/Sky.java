package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Sky extends JPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private static final Color SNOW_COLOR = Color.white;
	private static final Color SKY_COLOR = new Color(107, 146, 185);
	private static final int SPEED_OF_SNOW = 4;

	private Color color;
	private List<Snow> listSnows;
	private int width;
	private int height;
	private Bird bird;
	private Bird bird2;
	private int indexOfBirdStep;


	public Sky(int width, int height) {
		this.width = width;
		this.height = height;
		bird = new Bird();
		bird2 = new Bird();
		indexOfBirdStep = 0;
	}

	public Sky(Color color, int width, int height) {
		this.color = color;
		this.listSnows = new ArrayList<Snow>();
		this.width = width;
		this.height = height;
		bird = new Bird();
		indexOfBirdStep = 0;
		bird2 = new Bird();
	}

	public Sky(Color color, List<Snow> listSnows) {
		this.color = color;
		this.listSnows = listSnows;
	}

	@Override
	protected void paintComponent(Graphics graphic) {
		graphic.setColor(SKY_COLOR);
		graphic.fillRect(0, 0, width, height);

		URL url = getClass().getClassLoader().getResource("image/winter.jpg");
		Image image = null;
		try {
			image = ImageIO.read(new File(url.getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		graphic.drawImage(image, 0, 0, this);
		drawBird(graphic);
		drawBird2(graphic);
		for (int i = 0; i < listSnows.size(); i++) {
			Snow snow = listSnows.get(i);
			snow.setX(snow.getX() + SPEED_OF_SNOW);
			graphic.setColor(SNOW_COLOR);
			graphic.fillOval((int) snow.getY(), (int) snow.getX(), (int) snow.getWidth(), (int) snow.getWidth());
		}

		Toolkit.getDefaultToolkit().sync();
	}

	public void drawBird(Graphics graphic) {
		graphic.drawImage(this.bird.getImages().get(indexOfBirdStep), this.bird.getxBird(), this.bird.getyBird(), this);
	}
	
	public void drawBird2(Graphics graphic) {
		graphic.drawImage(this.bird2.getImages().get(indexOfBirdStep), this.bird2.getxBird(), this.bird2.getyBird(), this);
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

	public Bird getBird() {
		return bird;
	}

	public void setBird(Bird bird) {
		this.bird = bird;
	}

	public int getIndexOfBird() {
		return indexOfBirdStep;
	}

	public void setIndexOfBird(int indexOfBird) {
		this.indexOfBirdStep = indexOfBird;
	}

	public Bird getBird2() {
		return bird2;
	}

	public void setBird2(Bird bird2) {
		this.bird2 = bird2;
	}
	
}
