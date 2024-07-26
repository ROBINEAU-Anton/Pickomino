import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.data.STATUS
import iut.info1.pickomino.exceptions.*
import iut.info1.pickomino.data.Pickomino
import modele.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows



class TestGame {

    private var id = 0
    private var key = 0
    private var nb_players: Int = 2
    private val connect = Connector.factory("172.26.82.76", "8080", true)

    /*                             */
    /* Test du nombres de joueurs  */
    /*                             */

    @Test
    fun StartGameWith2Players() {
        val identification = connect.newGame(nb_players)
        this.id = identification.first
        this.key = identification.second
        assertTrue(connect.gameState(id, key).score().size == 2)
    }

    @Test
    fun StartGameWith3Players() {
        nb_players = 3
        val identification = connect.newGame(nb_players)
        this.id = identification.first
        this.key = identification.second
        assertTrue(connect.gameState(id, key).score().size == 3)
    }

    @Test
    fun StartGameWith4Players() {
        nb_players = 4
        val identification = connect.newGame(nb_players)
        this.id = identification.first
        this.key = identification.second
        assertTrue(connect.gameState(id, key).score().size == 4)
    }
    /*                                                    */
    /* Test des méthodes grâce à l'analyse partitionnelle */
    /*                                                    */

    /* méthode newGame() */

    /* Test qui vérifie que la méthode newGame() renvoie bien une paire différente de (-1, -1) si le nombre de joueur est valide */
    @Test
    fun testTrueNewGame() {
        val identification = connect.newGame(nb_players)
        this.id = identification.first
        this.key = identification.second
        assertTrue(this.key != -1 && this.id != -1)

    }

    /* Test qui vérifie que la méthode newGame() renvoie bien la paire (-1,-1) si le nombre de joueur est invalide */
    @Test
    fun testFalseNewGame() {
        val identification = connect.newGame(9)
        this.id = identification.first
        this.key = identification.second
        assertTrue(this.key == -1 && this.id == -1)

    }

    /* Initialisation de tous les tests suivant cette méthode */
    @BeforeEach
    fun setup() {
        val identification = connect.newGame(nb_players)
        this.id = identification.first
        this.key = identification.second
    }

    /* méthode gameState() */

    /* Test qui vérifie que la méthode gameState() renvoie bien une erreur si l'id est faux */
    @Test
    fun testIdErrorGameState() {
        assertThrows<UnknownIdException>{connect.gameState(-1, key)}
    }

    /* Test qui vérifie que la méthode gameState() renvoie bien une erreur si la clé est fausse */
    @Test
    fun testKeyErrorGameState() {
        assertThrows<IncorrectKeyException>{connect.gameState(id, key-1)}
    }

    /* Test qui vérifie que la méthode gameState() marche bien avec des entrées valides */
    @Test
    fun testValidGameState() {
        assertDoesNotThrow(){connect.gameState(id, key)}
    }

    /* méthode finalScore() */

    /* Test qui vérifie que la méthode finalScore() renvoie bien une erreur si l'id est faux */
    @Test
    fun testIdErrorFinalScore() {
        assertThrows<UnknownIdException>{connect.finalScore(-1, key)}
    }

    /* Test qui vérifie que la méthode finalScore() renvoie bien une erreur si la clé est fausse */
    @Test
    fun testKeyErrorFinalScore() {
        assertThrows<IncorrectKeyException>{connect.finalScore(id, key-1)}
    }

    /* Test qui vérifie que la méthode finalScore() ne peut pas être utilisé durant la patie */
    @Test
    fun testUseFinalScoreInGame() {
        assertThrows<BadStepException>{connect.finalScore(id, key)}
    }

