# yuque-to-markdown-api [![Build](https://github.com/Frederick-S/yuque-to-markdown/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/Frederick-S/yuque-to-markdown/actions/workflows/build.yml)
Export yuque repository to markdown files. 

Although it's not so useful to host it as a web app (see known issues), it could be an example of how to interact with yuque api.

## Getting started
1. Follow the [instructions](https://www.yuque.com/yuque/developer/create-oauth-apps) to create an oauth app, the callback url is `http://localhost:8080/oauth2/authorized`
2. A redis server
3. Run `mvn package` to package the app, then run `java -jar target/yuque2md-0.0.1-SNAPSHOT.jar` with the following environment variables:
    1. `YUQUE_CLIENT_ID`: the client id of your oauth app
    2. `YUQUE_CLIENT_SECRET`: the client secret of your oauth app
    3. `YUQUE_REDIRECT_URI`: http://localhost:8080/oauth2/authorized, this url will be called by yuque when the user is successfully authorized
    4. `YUQUE_CLIENT_REDIRECT_URI`: http://localhost:8000, this should be the host of [yuque-to-markdown-web](https://github.com/Frederick-S/yuque-to-markdown-web)
    5. `REDIS_HOST`: the host of your redis server
    6. `REDIS_PORT`: the port of your redis server

## Known issues
1. Due to the [rate limit](https://www.yuque.com/yuque/developer/api#5b3a1535) of yuque, it's highly possible that you'll receive `429 Too Many Requests` if you have a lot of docs to export. The doc only mentions the rate limit is 5000/hour, but it doesn't say how many requests can be sent per second
2. The doc content returned by the api might be not well formatted, especially the code block which doesn't wrap the whole code

## License
[MIT](LICENSE)
