package edu.neu.coe.info6205.life.base;

import java.util.ArrayList;
import java.util.List;

public class geneticAlgorithm {
	private List<Double> gList = new ArrayList<>();

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
			Selector.gen = 0;
			System.out.println("current generation:" + (i + 1));
			Selector.Select(population);
			List<String> current = new ArrayList<>();
			for (String s : population) {
				current.add(s);
			}

			for (String s : population) {
				Genotype g = new Genotype();
				Mutator m = new Mutator();
				g.toChro(s);
				for (int j = 0; j < (1 / Profile.SURVIVE_RATE) - 1; j++) {
					List<Integer> l = m.Mutate(g.intList(g.getList()));
					List<Chromosome> cl = m.intList(l);
					g.setGeno(cl);
					Phenotype p = new Phenotype(g);
					current.add(p.getPheno());
				}
			}
			population = current;
			gList.add(Selector.getGen());
			System.out.println("Average Generation: " + Selector.getGen() + "\n");
		}
		System.out.print("\nAverage generation route: ");
		for (double d : gList) {
			System.out.print(" ," + d);
		}
		System.out.println();
		return Selector.getBest(population);
	}
}
