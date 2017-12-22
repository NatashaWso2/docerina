import ballerina.net.http;

@Description { value:"Hello world service"}
service<http> helloWorld {
	@Description { value:"Resource to say hello"}
	@Param { value:"req: Request message"}
	@Param { value:"res: Response message"}
	resource sayHello (http:Request req, http:Response res) {
		res.setStringPayload("Hello, World!");
		_ = res.send();
	}
}