    /* Test qui vérifie que la méthode finalScore() peut être utilisé à la fin de la patie */
    @Test
    fun testUseFinalScoreAtGameOver() {

        for (i in 0..13) {
            connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
            connect.keepDices(id, key, DICE.d1)
            connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
            connect.keepDices(id, key, DICE.d3)
            connect.choiceDices(id, key, listOf(DICE.d3))
        }

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d2, DICE.worm, DICE.worm, DICE.d5))
        connect.keepDices(id, key, DICE.d2)
        connect.takePickomino(id, key, 22)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.worm, DICE.worm, DICE.d5))
        connect.keepDices(id, key, DICE.d1)
        connect.takePickomino(id, key, 21)

        assertTrue(connect.gameState(id,key).current.status == STATUS.GAME_FINISHED)
        assertTrue(connect.finalScore(id, key) == listOf(1, 1))
    }

    /* méthode choiceDices() */

    /* Test qui vérifie que la méthode choiceDices() renvoie bien une erreur si l'id est faux */
    @Test
    fun testIdErrorChoiceDices() {
        assertThrows<UnknownIdException>{connect.choiceDices(-1, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1)) }
    }

    /* Test qui vérifie que la méthode choiceDices() renvoie bien une erreur si la clé est fausse */
    @Test
    fun testKeyErrorChoiceDices() {
        assertThrows<IncorrectKeyException>{connect.choiceDices(id, key-1, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))}
    }

    /* Test qui vérifie que la méthode choiceDices() ne peut pas être utilisé si le mode debug n'est pas activé et renvoie une liste aléatoire*/
    @Test
    fun testModeDebugFalseChoiceDices() {
        val connect1 = Connector.factory("172.26.82.76", "8080", false)
        val identification = connect1.newGame(nb_players)
        this.id = identification.first
        this.key = identification.second
        connect1.choiceDices(id, key, listOf(DICE.d5)) /* on mets une liste d'un seul élément pour pouvoir éviter le cas ou la fonction renverrait aléatoirement la même liste */
        println(connect1.gameState(id, key).current.rolls)
        assertTrue(connect1.gameState(id, key).current.rolls != listOf(DICE.d5))
    }


    /* Test qui vérifie que la méthode choiceDices() ne peut pas être utilisé si le status n'est pas : ROLL_DICE ou ROLL_DICE_OR_TAKE_PICKOMINO() */
    @Test
    fun testStatutFalseChoiceDices() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        assertThrows<BadStepException>{connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))}
    }

    /* Test qui vérifie que la méthode choiceDices() enregistre la liste des valeurs des dés dans la liste rolls */
    @Test
    fun testCurrentRollsChoiceDices() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        assertTrue(connect.gameState(id,key).current.rolls == listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
    }

    /* Test qui vérifie que la méthode choiceDices() ne peut contenir que le nombre de dés qui ne sont pas conservé */
    @Test
    fun testSameSizeChoiceDices() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key,DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        assertTrue(connect.gameState(id, key).current.rolls.size == 4)
    }

    /* Test qui vérifie que la méthode choiceDices() ne renvoie pas d'erreur si les paramètres sont valides */
    @Test
    fun testValidParametreChoiceDices() {
        assertDoesNotThrow(){connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))}
    }

    /* méthode keepDices() */

    /* Test qui vérifie que la méthode keepDices() renvoie bien une erreur si l'id est faux */
    @Test
    fun testIdErrorKeepDices() {
        assertThrows<UnknownIdException>{connect.keepDices(-1, key, DICE.d2) }
    }

    /* Test qui vérifie que la méthode keepDices() renvoie bien une erreur si la clé est fausse */
    @Test
    fun testKeyErrorKeepDices() {
        assertThrows<IncorrectKeyException>{connect.keepDices(id, key-1, DICE.d3)}
    }

    /* Test qui vérifie que la méthode keepDices() ne peut pas être utilisé si le status n'est pas : KEEP_DICE */
    @Test
    fun testStatutFalseKeepDices() {
        assertThrows<BadStepException>{connect.keepDices(id, key, DICE.d1)}
    }

    /* Test qui vérifie que la méthode keepDices() ne peut conservé un dés de valeur 'dice que si la valeur est tombé dans le lancer courant */
    @Test
    fun testNotDiceKeepDices() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        assertThrows<DiceNotInRollException>{connect.keepDices(id, key, DICE.d3)}
    }

    /* Test qui vérifie que la méthode keepDices() ne peut conservé une valeur qu'une seule fois */
    @Test
    fun testNotSameValueKeepDices() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key,listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5))
        assertThrows<DiceAlreadyKeptException>{connect.keepDices(id, key, DICE.worm)}
    }

    /* Test qui vérifie que la méthode keepDices() enregistre la liste des valeurs des dés dans la liste kept */
    @Test
    fun testCurrentKeptKeepDices() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        assertTrue(connect.gameState(id,key).current.keptDices == listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm))
    }

    /* Test qui vérifie que la méthode keepDices() ne renvoie pas d'erreur si les paramètres sont valides */
    @Test
    fun testValidParametreKeepDices() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        assertDoesNotThrow(){connect.keepDices(id, key, DICE.worm)}
    }

    /* méthode rollDices() */

    /* Test qui vérifie que la méthode rollDices() renvoie bien une erreur si l'id est faux */
    @Test
    fun testIdErrorRollDices() {
        assertThrows<UnknownIdException>{connect.rollDices(-1, key) }
    }

    /* Test qui vérifie que la méthode rollDices() renvoie bien une erreur si la clé est fausse */
    @Test
    fun testKeyErrorRollDices() {
        assertThrows<IncorrectKeyException>{connect.rollDices(id, key-1)}
    }

    /* Test qui vérifie que la méthode rollDices() ne peut pas être utilisé si le status n'est pas : ROLL_DICE ou ROLL_DICE_OR_TAKE_PICKOMINO() */
    @Test
    fun testStatutFalseRollDices() {
        connect.rollDices(id, key)
        assertThrows<BadStepException>{connect.rollDices(id, key)}
    }

    /* Test qui vérifie que la méthode rollDices() enregistre la liste des valeurs des dés dans la liste rolls */
    @Test
    fun testCurrentRollsRollDices() {
        connect.rollDices(id, key)
        assertTrue(connect.gameState(id,key).current.rolls.size == 8)
    }

    /* Test qui vérifie que la méthode rollDices() ne renvoie pas d'erreur si les paramètres sont valides */
    @Test
    fun testValidParametreRollDices() {
        assertDoesNotThrow(){connect.rollDices(id, key)}
    }

    /* méthode takePickomino() */

    /* Test qui vérifie que la méthode takePickomino() renvoie bien une erreur si l'id est faux */
    @Test
    fun testIdErrorTakePickomino() {
        assertThrows<UnknownIdException>{connect.takePickomino(-1, key, 25) }
    }

    /* Test qui vérifie que la méthode takePickomino() renvoie bien une erreur si la clé est fausse */
    @Test
    fun testKeyErrorTakePickomino() {
        assertThrows<IncorrectKeyException>{connect.takePickomino(id, key-1, 25)}
    }

    /* Test qui vérifie que la méthode takePickomino() ne peut pas être utilisé si le status n'est pas : TAKE_PICKOMINO() ou ROLL_DICE_OR_TAKE_PICKOMINO() */
    @Test
    fun testStatutFalseTakePickomino() {
        assertThrows<BadStepException>{connect.takePickomino(id, key, 25)}
    }

    /* Test qui vérifie que la méthode takePickomino() ne peut pas prendre un pickomino qui n'existe pas */
    @Test
    fun testBadPickomino1TakePickomino() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        val gameState = connect.gameState(id, key)
        connect.takePickomino(id, key, 55)
        assertTrue(connect.gameState(id, key).accessiblePickos() == gameState.accessiblePickos())
    }

    /* Test qui vérifie que la méthode takePickomino() ne peut pas prendre un pickomino qui n'a pas le score de ces dés */
    @Test
    fun testBadPickomino2TakePickomino() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        assertThrows<BadPickominoChosenException>{connect.takePickomino(id, key, 25)}
    }

    /* Test qui vérifie que la méthode takePickomino() stock le pickomino en haut de la pile et l'enlève des pickominos disposé au milieu */
    @Test
    fun testStockPickominoTakePickomino() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 30)
        assertTrue(connect.gameState(id, key).pickosStackTops()[0] == 30)
        assertTrue(connect.gameState(id,key).accessiblePickos() == listOf(21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 36))
    }

    /* Test qui vérifie que la méthode takePickomino() renvoie bien true quand on peut l'utiliser */
    @Test
    fun testReturnTrueTakePickomino() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        assertTrue(connect.takePickomino(id, key, 30))
    }

    /* Test qui vérifie que la méthode takePickomino() renvoie bien false quand la valeur du pickomino est incorrecte */
    @Test
    fun testReturnFalseTakePickomino() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        assertTrue(!connect.takePickomino(id, key, 55))
    }

    /* Test qui vérifie que la méthode takePickomino() ne renvoie pas d'erreur si les paramètres sont valides */
    @Test
    fun testValidParametreTakePickomino() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        assertDoesNotThrow(){connect.takePickomino(id, key, 30)}
    }

    /*                 */
    /* Test des règles */
    /*                 */

    /* Test qui vérifie qu'un pickomino est bien récupérer par un joueur*/
    @Test
    fun testTakePickomino() {
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 25)
        assertTrue(connect.gameState(id, key).pickosStackTops()[0] == 25)
    }

    /* Test qui vérifie en cas de tour de jeu raté d'un joueur qui n'a pas de pickomino que le dernier pickomino est bien enlevé*/
    @Test
    fun testLooseRound1() {
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.choiceDices(id, key, listOf(DICE.d3))
        assertTrue(connect.gameState(id, key).accessiblePickos() == listOf(21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35))
    }

    /* Test qui vérifie en cas de tour de jeu raté d'un joueur qui a un pickomino que son dernier pickomino est bien enlevé et replacé avec les autres pickominos*/
    @Test
    fun testLooseRoundWithPickominos() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 25)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.choiceDices(id, key, listOf(DICE.d3))

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.choiceDices(id, key, listOf(DICE.d3))

        assertTrue(connect.gameState(id, key).pickosStackTops()[0] == 0)
        assertTrue(connect.gameState(id, key).accessiblePickos() == listOf(21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34))
    }

    /* Test qui vérifie en cas de tour de jeu raté d'un joueur qui a des pickominos que son dernier pickomino est bien enlevé et replacé
    avec les autres pickominos et que le haut de sa pile soit le pickomino d'avant*/
    @Test
    fun testLooseRoundWithPickomino() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 25)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.choiceDices(id, key, listOf(DICE.d3))

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.d5, DICE.d1, DICE.d4, DICE.d5, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d5)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4, DICE.worm))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d1, DICE.d1))
        connect.keepDices(id, key, DICE.d3)
        connect.takePickomino(id, key, 28)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.choiceDices(id, key, listOf(DICE.d3))

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.choiceDices(id, key, listOf(DICE.d3))

        assertTrue(connect.gameState(id, key).pickosStackTops()[0] == 25)
        assertTrue(connect.gameState(id, key).accessiblePickos() == listOf(21, 22, 23, 24, 26, 27, 28, 29, 30, 31, 32, 33))
    }

    /* Test qui vérifie qu'un joueur peut piquer un pickomino à un autre joueur */
    @Test
    fun testStealPickomino() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 25)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 25)

        assertTrue(connect.gameState(id, key).pickosStackTops()[0] == 0)
        assertTrue(connect.gameState(id, key).pickosStackTops()[1] == 25)
        assertTrue(connect.gameState(id, key).accessiblePickos() == listOf(21, 22, 23, 24, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36))
        }

    /* Test qui vérifie qu'un joueur peut piquer un pickomino à un autre joueur et que son ancien pickomino revienne en haut de la pile*/
    @Test
    fun testStealPickominos() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 25)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d4, DICE.d3, DICE.d3))
        connect.keepDices(id,key, DICE.d3)
        connect.takePickomino(id, key, 31)

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 30)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.d5, DICE.worm, DICE.d5, DICE.d5, DICE.d5, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.d5)
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.d5, DICE.worm))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 30)

        assertTrue(connect.gameState(id, key).pickosStackTops()[0] == 25)
        assertTrue(connect.gameState(id, key).pickosStackTops()[1] == 30)
        assertTrue(connect.gameState(id, key).accessiblePickos() == listOf(21, 22, 23, 24, 26, 27, 28, 29, 32, 33, 34, 35, 36))
    }

    /* Test qui vérifie qu'un joueur ne peut pas piquer un pickomino à un autre joueur si il n'est pas en haut de la pile*/
    @Test
    fun testStealPickominosNotPossible() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 25)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d4, DICE.d3, DICE.d3))
        connect.keepDices(id,key, DICE.d3)
        connect.takePickomino(id, key, 31)

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.takePickomino(id, key, 30)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.d1, DICE.worm, DICE.d5, DICE.d5, DICE.d5, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.d5)
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.d5, DICE.worm, DICE.d2))
        connect.keepDices(id, key, DICE.worm)
        assertThrows<BadPickominoChosenException>{connect.takePickomino(id, key, 25)}
    }

    /* Test qui vérifie si le joueur rate un tour et qu'il repose le pickomino le plus élevé alors aucun pickomino ne doit être retourné*/
    @Test
    fun testLoosePoundWithGreaterPickomino() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1))
        connect.keepDices(id, key, DICE.d1)
        connect.takePickomino(id, key, 36)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.choiceDices(id, key, listOf(DICE.d3))

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.choiceDices(id, key, listOf(DICE.d3))

        assertTrue(connect.gameState(id, key).accessiblePickos() == listOf(21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 36))
    }

    /* Test qui vérifie si le pickomino de la somme que le joueur est déjà pris alors il prend le pickomino le plus grand dans les
    pickominos plus petit que la somme du joueur*/
    @Test
    fun TestTakePickominoSmaller() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1))
        connect.keepDices(id, key, DICE.d1)
        connect.takePickomino(id, key, 36)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1))
        connect.keepDices(id, key, DICE.d1)
        connect.takePickomino(id, key, 35)

        assertTrue(connect.gameState(id, key).pickosStackTops()[1] == 35)
    }

    /* Test qui vérifie si le pickomino de la somme que le joueur est déjà pris et qu'il n'y a plus de pickominos plus petit alors il ne prend rien*/
    @Test
    fun TestNotHavePickominoSmaller() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d2, DICE.d5, DICE.d1, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d2, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d3)
        connect.takePickomino(id, key, 23)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d2, DICE.d5, DICE.d1, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d2, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d2)
        connect.takePickomino(id, key, 22)

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d2, DICE.d5, DICE.d1, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d2, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d1)
        connect.takePickomino(id, key, 21)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d2, DICE.d5, DICE.d1, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d4))
        connect.keepDices(id, key, DICE.d1)
        connect.choiceDices(id, key, listOf(DICE.d1))

        assertTrue(connect.gameState(id, key).pickosStackTops()[1] == 0)
    }

    /* Test qui vérifie il a pas de vers sur les dés, alors il ne peut pas prendre de pickomino*/
    @Test
    fun TestNotHaveWorm() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.d5, DICE.d5, DICE.d5, DICE.d5, DICE.d5, DICE.d3, DICE.d3))
        connect.keepDices(id, key, DICE.d5)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3))
        connect.keepDices(id, key, DICE.d3)
        assertThrows<BadStepException>{ connect.takePickomino(id, key, 36)}
    }

    /* Test qui vérifie que si le joueur à un score équivalent à son pickomino en haut de la pile et qu'il n'y a aucun pickomino
    accesible avec un score inférieur alors le tour est perdu*/
    @Test
    fun TestPickonimoInaccesible() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d2, DICE.d5, DICE.d1, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d2, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d1)
        connect.takePickomino(id, key, 21)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d2, DICE.d5, DICE.d1, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d2, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d2)
        connect.takePickomino(id, key, 22)

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d2, DICE.d5, DICE.d1, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.d2, DICE.d3, DICE.d4))
        connect.keepDices(id, key, DICE.d1)
        assertThrows<BadStepException>{connect.takePickomino(id, key, 21)}
    }

    /*                          */
    /* Test des Etat de parties */
    /*                          */

    /* Vérifie que l'état de partie GAME_FINISHED() est actif lorsque la partie est terminé */
    @Test
    fun TestStateGameFineshed() {

        for (i in 0..13) {
            connect.choiceDices(id, key, listOf(DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d1, DICE.d2, DICE.d5, DICE.d2))
            connect.keepDices(id, key, DICE.d1)
            connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3, DICE.d4))
            connect.keepDices(id, key, DICE.d3)
            connect.choiceDices(id, key, listOf(DICE.d3))
        }

        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d2, DICE.worm, DICE.worm, DICE.d5))
        connect.keepDices(id, key, DICE.d2)
        connect.takePickomino(id, key, 22)

        /* Joueur 2 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d1, DICE.worm, DICE.worm, DICE.d5))
        connect.keepDices(id, key, DICE.d1)
        connect.takePickomino(id, key, 21)

        assertTrue(connect.gameState(id,key).current.status == STATUS.GAME_FINISHED)
    }

    /* Vérifie que l'état de partie TAKE_PICKOMINO() est actif lorsque l'on peut prendre un pickomino*/
    @Test
    fun TestStateTakePickomino() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        connect.choiceDices(id, key, listOf(DICE.d2, DICE.d2, DICE.worm, DICE.d5))
        connect.keepDices(id, key, DICE.d2)
        connect.choiceDices(id, key, listOf(DICE.d3, DICE.d3))
        connect.keepDices(id, key, DICE.d3)
        assertTrue(connect.gameState(id,key).current.status == STATUS.TAKE_PICKOMINO)
    }

    /* Vérifie que l'état de partie KEEP_DICE() est actif lorsque l'on peut prendre des dés*/
    @Test
    fun TestStateKeepDice() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.d5, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        assertTrue(connect.gameState(id,key).current.status == STATUS.KEEP_DICE)
    }

    /* Vérifie que l'état de partie ROLL_DICE_OR_TAKE_PICKOMINO() est actif lorsque l'on peut lancer des dés ou prendre un pickomino */
    @Test
    fun TestStateRollDiceOrTakePickomino() {
        /* Joueur 1 */
        connect.choiceDices(id, key, listOf(DICE.d5, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.d2, DICE.d1))
        connect.keepDices(id, key, DICE.worm)
        assertTrue(connect.gameState(id,key).current.status == STATUS.ROLL_DICE_OR_TAKE_PICKOMINO)
    }

    /* Vérifie que l'état de partie ROLL_DICE() est actif lorsque l'on peut lancer des dés */
    @Test
    fun TestStateRollDice() {
        /* Joueur 1 */
        assertTrue(connect.gameState(id,key).current.status == STATUS.ROLL_DICE)
    }

}