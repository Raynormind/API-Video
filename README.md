# Projet Video SED

## Nom
API partage de vidéo Tutoriel.

## Description de la problèmatique
Le collège souhaite mettre en place une plateforme pour la création et le partage de vidéo tutoriel
accessible pour tous.

## Description du projet
L'application est une API publique qui permet à tous de consulter la liste des vidéos publier par des utilisateurs et de recherché une vidéo par son nom afin de visioner la vidéo.

Les utilisateurs connecté peuvent publier des vidéos tutoriel. Pour ce faire, l'utilisateur doit nommer la vidéo, fournir une images pour la miniature, décrire la vidéo et déposer le fichier vidéo. Après avoir publier une vidéo l'utilisateur peut modifier ou supprimé ses vidéos à sa guise.

Afin de n'avoir que des vidéos de tutoriel des comptes administrateur sont en mesure de supprimé n'importe quelle vidéo contenant du contenu inaproprier pour la plateforme. 

## Modèles
https://miro.com/app/board/uXjVLdJyHBQ=/?share_link_id=262411277066

## Installation
Pour l'authentification Auth0 il y a 2 roles Utilisateur(possède uniquement les droit suivants write:videos, update:videos, delete:videos) et admin possède tousles droit(updateAll:videos, deleteAll:videos, read:video, update:video ...)

La création et l'insertion des utilisateur auth0 dans la base de données de l'api est esssentielle à son fonctionnement.

Instructions pour déployer le projet (à venir)

## Utilisation
Exemples d'utilisation du projet (à venir)

## Auteurs
Lesly-Junior Gourdet

Jacob Zapitoski

## Licence
Licence GNU GPL

## Statut du projet
dévellopement en arrêt.
