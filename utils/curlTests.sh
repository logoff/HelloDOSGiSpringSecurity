#!/bin/bash
echo "|-----------------------------------|"
echo "|                                   |"
echo "| Hello DOSGi Spring Security Tests |"
echo "|                                   |"
echo "|-----------------------------------|"
echo ""
#=========== Response codes ================#
res200="HTTP/1.1 200"
res401="HTTP/1.1 401"
res403="HTTP/1.1 403"
#===========     sayHello   ================#
message="sayHello - user:user        (403 Forbidden expected):   "
result=$(curl -s -i -H "Authorization: Basic dXNlcjp1c2Vy" -X GET http://localhost:8181/sample/sayHello?name=user)
if [[ "$result" = $res403* ]]
then
	echo "$message - OK"
else
	echo "$message - Error! Actual response: ${result:0:12}"
fi
message="sayHello - admin:admin      (200 OK expected):          "
result=$(curl -s -i -H "Authorization: Basic YWRtaW46YWRtaW4=" -X GET http://localhost:8181/sample/sayHello?name=user)
if [[ "$result" = $res200* ]]
then
	echo "$message - OK"
else
	echo "$message - Error! Actual response: ${result:0:12}"
fi
message="sayHello - abc:abc          (401 Unauthorized expected):"
result=$(curl -s -i -H "Authorization: Basic YWJjOmFiYw==" -X GET http://localhost:8181/sample/sayHello?name=user)
if [[ "$result" = $res401* ]]
then
	echo "$message - OK"
else
	echo "$message - Error! Actual response: ${result:0:12}"
fi
message="sayHello - <No auth header> (401 Unauthorized expected):"
result=$(curl -s -i -X GET http://localhost:8181/sample/sayHello?name=user)
if [[ "$result" = $res401* ]]
then
	echo "$message - OK"
else
	echo "$message - Error! Actual response: ${result:0:12}"
fi
#===========     sayBye   ================#
message="sayBye   - user:user        (200 OK expected):          "
result=$(curl -s -i -H "Authorization: Basic dXNlcjp1c2Vy" -X GET http://localhost:8181/sample/sayBye?name=user)
if [[ "$result" = $res200* ]]
then
	echo "$message - OK"
else
	echo "$message - Error! Actual response: ${result:0:12}"
fi
message="sayBye   - admin:admin      (200 OK expected):          "
result=$(curl -s -i -H "Authorization: Basic YWRtaW46YWRtaW4=" -X GET http://localhost:8181/sample/sayBye?name=user)
if [[ "$result" = $res200* ]]
then
	echo "$message - OK"
else
	echo "$message - Error! Actual response: ${result:0:12}"
fi
message="sayBye   - abc:abc          (401 Unauthorized expected):"
result=$(curl -s -i -H "Authorization: Basic YWJjOmFiYw==" -X GET http://localhost:8181/sample/sayBye?name=user)
if [[ "$result" = $res401* ]]
then
	echo "$message - OK"
else
	echo "$message - Error! Actual response: ${result:0:12}"
fi
message="sayBye   - <No auth header> (401 Unauthorized expected):"
result=$(curl -s -i -X GET http://localhost:8181/sample/sayBye?name=user)
if [[ "$result" = $res401* ]]
then
	echo "$message - OK"
else
	echo "$message - Error! Actual response: ${result:0:12}"
fi