package com.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class World extends JFrame {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final double WIDTH = 720;
	private static final double HEIGHT = 500;
	private static final int SPEED_OF_MOVING = 50;
	private Sky sky;
	
	
	public World() {
		setPreferredSize(new Dimension(720, 500));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize((int)WIDTH, (int)HEIGHT);
		this.setVisible(true);
		sky = new Sky((int)WIDTH, (int)HEIGHT);
		this.add(sky);
		this.move();
	}
	
	public static void main(String[] args){
		new World();
	}
	
	public void move(){
		Timer timer = new Timer(SPEED_OF_MOVING, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		timer.start();
	}
	

}
