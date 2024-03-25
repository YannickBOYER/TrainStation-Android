# TrainStation - Android
## Contexte du projet

Pour ce projet nous avons développé une application Android permettant de trouvé les gares a proximité de la localisation du téléphone de l'utilisateur.

Cette application se base en partie sur des données récupérées de `l'API de la SNCF` mais elle utilise aussi les services `GoogleMap` pour afficher des informations sur une carte.

---

## Lancer l'application

Cloner le projet en local :
```sh
git clone https://github.com/YannickBOYER/TrainStation-Android.git
```

Ouvrir le projet avec `Android Studio` (ou l'IDE de votre choix) et lancer l'application sur un émulateur ou avec un appareil Android externe.\
Un appareil Android est fortement recommandé afin d'avoir un fonctionnement optimal.

---

## Prérequis

Prérequis pour utiliser l'application : 

- Avoir un accès internet
- Activer la localisation
- Accepter la demande accès à la localisation
- Clé API Google à mettre dans le projet

## Liste des stations

Après avoir lancé l'application, on arrive sur la Homepage. Une flèche directionnelle qui utilise le `capteur gyroscopique` est présente, elle pointe vers le titre de l'application, ainsi qu'un bouton redirigeant vers la `liste des gares` à proximité de l'appareil.

L'écran de liste des gares est composé d'une liste d'items avec :
- Le nom de la gare, le libellé court et le code INSEE sur `la partie gauche` de chaque items.
- Un `bouton` permettant d'afficher la gare sur une GoogleMaps avec `la partie droite` de l'item.

## Détails par gare
En cliquant sur la zone de gauche d'un items de la liste de gare, une redirection est faite vers une page donnant plus de détails sur la gare sélectionnée.

Cette page est divisée en 3 catégories : 
- Informations Générales
- Spécificités
- Horaires

---

### Informations générales
Sur cette partie on récapitules les informations précédement évoquées sur la liste de gares (nom, libellé...).\
On précise aussi si la gare sélectionnée est équipée ou non d'un `réseau wifi`.

---

### Spécificités
Pour les informations plus spécifiques, on donne la `répartition Homme/Femme` de l'effectif de chaque gare ainsi que la liste des `dispositifs d'assistance au Personne à Mobilité Réduites (PMR)` filtrées par années avec le nombre de fauteuils et de rampes.

---

### Horaires

Enfin, on affiche les horaires par jours de la semaines (également sous forme de liste), `filtrée par jours` avec les horaires classiques et les horaires de jours fériés.

---

## Carte
En sélectionnant le bouton localisation sur une des gares, une nouvelle page va apparaître.\
Cette fenêtre contient une `Map Google` et permet d'afficher la localisation de la gare sélectionnée mais aussi votre position GPS en temps réél.\
Par défaut, cette carte sera `centrée et zoomée` sur la gare, mais vous pouvez aussi vous déplacer la carte.\
Si vous sélectionner le marqueur représentant la gare, vous pourrez voir apparaître son nom, mais aussi 2 icônes en bas à droite de l'application.\
Ces icônes permettent d'accéder à l'emplacement de la gare sur `Google Maps`, ou même de tracer directement un `itinéraire` entre vous et la gare sélectionnée.

--- 

### Clé API Google
Une clé pour l'API de Google est nécessaire afin de faire fonctionner la carte. Afin d'éviter tout problème, cette clé ne sera pas partagée sur ce repository public.\
Cette clé est à ajouter dans le fichier `local.properties` de cette façon:
```sh
 MAPS_API_KEY=Identifiant_de_la_cle
 ```
