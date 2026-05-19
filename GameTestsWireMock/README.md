# Автотесты для веб-сервиса на Spring Boot с использованием WireMock

Приложение можно скачать: https://gametests.nyc.wf/aqa.7z
Пароль от архива: g7%Kp9#rX2bL

Стек технологий: Java 17, JUnit 5, , Allure, Maven

Для запуска необходима Java 17 или выше:
java -jar -Dsecret=qazWSXedc -Dmock=http://localhost:8888/ internal-0.0.1-SNAPSHOT.jar

## Эндпоинты приложения

У тестируемого приложения только один эндпоинт, который принимает на вход POST-запросы вида:
POST http://localhost:8080/endpoint
Content-Type: application/x-www-form-urlencoded
Accept: application/json
X-Api-Key: qazWSXedc
token=${token}&action=${action}

Для доступа к эндпоинту требуется заголовок `X-Api-Key`.
Это статический API-ключ, который проверяется приложением при каждом запросе.
token - строка длиной 32 символа, состоящая только из символов A-Z0-9
action - действие пользователя.

## 3 вида ACTION

1)**LOGIN - аутентификация**. Триггерит отправку запроса /auth на внешний сервис. В случае успеха
токен сохраняется во внутреннем хранилище

2)**ACTION - действие**. Триггерит отправку запроса /doAction на внешний сервис. Доступно только для
токенов, ранее прошедших LOGIN

3)**LOGOUT - завершение сессии юзера**. Удаляет токен из внутреннего хранилища
В ответ приходит json.
Тело успешного ответа:
{
"result": "OK"
}
Тело неуспешного ответа:
{
"result": "ERROR",
"message": "reason"
}

## Эндпоинты внешнего сервиса

У внешнего сервиса только два эндпоинта. На них отправляет запросы тестируемое приложение.
Оба могут возвращать любое тело ответа, тестируемое приложение смотрит только на код.
Примеры запросов от тестируемого приложения:
**1)/auth**
POST ${mock}/auth
Content-Type: application/x-www-form-urlencoded
Accept: application/json
token=${token}

**2)/doAction**
POST ${mock}/doAction
Content-Type: application/x-www-form-urlencoded
Accept: application/json
token=${token}