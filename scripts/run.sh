#!/usr/bin/env bash
# Compile all source into out/ and run one practice demo, e.g.:
#   scripts/run.sh practice.JobSchedulerDemo
set -e
cd "$(dirname "$0")/.."
if [ $# -ne 1 ]; then
  echo "usage: scripts/run.sh <demo class>, e.g. scripts/run.sh practice.JobSchedulerDemo" >&2
  exit 1
fi
javac -d out $(find src/main -name "*.java")
java -cp out "$1"
