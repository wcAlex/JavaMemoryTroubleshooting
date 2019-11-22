# JavaMemoryTroubleshooting
Examples and tips of solving problems relating to memory in Java

## memory leak 1

setup test application and generate load. 
```
cd memory_leaks1
mvn clean package
./run.sh
./generate_load.sh
```
Open Profiler, check out heap old generation.

Root cause is missing equal function for cache id comparison, so the cache keeps growing.

`

`
