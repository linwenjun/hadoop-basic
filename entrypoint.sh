#!/bin/sh
/etc/init.d/ssh start
./bin/hdfs namenode -format -force
ssh -o "StrictHostKeyChecking no" localhost
ssh -o "StrictHostKeyChecking no" 0.0.0.0
./sbin/start-dfs.sh
./sbin/start-yarn.sh

exec "$@"