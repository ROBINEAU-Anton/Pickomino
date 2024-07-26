# SAE 2.01 2023 > PICKOMINO

Le projet qui vous est proposé pour la SAE "2.01 : développement d'une application" consiste à développer une application client/serveur permettant de jouer à Pickomino.

Cette SAE est commune aux ressources "dev.objets", "IHM" et "Qualité/tests". L'objectif est de vous permettre de mettre en pratique les compétences acquises dans ces 3 ressources (et pas que).


## Contexte : le jeu Pickomino

Il s'agit d'un "petit" jeu de dés ; voir [wikipedia](https://fr.wikipedia.org/wiki/Pickomino) ; une version des règles du jeu est disponible [ici](pickomino-rules-fr-2011-updated.pdf)


Quelques autres ressources :

- [https://www.gigamic.com/jeu/pickomino](https://www.gigamic.com/jeu/pickomino)

- [https://www.youtube.com/watch?v=iKspB0A8JSg&ab_channel=DMDJeux](https://www.youtube.com/watch?v=iKspB0A8JSg&ab_channel=DMDJeux)

- [https://www.youtube.com/watch?v=SOtLakk6kQk&ab_channel=cleonis](https://www.youtube.com/watch?v=SOtLakk6kQk&ab_channel=cleonis)

- [https://videoregles.net/videoregle/pickomino](https://videoregles.net/videoregle/pickomino)


## Organisation du projet 

Vous aurez 2 périodes de ~4 jours sur les semaines 24 et 25 pour réaliser l'ensemble du travail demandé, en équipe de 3 à 4 développeu-r-se-s.
(on pourrait appeler ces périodes des "sprints", mais nous ne verrons cela que l'an prochain dans la ressource R3.10 avec l'agilité)

Vous avez des journées complètes de travail OBLIGATOIREMENT à l'IUT, comme indiqué dans votre [emploi du temps](https://edt.univ-nantes.fr/iut_nantes/g3145.html); des enseignants seront présents sur certains créneaux pour vous guider/répondre à d'éventuelles questions / vous assister, mais également pour évaluer votre manière de travailler en équipe.

**Le jeudi 22 (S25) après-midi sera consacré à la recette du projet.**


### Les équipes

Le document suivant vous indique les équipes-projets ; nosu avons tenu compte de vos voeux, quand ceux-ci respectaient les contraintes indiquées ; nous avons également affecté d'office, les étudiants non-inscrits :
[Equipes-projet](https://docs.google.com/spreadsheets/d/1DIbZ9-a_xc42tvuE0v-6TrqjPLoNh-VqDs5AmnsAaJ8/edit?usp=sharing)



### Questions / échange avec l'équipe enseignante

En plus des temps de présence des enseignants durant certaines séances, pour échanger avec nous, vous n'utiliserez que les [issues](https://gitlab.univ-nantes.fr/iut.info1.dev.objets/sae201.2023/pickomino-sujet/-/issues) de ce dépôt pour nous remonter des questions, des bugs éventuels dans la librairie et/ou le serveur de jeu, etc. (pour poster une issue, il faut être identifié sur gitlab ;-) 

L'intéret est d'éviter d'avoir *n* fois la même question/remarque et de vous faire utiliser un outil de "ticketing" ; étudiez les issues déjà postées avant d'en poster une nouvelle.


### Utilisation d'un dépot git

Vous devrez tous utiliser convenablement un dépôt git.

Vous utiliserez OBLIGATOIREMENT le serveur gitlab de l'université;

1. l'un des membres de l'équipe-projet demandera l'accès au sous-groupe [sae201.2023/project.etu/](https://univ-nantes.io/iut.info1.dev.objets/sae201.2023/project.etu) en cliquant sur "Request Access" (il faut être identifié ;-) )
2. vous réaliserez un "fork" du projet git [basic-project](https://gitlab.univ-nantes.fr/iut.info1.dev.objets/sae201.2023/basic-project) dans le sous-groupe précédent, en veillant à ce que votre projet git respecte la convention de nommage suivant :` <N°Projet>.SAE201.2023` 

**ATTENTION : pas de clone, mais bien des forks**

3. vous ajouterez ensuite à votre projet git tous les membres de l'équipe-projet
4. vous utiliserez ensuite votre dépot pour travailler à plusieurs et partager 
votre code ; vos rendus se feront également via votre dépôt git.

**Un "bon" usage de git implique forcément des commits/pushs réguliers plusieurs fois par 1/2 journée** ; en plus c'est facile pour nous d'observer cela.


## Travail à faire

### Contexte

Un serveur d'API REST permettant de jouer à Pickomino a été développé par nos soins. Il est normalement lancé sur une machine virtuelle du IAAS de l'université.

	adresse du serveur : 172.26.82.76
	port : 8080

**NB : le serveur n'est accessible que depuis les postes de l'IUT ou en utilisant le VPN de l'université (documentation [ici](https://wiki.univ-nantes.fr/doku.php?id=nomade:client_lourd))**


Une librairie `pickomino-lib.jar` a également été développée afin de vous permettre d'interagir "facilement" avec le serveur de jeu ; elle est déjà incluse dans le projet *basic project* que vous avez forké. La documentation de la librairie est accessible ici : [documentation pickomino-lib.jar](http://172.26.82.76/dokka/html/index.html) 

**NB : il faut que vous ajoutiez une exception dans les réglages du proxy de votre navigateur**

	Pas de proxy pour 172.26.82.76
	
Il est bien entendu OBLIGATOIRE que votre jeu Pickomino utilise le serveur, via la librairie fournie.	
	

### De votre côté

Le travail qui vous est demandé consiste à développer une application Kotlin/JavaFX utilisant la librairie `pickomino-lib.jar` pour communiquer avec le serveur de jeu, et permettre de jouer à Pickomino, plus précisément, vous devrez entre autre :

1. Etablir un diagramme de classes UML représentant le jeu Pickomino
2. Donner des cas tests permettant de valider le fonctionnement de la librairie `pickomino-lib.jar` (et de manière sous-jacente, du serveur de jeu)
3. Développer une interface graphique permettant de jouer à Pickomino

**NB : j'ai + de 200 cas de tests pour "valider" le bon fonctionnement de `pickomino-lib.jar`**


#### Travail à réaliser en Dev.Objets

##### 1ère semaine
1. Vous établirez un diagramme de classes UML (niveau conception) pour un ensemble de parties du jeu Pickomino ;vous apporterez une attention particulière aux respects des notations et des concepts UML.

2. Vous établirez un 2nd diagramme de classes UML (niveau implémentation) qui sera un raffinement du diagramme précédent précisant vos choix "techniques" d'implémentation.

Les modèles UML seront pushés dans votre dépôt git sous la forme d'un UNIQUE document `modeleUML.pdf` contenant le diagrame UML et d'éventuelles explications.

##### 2ème semaine 
Vous implémenterez en Kotlin votre 2nd diagramme de classes, en veillant à bien respecter toutes les règles de traduction.

#### Travail à réaliser en IHM

Vous développerez bien sûr une application respectant l'architecture MVC.

##### 1ère semaine
- vous devez réaliser une maquette des vues de votre application. Vous utiliserez l'outil *pencil* dont un descriptif vous est donné 
[ici](utilisationPencil.pdf). Vous apporterez une attention particulière aux interactions de l'utilisateur avec votre application (fenêtre de dialogue de confirmation d'action, d'avertissement de l'utilisateur avant une action, possibilité d'annuler une action, ... )

La maquette sera pushée dans votre dépôt git sous la forme d'un document `maquette.pdf`

- vous devez développer en *javaFX* les vues de votre application tout en vous appuyant sur votre maquette

##### 2ème semaine 
- vous devez développer les contrôleurs liés à votre application (liaison entre vue et modèle). 

#### Travail à réaliser en Qualité Logiciel - tester

Vous développerez une application de qualité, pour cela elle devra être testable et testée.

##### 1ère semaine 
- vous identifierez dans votre application les points de vigilance vis-à-vis de la testabilité. Vous rédigerez 2 pages en listant ces points.

##### 2ème semaine 
- vous concevrez des tests fonctionnelscpour la librairie `pickomino-lib.jar` et les implémenterez. Chaque étudiant de l'équipe s'occupe d'une partie des méthodes sous test et écrira 2 pages de conception de tests, et une ou deux classes de test.
- vous écrirez une synthèse de maximum 2 pages présentant les tests qui ont échoués et ont permis de détecter des bugs.

**Bonus :** si le serveur et/ou la lib fournis ne se comportent pas comme attendu, proposez des tests le démontrantn via des issues git

Le tout sera mis dans un rapport écrit au format markdown et pushé dans votre dépôt git : `rapportDeTest.md` (avec entête, sections)
