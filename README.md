# Dynamic Percentage API

API Spring Boot para cálculo con porcentaje dinámico, caché con TTL 30 minutos y registro asíncrono de historial en PostgreSQL.

## Endpoints

- **POST `/calc`**
  - Body:
    ```json
    { "num1": 100.0, "num2": 50.0 }
    ```
  - Respuesta:
    ```json
    { "result": 165.00, "appliedPercentage": 10.0, "percentageSource": "cache_or_external" }
    ```

- **GET `/history?page=0&size=20`**
  - Devuelve página de historial con: fecha, endpoint, parámetros, respuesta y error.

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`

## Requisitos

- Java 21
- Maven 3.9+
- Docker y Docker Compose

## Configuración

Variables de entorno soportadas:
- `DB_URL` (default `jdbc:postgresql://localhost:5432/dynamicdb`)
- `DB_USERNAME` (default `dynamic`)
- `DB_PASSWORD` (default `dynamic`)
- `EXTERNAL_PERCENTAGE_FIXED` (default `10`)
- `EXTERNAL_PERCENTAGE_FAIL` (default `false`)
- `PORT` (default `8080`)

## Ejecutar en local

```bash
mvn spring-boot:run
```

## Ejecutar con Docker Compose (API + PostgreSQL)

```bash
docker compose up --build
```

La API quedará disponible en `http://localhost:8080` y la BD en `localhost:5432`.

## Estrategia técnica

- **Cálculo**: suma de `num1 + num2` multiplicada por `(1 + porcentaje/100)` con redondeo a 2 decimales.
- **Porcentaje dinámico**: cliente externo simulado (`MockPercentageClient`) configurable.
- **Caché**: Caffeine, TTL 30 minutos. Si el servicio externo falla, se usa el último valor en caché; si no existe, se devuelve `503`.
- **Historial**: `HistoryService` registra asíncronamente en PostgreSQL mediante JPA. `GET /history` expone paginado.
- **Errores**: `@ControllerAdvice` mapea errores a códigos HTTP correctos.
- **Docs**: Springdoc OpenAPI (Swagger UI).

## Tests

- JUnit 5 + Mockito.
- Pruebas unitarias para `CalculationService` y `PercentageService` incluyendo fallos del servicio externo y uso de caché.

## Publicar imagen (opcional)

```bash
docker build -t <usuario>/dynamic-percentage-api:latest .
docker push <usuario>/dynamic-percentage-api:latest
```
