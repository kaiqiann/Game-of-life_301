package edu.neu.coe.info6205.life.base;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;
public class PhenotypeTest {

	@Test
	public void Phenotest() {
		Genotype gn = new Genotype();
		gn.addChromosome(new Chromosome(109870321));
		gn.addChromosome(new Chromosome(103320221));
		gn.addChromosome(new Chromosome(133863431));
		String temp = "321 987, 221 332, 3431 3386, ";
		Phenotype pn = new Phenotype(gn);
		assertEquals(temp,pn.getPheno());
	}
	
	@Test
	public void PhenoGenotest() {
		Genotype gn = new Genotype();
		gn.addChromosome(new Chromosome(109870321));
		gn.addChromosome(new Chromosome(103320221));
		gn.addChromosome(new Chromosome(133863431));
		String temp = "321 987, 221 332, 3431 3386, ";
		Phenotype pn = new Phenotype(gn);
		assertEquals(temp,pn.getPheno());
	}
}
