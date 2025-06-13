# Pump The Volume Up 
## Getting Started
### Prerequisites
- Docker
- Docker Compose

### Deployment
copy the `.env.example` file to `.env` and set the environment variables. 
```bash
cp .env.example .env
```
and copy in backend the `prod-application.properties.example` to `prod-application.properties` and set the database environment variable like in the `.env`.
```bash
cp prod-application.properties.example prod-application.properties
```
then run the following command to build and start the containers:
```bash
docker compose up 
```

### Development
#### Musics
to develop only the music service, you can run the following command in the musics directory:
```bash
mvn clean install 
mvn clean spring-boot:run
```
