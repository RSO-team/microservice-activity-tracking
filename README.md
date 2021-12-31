# RSO: Microservice Stats

Microservice which manages stats in our service

## Prerequisites

```bash
docker run -d --name pg-stats -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=stats -p 5432:5432 postgres:13
```