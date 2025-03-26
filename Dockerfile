# Utilisation de l'image OpenJDK 21 pour exécuter l'application Java
FROM openjdk:21

# Définir le répertoire de travail à l'intérieur du conteneur
WORKDIR /app

# Copier l'application Java dans le conteneur
COPY ./app /app

# Exposer le port 8080
EXPOSE 8080

# Définir la commande par défaut pour lancer l'application
CMD ["java", "-jar", "/app/ktor-all.jar"]
