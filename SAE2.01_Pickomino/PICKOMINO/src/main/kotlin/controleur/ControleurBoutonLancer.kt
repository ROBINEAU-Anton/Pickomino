package controleur

import composant.DiceButton
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.PickominoGame
import vue.VuePlateau

class ControleurBoutonLancer(modele : PickominoGame, vue : VuePlateau) : EventHandler<ActionEvent> {

    val modele : PickominoGame
    val vue : VuePlateau

    init {
        this.modele = modele
        this.vue = vue
    }

    override fun handle(event: ActionEvent?) {
        vue.buttonRoll.isDisable = true
        vue.buttonFinish.isDisable = true
        for ((_,value) in vue.pickos) {
            value.style = "-fx-background-color : transparent; -fx-border-width : 0"
            value.isDisable = true
        }
        val joueurPrecedent = modele.getFullState().current.player
        updateDiceArea()
        val joueurActuel = modele.getFullState().current.player
        if (joueurActuel != joueurPrecedent) {
            vue.buttonRoll.isDisable = false
            vue.stateGame.text = "${modele.getNamePlayer(joueurPrecedent)} a raté son tour, c'est à ${modele.getNamePlayer(joueurActuel)} de lancer les dés"
            vue.chosenDices.children.removeAll(vue.chosenDices.children)
            vue.pickos[vue.totalScore.text.toInt()]?.isDisable = true
            vue.totalScore.text = "0"
            vue.replacePickoPile(modele.getAccesiblePickos())
        } else {
            vue.stateGame.text = "${modele.getNamePlayer(joueurActuel)} doit choisir un dé"
        }
        vue.updatePile(modele.getPickoStackTop())
        vue.updateInfoPlayers(modele.getScoreVers())//Met à jour les infos des joueurs sur la vue

        vue.getResultParti(modele.getScoreVers())//affiche la fin de la partie si elle est terminée
    }

    fun updateDiceArea() {
        modele.roll()
        val tabDiceRoll = modele.getRoll()
        val tabDiceKeep = modele.getkeptDices()
        for (dice in tabDiceRoll)
            vue.thrownDices.children.add(DiceButton(dice.name, dice in tabDiceKeep))
        for (dice in vue.thrownDices.children){
            dice.addEventHandler(ActionEvent.ACTION,ControleurDice(modele,vue))
        }
    }





}