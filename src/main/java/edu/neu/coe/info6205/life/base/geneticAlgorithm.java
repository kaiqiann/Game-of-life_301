package edu.neu.coe.info6205.life.base;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class geneticAlgorithm {
	
	public List<String> initialPopulation(){
		
		List<String> population = new ArrayList<>();
		for(int i = 0; i < Profile.GA_POPULATION;i++) {
			InitialPattern ip = new InitialPattern();
			population.add(ip.getPattern());
		}
		return population;
	}
	
	public String run(List<String> population) {

		for (int i = 0; i < Profile.MAX_GENERATION; i++) {
			
			Selector.Select(population);
			List<String> current = new ArrayList<>();
			for(String s:population) {
				current.add(s);
			}
			
			// population ==> chromosome
			for(String s:population) {
				
				Mutator.Mutate(gene);
				
				current.add(e);
			}
			population = current;
		}
		return Selector.getBest(population);
	}	
}
