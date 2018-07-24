#!/bin/sh

BASE_URL=http://localhost:8080/test/1.0

curl -sSL -G -X GET "http://my.test.server/test/1.0/integrationTest" --data-urlencode "url=${BASE_URL}" -H  "accept: application/json" 


