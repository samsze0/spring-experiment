#!/bin/bash

set -e

until echo "quit" | mysql -h"$DB_HOST" -P3306 -uroot -p"$DB_PASSWORD"
do
  echo "MySQL is unavailable - sleeping"
  sleep 1
done

>&2 echo "MySQL is up - executing command"