# Java Benchmarks

This is a very minimal skeleton to fulfill my Java-related benchmarking needs. It uses the [Java Microbenchmark Harness](https://github.com/openjdk/jmh).

Please note: it is not a serious tool you can use to validate anything, nor to provide a solid base for your (business) decisions.

Currently, it has only one measurement related to 
[ConcurrentLinkedQueue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ConcurrentLinkedQueue.html) 
vs. [Stream API](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html).

## Requirements

- Java
- Maven

## Usage

To run the benchmark, execute the following command:

```bash
mvn clean install
java -jar target/java-benchmarks-1.0-SNAPSHOT-jar-with-dependencies.jar
```

The results will be outputted in the console and also saved in a Markdown file named `benchmarkResults.md`.

## Contributing

Feel free to fork this repository and modify according to your requirements. 

## License

[MIT](https://choosealicense.com/licenses/mit/)
