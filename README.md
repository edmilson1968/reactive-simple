# reactive-simple
Simple springboot Rest service using Reactor. There are 2 REST services (one of them using streams)

The *Response* is based on a '*Document*' described as follows: 

```Java
class Document {
	
	private int catalogNumber;
	private String content;
	
	public Document(int number) {
		catalogNumber = number;
		content = UUID.randomUUID().toString();
	}
	
	.... getter's and setter's 
}
```
##### compiling

```
$ mvn clean install
```

##### executing

```
$ mvn spring-boot:run
```

##### usage

* First, call http://localhost:8080/documents (use your browser or a curl statement) and see that it takes approximately 9 seconds to the server push all the information, simulating a synchronous connection.

```
$ time curl -v http://localhost:8080/documents
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /documents HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
>
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Content-Type: application/json;charset=UTF-8
<
* Connection #0 to host localhost left intact
[{"catalogNumber":1,"content":"ac56f635-8e2a-44f3-a89d-0c3b62dc2713"},{"catalogNumber":2,"content":"bfb33cb2-582d-477a-9fb8-10ed5d131814"},{"catalogNumber":3,"content":"6aa7ca45-6acd-4f04-8ce1-5b7a44169e36"},{"catalogNumber":4,"content":"3a1e7991-cf21-41ff-a17f-387b217aaf2e"},{"catalogNumber":5,"content":"6d2eb40a-3dfa-482c-8e55-1f5dbd59abf3"},{"catalogNumber":6,"content":"562c9426-be34-4d5b-bffe-e8fb671ddc30"},{"catalogNumber":7,"content":"1f8e26f3-8f24-4187-8f68-061a68ceed52"},{"catalogNumber":8,"content":"23d95b93-7fc1-486e-a90b-980aa67ec74d"},{"catalogNumber":9,"content":"53c68a83-e49c-4c0e-ac3f-f1b0eae8ca82"}]
real	0m9.051s
user	0m0.013s
sys	0m0.015s
```

* On a second case, call http://localhost:8080/streams and see "the magic of Reactive Programming". The connection apparently "closes", but the server continues pushing data to the browser, permitting the client to do other tasks while wait the server. Note that the elapsed time will take the same 9 seconds of the first example.

```
$ curl -v http://localhost:8080/streams
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /streams HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
>
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Content-Type: application/stream+json;charset=UTF-8
<
{"catalogNumber":1,"content":"4f2a703f-98ab-4adc-a0a6-dfec753d292f"}
{"catalogNumber":2,"content":"b6bd3cb2-4a38-4648-86d5-0a2621a71b31"}
{"catalogNumber":3,"content":"8f626a23-73dc-4f74-a6df-df95003168d7"}
{"catalogNumber":4,"content":"f0074328-2783-4289-a234-61c584447193"}
{"catalogNumber":5,"content":"73f1d023-76c0-45a0-9098-f682b6504acc"}
{"catalogNumber":6,"content":"8d843bcf-6fce-4376-9e88-1eb2cfddf8d3"}
{"catalogNumber":7,"content":"f7195fdf-4b07-4a2a-9cc9-b8ea4c87bcc8"}
{"catalogNumber":8,"content":"605c5ede-77a2-487c-ba5e-ffe5f80fe41c"}
{"catalogNumber":9,"content":"7a1966c8-140b-4819-bdb0-59583b64397c"}
* Connection #0 to host localhost left intact
```




Cool, right?