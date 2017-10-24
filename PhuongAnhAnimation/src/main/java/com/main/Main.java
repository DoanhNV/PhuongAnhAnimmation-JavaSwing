package com.main;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final int INTERVAL_TIME_OF_SNOW = 1;
	private static final int NUMBER_OF_SNOW = 300;
	private static final int INDEX_OF_START_BIRD = 0;
	private static final int X_BIRD = 0;
	private static final int Y_BIRD = 0;
	private static final String TITLE = "Snow Annimation";
	private int width;
	private int height;

	public Main() {
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0, 0, width, height);
		this.setTitle(TITLE);
		final Sky sky = new Sky(width, height);
		sky.setBackground(Color.blue);
		sky.setListSnows(init());
		sky.getBird().setxBird(X_BIRD);
		sky.getBird().setyBird(Y_BIRD);
		sky.getBird2().setxBird(X_BIRD);
		sky.getBird2().setyBird(Y_BIRD+15);
		this.add(sky);
		this.setVisible(true);
		this.dropSnow(sky);
	}

	public void dropSnow(final Sky sky) {
		Timer timer = new Timer(INTERVAL_TIME_OF_SNOW, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBirdStep(sky);
				changePosition(sky);
				sky.dropSnow();
			}
		});
		timer.start();
		
		Timer birdTimer = new Timer(INTERVAL_TIME_OF_SNOW, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBirdStep(sky);
				changeBird2Step(sky);
			}
		});
		birdTimer.start();
	}

	public List<Snow> init() {
		List<Snow> listSnows = new ArrayList<Snow>();
		for (int i = 0; i < NUMBER_OF_SNOW; i++) {
			int x = getRandomValue(1, height);
			int y = getRandomValue(1, width);
			int radius = getRandomValue(1, 10);
			listSnows.add(new Snow(x, y, radius, radius));
		}
		return listSnows;
	}

	public static int getRandomValue(int minValue, int maxValue) {
		return (int) (Math.random() * maxValue);
	}

	public void changePosition(Sky sky) {
		List<Snow> listSnows = sky.getListSnows();
		for (int i = 0; i < listSnows.size(); i++) {
			Snow snow = listSnows.get(i);
			if (snow.getX() > height) {
				snow.setX(-2);
			}
			if (snow.getY() > width) {
				snow.setY(-2);
			}
		}
	}

	public void changeBirdStep(Sky sky) {
		int indexOfBird = sky.getIndexOfBird();
		Bird bird = sky.getBird();
		int xBird = bird.getxBird();
		int yBird = bird.getyBird();
		bird.setxBird(xBird + 2);
		if (indexOfBird < 7) {
			yBird += 4;
		} else {
			yBird -= 4;
		}
		bird.setyBird(yBird);
		if(xBird > width) {
			bird.setxBird(-2);
		}
		if (indexOfBird < 13) {
			sky.setIndexOfBird(indexOfBird + 1);
		} else {
			sky.setIndexOfBird(INDEX_OF_START_BIRD);
		}
	}
	
	public void changeBird2Step(Sky sky) {
		int indexOfBirdStep = sky.getIndexOfBird();
		Bird bird2 = sky.getBird2();
		int xBird = bird2.getxBird();
		int yBird = bird2.getyBird();
		bird2.setxBird(xBird + 2);
		if (indexOfBirdStep < 7) {
			yBird += 3;
		} else {
			yBird -= 3;
		}
		bird2.setyBird(yBird);
		if(xBird > width) {
			bird2.setxBird(-2);
		}
		if (indexOfBirdStep < 13) {
			sky.setIndexOfBird(indexOfBirdStep + 1);
		} else {
			sky.setIndexOfBird(INDEX_OF_START_BIRD);
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}
