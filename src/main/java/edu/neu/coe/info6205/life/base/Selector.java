package edu.neu.coe.info6205.life.base;

import java.util.*;

import edu.neu.coe.info6205.life.base.Game.Behavior;

class Selector {

    static double growrate = 0;
    static long gen = 0;

    public static void Select(List<String> patternList) {
        Map<String, Long> select = new HashMap<>();
        for(String pattern: patternList){
//            Behavior b = Fitness(pattern);
//            long generation = b.generation;
//            growrate += b.growth;
            select.put(pattern, Fitness(pattern));
        }

        List<Map.Entry<String, Long>> list = new ArrayList<>(select.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());   
            }
        });

        patternList.clear();
        for (int i=0; i<list.size()/(1/Profile.SURVIVE_RATE); i++) {
            patternList.add(list.get(i).getKey());
            gen += list.get(i).getValue();
        }
    }

    public static long Fitness(String pattern){
        //Behavior generations = Game.run(0L, pattern);
        long generations = Game.cRun(pattern);
        return generations; 
    }

    public static String getBest(List<String> patternList) {
        Map<String, Long> select = new HashMap<>();
        for(String pattern: patternList){
//            Behavior b = Fitness(pattern);
//            long generation = b.generation;
//            select.put(pattern, generation);
        	select.put(pattern, Fitness(pattern));
        }

        List<Map.Entry<String, Long>> list = new ArrayList<>(select.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());   
            }
        });

        return list.get(0).getKey();
    }

    public static double getRate() {
        return growrate / Profile.GA_POPULATION;
    }
    
    public static double getGen() {
    	return gen / (Profile.GA_POPULATION*Profile.SURVIVE_RATE);
    }
}