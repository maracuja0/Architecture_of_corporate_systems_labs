# Практическая работа №1
## Приложение с типовой архитектурой JakartaEE
*Выполнили студенты группы 6132-010402D Сорокин Д.М. и Буторина П.В.*


### Задание 1
Запуск сервера приложений GlassFish осуществляется с помощью команды:
```
[путь к glassfish]\bin\asadmin start-domain
``` 
![img_1.png](assets/img_1.png)

На скриншоте представлена панель управления сервером GlassFish:

![img_2.png](assets/img_2.png)

### Задание 2
В качестве базы данных была выбрана **PostgreSQL**, **pgAdmin4** для доступа к базе данных, однако далее для доступа к бд использовалась только IDEA.

![img_8.png](assets/img_8.png)

Доступ к бд с помощью встроенной фичи IDE:

![img.png](assets/img.png)


### Задание 3

Скрипт для создания таблиц в базе данных:

```
CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       user_password VARCHAR(255) NOT NULL,
                       user_first_name VARCHAR(100) NOT NULL,
                       user_last_name VARCHAR(100) NOT NULL,
                       user_gender BOOLEAN,
                       user_bday DATE,
                       user_email VARCHAR(150),
                       user_phone VARCHAR(50)
);

CREATE TABLE positions (
                           position_id SERIAL PRIMARY KEY,
                           position_name VARCHAR(200) NOT NULL,
                           position_desc TEXT,
                           position_type VARCHAR(200),
                           position_date DATE
);

CREATE TABLE liked (
                       id SERIAL PRIMARY KEY,
                       user_id INTEGER NOT NULL REFERENCES users(user_id),
                       position_id INTEGER NOT NULL REFERENCES positions(position_id),
                       liked_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT unique_like UNIQUE (user_id, position_id)
);
```

### Задание 4

Классы users, positions и liked настроены как сущности с помощью аннотации @Entity. EntityManager используется для управления данными сущностями.

Подключение к базе данных организовано на уровне сервера приложений, в файле persistence.xml указан только настроенный JDBC Resource.

Все операции с данными реализованы в классах с подписью Repository.

### Задание 5

Бизнес логика реализована в классах c подписью Service, которые используют репозитории для доступа к данным БД.

### Задание 6

Для реализации пользовательского интерфейса использованы Java Servlets и JSP-страницы.

### Задание 7 
С помощью mvn clean install запускается приложение и создается war-файл, который загружается на сервер GlassFish в раздел Applications.

Далее представлены изображения, с примером работы программы:

![img_4.png](assets/img_4.png)

Необходимо выбрать пользователя для запуска эмулятора магазина электроники:

![img_5.png](assets/img_5.png)

После выбора можно добавить в избранное определенный товар:

![img_6.png](assets/img_6.png)

Избранное пользователя:

![img_7.png](assets/img_7.png)