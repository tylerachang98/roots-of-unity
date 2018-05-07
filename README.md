# roots-of-unity
Generates and sifts through data on the distribution of primitive roots of unity modulo c. <br/>
Written for Math290 Reading in Number Theory. <br/>

Updates: <br/>
Tested efficiency of data generator implementations in TimeTests folder. <br/>
Generator.cpp: array-based C++ implementation <br/>
Generator.java: array-based Java implementation <br/>
GeneratorList.java: list-based Java implementation <br/>
The Java implementation performs noticeably faster than the C++ implementation (using Windows command line or bash terminal for Windows).
Java runs at the same speed in the Windows command line and the bash terminal.
C++ .out file in the bash terminal runs at the same speed as C++ .exe file in the Windows command line.
As expected, array-based implementations are consistently faster than list-based implementations.
Should rewrite DataGenerator.java to use arrays.

