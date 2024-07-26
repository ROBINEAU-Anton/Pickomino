package modele

import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.data.STATUS

class PickominoGame() {
    private var id: Int
    private var key: Int
    private val players: Array<Player?>
    private var nb_players: Int = 2
    private val connect = Connector.factory("172.26.82.76", "8080")

    init {
        this.id = -1
        this.key = -1
        players = arrayOfNulls(4)
        players[0] = Player(1, "Joueur 1")
        players[1] = Player(2, "Joueur 2")
    }

    fun startGame() {
        val identification = connect.newGame(this.getNbPlayer())
        this.id = identification.first
        this.key = identification.second
        val currentGame = connect.gameState(this.id, this.key)
    }

    fun addPlayer() {
        if (nb_players < 4) {
            nb_players++
            players[nb_players - 1] = Player(nb_players, "Joueur $nb_players")
        }
    }

    fun delPlayer() {
        if (nb_players > 2) {
            players[nb_players - 1] = null
            nb_players--
        }
    }

    fun getNbPlayer(): Int = nb_players

    fun changepseudoPlayer(numberplayer : Int, newpseudo : String?) {
        if (numberplayer in 0 until nb_players)
            players[numberplayer]?.changepseudo(newpseudo)
    }

    fun getNamePlayer(numberplayer : Int) : String? {
        return players[numberplayer]?.getName()
    }

    fun getState(): STATUS = this.connect.gameState(this.id, this.key).current.status

    fun getFullState() = connect.gameState(this.id, this.key)

    fun getkeptDices(): List<DICE> = this.connect.gameState(this.id, this.key).current.keptDices

    fun getPickoStackTop(): List<Int> = this.connect.gameState(this.id, this.key).pickosStackTops()

    fun getAccesiblePickos(): List<Int> = this.connect.gameState(this.id, this.key).accessiblePickos()

    fun getScoreVers() : List<Int> = this.connect.gameState(this.id, this.key).score()

    fun roll() = this.connect.rollDices(this.id, this.key)

    fun getRoll() : List<DICE> = this.connect.gameState(this.id, this.key).current.rolls

    fun setRoll(list: List<DICE>) = connect.choiceDices(this.id, this.key, list)

    fun keepDices(dice: DICE) {
        if (dice in this.connect.gameState(this.id, this.key).current.rolls) {
            this.connect.keepDices(this.id, this.key, dice)
        }
    }

    fun takePickomino(Pickomino: Int) {
        //methode permettant au joueur courant de prendre un pickomino si celui si est sur le terrain ou au sommet de la pile d'un joueur
        //Nous controlerons l'accesibilité des pickomino via l'interface
        if ((Pickomino in getAccesiblePickos() || Pickomino in getPickoStackTop()))
            connect.takePickomino(this.id, this.key, Pickomino)
    }

    fun getScoreActualPlayer(): Int {
        var score = 0
        for (dice in getkeptDices()) {
            when (dice) {
                DICE.d1 -> score += 1
                DICE.d2 -> score += 2
                DICE.d3 -> score += 3
                DICE.d4 -> score += 4
                else -> score += 5
            }
        }
        return score
    }

    fun changePseudo(id : Int, newpseudo: String) {
        players[id - 1] = Player(id, newpseudo)
    }


}