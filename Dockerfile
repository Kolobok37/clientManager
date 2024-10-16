# Используем официальный образ Maven для сборки проекта
FROM maven:3.8.5-amazoncorretto-17 AS build

# Устанавливаем рабочую директорию для сборки
WORKDIR /app

# Копируем файл с зависимостями проекта (pom.xml)
COPY pom.xml .

# Загружаем зависимости, чтобы их можно было кешировать
RUN mvn dependency:go-offline

# Копируем весь исходный код проекта в контейнер
COPY . .

# Собираем проект, создавая JAR-файл
RUN mvn clean package -DskipTests

# Используем более легкий образ JDK для запуска
FROM amazoncorretto:17-alpine-jdk

# Устанавливаем рабочую директорию для приложения
WORKDIR /app

# Копируем JAR-файл из предыдущего этапа сборки
COPY --from=build /app/target/*.jar app.jar

# Устанавливаем переменные среды для Java-инструментов (если нужно)
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9091

# Указываем команду для запуска JAR-файла
ENTRYPOINT ["java", "-jar", "app.jar"]
