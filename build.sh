#!/bin/bash
javac **/*.java
echo "java -cp src Manhattan" > manhattan
echo "java -cp src Astar" > astar
echo "java -cp src Price" > price
echo "java -cp src DataRetrieval" > data
chmod +x manhattan
chmod +x astar
chmod +x price
chmod +x data

