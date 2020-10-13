# Eigenvalue algorithms demo
Simple command line app to experiment with matrixes by finding its eigenvalues and eigen vectors

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)
* [Invocation Example](#invocation-example)

## General info
At the start this project was intended to be a university lab with main purpose of 
finding eigenvalues and eigenvectors for two fixed matrixes. Additional features like
support for CSV martrix input was added to increase overall usability of the program
and to further practice developing CLI command. The main goals of this project apart 
from being a univercity lab are impoving skills and gaining knowlage related to OOP design, 
writing git commit messages, command line program development using Picocli framework.

## Technologies
Project is created with:
* Java 8
* EJML library version: 0.39
* Picocli framework version: 4.5.1
* Hibernate Validator library version: 6.0.2.Final

## Features
* Input matrix in CSV
* Output results in CSV and simple text
* Support for 3 different algorithms: QR, Jacobi, Power iterations

## Setup
To run this project, build it using Apache Maven version 3.3.9 or higher and add alias to simplify command invocation:

```
$ cd ../demo-eigenvalue-algorithms
$ mvn package
$ alias evad='java -jar "target/eigenvalue-algorithms-demo-1.0-SNAPSHOT-jar-with-dependencies.jar"'
```

## Invocation Example
In this example we are trying to find eigenvalues and eigenvectors for a given matrix

```
$ cat matrix.csv
818,-287,-162,184,-383
-287,718,-161,10,3
-162,-161,667,-588,-239
184,10,-588,828,-4
-383,3,-239,-4,843
$ evad matrix.csv -a=QR
{1303.876326208391=[Type = DDRM , rows = 5 , cols = 1
-6.1204E-01 
 3.7468E-01 
-2.5538E-01 
 8.1375E-02 
 6.4279E-01 
], 759.8249095295616=[Type = DDRM , rows = 5 , cols = 1
-1.1415E-01 
 8.0006E-01 
-1.2018E-02 
 5.2663E-02 
-5.8648E-01 
], 1437.953099608692=[Type = DDRM , rows = 5 , cols = 1
 3.6142E-01 
 3.0977E-03 
-6.1439E-01 
 7.0129E-01 
 9.4443E-03 
], 340.2034610028594=[Type = DDRM , rows = 5 , cols = 1
-6.1459E-01 
-3.6715E-01 
 2.6206E-01 
 5.5248E-01 
-3.3700E-01 
], 32.14220364981876=[Type = DDRM , rows = 5 , cols = 1
 3.2254E-01 
 2.9105E-01 
 6.9892E-01 
 4.3996E-01 
 3.5945E-01 
]}
```
