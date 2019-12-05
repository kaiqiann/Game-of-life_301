package edu.neu.coe.info6205.life.base;

import java.util.ArrayList;
import java.util.List;

public class Genotype {
	private List<Chromosome> geno;
	
	public Genotype() {
		geno = new ArrayList<>();
	}
	
	public void addChromosome(Chromosome chromosome) {
		geno.add(chromosome);
	}
	
	public List<Chromosome> getList(){
		return geno;
	}


	public void setGeno(List<Chromosome> geno) {
		this.geno = geno;
	}

	public List<Integer> intList(List<Chromosome> geno){
		List<Integer> ls = new ArrayList<Integer>();
		geno.forEach(g -> ls.add(g.getChromosome()));
		
		return ls;
	} 
}
