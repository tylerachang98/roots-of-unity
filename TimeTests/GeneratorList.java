/** Tests a list-based Java implementation. */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GeneratorList
{  
   private int MIN;
   private int MAX;
   
   public GeneratorList()
   {
      MIN = 100; //starting test value
      MAX = 1200; //ending test value
   }
   
   /** Calculates the desired values */
   void calculate()
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
      }
   }
   
   /** Calculates the results */
   public static void main(String[] args)
   {  
      GeneratorList test = new GeneratorList();
      long time;
      time = System.currentTimeMillis();
      test.calculate();
      time = System.currentTimeMillis() - time;
      System.out.println(time + " milliseconds");
   }
}