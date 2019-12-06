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
	public Phenotype(String pheno) {
		this.pheno = pheno;
	}
	
	private void transform(Genotype geno) {
		StringBuilder sb = new StringBuilder();
		geno.getList().forEach(g -> {
			if(g.isAlive()) {
				int x = g.getXaix();
				int y = g.getYaix();
				String temp = String.valueOf(x)+" "+String.valueOf(y);
				sb.append(temp+", ");
			}
		});
		pheno = sb.toString();
	}
	
	public String getPheno() {
		return pheno;
	}
}
