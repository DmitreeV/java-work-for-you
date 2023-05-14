
<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/github_username/repo_name">
   <img src="https://x-lines.ru/letters/i/cyrillicscript/1533/b4b4b6/60/0/k7zzr43yc3zzred3p74o.png">


  </a>

  <p align="center">
  </p>
</div>


</details>
<details><summary><b>Содержание</b></summary>
  
    1. Краткое описание
    2. Стек-технологий
    3. Функциональности проекта
    4. Схема базы данных
    5. Системные требования
    
</details>


## Краткое описание

Work for you - это приложение для поиска работы. Пользователь может создавать резюме, просматривать опубликованные вакансии и откликаться на них, просматривать информацию о компаниях и оставлять отзывы, искать подходящие для себя вакансии в поиске и др. API сервиса разделен на три части. Первая — публичная, доступна без регистрации любому пользователю сети. Вторая — закрытая, доступна только авторизованным пользователям. Третья — административная, для администраторов сервиса. Для ограничения доступа реализована авторизация с помощью Spring Security. Взаимодействие с базой данных Postgres настроено через docker-compose.

Данное приложение является pet(домашним) проектом, в котором я развивал свои навыки, добавлял новые функциональности, возможности и идеи. 

## Стек-технологий

* Spring Framework
* Spring Boot
* JPA+API
* Spring Security
* Mapstruct
* Swagger
* Maven
* Postgres
* Docker

## Функциональности проекта
Приложение отвечает требованиям [спецификации](./work-for-you-spec.json).

С полным списком функциональностей проекта можно ознакомиться при помощи [swagger](https://editor-next.swagger.io).

## Схема базы данных
![Work-for-you Data Base diagram](https://github.com/DmitreeV/java-work-for-you/blob/add-improvements/image/db%20diagram%20workforyou.jpg)

## Системные требования

В данном репозитории представлен бэкенд приложения. Для проверки работоспособности приложение было протестировано по WEB API, для этого мной были написаны Postman тесты, они расположены в папке [postman](./postman/).

Приложение работает корректно в текущем виде при наличии:

- установленный [JDK версии 11](https://docs.aws.amazon.com/corretto/),
- сборка с использованием [Maven](https://maven.apache.org/),
- установленный [Docker](https://www.docker.com/products/docker-desktop/).
