HelloDOSGiSpringSecurity
=======================

Hello World project using [Spring Security][spring-security] and [Spring DM][spring-dm] as an OSGI bundle.
REST API is published using [CXF DOSGi][dosgi].

It can be deployed into [OpenNaaS][opennaas] deploy folder (minimum version 0.19).

A secured hello world method located in [http://localhost:8181/sample/sayHello?name=UserName](http://localhost:8181/sample/sayHello?name=UserName) can be accessed using credentials:

 - __User__: admin
 - __Password__: admin

These credentials must be introduced using Authorization [HTTP Request Header][http-authz]

[opennaas]: https://github.com/dana-i2cat/opennaas
[spring-security]: http://www.springsource.org/spring-security
[spring-dm]: http://www.springsource.org/osgi
[dosgi]: http://cxf.apache.org/distributed-osgi.html
[http-authz]: http://tools.ietf.org/html/rfc1945#section-10.2