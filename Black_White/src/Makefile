# ### CSCI561 - Fall 2014 - Sample Makefile
#
# ### Example for a C++ agent:
# #agent: agent.cpp
# #	g++ agent.cpp -o agent
# #
# #run: agent
# #	./agent
#
# ### For Java you might use:
# agent: agent.class
#
# Node.class: Node.java
# 	javac   Node.java
#
# agent.class: ^_agent.java
#   javac 	agent.java
#
# run: agent.class
# 	java agent
#
#
#
# ### For python or other non-compiled languages:
# #agent:
# #
# #run:
# #	./agent.py
JCC = javac
JFLAGS = -g

default: Node.class agent.class

Node.class: Node.java
		$(JCC) $(JFLAGS) Node.java

agent.class: agent.java
		$(JCC) $(JFLAGS) agent.java

run:
		java agent

clean:
		$(RM) *.class
