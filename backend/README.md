# ChâTop - Backend

API REST pour le portail de location saisonnière **ChâTop**.

Ce back-end remplace le mock Mockoon utilisé par le front-end Angular et fournit toutes les fonctionnalités nécessaires : authentification, gestion des locations, des utilisateurs et des messages.

## Stack

- Java 17
- Spring Boot 3.4 (Web, Data JPA, Security)
- MySQL 8
- JWT (jjwt 0.12.6)
- Springdoc OpenAPI (Swagger)
- Maven

## Prérequis

- Java 17
- MySQL 8+
- Maven (ou utiliser le wrapper `mvnw` fourni)

## Installation

### 1. Cloner le repo

```bash
git clone <url-du-repo>
cd project3-exercises/backend
```

### 2. Base de données

Créer la base et l'utilisateur dans MySQL :

```sql
CREATE DATABASE chatop_db;
CREATE USER 'chatop_user'@'localhost' IDENTIFIED BY 'votre_mot_de_passe';
GRANT ALL PRIVILEGES ON chatop_db.* TO 'chatop_user'@'localhost';
FLUSH PRIVILEGES;
```

Importer le schéma (le script SQL est dans le dossier front-end.ressources/sql) :

```bash
mysql -u chatop_user -p chatop_db < ../frontend/ressources/sql/script.sql
```

### 3. Variables d'environnement

L'application a besoin de 3 variables :

| Variable      | Description                             |
| ------------- | --------------------------------------- |
| `DB_USERNAME` | Utilisateur MySQL                       |
| `DB_PASSWORD` | Mot de passe MySQL                      |
| `JWT_SECRET`  | Clé secrète JWT (32 caractères minimum) |

Pour VS Code, créer `.vscode/launch.json` à la racine du projet (déjà ignoré par Git) :

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Spring Boot-BackendApplication",
      "request": "launch",
      "cwd": "${workspaceFolder}/backend",
      "mainClass": "com.chatop.backend.BackendApplication",
      "projectName": "backend",
      "env": {
        "DB_USERNAME": "chatop_user",
        "DB_PASSWORD": "votre_mot_de_passe",
        "JWT_SECRET": "votre_cle_jwt"
      }
    }
  ]
}
```

## Lancement

Depuis le dossier `backend/` :

```bash
./mvnw spring-boot:run
```

(Sous Windows : `mvnw.cmd spring-boot:run`)

L'API tourne sur `http://localhost:8080`.

## Documentation Swagger

Une fois l'application lancée :

http://localhost:8080/swagger-ui/index.html

Pour tester les routes protégées : récupérer un token via `/api/auth/login`, cliquer sur "Authorize" en haut à droite, coller le token (sans `Bearer `).

## Routes

| Méthode | URL                  | Description           |
| ------- | -------------------- | --------------------- |
| POST    | `/api/auth/register` | Inscription           |
| POST    | `/api/auth/login`    | Connexion             |
| GET     | `/api/auth/me`       | Utilisateur connecté  |
| GET     | `/api/user/{id}`     | Utilisateur par id    |
| GET     | `/api/rentals`       | Liste des locations   |
| POST    | `/api/rentals`       | Créer une location    |
| GET     | `/api/rentals/{id}`  | Détail d'une location |
| PUT     | `/api/rentals/{id}`  | Modifier une location |
| POST    | `/api/messages`      | Envoyer un message    |

Toutes les routes sauf `register` et `login` nécessitent un token JWT dans le header `Authorization: Bearer <token>`.

## Notes

- Les images uploadées sont stockées dans `backend/uploads/` et accessibles via `/uploads/{filename}`
- Taille max d'upload : 10 MB (modifiable dans `application.properties`)
- Les tokens JWT expirent après 24h
