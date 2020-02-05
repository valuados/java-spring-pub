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

## Barman

Поля:
* Почта
* ФИО
* Пол
* Возраст
* Номер телефона

Связи:
* отсутствуют(временно)

## Place(столик в бареб позиция у стойки) - это второстепенный функционал, вводится для возможности бронирования столов.

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

## Check 

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

## JSPP-2 Как "Посетитель" я хочу войти в систему, будучи зарегистрированным пользователем; 
если почта и пароль пользователя совпадает, войти в систему.

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
