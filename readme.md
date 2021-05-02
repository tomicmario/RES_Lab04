# Teaching-HEIGVD-RES-2021-Labo-SMTP

## Description du projet

Le but de se projet est de mettre en place une application qui permet l'envoie de mails forgés. L'utilisateur peut configurer une liste de mails et d'adresses pour ce faire. Cette application a été développée dans le but de se familiariser avec le protocole SMTP.

## Comment utiliser cette application ?

Avant de lancer l'application, il est préférable de changer quelques fichiers de configuration.

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
** Le premier mail dans un groupe sera l'émetteur. 
** Un retour à la ligne est nécessaire pour que l'adresse soit prise en compte dans la liste.

* Le fichier config

Ce fichier contient l'adresse 
