package edu.neu.coe.info6205.life.base;

import java.util.*;

import edu.neu.coe.info6205.life.base.Game.Behavior;

class Selector {

    public static void Select(List<String> patternList) {
        Map<String, Long> select = new HashMap<>();
        for(String pattern: patternList){
            Behavior generation = Game.run(0L, pattern);
            select.put(pattern, generation.generation);
        }

        List<Map.Entry<String, Long>> list = new ArrayList<>(select.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());   
            }
        });

        patternList.clear();
        for (int i=0; i<list.size()/2; i++) {
            patternList.add(list.get(i).getKey());
        }
    }
}