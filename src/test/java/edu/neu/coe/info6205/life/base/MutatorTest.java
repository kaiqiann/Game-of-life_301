package edu.neu.coe.info6205.life.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MutatorTest {
    
    @Test
    public void testMutator1() {
            List<Integer> gene=new ArrayList<>();
            gene.add(207940458);
            gene.add(200010234);
            gene.add(100870022);
            gene.add(208540034);
            List<Integer> origin=new ArrayList<>(gene);
            Mutator my = new Mutator();
            List<Integer> gene1 = my.Mutate(origin);
            // for(int i=0; i<gene1.size(); i++)
            //     assertNotEquals(gene1.get(i), origin.get(i));
            assertNotEquals(gene.size(), gene1.size());
    }

    @Test
    public void testMutator2() {
            List<Integer> gene2=new ArrayList<>();
            gene2.add(210000000);
            gene2.add(100000000);
            gene2.add(110001000);
            gene2.add(208540034);
            gene2.add(100870697);
            gene2.add(208540345);
            List<Integer> origin2=new ArrayList<>(gene2);
            Mutator my2 = new Mutator();
            List<Integer> gene11 = my2.Mutate(origin2);
            // for(int i=0; i<gene1.size(); i++)
            //     assertNotEquals(gene1.get(i), origin.get(i));
            assertEquals(gene11.size(), gene2.size());
            
    }

    @Test
    public void testMutator3() {
            List<Integer> gene3=new ArrayList<>();
            gene3.add(110000000);
            List<Integer> origin3=new ArrayList<>(gene3);
            Mutator my3 = new Mutator();
            List<Integer> gene13 = my3.Mutate(origin3);
            // for(int i=0; i<gene1.size(); i++)
            //     assertNotEquals(gene1.get(i), origin.get(i));
            assertEquals(gene13.size(), gene3.size());
    }

}