# Автотесты для веб-сервиса https://reqres.in использованием фреймворка Gatling

## Что тестируется

API: ReqRes
Endpoints:
GET /api/users?page=1
GET /api/users/2

## Нагрузочное тестирование:
**Цель теста**
Проверка поведения API под параллельными пользователями
Демонстрация load testing с ramp-up моделью


## Модель нагрузки

rampUsers(20).during(10)

означает:

20 виртуальных пользователей
плавный рост нагрузки за 10 секунд
каждый user выполняет сценарий 1 раз

## Сценарии

Scenario 1: Get Users list
Scenario 2: Get Single User