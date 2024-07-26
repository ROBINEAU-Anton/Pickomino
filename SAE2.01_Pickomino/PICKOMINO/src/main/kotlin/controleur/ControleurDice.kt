package controleur

import composant.DiceButton
import iut.info1.pickomino.data.DICE
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.PickominoGame
import vue.VuePlateau

class ControleurDice (modele : PickominoGame, vue : VuePlateau) : EventHandler<ActionEvent> {

    val modele : PickominoGame
    val vue : VuePlateau
    var pickoChoosable = false

    init {
        this.modele = modele
        this.vue = vue
    }

    override fun handle(event: ActionEvent?) {


        val joueurPrecedent = modele.getFullState().current.player
        if (event?.source != null) {
            val v = event.source as DiceButton
            keepThisDice(v.value)
            updateDiceArea()
            vue.buttonRoll.isDisable = modele.getkeptDices().size == 8
        }
        val jouerActuel = modele.getFullState().current.player
        val pickoAccessible = modele.getAccesiblePickos()
        val pickoTop = modele.getPickoStackTop()

        if (((vue.totalScore.text.toInt() in pickoAccessible || vue.totalScore.text.toInt() in pickoTop) && pickoTop[jouerActuel] != vue.totalScore.text.toInt()) && DICE.worm in modele.getkeptDices()) {
            vue.pickos[vue.totalScore.text.toInt()]?.isDisable = false
            pickoChoosable = true
        } else if ((vue.totalScore.text.toInt() !in pickoAccessible && (vue.totalScore.text.toInt() !in pickoTop || vue.totalScore.text.toInt() == pickoTop[jouerActuel])) && DICE.worm in modele.getkeptDices()) {
            if (pickoAccessible.size == 1 && pickoAccessible[0] < vue.totalScore.text.toInt()) {
                vue.pickos[pickoAccessible[0]]?.isDisable = false
                pickoChoosable = true
                print("passage if 1")
            } else if (pickoAccessible[pickoAccessible.size -1] < vue.totalScore.text.toInt()) {
                vue.pickos[pickoAccessible[pickoAccessible.size -1]]?.isDisable = false
                pickoChoosable = true
            } else {
                for (i in 0 until  pickoAccessible.size -1) {
                    if (pickoAccessible[i] < vue.totalScore.text.toInt() && vue.totalScore.text.toInt() < pickoAccessible[i + 1]) {
                        vue.pickos[pickoAccessible[i]]?.isDisable = false
                        pickoChoosable = true
                    }
                }
            }
        }
        if (pickoChoosable) {
            if (modele.getkeptDices().size == 8) {
                vue.buttonFinish.isDisable = true
                vue.stateGame.text = "${modele.getNamePlayer(jouerActuel)} doit sélectionner un pickomino"
            } else {
                vue.stateGame.text = "${modele.getNamePlayer(jouerActuel)} peut lancer les dés ou sélectionner un pickomino"
            }
        } else {
            if (modele.getFullState().current.player != joueurPrecedent) {
                vue.stateGame.text = "${modele.getNamePlayer(joueurPrecedent)} à rater son tour, c'est à ${modele.getNamePlayer(jouerActuel)} de lancer les dés"
                vue.replacePickoPile(modele.getAccesiblePickos())
            } else {
                vue.stateGame.text = "${modele.getNamePlayer(jouerActuel)} doit lancer les dés"
            }
        }
        vue.updatePile(modele.getPickoStackTop())
    }

    fun keepThisDice(id: String) {
        when (id) {
            "d1" -> modele.keepDices(DICE.d1)
            "d2" -> modele.keepDices(DICE.d2)
            "d3" -> modele.keepDices(DICE.d3)
            "d4" -> modele.keepDices(DICE.d4)
            "d5" -> modele.keepDices(DICE.d5)
            else -> modele.keepDices(DICE.worm)
        }
    }


    fun updateDiceArea() {
        //met à jour l'affichage des dés'
        vue.totalScore.text = modele.getScoreActualPlayer().toString()

        vue.chosenDices.children.clear()
        val tab = modele.getkeptDices()
        for (dice in tab)
            vue.chosenDices.children.add(DiceButton(dice.name))
        vue.thrownDices.children.clear()
    }


}