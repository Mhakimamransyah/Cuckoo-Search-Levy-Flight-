/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import CS.CuckooSearch;
/**
 *
 * @author M.Hakim Amransyah
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CuckooSearch cs = new CuckooSearch(15, 512,-512, 2,0.25); //MAX GENERAION, UPPER BOUND, LOWER BOUND,DIMENSIION, PA
        cs.debugCuckooEgg();
        cs.doSearch(100000);
        
        // this is use egg holder function        
        for(int i=0;i<cs.getMostOptimalNest().getCuckoo_eggs().size();i++){
            System.out.println("sol "+(i+1)+" : "+cs.getMostOptimalNest().getCuckoo_eggs().get(i).getEgg());    
        }
        System.out.println("fitness : "+cs.getMostOptimalNest().getFitness());
    }
}
