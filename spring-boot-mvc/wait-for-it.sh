#!/bin/bash

set -e

host="$1"
shift
password="$2"
shift
cmd="$@"

until mysql -h"$host" -P3306 -uroot -p"$password" &> /dev/null
do
  echo "MySQL is unavailable - sleeping"
  sleep 1
done

>&2 echo "MySQL is up - executing command"
exec $cmd