#Kyle Matsumoto
#kytmatsu
#CMPS012B
#11/21/14
#MY makefile for asg3 im not certain if the cleaning is working though.

JAVASRC    = xref.java auxlib.java Tree.java Queue.java X.java
SOURCES    = ${JAVASRC} Makefile
ALLSOURCES = ${SOURCES}
MAINCLASS  = xref
CLASSES    = ${patsubst %.java, %.class, ${JAVASRC}}
INNCLASSES = xref\$$printer.class Tree\$$node.class Queue\$$iterate.class Queue\$$node.class Queue\$$1.class 
JARCLASSES = ${CLASSES} ${INNCLASSES}
JARFILE    = xref 
all : ${JARFILE}

${JARFILE} : ${CLASSES}
	echo Main-class: ${MAINCLASS} >Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	chmod +x ${JARFILE}
	- rm Manifest

%.class : %.java
	javac -Xlint $< 

clean : 
	- rm ${JARCLASSES} Manifest

spotless : clean 
	- rm ${JARFILE
