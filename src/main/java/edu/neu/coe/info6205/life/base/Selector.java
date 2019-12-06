package edu.neu.coe.info6205.life.base;

import java.util.*;

import edu.neu.coe.info6205.life.base.Game.Behavior;

class Selector {

    static double growrate = 0;

    public static void Select(List<String> patternList) {
        Map<String, Double> select = new HashMap<>();
        for(String pattern: patternList){
            Behavior b = Fitness(pattern);
            long generation = b.generation;
            growrate += b.growth;
            select.put(pattern, b.growth);
        }

        List<Map.Entry<String, Double>> list = new ArrayList<>(select.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());   
            }
        });

        patternList.clear();
        for (int i=0; i<list.size()/2; i++) {
            patternList.add(list.get(i).getKey());
        }
    }

    public static Behavior Fitness(String pattern){
        Behavior generations = Game.run(0L, pattern);
        return generations; 
    }

    public static String getBest(List<String> patternList) {
        Map<String, Double> select = new HashMap<>();
        for(String pattern: patternList){
            Behavior b = Fitness(pattern);
            //Double generation = b.generation;
            select.put(pattern, b.growth);
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
}
