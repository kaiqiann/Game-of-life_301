package edu.neu.coe.info6205.life.base;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
		Mutator mt = new Mutator();
		List<Integer> temp1 = mt.Mutate(gn.intList(gn.getList()));
		System.out.println(temp1);
		System.out.println(gn.intList(gn.getList()));
		gn.setGeno(mt.intList(temp1));
		Phenotype pn = new Phenotype(gn);
		assertNotEquals(temp,pn.getPheno());
	}
	
	@Test
	public void Phenoalivetest() {
		Genotype gn = new Genotype();
		gn.addChromosome(new Chromosome(209870321));
		gn.addChromosome(new Chromosome(203320221));
		gn.addChromosome(new Chromosome(133863431));
		gn.addChromosome(new Chromosome(237864031));
		
		String temp = "3431 3386, ";
		Phenotype pn = new Phenotype(gn);
		assertEquals(temp,pn.getPheno());
	}
}
