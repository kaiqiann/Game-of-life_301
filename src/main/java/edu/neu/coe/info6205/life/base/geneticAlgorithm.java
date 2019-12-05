package edu.neu.coe.info6205.life.base;

import java.util.List;

public class geneticAlgorithm {

	public String run(List<String> population) {

		for (int i = 0; i < Profile.MAX_GENERATION; i++) {
			Selector.Select(population);
			// population ==> chromosome
			// Mutator.Mutate(gene);
			//gene ==> poplulation;
		}
		return "";//getBest(population);
	}	
}
