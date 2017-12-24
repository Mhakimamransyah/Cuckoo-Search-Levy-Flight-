/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author M.Hakim Amransyah
 */
public class CuckooSearch {
    
    private ArrayList<Nest> nests;
    private LevyFlight lv;
    private int ub,lb;
    private int egg;
    private double pa;
    
    public CuckooSearch(int num_nest,int ub,int lb,int egg,double pa){
        lv = new LevyFlight();
        this.nests = new ArrayList<Nest>(num_nest);
        this.ub = ub;
        this.lb = lb;
        this.egg = egg;
        this.pa = pa;
        this.inisiasiSarangCuckoo(num_nest);
    }
    
    private void inisiasiSarangCuckoo(int num_nest){
        Nest nest;
        for(int i=0;i<num_nest;i++){
            nest = new Nest(this.egg,this.ub,this.lb);
            nests.add(i,nest);
        }
    }
   
    public void doSearch(int max_gen){
       int i=0;
       int cur_index;
       int nest_size = this.nests.size();
       while(i<max_gen){
           /*
           *
           *Algoritma utama cuckoo search 
           *
           */
           Nest new_nest = lv.getACuckooByLevyFlight(this.nests.get(new Random().nextInt(nest_size)),this.getBestNest());
           cur_index =  new Random().nextInt(nest_size);
           Nest current_nest = this.nests.get(cur_index);
           if(current_nest.getFitness() > new_nest.getFitness()){
               this.nests.set(cur_index, new_nest);
           }
           this.sortNest();
           this.reInitiateWorstNest();
           i++;
       }
       this.sortNest();
       this.debugCuckooEgg();
       System.out.println("---------------------------------\n\n");
    }
    
    private Nest getBestNest(){
//       TODO : ambil sarang cuckoo terbaik    
      int best_index = 0;
      for(int i=0;i<this.nests.size();i++){
          if(this.nests.get(best_index).getFitness() > this.nests.get(i).getFitness()){
              best_index = i;
          }
      }
      return this.nests.get(best_index);
    }
    
    private void sortNest(){
//       TODO : urutkan sarang dengan fitness terkecil
       for(int j=this.nests.size()-1;j>=0;j--){
           for(int i=0;i<=j;i++){
              Nest temp;
              if(i!= j){
                    if(this.nests.get(i).getFitness() > this.nests.get(i+1).getFitness()){
                      temp = this.nests.get(i);
                      this.nests.set(i, this.nests.get(i+1));
                      this.nests.set(i+1,temp);
                    }   
              }       
           }
       }
    }
    
    private void reInitiateWorstNest(){
//      TODO : abaikan sarang yang buruk , induk sarang akan menemukannya dengan kemungkinan pa
       int start = (int) Math.ceil(this.pa * this.nests.size());
       int keep = this.nests.size() - start;    
       Nest re;
       for(int i=keep;i<this.nests.size();i++){
          re = new Nest(this.egg,this.ub,this.lb);
          this.nests.set(i, re);
       }
    }
    
    public Nest getMostOptimalNest(){
       return this.nests.get(0);
    }
    
   
    public void debugCuckooEgg(){
        int i=0;
         for(Nest nest: nests){
             System.out.println("Sarang ke "+i);
             for(Cuckoo cuckoo : nest.getCuckoo_eggs()){
                 System.out.println("sol : "+cuckoo.getEgg());
             }
             System.out.println("fitness : "+nest.getFitness());
             System.out.println("\n");
             i++;
         }
         
         System.out.println("---FITNESS SANGKAR TERBAIK----");
         System.out.println(" "+this.getBestNest().getFitness());
    }
}
