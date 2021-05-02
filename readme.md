# Teaching-HEIGVD-RES-2021-Labo-SMTP

## Description du projet

Le but de se projet est de mettre en place une application qui permet l'envoie de mails forgés. L'utilisateur peut configurer une liste de mails et d'adresses pour ce faire. Cette application a été développée dans le but de se familiariser avec le protocole SMTP.

## Comment configurer cette application ?

Avant de lancer l'application, il est préférable de changer quelques fichiers de configuration.
* Le fichier config

Ce fichier en tout trois groupes d'attributs. Le premier est la taille de chaque groupe. Celle-ci est définie par :
```
groupeSizeX=Y
```
où X est le numéro du groupe (commence à 1) et Y, sa taille. Il est important de noter que la taille du groupe doit être de 3 au minimum, et que le premier membre du groupe sera celui qui enverra les mails. 

Viennent ensuite les attributs address (l'adresse du serveur, localhost par défaut) et le numéro du port SMTP (25 par défaut)

* Le fichier messages.txt

Ce fichier contient les mails qu'on veut envoyer. La structure d'un mail individuel est :
```
%MAIL_START%
Subject: [Objet]
[Corps]
%MAIL_END%
```
Les attributs Corps et Sujet pouvant être modifié comme bon nous semble. Il est juste important de noter que l'objet doit tenir sur une ligne.

* Le fichier victimes.txt

Le fichier contient toutes les adresses mails qui seront utilisées pour l'opération. Il suffit simplement d'entrer une adresse mail par ligne.
### Deux notes importantes :
1. Le premier mail dans un groupe sera l'émetteur. 
2. Un retour à la ligne est nécessaire pour que l'adresse soit prise en compte dans la liste.


## Comment utiliser l'application ?

Il suffit simplement de lancer le main de l'application, avec un serveur SMTP qui tourne en fond. Le serveur fourni pour ce faire vient du projet https://github.com/tweakers/MockMock. Une fois le serveur lancé, on peut y accéder en entrant l'adresse http://localhost::8282 dans le navigateur internet de notre choix. 

Ainsi, l'exécution du côté client, si tout se passe bien, produit le résultat suivant :
![](figures/executionClient.png)

On peut observer le résultat du côté serveur aussi :
![](figures/resultatServeur1.png)
![](figures/resultatServeur2.png)
