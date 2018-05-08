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
import java.util.Arrays;
import java.util.Scanner;

public class DataGenerator
{  
   private int MIN;
   private int MAX;
   private int[] totients;
   
   /** Constructor for DataGenerator */
   public DataGenerator(int minimum, int maximum)
   {
      MIN = minimum; //starting test value
      MAX = maximum; //ending test value
      totients = new int[MAX+1];
   }

   /** Loads totient values (Euler phi function) */
   private void loadTotients()
   {
      List<Integer> thisFactors = new ArrayList<Integer>();
      totients[0] = 1;
      totients[1] = 1;
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
         totients[i] = totientCounter;
      }
   }
   
   /** Calculates and prints the desired values */
   private void calculate()
   {
      int value[];
      value = new int[MAX+1];
      int loopSizes[];
      loopSizes = new int[MAX+1];
      int counter = 0;
      for (int c = MIN; c <= MAX; c++) //how many values to iterate through, start at 2
      {  
         int loopCounter = 0;
         int maxLoopSize = 0;
         int thisLoopSize = 0;
         for (int b = 1; b < c; b++) //check for each b until c, start at 1
         {
            value[0] = 1;
            counter = 0;
            boolean stop = false;
            while (!stop)
            {
               counter = counter + 1;
               value[counter] = (value[counter-1]*b) % c;
               for (int i = 0; i < counter; i++)
               {
                  if (value[i] == value[counter])
                  {
                     stop = true;
                     thisLoopSize = counter - i;
                  }
               }
            }
            //check if repeats at 1 (then is a root of unity)
            if (value[counter] == 1)
            {
               loopSizes[loopCounter] = thisLoopSize;
               loopCounter++;
               if (thisLoopSize > maxLoopSize)
                  maxLoopSize = thisLoopSize;
            }
         }
         
         //there should be phi(c) roots of unity (corresponding with units in Z/cZ)
         if (loopCounter != totients[c])
         {
            //hopefully this line never prints
            System.out.println("CHECKTOTIENT c = " + c + ";  " + loopCounter + " does not equal " + totients[c]);
         }
         
         System.out.println("c=" + c + "; totient=" + totients[c] + "; max root=" + maxLoopSize + "; multiplier=" + totients[c]/maxLoopSize);
         
         //sort loop lengths
         Arrays.sort(loopSizes, 0, loopCounter);
         
         //print loop lengths
         System.out.print("   ");
         int index = 0;
         while (index < loopCounter)
         {
            int howManyRemoved = 0;
            int size = loopSizes[index];
            System.out.print(size + ":");
            while(loopSizes[index] == size)
            {
               howManyRemoved++;
               index++;
            }
            System.out.print(howManyRemoved + "  ");
         }
         System.out.println();
      }
   }
   
   /** Calculates and prints the data */
   public static void main(String[] args)
   {  
      Scanner scan = new Scanner(System.in);
      System.out.print("Minumum value: ");
      int minimum = Integer.valueOf(scan.nextLine());
      System.out.print("Maximum value: ");
      int maximum = Integer.valueOf(scan.nextLine());
      scan.close();
      
      DataGenerator test = new DataGenerator(minimum, maximum);
      System.out.println("MIN = " + test.MIN);
      System.out.println("MAX = " + test.MAX + "\n");
      test.loadTotients();
      System.out.println("Loaded totients.");
      System.out.println("Current time: " + System.currentTimeMillis());
      test.calculate();
      System.out.println("End time: " + System.currentTimeMillis());
   }
}