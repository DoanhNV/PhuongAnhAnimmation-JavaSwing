package com.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final int INTERVAL_TIME_OF_SNOW = 1000;

	public Main() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 20, 1000, 700);
		final Sky sky = new Sky();
		sky.setBackground(Color.blue);
		this.add(sky);
		this.setVisible(true);
		this.dropSnow(sky);
	}
	
	public void dropSnow(final Sky sky) {
		Timer timer = new Timer(INTERVAL_TIME_OF_SNOW, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sky.dropSnow();
			}
		});
		timer.start();
	}

	public static void main(String[] args) {
		new Main();
	}
}
