#!/bin/sh
# pretty print the test json output
./test.sh | python2 -m json.tool
