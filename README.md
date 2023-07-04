# NHL service (Java)

Business service between free [NHL api](https://statsapi.web.nhl.com/api/v1/teams) and clients ([235 NHL React Native app](https://github.com/ljomoila/235)).
Purpose of the service is to map data from NHL api to more usable form for the clients.

## Docker

-   `docker build -t nhl-service .` - Creates a docker image
-   `docker run -d -p 8080:8080 --name dockerspringboot nhl-service:latest` - Create a docker container
-   `docker container ps` - Verify whether the container has been created successfully
-   Navigate to `http://localhost:8080/teams` to view application.
