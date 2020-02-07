# java-spring-pub

# Overview

Приложение для демонстрации механизма работы Бара, контроля заполненности заведения, работы персонала, 
отслеживания закупок продуктов и платежей клиентов.

# Сущности

## Посетитель (Client)

Поля:
* Почта
* ФИО
* Номер телефона
* Пол
* Возраст
* Скидка

Связи:
* отсутствуют(временно)

## Менеджер (Manager)

Поля:
* Почта
* ФИО
* Пол
* Возраст
* Номер телефона

Связи:
* отсутствуют(временно)

## Waiter

Поля:
* Почта
* ФИО
* Пол
* Возраст
* Номер телефона

Связи:
* отсутствуют(временно)

## Place(столик в баре, позиция у стойки) - это второстепенный функционал, вводится для возможности бронирования столов.

Поля:
* Номер стола
* Время бронирования
* ФИО посетителя (заказавшего столик)
* Общее кол-во мест
* Кол-во занятых мест
* ФИО бармена(официанта)

Связи:
* отсутствуют(временно)


## Menu Item

Поля:
* Наименование напитка
* Порция(мл)
* Объем бутылки
* Стоимость за порцию
* Стоимость за бутылку
* Крепость
* Описание

Связи:
* отсутствуют(временно)

## Order 

Поля:
* Наименование
* Количество порций
* Цена по наименованию
* Итоговая цена
* Чаевые - (возможно будет выделено в отдельную сущность с суммой + чаевыми)

Связи:
* отсутствуют(временно)

# User Stories

## JSPP-1 Как "Посетитель" я хочу зарегистрироваться в системе, если такого пользователя не найдено.

Request:

```
POST /java-spring-pub/client/sign-up
```
```json
{
  "email" : "vasya@gmail.com",
  "password" : "qwerty",
  "fio" : "Пупкин Василий Иванович",
  "mobileNumber" : "+8-800-555-35-35",
  "gender" : "male", 
  "age" : "69" 
}
```
Response: ``` 201 CREATED ```

```json
{
  "id" : 1
}
```

## JSPP-2 Как "Посетитель" я хочу войти в систему, будучи зарегистрированным пользователем. Если почта и пароль пользователя совпадает, войти в систему.

Request:
```
POST /java-spring-pub/client/sign-in
```
```json
{
  "email" : "vasya@gmail.com",
  "password" : "qwerty"
}
```
Response: ```200 OK```

## JSPP-3 Как "Клиент" я хочу получить список "Menu Item", если пользователь зарегистрирован.

Request:
```
GET /java-spring-pub/client/menu
```
Response: ```200 OK```
```json
[
  {
    "id" : 1, 
    "title" : "Zubrowka",
    "portion" : "50", 
    "bottleVolume" : "1000",
    "portionPrice" : "5", 
    "bottlePrice" : "50", 
    "strength" : "40",
    "description" : "Водка Зубровка"
  }
]
```

## JSPP-4 Как "Клиент" я хочу сделать "Order", если пользователь зарегистрирован.

Request:
```
POST /java-spring-pub/client/order
```
```
{
   "waiterId" : "1",
   "itemsList": [
      {"menuItemId": "1", "volume": "1550", "volumeAmount" : "105"}
      {"menuItemId": "2", "volume": "150", "volumeAmount" : "45"}
                ],
   "totalAmount" : "150"
}
```
Response: ```200 OK```

## JSPP-5 Как "Клиент" я хочу оплатить "Payment", если пользователь зарегистрирован.

Request:
```
GET /java-spring-pub/client/payment?orderId=1&amount=50&tips=10
```
Response: ```200 OK```
```
{
    "oddMoney" : "5"
}
```

## JSPP-6 Как "Менеджер" я хочу добавить "Menu Item", если пользователь зарегистрирован.

Request:
```
POST /java-spring-pub/manager/menu/add
```
```json
{
    "id" : 1, 
    "title" : "Zubrowka",
    "portion" : "50", 
    "bottleVolume" : "1000",
    "portionPrice" : "5", 
    "bottlePrice" : "50", 
    "strength" : "40",
    "description" : "Водка Зубровка"
  }
```
Response: ```200 OK```

## JSPP-7 Как "Менеджер", я хочу удалить "Menu Item", и если такое наименование есть, удаляю его

Request:
```
DELETE /api/admin/beer/${itemId} Headers: itemId=1
```
```
Response: 200 OK
```

## JSPP-8 Как "Менеджер" я хочу посмотреть "Прибыль" за промежуток времени, если пользователь зарегистрирован.
Время передается либо датой, либо Timestamp-ом

Request:
```
GET /java-spring-pub/manager/profit?startDate=1577836800&endDate=1577923200
```
Response: ```200 OK```
```
{
  "amount" : 351.50
}
```

## JSPP-9 Как "Менеджер" я хочу увидеть объем проданных "Menu Item", если пользователь зарегистрирован.

Request:
```
GET /java-spring-pub/manager/menu/spent
```
Response: ```200 OK```
```json
{
    "id" : 1, 
    "volume" : "3550",
    "bottleCount" : "3.55",
    "description" : "Водка Зубровка"
  }
```

## JSPP-10 Как "Менеджер", я хочу изменить цену "Menu Item", и если такого наименования есть, изменяю ему цену
Request:
```
PUT /api/admin/beer/${beerId}  Headers: beerId=1
```
```
{
    "price": "8.30"
}
```
Response: ```200 OK```
```
{
    "id" : 1
}
```
