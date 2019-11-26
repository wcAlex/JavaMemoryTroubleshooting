# JavaMemoryTroubleshooting
Examples and tips of solving problems relating to memory in Java

## Memory leak sample 1 (Simple Memory Leak)

setup test application and generate load.

```bash
cd memory_leaks1
mvn clean package

running as a console app:
./run-leak-runner.sh

or running as webservice:
./run.sh
./generate_load.sh
```

Open Profiler, check out heap old generation, you will find memory is leaking. The root cause is missing equal function for cache id comparison, so the cache keeps growing.
Check out code: <https://github.com/wcAlex/JavaMemoryTroubleshooting/blob/master/memory_leaks1/src/main/java/com/monotonic/klassified/resources/PostingCache.java#L42>

## Memory leak sample 2 (out of memory)

Start `MemoryHeavyStatisticsService` as a backend api server, which will load entire file as one go, thus cause a memory leak.
https://github.com/wcAlex/JavaMemoryTroubleshooting/tree/master/outofmemory.

Note that the project is set max heap size to 50MB and heap dump will be generated automatically when OOM happens, please check out pom file for details.

check out ./run.sh to post a file upload request to cause OOM. The test csv file is from https://www.gov.uk/government/statistical-data-sets/uk-house-price-index-data-downloads-april-2016, any 50M+ csv file will trigger OOM.
```bash
curl -X POST --data-binary @$HOME/Downloads/UK-HPI-full-file-2016-04.csv 'http://localhost:9800'
```

Proper way of reading files is at `MemoryLightStatisticsService`, suggest to consume file line by line.
