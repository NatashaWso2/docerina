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

@Description { value:"Echo service"}
service<http> echo {
    @Description { value:"Echo Resource_2"}
    @Param { value:"req: Request message"}
    @Param { value:"res: Response message"}
    @http:resourceConfig {
        methods:["POST", "PUT", "GET"],
        path:"/"
    }
    resource echoResource_1 (http:Request req, http:Response res) {
        res.setStringPayload("Echo Resource 1 is invoked");
        _ = res.send();
    }
    @Description { value:"Echo Resource_2"}
    @Param { value:"req: Request message"}
    @Param { value:"res: Response message"}
    @http:resourceConfig {
        methods:["POST", "PUT", "GET"],
        path:"/"
    }
    resource echoResource_2 (http:Request req, http:Response res) {
        res.setStringPayload("Echo Resource 2 is invoked");
        _ = res.send();
    }
}