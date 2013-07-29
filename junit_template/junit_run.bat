echo off 
del *.class 
set CLASSPATH=..\lib\junit.jar;..\lib\hamcrest.jar;.
set old=%path%
set path=%path%;"C:\Program Files\Java\jdk1.7.0_25\bin\"
javac TestAll.java 
echo on
java org.junit.runner.JUnitCore  TestAll
echo off
set path=%old%
del *.class 
pause ...
