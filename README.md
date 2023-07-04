# NHL service (Java)

Business service between free [NHL api](https://statsapi.web.nhl.com/api/v1/teams) and clients ([235 NHL React Native app](https://github.com/ljomoila/235)).
Purpose of the service is to map data from NHL api to more usable form for the clients.

## Docker

-   `docker-compose up --build` - starts nhl-service
-   Navigate to `http://localhost:8080/teams` to view application.
-   `docker-compose down` - stops container
