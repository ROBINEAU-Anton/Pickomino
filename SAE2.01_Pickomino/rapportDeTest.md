**Point de vigilance :**

_Contrôlabilité_ : Lors du développement d'une application pour jouer au Pickomino, il est essentiel de prendre en compte la contrôlabilité pour garantir une testabilité adéquate. Cela signifie que l'application doit permettre de contrôler et de manipuler facilement les différentes fonctionnalités et scénarios du jeu lors des tests. Il est important de concevoir des interfaces claires et des mécanismes de contrôle appropriés pour simuler différentes actions des joueurs, telles que le lancer des dés, la sélection des pickominos, etc. Ainsi, il sera possible d'exécuter les tests de manière précise et de reproduire les conditions de jeu spécifiques.

_Conservabilité_ : La conservabilité fait référence à la capacité de maintenir et de conserver les tests de manière efficace tout au long du projet. Il est crucial d'établir des tests solides et de documenter les cas de test de manière claire et détaillée. Cela facilitera la reproduction des tests et permettra de suivre l'évolution de l'application au fil du temps. Tout cela à pour but de maintenir l’infrastructure des tests et du code.

_Intégrité_ : L'intégrité se rapporte à la fiabilité et à la cohérence des tests. Lors du développement de l'application Pickomino, il est important de s'assurer que les tests sont fiables et qu'ils vérifient correctement les fonctionnalités de l'application. Cela implique d'avoir des assertions et des vérifications précises pour s'assurer que le comportement de l'application est conforme aux règles du jeu et aux attentes des utilisateurs. Les tests doivent être conçus de manière à être indépendants et reproductibles, de sorte que les résultats obtenus lors des tests soient cohérents et prévisibles. Veiller à l'intégrité des tests permet d'avoir confiance dans les résultats obtenus et d'identifier rapidement les problèmes ou les erreurs éventuelles.

_Extensibilité_ : L'extensibilité concerne la capacité de l'application à évoluer et à intégrer de nouvelles fonctionnalités ou des modifications ultérieures. Lors des tests, il est essentiel de prendre en compte les évolutions potentielles du jeu Pickomino. Assurez-vous que les tests sont conçus de manière à être facilement adaptables en cas de modifications de règles ou d'autres fonctionnalités spécifiques. Cela permettra de garantir que les tests existants restent valides et fonctionnent correctement même après des modifications de l'application.

_Automatisation des tests_ : L'automatisation des tests est un aspect clé pour améliorer l'efficacité et la répétabilité des tests. En développant une application pour jouer au Pickomino, il est recommandé de mettre en place des tests automatisés pour exécuter les scénarios de test de manière systématique. Cela peut inclure des tests unitaires pour valider le comportement des composants individuels, des tests d'intégration pour vérifier l'interaction entre les différentes parties de l'application, et des tests fonctionnels pour évaluer le jeu dans son ensemble. L'automatisation des tests permet d'effectuer des vérifications rapides et régulières, d'accélérer le processus de test et de réduire les risques d'erreurs humaines.

_Traçabilité des tests_ : La traçabilité des tests consiste à établir une relation claire entre les tests et les exigences du projet. Il est important de savoir quelles exigences fonctionnelles ou non fonctionnelles sont couvertes par chaque test. Cela permet de s'assurer que toutes les fonctionnalités et les scénarios critiques du jeu Pickomino sont testés. Une bonne traçabilité facilite également la vérification de la couverture des tests et permet de s'assurer que tous les aspects importants de l'application sont testés de manière adéquate.

**Analyse partitionnelle fun Pickomino :**

_Value :_
     [-2^31; 20],
     [21; 36],
     [37; 2^31 -1]

_nbWorms :_
     [-2^31; 0],
     [1; 4],
     [5; 2^31 -1]

| Entrées                | Value             | [-2^31; 20]   | X |   | I | I |   |
| :---------------------- | ----------------- | ------------- | - | - | - | - | - |
|                         |                   | [21; 36]      |   |   | I | I | X |
|                         |                   | [37; 2^31 -1] |   | X | I | I |   |
|                         | nbWorms           | [-2^31; 0]    | I | I | X |   |   |
|                         |                   | [1; 4]        | I | I |   |   | X |
|                         |                   | [5; 2^31 -1]  | I | I |   | X |   |
| Sorties<br /> attendues | Entrées Valide   |               |   |   |   |   | X |
|                         | Entrées Invalide |               | X | X | X | X |   |

