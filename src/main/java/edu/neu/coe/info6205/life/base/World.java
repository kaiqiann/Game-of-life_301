package edu.neu.coe.info6205.life.base;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class World extends JPanel implements Runnable{
	private final int rows;
	private final int columns;
	JLabel  record;
	boolean diy=false;
	boolean clean=false;
	private int speed=0;
	private int lnum;
	private static int shape[][]=new int [300][300];
	private static int zero[][]=new int [300][300];
	static  int pauseshape[][]=new int [300][300];
	private final CellStatus[][] generation1;
	private final CellStatus[][] generation2;
	private CellStatus[][] currentGeneration;
	private CellStatus[][] nextGeneration;
	private volatile boolean isChanging = false;
	private static String pattern;
	private static int count;

	public World(int rows, int columns, String s, int c)
	{
		this.count = c;
		this.pattern = s;
		this.rows=rows;
		this.columns=columns;
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		record = new JLabel();
		add(record);
		generation1=new CellStatus[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				generation1[i][j]=CellStatus.Dead;
			}
		}
		generation2=new CellStatus[rows][columns];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				generation2[i][j]=CellStatus.Dead;
			}
		}
		currentGeneration=generation1;
		nextGeneration=generation2;
	}
	
	
	public void transfrom(CellStatus[][] generation, int pauseshape[][])
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(generation[i][j]==CellStatus.Active)
				{
					pauseshape[i][j]=1;
				}
				else if(generation[i][j]==CellStatus.Dead)
				{
					pauseshape[i][j]=0;
				}
			}
		}
	}
	public void run()
	{
//		begintime=System.currentTimeMillis();
		while(true)
		{

			synchronized(this)
			{

				while(isChanging)
				{
					
					try
					{
						this.wait();
					}catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
//				repaint();
				sleep(speed);
				for(int i=0;i<rows;i++)
				{
					for(int j=0;j<columns;j++)
					{
						evolve(i,j);
					}
				}
				CellStatus[][]temp=null;
				temp=currentGeneration;
				currentGeneration=nextGeneration;
				nextGeneration=temp;
				for(int i=0;i<rows;i++)
				{
					for(int j=0;j<columns;j++)
					{
						nextGeneration[i][j]=CellStatus.Dead;	
					}
				}
				//transfrom(currentGeneration,shape);
				transfrom(currentGeneration,pauseshape);
				repaint();
				//endtime=System.currentTimeMillis();
				updateNumber();
			}
		}
	}
	
	//update the number of alive count
	public void updateNumber()
	{
		String s = " survival count: " + lnum ;
		record.setForeground(Color.BLACK);
		record.setText(s);
	}
	// paint the points
	public void paintComponent(Graphics g)
	{
		lnum=0;
		super.paintComponent(g);
//		g.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(currentGeneration[i][j]==CellStatus.Active)
				{
					g.setColor(Color.BLACK);
					g.fillRect(j*5, i*5, 5, 5);
					lnum++;

				}
				else
				{
					g.setColor(Color.LIGHT_GRAY);
					g.drawRect(j*5, i*5, 5, 5);
				}
			}
		}
		//if count matched pause
		if(lnum ==World.count) {
			setPause();
			System.out.println(lnum);
		}

	}
	//start operation
	public void setShape()
	{
		
		setShape(helper(World.pattern));
		
	}
	
	public int[][] helper(String s)
	{
		Genotype g = new Genotype();

		g.toChro(s);
		List<Integer> l = g.intList(g.getList());
		
		l.forEach(i ->{
			int x=i % 10000+80;
            int y=(i/10000) % 10000+80;
            int alive=i/100000000;
            shape[x][y] = alive;
            pauseshape[x][y] = shape[x][y];
		});
		setShapetemp(shape);
		return shape;
	}
	
	public void setPause()
	{
		shape=pauseshape;
		setShapetemp(pauseshape);
	}
	//used in pause
	private void setShapetemp(int [][]shape)
	{
		isChanging=true;
		int arrowsRows=shape.length;
		int arrowsColumns=shape[0].length;
		int minimumRows=(arrowsRows<rows)?arrowsRows:rows;
		int minimunColumns=(arrowsColumns<columns)?arrowsColumns:columns;
		synchronized(this)
		{

			for(int i=0;i<rows;i++)
			{
				for(int j=0;j<columns;j++)
				{
					currentGeneration[i][j]=CellStatus.Dead;
				}
			}
			for(int i=0;i<minimumRows;i++)
			{
				for(int j=0;j<minimunColumns;j++)
				{
					if(shape[i][j]==1)
					{
						currentGeneration[i][j]=CellStatus.Active;
					}
				}
			}
			//transfrom(currentGeneration,pauseshape);
			repaint();
			updateNumber();
//			isChanging=true;
//			this.notifyAll();
		}
	}
	//used in start
	private void setShape(int [][]shape)
	{
		isChanging=true;
		int arrowsRows=shape.length;
		int arrowsColumns=shape[0].length;
		int minimumRows=(arrowsRows<rows)?arrowsRows:rows;
		int minimunColumns=(arrowsColumns<columns)?arrowsColumns:columns;
		synchronized(this)
		{
			for(int i=0;i<rows;i++)
			{
				for(int j=0;j<columns;j++)
				{
					currentGeneration[i][j]=CellStatus.Dead;
				}
			}
			for(int i=0;i<minimumRows;i++)
			{
				for(int j=0;j<minimunColumns;j++)
				{
					if(shape[i][j]==1)
					{
						currentGeneration[i][j]=CellStatus.Active;
					}
				}
			}
			isChanging=false;
			this.notifyAll();
		}
		
	}
	
	public void evolve(int x,int y)
	{
		int activeSurroundingCell=0;
		if(isVaildCell(x-1,y-1)&&(currentGeneration[x-1][y-1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x,y-1)&&(currentGeneration[x][y-1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x+1,y-1)&&(currentGeneration[x+1][y-1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x+1,y)&&(currentGeneration[x+1][y]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x+1,y+1)&&(currentGeneration[x+1][y+1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x,y+1)&&(currentGeneration[x][y+1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x-1,y+1)&&(currentGeneration[x-1][y+1]==CellStatus.Active))
			activeSurroundingCell++;
		if(isVaildCell(x-1,y)&&(currentGeneration[x-1][y]==CellStatus.Active))
			activeSurroundingCell++;
		//
		if(activeSurroundingCell==3)
		{
			nextGeneration[x][y]=CellStatus.Active;
		}
		else if(activeSurroundingCell==2)
		{
			nextGeneration[x][y]=currentGeneration[x][y];
		}
		else
		{
			nextGeneration[x][y]=CellStatus.Dead;
		}
	}
	private boolean isVaildCell(int x,int y)
	{
		if((x>=0)&&(x<rows)&&(y>=0)&&(y<columns))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private void sleep(int x)
	{
		try {
			Thread.sleep(80*x);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
	static enum CellStatus
	{
		Active,
		Dead;
	}
	
}

