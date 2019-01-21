#!/usr/bin/env bash

spark-submit --master yarn \
    --class HiggsClassifier \
    --conf spark.yarn.naxAppAttempts=1 \
    --num-executors 2 \
    --queue pico \
    --executor-memory 200G \
    --executor-cores 32 \
    --deploy-mode cluster \
    ./spark-2.4-ml-tutorial-1.0-SNAPSHOT.jar