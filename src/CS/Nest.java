/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Nest {
    
    private int num_egg;
    private int ub;
    private int lb;
    private double fitness;
    private ArrayList<Cuckoo> cuckoo_eggs;
    Random rand;
    
    
    public Nest(int egg,int ub,int lb){
        rand = new Random();
        this.cuckoo_eggs = new ArrayList<Cuckoo>(egg);
        this.num_egg = egg;
        this.ub = ub;
        this.lb = lb;
        this.initiateCuckooEgg();
    }
   
    
    private void initiateCuckooEgg(){
        Cuckoo cuckoo;
        for(int i=0;i<num_egg;i++){
            cuckoo = new Cuckoo();
            cuckoo.setEgg(lb+(ub-lb)*this.rand.nextDouble());
            this.cuckoo_eggs.add(i, cuckoo);
        }
       this.fitness = this.doEggHolderFitnessFunction(cuckoo_eggs);
//          this.fitness = this.doMichaelWichzMin(cuckoo_eggs);
    }
    
    public int getNum_egg() {
        return num_egg;
    }

    public int getUb() {
        return ub;
    }

    public int getLb() {
        return lb;
    }

    public ArrayList<Cuckoo> getCuckoo_eggs() {
        return cuckoo_eggs;
    }
    
    public double doEggHolderFitnessFunction(ArrayList<Cuckoo> cuckoo){
//      TODO : evaluasi cuckoo search fitness, eggHolder
        double x = cuckoo.get(0).getEgg();
        double y = cuckoo.get(1).getEgg();
        return -(y+47)*Math.sin(Math.sqrt(Math.abs(y+x/2+47)))
					-x*Math.sin(Math.sqrt(Math.abs(x-(y+47))));
    }
    
    public double doMichaelWichzMin(ArrayList<Cuckoo> cuckoo){
//        TODO : evaluasi cuckoo search fitness, michaelwichz
        final double M = 10;
        double x = cuckoo.get(0).getEgg();
        double y = cuckoo.get(1).getEgg();
         return
            -Math.sin(x) * Math.pow(Math.sin(  Math.pow(x, 2) / Math.PI), 2*M) -
             Math.sin(y) * Math.pow(Math.sin(2*Math.pow(y, 2) / Math.PI), 2*M);
    }
        
    public double getFitness(){
        return this.fitness;
    }
    
    public void setFitness(double fitness){
        this.fitness = fitness;
    }
}
