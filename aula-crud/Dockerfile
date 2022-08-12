#FROM openjdk
#/WORKDIR /app
#COPY target/aula-crud-0.0.1-SNAPSHOT.jar /app/aula-crud.jar
#CMD ["java", "-jar","aula-crud.jar"]


FROM openjdk:11
#diretório criado dentro do container
WORKDIR /myapp
#
COPY ./target/aula-crud-0.0.1-SNAPSHOT.jar /myapp/aula-crud.jar
#comandos que serão executados quando o container for levantado
CMD ["java", "-jar","aula-crud.jar"]