/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS;

import java.util.Random;

/**
 *
 * @author M.Hakim Amransyah
 */
public class LevyFlight {
    
    double beta = 1.5;
    
    public Nest getACuckooByLevyFlight(Nest choosen_nest,Nest best_nest){
        Nest newnest = new Nest(choosen_nest.getNum_egg(),choosen_nest.getUb(),choosen_nest.getLb());
        double sigma = this.doSigma();
        double step = this.getStep(sigma);
      
        for(int i=0;i<newnest.getCuckoo_eggs().size();i++){
            double stepsize = 0.01 * step *(choosen_nest.getCuckoo_eggs().get(i).getEgg()-best_nest.getCuckoo_eggs().get(i).getEgg());
            double levy_distribution = choosen_nest.getCuckoo_eggs().get(i).getEgg()+stepsize * new Random().nextGaussian();
            if(levy_distribution > choosen_nest.getUb()){
                levy_distribution = choosen_nest.getUb();
            }else if(levy_distribution < choosen_nest.getLb()){
                levy_distribution = choosen_nest.getLb();
            }
            newnest.getCuckoo_eggs().get(i).setEgg(levy_distribution);
        }
        newnest.setFitness(newnest.doEggHolderFitnessFunction(newnest.getCuckoo_eggs()));
//        newnest.setFitness(newnest.doMichaelWichzMin(newnest.getCuckoo_eggs()));
        return newnest;
        
    }
    
    private double getStep(double sigma){
       double u = new Random().nextGaussian()*sigma;
       double v = new Random().nextGaussian();
       return u/Math.pow(Math.abs(v), (1/this.beta));
    }
    
   
    private double doSigma(){
        double term1 = this.logGamma(beta+1)*Math.sin((Math.PI*beta)/2);
        double term2 = this.logGamma((beta+1)/2)*beta*Math.pow(2,(beta-1)/2);
        return Math.pow((term1/term2),(1/beta));
    }
    
    private Double logGamma(double x){
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
                       + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
                       +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
        return Math.exp(tmp + Math.log(ser * Math.sqrt(2 * Math.PI)));
    }
}
