package edu.neu.coe.info6205.life.base;

import java.util.*;

import edu.neu.coe.info6205.life.base.Game.Behavior;

class Selector {

	static double growrate = 0;
	static long gen = 0;

	public static void Select(List<String> patternList) {
		Map<String, Behavior> select = new HashMap<>();
		for (String pattern : patternList) {
//            Behavior b = Fitness(pattern);
//            long generation = b.generation;
//            growrate += b.growth;
			select.put(pattern, Fitness(pattern));
		}

		List<Map.Entry<String, Behavior>> list = new ArrayList<>(select.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Behavior>>() {
			@Override
			public int compare(Map.Entry<String, Behavior> o1, Map.Entry<String, Behavior> o2) {
				if (o2.getValue().generation > o1.getValue().generation)
					return 1;
				else if(o2.getValue().generation < o1.getValue().generation)
					return -1;
				else if (o2.getValue().growth > o1.getValue().growth)
					return 1;
				else if(o2.getValue().growth > o1.getValue().growth)
					return -1;
				return 0;
			}
		});

		patternList.clear();
		for (int i = 0; i < list.size() / (1 / Profile.SURVIVE_RATE); i++) {
			patternList.add(list.get(i).getKey());
			gen += list.get(i).getValue().generation;
		}
	}

//	public static long Fitness(String pattern) {
//		// Behavior generations = Game.run(0L, pattern);
//		long generations = Game.cRun(pattern);
//		return generations;
//	}

	public static Behavior Fitness(String pattern) {
		Behavior generations = Game.cRun(pattern);
		return generations;
	}

	public static String getBest(List<String> patternList) {
		Select(patternList);
		Map<String, Double> select = new HashMap<>();
//        for(String pattern: patternList){
//			Behavior b = getBehavior(pattern);
//			double generation = b.growth;
//			select.put(pattern, generation);
//        	select.put(pattern, Fitness(pattern));
//        }
		for (String pattern : patternList) {
			Behavior b = Fitness(pattern);
			double grrate = b.growth;
			if (b.generation == Profile.GAME_MAX_GENERATION)
				select.put(pattern, grrate);
		}

		List<Map.Entry<String, Double>> list = new ArrayList<>(select.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			@Override
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		return list.get(0).getKey();
	}

	public static double getRate() {
		return growrate / Profile.GA_POPULATION;
	}

	public static double getGen() {
		return gen / (Profile.GA_POPULATION * Profile.SURVIVE_RATE);
	}
}

