package edu.neu.coe.info6205.life.base;

public class Chromosome {
	private int chromosome;
	
	public Chromosome(int chromosome) {
		this.chromosome = chromosome;
	}
	
	public int getXaix() {
		return chromosome%10000;
	}
	
	public int getYaix() {
		return (chromosome/10000) %10000;
	}
	
	public int getChromosome() {
		return chromosome;
	}

	public boolean isAlive() {
		if(chromosome/100000000 ==1) {
			return true;
		}
		return false;
	}
}
