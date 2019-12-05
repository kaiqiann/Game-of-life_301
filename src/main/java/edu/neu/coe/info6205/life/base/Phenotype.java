package edu.neu.coe.info6205.life.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Phenotype {
	private Genotype geno;
	private String pheno;
	
	public Phenotype(Genotype geno) {
		this.geno = geno;
		transform(geno);
	}
	
	private void transform(Genotype geno) {
		StringBuilder sb = new StringBuilder();
		geno.getList().forEach(g -> {
			int x = g.getXaix();
			int y = g.getYaix();
			String temp = String.valueOf(x)+" "+String.valueOf(y);
			sb.append(temp+", ");
		});
		pheno = sb.toString();
	}
	
	public String getPheno() {
		return pheno;
	}
	
	/*public static void main(String args[]) {
		Genotype gn = new Genotype();
		gn.addChromosome(new Chromosome(109870321));
		gn.addChromosome(new Chromosome(103320221));
		gn.addChromosome(new Chromosome(133863431));
		
		Mutator mt = new Mutator();
		List<Integer> temp = mt.Mutate(gn.intList(gn.getList()));
		gn.setGeno(mt.intList(temp));
				
		String temp = "321 987, 221 332, 3431 3386, ";
		Phenotype pn = new Phenotype(gn);
		System.out.println(pn.getPheno());
		if(temp.equals(pn.getPheno())) {
			System.out.println("yes");
		}
	}*/
}
