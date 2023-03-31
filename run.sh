#!/bin/bash

### [INFO] ####################################################################################

#export JAVA_TOOL_OPTIONS=""
#export JAVA_TOOL_OPTIONS="-Xmx128m"
#export JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75"
#export JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -Xmx1g" #Ignored

#export JAVA_TOOL_OPTIONS="-XX:+UseSerialGC"
#export JAVA_TOOL_OPTIONS="-XX:+UseParallelGC"
#export JAVA_TOOL_OPTIONS="-XX:+UseG1GC"
#export JAVA_TOOL_OPTIONS="-XX:+UnlockExperimentalVMOptions -XX:+UseZGC"

#export JAVA_TOOL_OPTIONS="-XX:ActiveProcessorCount=1"
#export JAVA_TOOL_OPTIONS="-XX:ActiveProcessorCount=2"

### [TESTS] ###################################################################################
#export JAVA_TOOL_OPTIONS=""


### [INFO] #####################################################################################

# -Xmx[N]                      => Max heap size.
# -Xms[N]                      => Start heap size.
# -XX:MaxRAMPercentage=[N]     => Set maximum heap size as a percentage of total memory.
# -XX:InitialRAMPercentage=[N] => Set initial heap size as a percentage of total memory.
# -XX:ActiveProcessorCount=[N] => Override the number of CPUs that the VM automatically
#                                 detects and uses when creating threads for various subsystems.

# Garbage Collector Definition:
# -XX:+UseSerialGC
# -XX:+UseParallelGC
# -XX:+UseG1GC
# -XX:+UnlockExperimentalVMOptions -XX:+UseZGC  => Stop-the-world phases as short as possible


### [EXEC] #####################################################################################

#mvn spring-boot:run

java -jar target/*.jar