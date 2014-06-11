##Reference

This repository contains the code to run the multiword term generation.
It is part of the domain extension tools developed in the OpeNER project. It reads a set of KAF documents and outputs a list of potential multiword terms.

If the provided KAF documents come from a domain specific set of texts, then the generated multiword term should contain domain aware expression.

The employed technique is based on the calculation of the Log-Likelihood ratio measure of the co-ocurrence of the individual components of a potential multiword term.
This code uses the Log-Likelihood Ratio calculation already present in the Apache Mahout library (the very same Java class is used internally) with some tweaking in the score counts.

###Command line interface

First you need Apache Maven in your system in order to package the code.
Go to the folder containg the source code and the pom.xml file and issue the following command:

```
mvn clean package
```

This will package the code into an executable jar file.

Now you can run the multiword generation program issuing:

```
java -jar NAME_OF_THE_JAR -i INPUT_DIRECTORY [-n NGRAM_SIZE] [-l LIST_SIZE] [-o OUTPUT_PATH]
```

The program will read the KAF files inside the INPUT_DIRECTORY specified int -i parameter.
The -n parameter stablishes the maximum length of the generated multiwords (two by default).
The -l parameter sets the desired size of the generated list (the generated multiwords are ranked, so in the lower part are the less likely correct multiword terms, and you may want to prune the list). Default is 100.
The -o parameter sets the path to the output file that will be generated containing the multiword terms.

