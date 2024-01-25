#!/bin/bash

set -e

# Or use mysqladmin ping
until echo "quit" | mysql -h"$DB_HOST" -P3306 -uroot -p"$DB_PASSWORD" &> /dev/null
do
  echo "MySQL is unavailable - sleeping"
  sleep 1
done

>&2 echo "MySQL is up - executing command"