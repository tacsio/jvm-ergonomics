#!/bin/bash

# CPU
wrk -t4 -c100 -d30s http://localhost:8080/work/cpu

# Factorial
#wrk -t4 -c100 -d30s http://localhost:8080/work/fact/1000
