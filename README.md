# Wine App (Java Swing + Gradle, sem Spring Boot)

Projeto Java **puro** com **Swing** e **Gradle** + **PostgreSQL via Docker** e **migrations Flyway**.

## Requisitos
- Java 21+
- Docker + Docker Compose
- Gradle (ou use `./gradlew` se adicionar wrapper depois)

## Subir o Postgres
```bash
    docker compose up -d
```

A base padrão ficará acessível em `localhost:5432` com:
- DB: `db_wine`
- user: `postgres`
- password: `postgres`

## Rodar as migrations
Scripts em `src/main/resources/db/migration` (ex.: `V1__create_table_wine.sql`).

```bash
    ./gradlew flywayInfo
    ./gradlew flywayMigrate
```

> Você pode sobrescrever via variáveis de ambiente:
> `FLYWAY_URL`, `FLYWAY_USER`, `FLYWAY_PASSWORD`.

## Rodar a aplicação Swing
```bash
    ./gradlew run
```

Para gerar o JAR:
```bash
    ./gradlew build
    java -jar build/libs/wine-app-1.0-SNAPSHOT.jar
```
