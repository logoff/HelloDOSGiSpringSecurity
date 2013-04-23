#!/bin/bash
echo "|-----------------------------------|"
echo "|                                   |"
echo "| Hello DOSGi Spring Security Tests |"
echo "|                                   |"
echo "|-----------------------------------|"
echo ""
res200="HTTP/1.1 200"
res401="HTTP/1.1 401"
res403="HTTP/1.1 403"
echo "user:user (403 Forbidden expected):"
result=$(curl -s -i -H "Authorization: Basic dXNlcjp1c2Vy" -X GET http://localhost:8181/sample/sayHello?name=user)
if [[ "$result" = $res403* ]]
then
	echo "OK"
else
	echo "Error!Actual response: ${result:0:12}"
fi
echo ""
echo "admin:admin (200 OK expected):"
result=$(curl -s -i -H "Authorization: Basic YWRtaW46YWRtaW4=" -X GET http://localhost:8181/sample/sayHello?name=user)
if [[ "$result" = $res200* ]]
then
	echo "OK"
else
	echo "Error! Actual response: ${result:0:12}"
fi
echo ""
echo "abc:abc (401 Unauthorized expected):"
result=$(curl -s -i -H "Authorization: Basic YWJjOmFiYw==" -X GET http://localhost:8181/sample/sayHello?name=user)
if [[ "$result" = $res401* ]]
then
	echo "OK"
else
	echo "Error! Actual response: ${result:0:12}"
fi
echo ""
echo "<No auth header> (401 Unauthorized expected):"
result=$(curl -s -i -X GET http://localhost:8181/sample/sayHello?name=user)
if [[ "$result" = $res401* ]]
then
	echo "OK"
else
	echo "Error! Actual response: ${result:0:12}"
fi

