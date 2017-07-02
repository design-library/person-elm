#! /bin/sh

echo "MYSQL LOGIN & CREATE TEST DATABASE/TABLES."

mysql -u root -paws.mysql@8944 <<EOF

source /src/test/resources/META-INF/use_to_utest.sql

EOF

echo "COMPLEATE!"
