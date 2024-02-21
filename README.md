# Système de Caisse

Ce projet représente un système de caisse simple conçu pour faciliter les transactions et la gestion des ventes dans un contexte commercial.
Il offre une interface utilisateur intuitive pour enregistrer rapidement les ventes, modifier les quantités vendues et visualiser les transactions.

## Fonctionnalités

- Authentification de caissier
- Enregistrement des ventes effectuée.
- Modification de la quantité d'un article directement depuis l'interface.
- Annulation d'une vente ou d'un produit spécifique.
- Visualisation et impression des reçus de vente.

## Prérequis

- Java `JDK 11` ou supérieur.
- Système de gestion de base de données `Oracle` pour le stockage des données.
- (Facultatif) `IDE` tel que IntelliJ IDEA ou Eclipse pour le développement.

## Installation

1. Clonez le dépôt GitHub sur votre système local :
   ```bash
    git clone https://github.com/ismailza/Caisse.git
    ```

2. (Facultatif) Importez le projet dans votre IDE préféré pour faciliter le développement.
3. Compilez et exécutez le projet. Dans un terminal ou une invite de commande, naviguez jusqu'au répertoire du projet et exécutez :
    ```bash
    javac Caisse.java
    java Caisse
    ```

## Utilisation

### Configuration de la Base de Données Oracle

Avant de lancer l'application, une base de données doit être configurée dans Oracle pour stocker les données de vente.

1. Créer une Base de Données `caisse`
2. Exécution du Script `database.sql` : Le fichier database.sql fourni dans le package database contient les instructions SQL pour créer les tables nécessaires et insérer quelques données initiales.
3. Configurer la Classe de `Connexion`

Après avoir configuré la base de données et la classe de connexion, vous êtes prêt à lancer l'application. L'interface graphique qui s'affiche permettra de :

- **S'authentifier** : Utilisez le nom d'utilisateur (`ismail`) et le mot de passe (`1234`) pour vous connecter.
- **Ajouter des Articles** : Ajoutez des articles à une vente en cours en spécifiant leur référence et la quantité.
- **Modifier la Quantité** : Modifiez la quantité d'un article déjà ajouté si nécessaire.
- **Annuler une Vente** : Annulez une vente en cours ou supprimez un article spécifique de la vente.

## Contribution

Les contributions sont toujours les bienvenues ! Veuillez suivre ces étapes pour contribuer :

1. Fork le projet.
2. Créez votre propre branche pour la fonctionnalité (git checkout -b feature/feature_name).
3. Committez vos changements (git commit -am 'Ajout de certaines fonctionnalités').
4. Poussez la branche (git push origin feature/feature_name).
5. Ouvrez une Pull Request.

## Support
Pour obtenir de l'aide ou signaler des problèmes, veuillez ouvrir une `issue` dans le dépôt GitHub du projet.

## Licence
Distribué sous la licence `MIT`. Voir [LICENSE](LICENSE) pour plus d'informations.