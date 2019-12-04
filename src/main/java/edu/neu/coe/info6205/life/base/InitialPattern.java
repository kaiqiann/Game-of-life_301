package edu.neu.coe.info6205.life.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class InitialPattern {
//	private final int population = 1000;
	private String pattern = "";
	private List<String> plist = new ArrayList<>();
	
	public String getPattern() {
		return pattern;
	}

	public InitialPattern() {
		//add GA pattern here
		initialize();
	}
	/**
	 * Initialize function basically generates a string of points
	 * 9 blocks as a unit to generate multiple points and append into string builder
	 * uses set to eliminate duplicate points
	 */
	public void initialize() {
		StringBuilder sb = new StringBuilder();
//		int counter = 0;
		Set<String> hs = new HashSet<String>();
		for(int  x = 1; x <=20; x=x+3) {
			for(int y = 1; y <=20; y=y+3) {
				String temp = String.valueOf(x)+" "+String.valueOf(y);
				sb.append(temp+ ", ");
				hs.add(temp);
				int random = (int) (Math.random()*6);
				for(int i =0; i<random;i++) {
					int xaix = (int) (Math.random()*3)+x;
					int yaix = (int) (Math.random()*3)+y;
					temp = String.valueOf(xaix)+" "+String.valueOf(yaix);
					if(!hs.contains(temp)) {
						hs.add(temp);
						sb.append(temp+ ", ");
					}
				}
			}
		}
		pattern = sb.toString();
		plist.add(pattern);
//		System.out.println(counter);
	}
	
	
	public List<String> getPlist() {
		return plist;
	}

	/*public static void main(String args[]) {
		InitialPattern ip = new InitialPattern();
		System.out.println(ip.getPattern());
	}*/
	
	

}
