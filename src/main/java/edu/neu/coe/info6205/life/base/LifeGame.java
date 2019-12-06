package edu.neu.coe.info6205.life.base;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class LifeGame extends JFrame {
	private final World world;
	//JButton button = new JButton ("button");
	static JMenu location=new JMenu();
	public LifeGame(int rows,int columns)
	{
		world=new World(rows, columns);
		world.setBackground(Color.WHITE);
		Thread n = new Thread(world);
		n.start();
		getContentPane().add(world);
	}
	
	public static void main(String[]args)
	{
		LifeGame frame = new LifeGame(300, 300);
		
		JMenuBar menu=new JMenuBar();
		frame.setJMenuBar(menu);
		
		
		JMenu options =new JMenu("Options");
		menu.add(options);
	
		JMenuItem start=options.add("Start");
		start.addActionListener(frame.new StartActionListener());
		JMenuItem random=options.add("Random");
		random.addActionListener(frame.new RandomActionListener());
		
		JMenuItem stop=options.add("Stop");
		stop.addActionListener(frame.new StopActionListener());
		JMenuItem pause=options.add("Pause");
		pause.addActionListener(frame.new PauseActionListener());

		JMenuItem clean=options.add("Kill");
		clean.addActionListener(frame.new CleanActionListener());
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1007,859);
		frame.setTitle("Game of Life");
		frame.setVisible(true);
		frame.setResizable(false);
	}
	class RandomActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.diy=false;
			world.clean=false;
			world.setBackground(Color.WHITE);
			//world.setStop();
			world.setRandom();
		}
	}
	class StartActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//world.begintime=System.currentTimeMillis();
			world.setBackground(Color.WHITE);
			world.diy=false;
			world.clean=false;
			world.setShape();
		}
	}
	class StopActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//world.time=0;
			world.setBackground(Color.WHITE);
			world.diy=false;
			world.clean=false;
			world.setStop();
		}
	}
	class PauseActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setBackground(Color.WHITE);
			world.diy=false;
			world.clean=false;
			world.setPause();
		}
	}

	class CleanActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.clean=true;
			world.diy=false;
			world.setBackground(Color.orange);
		}
	}

}