CT1(DT1(-7, 3), PickominoException)

CT2(DT2(1500, 2), PickominoException)

CT3(DT3(25, -6), PickominoException)

CT4(DT4(29, 70), PickominoException)

CT5(DT5(31, 3), Entrées Valide)

Cependant ces tests ne sont pas réalisables étant donné qu’aucune exception n’est renvoyée avec cette méthode.

**Analyse partitionnelle fun choicesDices :**

_id :_
     [-2^31; -1],
     [0; 2^31-1]

_key :_
     [-2^31; -1],
     [0; 2^31-1]

_dices :_
    La valeur de dice ne changera pas la sortie de la méthode

_Status :_
     Status_Valid = [ROLL_DICE, ROLL_DICE_OR_TAKE_PICKOMINO],
     Status_Invalid = [GAME_FINISHED, TAKE_PICKOMINO, KEEP_DICE]

| Entrées                | id                | [-2^31; -1]                  | X | I | I |   |
| :---------------------- | :---------------- | :--------------------------- | - | - | - | - |
|                         |                   | [0; 2^31 -1]                 |   | I | I | X |
|                         | key               | [-2^31; -1]                  | I | X | I |   |
|                         |                   | [0; 2^31 -1]                 | I |   | I | X |
|                         | Status            | Status_Valid                 | I | I |   | X |
|                         |                   | Status_Invalid               | I | I | X |   |
| Sorties<br /> attendues | Entrées Valide   | Retour Valide                |   |   |   | X |
|                         | Entrées Invalide | Unknown Id<br />Exception    | X |   |   |   |
|                         |                   | Incorrect Key<br />Exception |   | X |   |   |
|                         |                   | Bad Step<br />Exception      |   |   | X |   |

id = id correct

key = key correct

