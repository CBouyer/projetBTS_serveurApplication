# ⚙️ Serveur d'application – Projet de fin d'année BTS

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)
![Ktor](https://img.shields.io/badge/Ktor-087CFA?logo=ktor&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white)

Serveur back-end du projet de supervision de capteurs électriques. Il fait le lien entre la base de données, les données des capteurs remontées via **The Things Network** et le site web.

> ⚠️ Ce serveur fonctionne de pair avec le **site web** du projet : [ProjetBTSWebsite](https://github.com/CBouyer/ProjetBTSWebsite)

---

## 📋 Présentation

Le serveur expose une API consommée par le site web (React). Il gère notamment l'authentification des utilisateurs, la distinction entre utilisateurs et administrateurs, ainsi que la récupération et la mise à disposition des données de consommation des capteurs.

## 🏗️ Architecture

```
Capteurs ──► The Things Network ──► Serveur Ktor (ce dépôt) ◄──► MySQL
                                           ▲
                                           │ API (JSON)
                                           ▼
                                   Site web React
```

## 🛠️ Technologies

| Outil | Rôle |
|-------|------|
| Kotlin + Ktor | Serveur et API |
| GSON | Sérialisation JSON |
| Content Negotiation | Conversion automatique des requêtes et réponses |
| Call Logging | Journalisation des requêtes |
| MySQL | Base de données |
| Docker / Docker Compose | Conteneurisation du serveur et de la base |
| Gradle | Build du projet |

## 📁 Structure du dépôt

| Élément | Contenu |
|---------|---------|
| `src/` | Code source du serveur |
| `db/` | Fichiers liés à la base de données |
| `app/` | Emplacement du JAR exécutable utilisé par Docker |
| `application_serveurweb.sql` | Script de création et d'initialisation de la base |
| `Dockerfile` / `docker-compose.yml` | Configuration Docker |

## 🚀 Lancement

### Avec Docker (recommandé)

Prérequis : [Docker](https://www.docker.com/) et Docker Compose.

1. Générer le JAR du serveur :

```bash
./gradlew buildFatJar
```

2. Copier le JAR généré (dans `build/libs/`) vers `app/ktor-all.jar`.

3. Lancer les conteneurs :

```bash
docker compose up -d
```

La base MySQL est automatiquement initialisée avec `application_serveurweb.sql` au premier démarrage.

| Service | Port |
|---------|------|
| Serveur Ktor | `8080` |
| MySQL | `3306` |

### Sans Docker

Prérequis : JDK installé et une base MySQL accessible, initialisée avec `application_serveurweb.sql`.

```bash
./gradlew run
```

Si le démarrage réussit, le serveur répond sur `http://localhost:8080`.

## 🔌 Routes de l'API

<!-- À compléter avec tes routes réelles, par exemple : -->

| Méthode | Route | Description |
|---------|-------|-------------|
| `POST` | `/login` | Connexion d'un utilisateur |
| `GET` | `/capteurs` | Liste des capteurs de l'utilisateur |
| `GET` | `/capteurs/{id}` | Données de consommation d'un capteur |

## 💬 À propos

Ce serveur a été réalisé dans le cadre de mon projet de fin d'année de BTS, en tant qu'exercice d'apprentissage. Si vous souhaitez en discuter, n'hésitez pas à me contacter par mail : **corentinbouyer456@gmail.com**
