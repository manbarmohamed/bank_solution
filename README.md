# Application E-Bank - Bank Solutions

## Contexte du projet

Bank Solutions, une enseigne bancaire traditionnelle, souhaite développer une application e-bank pour offrir des services bancaires en ligne. Ce projet vise à créer une plateforme sécurisée et intuitive permettant aux utilisateurs de gérer leurs comptes bancaires et d'effectuer des opérations financières en ligne.

## User Stories

### Gestion des comptes

#### Création de comptes bancaires
- En tant qu'utilisateur, je veux pouvoir créer un nouveau compte bancaire (courant, épargne, etc.) pour gérer mes finances.
- Attributs : Type de compte, Solde initial, Date de création

#### Consultation des soldes et historiques de transactions
- En tant qu'utilisateur, je veux consulter le solde et l'historique des transactions de mes comptes pour suivre mes dépenses et revenus.
- Attributs : ID de la transaction, Date et heure, Montant, Type de transaction (Crédit, Débit), Description

#### Fermeture de comptes
- En tant qu'utilisateur, je veux pouvoir fermer un compte bancaire pour ne plus l'utiliser.
- Attribut : Raison de la fermeture

### Gestion des cartes bancaires

#### Consultation des informations de la carte
- En tant qu'utilisateur, je veux pouvoir consulter les informations de ma carte bancaire.
- Attributs : Numéro de carte, Date d'expiration, Type de carte (Débit, Crédit)

#### Activation et désactivation de la carte
- En tant qu'utilisateur, je veux pouvoir activer ou désactiver ma carte bancaire pour sécuriser mes transactions.

#### Blocage en cas de perte ou de vol
- En tant qu'utilisateur, je veux pouvoir bloquer ma carte bancaire en cas de perte ou de vol.
- Attribut : Raison du blocage

### Transferts d'argent

#### Transferts internes entre comptes du même utilisateur
- En tant qu'utilisateur, je veux pouvoir transférer de l'argent entre mes propres comptes.
- Attributs : Montant, Description

#### Transferts externes vers des comptes dans d'autres banques
- En tant qu'utilisateur, je veux pouvoir transférer de l'argent vers des comptes externes.
- Attributs : Détails du compte externe (Numéro de compte, Banque, etc.), Montant, Description

#### Gestion des bénéficiaires
- En tant qu'utilisateur, je veux pouvoir ajouter, modifier ou supprimer des bénéficiaires.
- Attributs : Nom, Détails du compte (Numéro de compte, Banque, etc.)

## Exigences techniques

### Structuration du projet
- Le projet doit suivre les bonnes pratiques recommandées pour une application Spring Boot.

### Documentation du Code
- Chaque classe et méthode doit être documentée, incluant :
  - Description des responsabilités
  - Détails sur les paramètres
  - Valeurs de retour

### Logique métier des opérations bancaires
- Validation des données lors de la création et de la fermeture des comptes
- Gestion des exceptions pour les opérations illégales
- Validation des montants avant les transferts

## Prérequis

- Java JDK 21
- Maven 3.6 ou supérieur
- Un IDE de votre choix (IntelliJ IDEA, Eclipse, VS Code, etc.)
- Postman pour tester l'API

## Configuration de l'environnement de développement

1. Clonez le dépôt : `https://github.com/manbarmohamed/bank_solution.git`
2. Naviguez dans le dossier du projet : `cd banksolution`
3. Installez les dépendances : `mvn clean install`

## Exécution de l'application

1. Lancez l'application avec Maven : `mvn spring-boot:run`
2. L'application sera accessible à l'adresse `http://localhost:8081`

## Tests de l'API avec Postman

1. Ouvrez Postman
2. Importez la collection de requêtes fournie dans le dossier postman du projet
3. Configurez l'environnement Postman avec la variable `base_url` définie à `http://localhost:8080`
4. Exécutez les requêtes pour tester les différents endpoints de l'API

Exemples de requêtes :
- Création d'un compte : `POST {{base_url}}/api/accounts`
- Consultation du solde : `GET {{base_url}}/api/accounts/{accountId}/balance`
- Transfert d'argent : `POST {{base_url}}/api/transfers`

## Informations pour les développeurs

- Developper : Manbar Mohamed
- Email : manbar.mohammed@gmail.com
- Social : [LinkedIn](https://www.linkedin.com/in/mohamed-manbar-713a532b4/)
- CodPen : [CodePen](https://codepen.io/Mohamed-Manbar/pen/oNRNvPJ)
- Medium : [Medium](https://medium.com/@MohamedManbar)
