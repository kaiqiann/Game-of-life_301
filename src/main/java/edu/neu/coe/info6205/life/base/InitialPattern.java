package edu.neu.coe.info6205.life.base;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class InitialPattern {
//	private final int population = 1000;
	private String pattern = "";
	private List<String> plist = new ArrayList<>();
	private long seed;
	
	public String getPattern() {
		return pattern;
	}

	public InitialPattern(long seed) {
		//add GA pattern here
		this.seed = seed;
//		setSeed();
		initialize(seed);
	}
	/**
	 * Initialize function basically generates a string of points
	 * 9 blocks as a unit to generate multiple points and append into string builder
	 * uses set to eliminate duplicate points
	 */
	public void initialize(long seed) {
		StringBuilder sb = new StringBuilder();
		Set<String> hs = new HashSet<String>();

		Random rd = new Random(seed);
//		int i =0;
		/*while(i < Profile.initialcount) {
			String temp = String.valueOf(rd.nextInt()*Profile.initialcount)+" "+String.valueOf(rd.nextInt()*Profile.initialcount);
			if(!hs.contains(temp)) {
				hs.add(temp);
				sb.append(temp+ ", ");
			}
			i++;
		}*/
		
		for(int  x = 0; x <=20; x=(int) (x+rd.nextDouble()*5)) {
			for(int y = 0; y <=30; y=(int) (y+rd.nextDouble()*5)) {
				String temp = String.valueOf(x)+" "+String.valueOf(y);
				if(!hs.contains(temp)) {
					hs.add(temp);
					sb.append(temp+ ", ");
				}
//				rd = new Random(Profile.RANDOM_SEED);
				int random = (int) (rd.nextDouble()*6);
				for(int i =0; i<random;i++) {
//					rd = new Random(Profile.RANDOM_SEED+10000);
					int xaix = (int) (rd.nextDouble()*3)+x;
//					rd = new Random(Profile.RANDOM_SEED+2000);
					int yaix = (int) (rd.nextDouble()*3)+y;
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
	}
	
	public long getSeed() {
		return seed;
	}

	public void setSeed() {
		this.seed = System.currentTimeMillis();
	}

	public List<String> getPlist() {
		return plist;
	}
/*
	public static void main(String args[]) {
		InitialPattern ip = new InitialPattern(Profile.RANDOM_SEED);
		System.out.println(ip.getPattern());
		Random rd = new Random(ip.getSeed());
		System.out.println(rd.nextInt());
		Random rd1 = new Random(ip.getSeed()+10000);
		System.out.println(rd1.nextInt());
		Random rd2 = new Random(ip.getSeed()+2000);
		System.out.println(rd2.nextInt());
	}

*/

}
