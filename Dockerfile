# Etapa de compilación: Compilar la aplicación usando Gradle
FROM gradle:7.4.2-jdk17 AS builder

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /home/gradle/project

# Copiar los archivos de configuración de Gradle y el código fuente
COPY gradlew ./gradlew
COPY gradlew.bat ./gradlew.bat
COPY gradle ./gradle
COPY build.gradle ./build.gradle
COPY settings.gradle ./settings.gradle
COPY main.gradle ./main.gradle
COPY . .

# Hacer que el wrapper de Gradle sea ejecutable
RUN chmod +x gradlew

# Limpiar y construir la aplicación
RUN ./gradlew clean build --no-daemon --stacktrace

# Etapa de runtime: Crear la imagen de runtime
FROM eclipse-temurin:17-jdk

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Volumen para archivos temporales
VOLUME /tmp

# Copiar el archivo JAR desde la etapa de compilación
COPY --from=builder /home/gradle/project/applications/app-service/build/libs/*.jar pokemonapp.jar

# Establecer variables de entorno
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

# Instalar utilidades necesarias para agregar usuario y grupo
RUN apt-get update && apt-get install -y passwd && rm -rf /var/lib/apt/lists/*

# Crear un usuario no-root y cambiar a él
RUN groupadd --system appgroup && useradd --system --ingroup appgroup appuser
USER appuser

# Ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar pokemonapp.jar"]
