FROM maven

WORKDIR /jwt-authentication-api
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run