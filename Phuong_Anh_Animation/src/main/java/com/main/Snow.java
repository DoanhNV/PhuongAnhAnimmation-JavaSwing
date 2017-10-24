package com.main;

import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Snow extends JFrame {
	
	public Snow() {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());// set Size to full screeb
		sky s = new sky();
		add(s);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	class sky extends JComponent {
		Color sky = new Color(107, 146, 185);// Almost sky color
		Graphics2D graphics2D;
		Timer timer;
		int maxParticles, w, h;// __,width of screen,hight of scrn
		float angle = 0;
		ArrayList<SnowParticle> particles;

		// dint use array of SnowParticle objects because ArrayList has the special
		// property which
		// it can delete the element inside the array.
		// Even though i din use the function remove() to delete cuz it helps u wen u
		// make Animations such as fire n etc.
		public sky() {
			setDoubleBuffered(false);
			w = Snow.this.getWidth();
			h = Snow.this.getHeight();
			maxParticles = 100;// Nuumber of particles
			particles = new ArrayList<SnowParticle>(maxParticles);
			/** just storing some default random values for particles */
			for (int i = 0; i < maxParticles; i++) {
				particles.add(new SnowParticle((int) (Math.random() * w), (int) (Math.random() * h),
						(int) (Math.random() * 11 + 1), (int) (Math.random() * maxParticles)));
			}
			// setting the timer here but activated later
			timer = new Timer(40, new ActionListener() {
				public void actionPerformed(ActionEvent a) {
					changeParticlePositions();
					repaint();
				}
			});
		}

		public void paintComponent(Graphics g) {
			setDoubleBuffered(true);
			g.setColor(sky);
			g.fillRect(0, 0, w, h);
			g.setColor(Color.white);
			
			// Lets Draw the particles on Screen
			for (int i = 0; i < maxParticles; i++) {
				particles.get(i).X++;
				particles.get(i).Y++;
				g.fillOval(particles.get(i).X, particles.get(i).Y, particles.get(i).R, particles.get(i).R);
			}
			// To insitate the timer
			if (angle == 0.0) {
				timer.start();
				angle = 0.01f;
			}
			Toolkit.getDefaultToolkit().sync();// flushes out all the Graphic Memorys ==>Smooth Rendering
		}

		public void changeParticlePositions() {
			angle += 0.01;// it is in Radians
			for (int i = 0; i < maxParticles; i++) {
				particles.get(i).Y += Math.cos(angle + particles.get(i).D) + 1 + particles.get(i).R / 2;
				particles.get(i).X += Math.sin(angle) * 2;
				// Just to create more randomness
				if (particles.get(i).X > w + 5 || particles.get(i).X < -5 || particles.get(i).Y > h) {
					if (i % 3 > 0) {
						particles.get(i).X = (int) (Math.random() * w);
						particles.get(i).Y = -10;
					} else {
						if (Math.sin(angle) > 0) {
							particles.get(i).X = -5;
							particles.get(i).Y = (int) (Math.random() * h);
						} else {
							particles.get(i).X = w + 5;
							particles.get(i).Y = (int) (Math.random() * h);
						}
					}
				}
			}
		}

		// Stores the parameters of Snow particles
		public class SnowParticle {
			public int X, Y, R, D;

			// x-coordinate,y-coordinate,radius,density
			public SnowParticle(int x, int y, int r, int d) {
				this.X = x;
				this.Y = y;
				this.R = r;
				this.D = d;
			}
		}
	}

	public static void main(String args[]) {
		new Snow();
	}
}