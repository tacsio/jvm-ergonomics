# JVM Ergonomics

## JVM Params

### Common Params
```
-Xmx => Max heap size.
-Xms => Start heap size.
-Xss => Thread stack size.
```

### Optimization Params
```
-XX:+UseStringCache      => Enables caching of commonly allocated strings.
-XX:MaxRAMPercentage     =>	Set maximum heap size as a percentage of total memory.
-XX:InitialRAMPercentage =>	Set initial heap size as a percentage of total memory.
-XX:ActiveProcessorCount => Override the number of CPUs that the VM automatically detects and uses when creating threads for various subsystems. 
```
#### Info
> _If you set a value for -Xms, the -XX:InitialRAMPercentage option is ignored. If you set a value for -Xmx, the -XX:MaxRAMPercentage option is ignored._

### Garbage Collector Definition
```
-XX:+UseSerialGC
-XX:+UseParallelGC
-XX:+UseG1GC
```

### Garbage Collector Tuning
```
-XX:MaxPermSize
-XX:PermSize
-XX:NewSize
-XX:MaxNewSize
-XX:SurvivorRatio
-XX:MaxTenuringThreshold
```

### JVM Inspection
```
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps
-XX:+PrintHeapAtGC
-XX:+PrintCommandLineFlags
-XX:+PrintFlagsFinal        => Prints configured flags 
```

## JVM Ergonomics Defaults

### Garbage Collector
|               **Resources available**               | **Default GC** |
|:---------------------------------------------------:|:--------------:|
| Any number of processors<br>Up to 1791 MB of memory | SerialGC       |
| 2+ processors<br>1792 MB or more of memory          | G1GC           |

### Heap Size
| **Memory available** | **Default maximum heap size** |
|:--------------------:|:-----------------------------:|
| Up to 256 MB         | 50% of available memory       |
| 256 MB to 512 MB     | ~127MB                        |
| More than 512 MB     | 25% of available memory       |

## Heap

### Used

The used space is the amount of memory that is currently occupied by Java objects.

### Committed

The committed size is the amount of memory guaranteed to be available for use by the Java virtual machine.

## Util

https://convertlive.com/u/convert/bytes/to/megabytes#1

## References

https://www.baeldung.com/java-heap-used-committed-max
https://www.baeldung.com/jvm-parameters
https://learn.microsoft.com/en-us/azure/developer/java/containers/