/** Tests an array-based C++ implementation. */

#include<stdio.h>
#include<iostream>
#include <time.h>

using namespace std;

void calculate();

int MIN = 100;
int MAX = 1200;

int main()
{
   clock_t timer;

	timer = clock();
   calculate();
   timer = clock()-timer;
   cout << timer / 1000 << " milliseconds" << endl; //give time in milliseconds
   return 0;
}


/** Calculates the desired values */
void calculate()
{
   int value[MAX+1];
   int loopSizes[MAX+1];
   int counter = 0;
   for (int c = MIN; c <= MAX; c++) //how many values to iterate through, start at 2
   {  
      int maxLoopSize = 0;
      int thisLoopSize = 0;
      for (int b = 1; b < c; b++) //check for each b until c, start at 1
      {
         value[0] = 1;
         counter = 0;
         bool stop = false;
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