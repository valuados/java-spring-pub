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
* Дата Рождения
* Скидка

Связи:
* отсутствуют(временно)

## Менеджер (Manager)

Поля:
* Почта
* ФИО
* Пол
* Дата Рождения
* Номер телефона

Связи:
* отсутствуют(временно)

## Waiter

Поля:
* Почта
* ФИО
* Пол
* Дата Рождения
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
POST /java-spring-pub/sign-up
```
```json
{
  "email" : "vasya@gmail.com",
  "password" : "qwerty",
  "fio" : "Пупкин Василий Иванович",
  "phoneNumber" : "+8-800-555-35-35",
  "gender" : "male", 
  "birthDate" : "19.01.1995" 
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
POST /java-spring-pub/sign-in
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
GET /java-spring-pub/menuitems
```
Response: ```200 OK```
```json
[
  {
    "id" : 1, 
    "title" : "Zubrowka",
    "description" : "Водка Зубровка",
    "portion" : "50", 
    "bottleVolume" : "1000",
    "portionPrice" : "5", 
    "bottlePrice" : "50", 
    "strength" : "40"
  }
]
```

## JSPP-4 Как "Клиент" я хочу сделать "Order", если пользователь зарегистрирован.
Request:
```
POST /java-spring-pub/orders/
```

Response: ``` 201 CREATED ```

```json
{
  "id":1,
  "userId":1,
  "totalPrice":0,
  "status":"NEW",
  "creationDate":"2020-04-13@16:34:12",
  "updateDate":null,
  "paidDate":null,
  "orderedItemRequests":null
}
```

## JSPP-5 Как "Клиент" я хочу добавить "MenuItem" в "Order", если пользователь зарегистрирован.
Request:
```
POST /java-spring-pub/orders/add
```
```
{
   "orderedItems":[
      {
         "menuItem":{
            "id":1
         },
         "volume":1500
      },
      {
         "menuItem":{
            "id":2
         },
         "volume":150
      }
   ]
}
```
Response: ``` 201 CREATED ```

```json
{
  "id" : 1,
  "userId" : 1,
  "totalPrice" : 34.50,
  "status" : "NEW",
  "orderedItems":[
      {
         "menuItem":{
            "id":1
         },
         "volume":1500,
         "totalPrice": 19.50
      },
      {
         "menuItem":{
            "id":2
         },
         "volume":150,
         "totalPrice": 15.00
      }
   ]
}
```

## JSPP-6 Как "Клиент" я хочу оплатить "Payment", если пользователь зарегистрирован.

Request:
```
POST /java-spring-pub/payments
```
```
{
    "orderId" : 1,
    "amount" : 50.0,
    "tips" : 10.0
}
```
Response: ```200 OK```
```
{
    "orderId" : 1,
    "amount" : 50.0,
    "tips" : 10.0,
    "oddMoney" : 5.0
}
```

## JSPP-7 Как "Менеджер" я хочу добавить "Menu Item", если пользователь зарегистрирован.

Request:
```
POST /java-spring-pub/menuitems
```
```json
{
    "title" : "Zubrowka",
    "description" : "Водка Зубровка",
    "portion" : 50, 
    "bottleVolume" : 1000,
    "portionPrice" : 5.0, 
    "bottlePrice" : 50.0, 
    "strength" : 40
  }
```
Response: ```200 OK```

## JSPP-8 Как "Менеджер", я хочу удалить "Menu Item", и если такое наименование есть, удаляю его

Request:
```
DELETE /api/manager/menuitems/${itemId}
```
```
Response: 200 OK
```

## JSPP-9 Как "Менеджер" я хочу посмотреть "Прибыль" за промежуток времени, если пользователь зарегистрирован.
Время передается либо датой, либо Timestamp-ом

Request:
```
GET /java-spring-pub/reports/profit?startDate=1577836800&endDate=1577923200
```
Response: ```200 OK```
```
{
  "amount" : 351.50
}
```

## JSPP-10 Как "Менеджер" я хочу увидеть объем проданных "Menu Item", если пользователь зарегистрирован.

Request:
```
GET /java-spring-pub/reports/spent?startDate=1577836800&endDate=1577923200
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

## JSPP-11 Как "Менеджер", я хочу изменить цену "Menu Item", и если такого id есть, изменяю ему цену
Request:
```
PATCH /java-spring-pub/menuitems/${menuItemId}
```
```
{
    "portionPrice" : 6.0
}
```
Response: ```200 OK```

## JSPP-12 Как "Менеджер", я хочу изменить порцию(portion) "Menu Item", и если такого id есть, изменяю порцию.
Request:
```
PATCH /java-spring-pub/menuitems/${menuItemId}
```
```
{
    "portion" : 500
}
```
Response: ```200 OK```
## JSPP-13 Как "Менеджер", я хочу изменить название(title) "Menu Item", и если такого id есть, изменяю название.
Request:
```
PATCH /java-spring-pub/menuitems/${menuItemId}
```
```
{
    "title" : "Новый бровар"
}
```
Response: ```200 OK```
