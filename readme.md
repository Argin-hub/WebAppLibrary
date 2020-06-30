# WebLibrary

WebLibrary - это веб-приложение для управления читателями и книгами в библиотеке.
Tested with:
Intellij Idea 2020 1.1
Java 8, jdk 1.8.
MySQL Server 8.0.19
Tomcat 9.0.35
Сборщик: Maven 3.8.1
## Установка
1. Развернуть БД запросами из файла db_schema.sql
2. Заполнить БД запросами из файла db_data.sql
3. В файле ./WebAppLibrary/src/main/resources/db.properties заменить имя пользователя БД и пароль на свои.
4. Поместить файл log4j-1.2.17.jar в директорию /lib Apache Tomcat (в папке, куда установлен Apache Tomcat).
5. Возможно, Idea попросит подгрузить файлы "Configure", нажать подгрузить, web -> web.xml. 
6. Название у проекта не изменять. 
7. WebAppLibrary_war_exploded
8. собрать проект
## Использование
Admin:
admin@admin.com
123456

User:
test@test.ru
123456
