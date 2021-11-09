# RSO: Microservice activity-tracking

Microservice which manages activity-tracking in our service

## Prerequisites

```bash
docker run -d --name pg-activity-tracking -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=activity-tracking -p 5432:5432 postgres:13
```