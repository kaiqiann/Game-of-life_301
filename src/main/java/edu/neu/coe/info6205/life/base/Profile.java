package edu.neu.coe.info6205.life.base;

public class Profile {
	//Game of Life
	static final int GAME_MAX_GENERATION = 2000;
	
	//Genetic Algorithm
	static final int MAX_GENERATION = 15;
	
	static final int GA_POPULATION = 20;
	
	static final int CYCLECHECK_NUM = 10;
	
	static long RANDOM_SEED = 15755951;
	
	//Select
	static final double SURVIVE_RATE = 0.25;
	
	//Mutate
	static final double ADD_PROBABILITY = 0.15;
	
	static final double DELETE_PROBABILITY = 0.15;
	
	static final double MUTATE_PROBABILITY = 0.2;
	
}
