package edu.neu.coe.info6205.life.base;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class geneticAlgorithm {

	public List<String> initialPopulation() {

		List<String> population = new ArrayList<>();
		for (int i = 0; i < Profile.GA_POPULATION; i++) {
			InitialPattern ip = new InitialPattern();
			population.add(ip.getPattern());
		}
		return population;
	}

	public String run(List<String> population) {
		
		for (int i = 0; i < Profile.MAX_GENERATION; i++) {
			Selector.growrate = 0;
			System.out.println("current generation:" + (i + 1));
			Selector.Select(population);
			List<String> current = new ArrayList<>();
			for (String s : population) {
				current.add(s);
			}

			// population ==> chromosome
			for (String s : population) {
				Genotype g = new Genotype();
				Mutator m = new Mutator();

				g.toChro(s);
				List<Integer> l = m.Mutate(g.intList(g.getList()));
				List<Chromosome> cl = m.intList(l);
				g.setGeno(cl);
				Phenotype p = new Phenotype(g);

				current.add(p.getPheno());
			}
			population = current;
			System.out.println("growth rate: "+Selector.getRate()+"\n");
		}
		return Selector.getBest(population);
	}
}
