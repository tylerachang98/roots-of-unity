/** Tests an array-based Java implementation. */

public class Generator
{  
   private int MIN;
   private int MAX;
   
   public Generator()
   {
      MIN = 100; //starting test value
      MAX = 1200; //ending test value
   }
   
   /** Calculates the desired values */
   void calculate()
   {
      int value[];
      value = new int[MAX+1];
      int loopSizes[];
      loopSizes = new int[MAX+1];
      int counter = 0;
      for (int c = MIN; c <= MAX; c++) //how many values to iterate through, start at 2
      {  
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
               loopSizes[b] = thisLoopSize;
               if (thisLoopSize > maxLoopSize)
                  maxLoopSize = thisLoopSize;
            }
         }
      }
   }
   
   /** Calculates the results */
   public static void main(String[] args)
   {  
      Generator test = new Generator();
      long time;
      time = System.currentTimeMillis();
      test.calculate();
      time = System.currentTimeMillis() - time;
      System.out.println(time + " milliseconds");
   }
}