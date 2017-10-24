package com.main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Bird {

	private static final String IMAGE_NAME = "image/sprite";
	private static final String IMAGE_EXTENSION = ".gif";
	private static final int NUMBER_OF_IMAGE = 14;
	private List<Image> images = new ArrayList<Image>(NUMBER_OF_IMAGE);
	private int xBird;
	private int yBird;

	public Bird() {
		init();
	}

	public Bird(int xBird, int yBird) {
		this.xBird = xBird;
		this.yBird = yBird;
	}

	public void init() {
		for (int i = 0; i < NUMBER_OF_IMAGE; i++) {
			String filepath = IMAGE_NAME + (i + 1) + IMAGE_EXTENSION;
			String fileName = getClass().getClassLoader().getResource(filepath).getFile();
			try {
				images.add(ImageIO.read(new File(fileName)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public int getxBird() {
		return xBird;
	}

	public void setxBird(int xBird) {
		this.xBird = xBird;
	}

	public int getyBird() {
		return yBird;
	}

	public void setyBird(int yBird) {
		this.yBird = yBird;
	}

}
