package edu.neu.coe.info6205.life.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Mutator {
    
    Random rd = new Random(Profile.RANDOM_SEED);

    public Mutator() {
	}

	public List<Chromosome> intList(List<Integer> geno){
		List<Chromosome> ls = new ArrayList<>();
		geno.forEach(g -> ls.add(new Chromosome(g)));
		return ls;
	}

    public List<Integer> Mutate(List<Integer> gene) {
        //List<Integer> gene=new ArrayList<>();
        List<Integer> mutagene=new ArrayList<>();
        for(int i: gene){
            int x=i % 10000;
            int y=(i/10000) % 10000;
            int alive=i/100000000;
            double xNum = rd.nextDouble();
            double yNum = rd.nextDouble();
            double liveNum = rd.nextDouble();
            double muteNum = rd.nextDouble();
            
            // Mutate the genecode，第一位为1/2，1代表存活，2代表死亡
            // 2-5位为y，6-9位为x
            if(muteNum < 0.5) {
                double xr = rd.nextDouble();
                if(xr < 0.5){
                    if(xNum < 0.5 && (x+10)<=1000) {
                        x+=10;
                    }
                    if(xNum >= 0.5 && (x-10)>=0){
                        x-=10;
                    }
                } else {
                    if(xNum < 0.5 && (x+1)<=1000) {
                        x++;
                    }
                    if(xNum >= 0.5 && (x-1)>=0){
                        x--;
                    }
                }  
            } else {
                double yr = rd.nextDouble();
                if(yr < 0.5) {
                    if(yNum < 0.5 && (y+10)<=1000) {
                        y+=10;
                    }
                    if(yNum >= 0.5 && (y-10)>=0){
                        y-=10;
                    }
                } else {
                    if(yNum < 0.5 && (y+1)<=1000) {
                        y++;
                    }
                    if(yNum >= 0.5 && (y-1)>=0){
                        y--;
                    }
                }
            }

            if(liveNum < 0.5) {
                if(alive==1) alive=2;
                else alive=1;
            }

            //重新拼接code
            i=alive*100000000 + y*10000 + x;
//            if(alive == 1) 
            mutagene.add(i);
        }       
        
        List<Integer> res=new ArrayList<>();
        for(int i:mutagene) res.add(i);
        for(int i:mutagene) {
            int x = i % 10000;
            int y=(i/10000) % 10000;
            int alive=i/100000000;
            double addNum = rd.nextDouble();
            if(addNum < 0.3) {
                    int random_index=(int)(1+rd.nextDouble()*(7-1+1));
                    if(random_index == 1 && (y+1)<=1000) {
                        y++;
                    } 
                    if(random_index == 2 && (x+1)<=1000 && (y+1)<=1000) {
                        x++;
                        y++;
                    }
                    if(random_index == 3 && (x+1)<=1000) {
                        x++;
                    }
                    if(random_index == 4 && (x+1)<=1000 && (y-1)>=0) {
                        x++;
                        y--;
                    }
                    if(random_index == 5 && (y-1)>=0) {
                        y--;
                    }
                    if(random_index == 6 && (x-1)>=0 && (y-1)>=0) {
                        x--;
                        y--;
                    }
                    if(random_index == 7 && (x-1)>=0) {
                        x--;
                    } 
                    if(random_index == 8 && (x-1)>=0 && (y+1)<=1000) {
                        x--;
                        y++;
                    }
             }
             i=alive*100000000 + y*10000 + x;
             if(!res.contains(i)) res.add(i);
        }

        // gene.clear();
        // for(int i:mutagene) gene.add(i);

        return res;
    }
}