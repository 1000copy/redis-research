echo off 
set prompt=~
set CLASSPATH=..\lib\junit.jar;..\lib\hamcrest.jar;.
set old=%path%
set path=%path%;"C:\Program Files\Java\jdk1.7.0_25\bin\"
rem 约定 java source 的编码为 utf8
javac -encoding utf8 TestAll.java TestBubble.java Bubble.java
if %errorlevel% neq 0 exit /b
echo on
java org.junit.runner.JUnitCore  TestAll
echo off
set path=%old%
del *.class

