# Comfortly: Analyzed trip data microservice

## Prerequisites

```bash
docker run -d --name pg-analyzed-trip-data -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=analyzed-trip-data -p 5435:5432 postgres:13
```