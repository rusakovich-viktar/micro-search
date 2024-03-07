# Система управления новостями

## Микросервис micro-search, работающий с базой данных для полнотекстового поиска

Является частью общей системы
- [ News Management System](https://github.com/rusakovich-viktar/news-management-system/tree/develop)

### Основные сущности и назначение:

-	news (новость) содержит поля: id, time, title, text и comments (list).
-	comment содержит поля: id, time, text, username и news_id.
 - полнотекстовый поиск по различным параметрам (для новостей и комментариев). Для потенциально объемных запросов реализована постраничность


### Стек технологий, примененный в micro-search

- Использованы Spring Boot 3.2.3, Java 17, Gradle 8.5 и PostgreSQL 15.1;
- Spring Data JPA, hibernate-search-mapper-orm, hibernate-search-backend-elasticsearch, Spring Web, liquibase, mapstruct, spring-cloud-config-client, lombok
- Для тестирования использованы spring-boot-testcontainers, junit5, Mockito, Wiremock

### Реализация. Общие пояснения к деталям

- Микросервис получает конфиги из micro-config-server-cloud сервиса, поэтому **необходимо чтобы сервер с конфигами при
старте данного микросервиса работал.**

### Используется одна база данных, к каждой таблице которой обращается свой микросервис. Также совместно с базой данных в контейнере работает Elasticsearch, обеспечивающий реализацию полнотекстового поиска

![структура](https://github.com/rusakovich-viktar/NMS-resourses/raw/rusakovich-viktar-patch-1/Снимок%20экрана%202024-03-04%20151246.jpg)

### Реализованы и подключены два Spring Boot Starter:


- logger-aspect-starter реализует логирование запрос-ответ в аспектном стиле (для слоя Controlles)
- exception-handler-starter реализует глобальную обработку исключений и интерпретацию их согласно REST.
-    Стартеры при сборке загружаются первыми, далее из локального репозитория .m2 как зависимости подключаются к другим микросервисам.



### Документация swagger доступна по пути `/api`

```
Код документирован @JavaDoc, подключен Swagger (OpenAPI 3.0)
```

### Тестирование. Сервисный слой покрыт на 100%, весь код 85-87%.

![news-coverage](https://github.com/rusakovich-viktar/NMS-resourses/raw/rusakovich-viktar-patch-1/search-coverage.jpg)

    Использованы testcontainers в тестах persistence layer (для БД)
 	Написаны интеграционные тесты

    Для тестирования порты 8083 должны быть свободны.    
    Также для тестирования база данных поднималась локально. Тестирование проводится в @Profile dev.

### Использован Docker, написан DockerFile

Написаны Dockerfile – для каждого spring boot приложения, создан общий docker-compose.yml для поднятия всех сервисов в контейнерах, настроено взаимодействие между ними)

### Реализована поддержка @Profile prod и dev. Конфиги вынесены в Spring Cloud Config:

- В контексте приложения условно будем считать что профиль prod будет применяться для запуска и взаимодействия микосервисов в docker сети.
Для локальной разработки, а также для тестирования будем использовать профиль dev.
- Профили конфигурируются через micro-config-server-cloud, поэтому он должен быть запущен или в контейнере для профиля prod или локально для профиля dev.
- Для смены профиля dev(локально и тесты) и prod(в докере) необходимо в папка проекта в application.yml сменить активный профиль на 
  - profiles: active: dev 
    - или 
  - profiles: active: prod

# Как запустить приложение

Является частью сервиса по управлению новостями, запускать совместно с прочими микросервисами по инструкции
из [News Management System](https://github.com/rusakovich-viktar/news-management-system/tree/develop)

- Можно запустить одиночно, но так как он лишь перенаправляет запросы по OpenFeign - работать одиночно он не будет.
- Необходимы все микросервисы с их зависимостями.
- Порт для запуска `8080`

## ЭНДПОИНТЫ И ИНТЕРФЕЙС

Все эндпоинты выполнены с учетом требований REST, Документация swagger доступна по пути `/api`
запросы и ответы дублируются как с сервисами micro-news, micro-comments, micro-search с некоторыми изменениями
- все запросы направляются на порт `8080`
- у всех адресов будет предикат `/api/v1`
- запросы к сервису поиска будут изменены вместо search/news?text=мин нужно будет обращаться к news/search?text=мин

<details>
 <summary><strong>
 подробнее - раскрывающийся список эндпоинтов
</strong></summary>

- можно настроить пагинацию в каждом запросе, например, добавив к запросу `?page=0&size=2`, где page номер страницы с 0, а size количество отображаемых новостей на странице  

#### 1. GET запрос на http://localhost:8083/search/news?text={qwerty}, где qwerty = набор символов для поиска в базе данных
будет возвращать список новостей, в названии или тексте которой будет встречен данный набор символов
например,
http://localhost:8083/search/news?text=мин 
вернет 
```
{
    "content": [
        {
            "id": 5,
            "time": "2024-02-29T17:34:51.156603",
            "updateTime": "2024-02-29T17:34:51.156603",
            "title": "ГАИ Минска рассказала, как поменять права по предварительной записи",
            "text": "Замена водительского удостоверения — процедура несложная, но порой..."
        },
        {
            "id": 6,
            "time": "2024-02-29T17:34:51.156603",
            "updateTime": "2024-02-29T17:34:51.156603",
            "title": "Рентгеновский снимок и стройинструменты: вот что пассажиры забывают в минском аэропорту",
            "text": "За 2023 год сотрудники Национального аэропорта Минск нашли ..."
        },
        {
            "id": 17,
            "time": "2024-02-29T17:34:51.156603",
            "updateTime": "2024-02-29T17:34:51.156603",
            "title": "Минсвязи выпустит в обращение конверт с маркой \"Белыничи - культурная столица Беларуси\"",
            "text": "Министерство связи и информатизации 21 ....."
        },
        {
            "id": 18,
            "time": "2024-02-29T17:34:51.156603",
            "updateTime": "2024-02-29T17:34:51.156603",
            "title": "Беларусь существенно обновит свой пассажирский автопарк в 2024 году",
            "text": "Власти каждый год проводят в столице и регионах апгрейд ... Минтранса Андрей Гладкий.\nПо его словам, в 2023 году для организаций транспорта о...."
        },
        {
            "id": 19,
            "time": "2024-02-29T17:34:51.156603",
            "updateTime": "2024-02-29T17:34:51.156603",
            "title": "Рекомендации по применению некоторых требований и методов испытаний ТКП 290-2023",
            "text": "Настоящим информируем органы по оценке соответствия, аккредитованные на проведение ... Министерства энергетики от 29.08...."
        },
        {
            "id": 20,
            "time": "2024-02-29T17:34:51.156603",
            "updateTime": "2024-02-29T17:34:51.156603",
            "title": "Сколько стоила самая дорогая квартира в Минске, проданная в январе",
            "text": "В январе 2024 года самой дорогой из проданных в столице квартир оказалась «трешка», .... Минске продали ещ..."
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 20,
        "sort": [],
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 6,
    "first": true,
    "numberOfElements": 6,
    "size": 20,
    "number": 0,
    "sort": [],
    "empty": false
}
```
#### 2. GET запрос на http://localhost:8083/search/comments?text={qwerty}, где qwerty = набор символов для поиска в базе данных
будет возвращать список комментариев, в юзернейме или тексте комментария которых будет встречен данный набор символов
например,
http://localhost:8083/search/comments?text=пер
вернет
```
{
    "content": [
        {
            "id": 1,
            "time": "2024-02-29T17:34:51.185191",
            "updateTime": "2024-02-29T17:34:51.185191",
            "text": "первый",
            "username": "user1",
            "newsId": 1
        },
        {
            "id": 11,
            "time": "2024-02-29T17:34:51.185191",
            "updateTime": "2024-02-29T17:34:51.185191",
            "text": "первый",
            "username": "user1",
            "newsId": 2
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 20,
        "sort": [],
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "first": true,
    "numberOfElements": 2,
    "size": 20,
    "number": 0,
    "sort": [],
    "empty": false
}
```
</details>