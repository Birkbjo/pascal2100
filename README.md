# A compilator for Pascal2100.

Course INF2100 at University of Oslo.

By Odd-Tørres Lunde and Birk Johansson.

##Progress
Done with part 4. 

Tested files diffed with their reference:
mini.s, gcd.s, easter.s and opers.s

## Usage
```
ant compile
```
To compile the source. This creates "pascal2100.jar". 

##### Run with
```
java -jar pascal2100.jar [-log{B|P|S|T|Y}] [-test{parser|scanner}] path/to/file.pas
```

##### Options
* -logB : Binding log

* -logP : Parser log

* -logS : Scanner log

* -logT : TypeCheck log

* -logY : Pretty print

* -testparser : Runs the parser only, enables logging for parser and prettyprint.

* -testscanner : Runs the scanner only, enables logging for scanner.

