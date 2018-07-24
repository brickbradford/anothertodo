#!/bin/sh

#BASE_URL=http://anothertodo-tododo.193b.starter-ca-central-1.openshiftapps.com/test/1.0
BASE_URL=http://localhost:8080/test/1.0

curl -sSL -G -X GET "http://join.autogeneral.com.au/test/1.0/integrationTest" --data-urlencode "url=${BASE_URL}" -H  "accept: application/json" 


