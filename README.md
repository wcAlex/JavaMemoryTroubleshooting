# JavaMemoryTroubleshooting
Examples and tips of solving problems relating to memory in Java

## Memory leak sample 1 ()

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

## Memory leak sample 2

