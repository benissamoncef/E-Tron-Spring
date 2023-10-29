# Utilisez une image de base avec Java (par exemple, OpenJDK)
FROM openjdk:11

# Définissez le répertoire de travail
WORKDIR /app

# Copiez le fichier JAR de votre application Spring dans le conteneur
COPY build/libs/siav2.0-0.0.1-SNAPSHOT.jar app.jar

# Commande pour démarrer l'application lorsque le conteneur démarre
CMD ["java", "-jar", "app.jar"]
