/**
For each number c, prints:
   -totient (Euler phi function)
   -maximum degree of a root of unity modulo c
   -multiplier of that degree to obtain phi(c)
   -distribution of the primitive roots of unity modulo c

Originally written 2017-02-19
Updated for Math290 Reading in Number Theory
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DataGenerator
{  
   private int MIN;
   private int MAX;
   private List<Integer> totients;
   
   /** Constructor for DataGenerator */
   public DataGenerator()
   {
      totients = new ArrayList<Integer>();
      MIN = 2729; //starting test value
      MAX = 10000; //ending test value
   }

   /** Loads totient values (Euler phi function) */
   private void loadTotients()
   {
      List<Integer> thisFactors = new ArrayList<Integer>();
      totients.add(1);
      totients.add(1);
      int totientCounter = 0;
      //for all from 2 through MAX
      for (int i = 2; i<=MAX; i++)
      {
         totientCounter = 0;
         thisFactors.clear();
         //find prime factors of i
         for (int j = 2; j< (i/2+1); j++)
         {
            if (i % j == 0) //find factors of i
            {
               boolean isPrime = true;
               for(int k : thisFactors)
               {
                  if (j % k == 0) //if is divisible by some number already in list
                     isPrime = false;
               }        
               if (isPrime)
                  thisFactors.add(j);
            }  
         }
         
         //go through all lower numbers and see if divisible by any factor of i
         boolean shouldAdd = true;
         for (int j = 1; j < i; j++)
         {
            shouldAdd = true;
            for (int k : thisFactors)
            {
               if (j % k == 0) //is divisible
                  shouldAdd = false;
            }
            if (shouldAdd)
               totientCounter++;
         }
         totients.add(totientCounter);
      }
   }
   
   /** Calculates the desired values */
   private void calculate()
   {
      List<Integer> value = new ArrayList<Integer>();
      List<Integer> loopSizes = new ArrayList<Integer>();
      int counter = 0;
      for (int c = MIN; c <= MAX; c++) //how many values to iterate through, start at 2
      {  
         loopSizes.clear();
         int maxLoopSize = 0;
         int thisLoopSize = 0;
         for (int b = 1; b < c; b++) //check for each b until c, start at 1
         {
            value.clear();
            value.add(1);
            counter = 0;
            boolean stop = false;
            while (!stop)
            {
               counter = counter + 1;
               value.add((value.get(counter-1) * b) % c);
               for (int i = 0; i < counter; i++)
               {
                  if (value.get(i).equals(value.get(counter)))
                  {
                     stop = true;
                     thisLoopSize = counter - i;
                  }
               }
            }
            //check if repeats at 1 (then is a root of unity)
            if (value.get(counter) == 1)
            {
               loopSizes.add(thisLoopSize);
               if (thisLoopSize > maxLoopSize)
                  maxLoopSize = thisLoopSize;
            }
         }
         
         //there should be phi(c) roots of unity (corresponding with units in Z/cZ)
         if (loopSizes.size() != totients.get(c))
         {
            //hopefully this line never prints
            System.out.println("CHECKTOTIENT c = " + c + ";  " + loopSizes.size() + " does not equal " + totients.get(c));
         }
         
         System.out.println("c=" + c + "; totient=" + totients.get(c) + "; max root=" + maxLoopSize + "; multiplier=" + totients.get(c)/maxLoopSize);
         
         //sort loop lengths
         Collections.sort(loopSizes);
         
         //print loop lengths
         System.out.print("   ");
         while(!loopSizes.isEmpty())
         {
            int howManyRemoved = 0;
            int size = loopSizes.remove(0);
            System.out.print(size + ":");
            howManyRemoved++;
            while(loopSizes.contains(size))
            {
               loopSizes.remove(new Integer(size));
               howManyRemoved++;
            }
            System.out.print(howManyRemoved + "  ");
         }
         System.out.println();
      }
   }
   
   /** Calculates and prints the results */
   public static void main(String[] args)
   {  
      DataGenerator test = new DataGenerator();
      System.out.println("MAX = " + test.MAX + "\n");
      test.loadTotients();
      System.out.println("Loaded totients.");
      test.calculate();
   }
}