CT1(DT1(id-1, key, listOf(DICE.d5, …), UnknownIdException)

CT2(DT2(id, key-1, listOf(DICE.d5, …), IncorrectKeyException)

CT3(DT3(id, key, listOf(Dice.d5, ..)), BadStepException) Status = GAME_FINISHED

CT4(DT4(id, key, listOf(Dice.d5, ..)),Entrées Valide) Status = ROLL_DICES

**Analyse partitionnelle fun finalScores :**

_id :_
     [-2^31; -1],
     [0; 2^31-1]

_key :_
     [-2^31; -1],
     [0; 2^31-1]

_Status :_
     Status_Valid = {GAME_FINISHED},
     Status_Invalid = [TAKE_PICKOMINO, KEEP_DICE, ROLL_DICE, ROLL_DICE_OR_TAKE_PICKOMINO]

| Entrées                | id                | [-2^31; -1]                  | X | I | I |   |
| :---------------------- | :---------------- | :--------------------------- | - | - | - | - |
|                         |                   | [0; 2^31 -1]                 |   | I | I | X |
|                         | key               | [-2^31; -1]                  | I | X | I |   |
|                         |                   | [0; 2^31 -1]                 | I |   | I | X |
|                         | Status            | Status_Valid                 | I | I |   | X |
|                         |                   | Status_Invalid               | I | I | X |   |
| Sorties<br /> attendues | Entrées Valide   | Retour Valide                |   |   |   | X |
|                         | Entrées Invalide | Unknown Id<br />Exception    | X |   |   |   |
|                         |                   | Incorrect Key<br />Exception |   | X |   |   |
|                         |                   | Bad Step<br />Exception      |   |   | X |   |

id = id correct

key = key correct

CT1(DT1(id-1, key), UnknownIdException)

CT2(DT2(id, key-1), IncorrectKeyException)

CT3(DT3(id, key), BadStepException) Status = ROLL_DICES

CT4(DT4(id, key),Entrées Valide) Status = GAME_FINISHED

**Analyse partitionnelle fun gameState :**

_id :_
     [-2^31; -1],
     [0; 2^31-1]

_key :_
     [-2^31; -1],
     [0; 2^31-1]

| Entrées               | id                | [-2^31; -1]                  | X | I |   |
| :--------------------- | :---------------- | :--------------------------- | - | - | - |
|                        |                   | [0; 2^31 -1]                 |   | I | X |
|                        | key               | [-2^31; -1]                  | I | X |   |
|                        |                   | [0; 2^31 -1]                 | I |   | X |
| Sorties<br />Attendues | Entrées Valide   | game renvoyé                |   |   | X |
|                        | Entrées Invalide | Unknown Id<br />Exception    | X |   |   |
|                        |                   | Incorrect Key<br />Exception |   | X |   |

id = id correct

key = key correct

CT1(DT1(id-1, key), UnknownIdException)

CT2(DT2(id, key-1), IncorrectKeyException)

CT4(DT4(id, key),Entrées Valide)

**Analyse partitionnelle fun keepDices :**

_id :_
     [-2^31; -1],
     [0; 2^31-1]

_key :_
     [-2^31; -1],
     [0; 2^31-1]

_Status :_
     Status_Valid = [KEEP_DICE],
     Status_Invalid [GAME_FINISHED, TAKE_PICKOMINO, ROLL_DICE, ROLL_DICE_OR_TAKE_PICKOMINO]

_Rolls :_
     Dices_Valid = Dices in Rolls
     Dices_Invalid = Dices not in Rolls

_Keeps :_
     Dices_Invalid = Dices in Keep
     Dices_Valid = Dices not in Keep

| Entrées               | id                     | [-2^31; -1]                      | X | I | I | I | I |   |
| :--------------------- | :--------------------- | :------------------------------- | - | - | - | - | - | - |
|                        |                        | [0; 2^31 -1]                     |   | I | I | I | I | X |
|                        | key                    | [-2^31; -1]                      | I | X | I | I | I |   |
|                        |                        | [0; 2^31 -1]                     | I |   | I | I | I | X |
|                        | Status                 | Status_Valid                     | I | I |   | I | I | X |
|                        |                        | Status_Invalid                   | I | I | X | I | I |   |
|                        | Rolls                  | Dices_Valid                      | I | I | I |   | I | X |
|                        |                        | Dices_Invalid                    | I | I | I | X | I |   |
|                        | Keeps                  | Dices_Valid                      | I | I | I | I | X |   |
|                        |                        | Dices_Invalid                    | I | I | I | I |   | X |
| Sorties<br />Attendues | Entrées<br />Valide   | True                             |   |   |   |   |   | X |
|                        | Entrées<br />Invalide | Unknown Id<br />Exception        | X |   |   |   |   |   |
|                        |                        | Incorrect Key<br />Exception     |   | X |   |   |   |   |
|                        |                        | Bad Step<br />Exception          |   |   | X |   |   |   |
|                        |                        | Dice Not In<br />Roll Exception  |   |   |   | X |   |   |
|                        |                        | Dice Already<br />Kept Exception |   |   |   |   | X |   |

id = id correct

key = key correct

CT1(DT1(id-1, key, DICE.d5), UnknownIdException)

CT2(DT2(id, key-1,DICE.d5), IncorrectKeyException)

CT3(DT3(id, key, DICE.d5), BadStepException) Status = ROLL_DICES

CT4(DT4(id, key, DICE.d5), DiceNotInRollException) Status = KEEP_DICES, Not in Roll

CT5(DT5(id, key, DICE.d5), DiceAlreadyKeptException) Status = KEEP_DICES, In Roll, In Keep

CT6(DT6(id, key, DICE.d5), True) Status = KEEP_DICES, In Roll,Not In Keep

**Analyse partitionnelle fun gameState :**

_nbPlayers :_
     [-2^31; 0],
     [0; 4],
     [5; 2^31 -1]

| Entrées               | nbPlayers            | [-2^31; 0]   | X |   |   |
| :--------------------- | :------------------- | :----------- | - | - | - |
|                        |                      | [0; 4]       |   | X |   |
|                        |                      | [5; 2^31 -1] |   |   | X |
| Sorties<br />Attendues | Entrées<br />Valide | (-1,-1)      | X |   | X |
|                        |                      | autre pairs  |   | X |   |

CT1(DT1(-6),(-1,-1))

CT2(DT2(3),(id, key))

CT3(DT3(45), (-1,-1))

**Analyse partitionnelle fun rollDices :**

_id :_
     [-2^31; -1],
     [0; 2^31-1]

_key :_
     [-2^31; -1],
     [0; 2^31-1]

_Status :_
     Status_Valid = [ROLL_DICE, ROLL_DICE_OR_TAKE_PICKOMINO]
     Status_Invalid [GAME_FINISHED, TAKE_PICKOMINO, KEEP_DICE]

| Entrées               | id                     | [-2^31; -1]                  | X | I | I |   |
| :--------------------- | :--------------------- | :--------------------------- | - | - | - | - |
|                        |                        | [0; 2^31 -1]                 |   | I | I | X |
|                        | key                    | [-2^31; -1]                  | I | X | I |   |
|                        |                        | [0; 2^31 -1]                 | I |   | I | X |
|                        | Status                 | Status_Valid                 | I | I |   | X |
|                        |                        | Status_Invalid               | I | I | X |   |
| Sorties<br />Attendues | Entrées<br />Valide   | retour liste                 |   |   |   | X |
|                        | Entrées<br />Invalide | Unknown Id<br />Exception    | X |   |   |   |
|                        |                        | Incorrect Key<br />Exception |   | X |   |   |
|                        |                        | Bad Step<br />Exception      |   |   | X |   |

id = id correct

key = key correct

CT1(DT1(id-1, key), UnknownIdException)

CT2(DT2(id, key-1), IncorrectKeyException)

CT3(DT3(id, key), BadStepException) Status = GAME_FINISHED

CT4(DT4(id, key), listOf(DICE.d5, …)) Status = ROLL_DICES

**Analyse partitionnelle fun takePickomino :**

_id :_
     [-2^31; -1],
     [0; 2^31-1]

_key :_
     [-2^31; -1],
     [0; 2^31-1]

_Status :_
     Status_Valid = [ROLL_DICE_OR_TAKE_PICKOMINO, TAKE_PICKOMINO],
     Status_Invalid [GAME_FINISHED, KEEP_DICE, ROLL_DICE]

_Pickomino :_
     [-2^31; 20],
     [21; 36],
     [37; 2^31 -1]

_TakePickomino :_
     Pickomino prenable
     Pickomino non prenable

| Entrées               | id                     | **[-2^31; -1]**                          | X | I | I |   |   |   |   |
| :--------------------- | :--------------------- | ---------------------------------------------- | - | - | - | - | - | - | - |
|                        |                        | **[0; 2^31 -1]**                         |   | I | I | X | X | X | X |
|                        | key                    | **[-2^31; -1]**                          | I | X | I |   |   |   |   |
|                        |                        | **[0; 2^31 -1]**                         | I |   | I | X | X | X | X |
|                        | Status                 | **Status Valid**                         | I | I |   | X | X | X | X |
|                        |                        | **Status Invalid**                       | I | I | X |   |   |   |   |
|                        | Pickomino              | **[-2^31; 20]**                          | I | I | I |   | X |   |   |
|                        |                        | **[21; 36]**                             | I | I | I | X |   |   | X |
|                        |                        | **[37; 2^31-1]**                         | I | I | I |   |   | X |   |
|                        | Take<br />Pickomino    | **Pickomino<br /> prenable**             | I | I | I |   |   |   | X |
|                        |                        | **Pickomino non<br /> prenable**         | I | I | I | X | X | X |   |
| Sorties<br />Attendues | Entrées<br />Valide   | True                                           |   |   |   |   |   |   | X |
|                        |                        | False                                          |   |   |   |   | X | X |   |
|                        | Entrées<br />Invalide | **Unknown Id <br />Exception**           | X |   |   |   |   |   |   |
|                        |                        | **Incorrect Key <br />Exception**        |   | X |   |   |   |   |   |
|                        |                        | **Bad Step <br />Exception**             |   |   | X |   |   |   |   |
|                        |                        | **Bad Pickomino <br />Chosen Exception** |   |   |   | X |   |   |   |

id = id correct

key = key correct

CT1(DT1(id-1, key, 25), UnknownIdException)

CT2(DT2(id, key-1,25), IncorrectKeyException)

CT3(DT3(id, key,25), BadStepException) Status = ROLL_DICES

CT4(DT4(id, key, 25), DiceNotInRollException) Status =TAKE_PICKOMINO Pickomino non prenable

CT5(DT5(id, key-1,-5), false) Status =TAKE_PICKOMINO

CT6(DT3(id, key,65), false) Status = TAKE_PICKOMINO

CT7(DT4(id, key, 26), true) Status =TAKE_PICKOMINO Pickomino prenable
