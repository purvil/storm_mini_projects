# Storm Mini Projects

### [Hello World](https://github.com/purvil/storm_mini_projects/tree/master/helloWorld)
* It is a very basic project with one spout and one bolt. 
* Spout will generate incremental integer numbers and pass to connected Bolt. Bolt will multiply this number by 2, to make it even.

### [Read From File](https://github.com/purvil/storm_mini_projects/tree/master/readingFile)
* Spout will read data from file line by line
* Bolt will process each line and modify it.

### [Read file and write to file](https://github.com/purvil/storm_mini_projects/tree/master/readingFile2)
* Spout will read data from file line by line
* Bolt will process each line and parse it, also write back to text file.

### [Shuffle Grouping](https://github.com/purvil/storm_mini_projects/tree/master/shuffleGrouping)
* Spout will emit integers 1 to 100
* 2 instance of the bolt will process it and write to file with shuffle grouping.

### [Field Grouping](https://github.com/purvil/storm_mini_projects/tree/master/fieldsGrouping)
* Spout will emit integers 1 to 100
* 2 instance of the bolt will process it and write to file with field grouping.

### [All Grouping](https://github.com/purvil/storm_mini_projects/tree/master/allGrouping)
* Spout will emit integers 1 to 100
* 2 instance of the bolt will process it and write to file with all grouping.

### [Custom Grouping](https://github.com/purvil/storm_mini_projects/tree/master/customGrouping)
* Spout will emit integers 1 to 100
* 2 instance of the bolt will process it and write to file with custom grouping.
* Custom grouping class define grouping strategy such as, all instances receive same load.

### [Direct Grouping](https://github.com/purvil/storm_mini_projects/tree/master/directGrouping)
* Spout will emit integers 1 to 100
* 2 instance of the bolt will process it and write to file with direct grouping.
