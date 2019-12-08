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
	public LifeGame(int rows,int columns,String s,int c)
	{
		world=new World(rows, columns, s, c);
		world.setBackground(Color.WHITE);
		Thread n = new Thread(world);
		n.start();
		getContentPane().add(world);
	}
	
	public static void run(String s, int c){
		LifeGame frame = new LifeGame(300, 300, s, c);
		
		JMenuBar menu=new JMenuBar();
		frame.setJMenuBar(menu);
		
		
		JMenu options =new JMenu("Options");
		menu.add(options);
	
		JMenuItem start=options.add("Start");
		start.addActionListener(frame.new StartActionListener());

		JMenuItem pause=options.add("Pause");
		pause.addActionListener(frame.new PauseActionListener());

		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,1000);
		frame.setTitle("Game of Life");
		frame.setVisible(true);
		frame.setResizable(false);
	}
	//to run the jframe
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
	//to pause the program and print the count number
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


}

