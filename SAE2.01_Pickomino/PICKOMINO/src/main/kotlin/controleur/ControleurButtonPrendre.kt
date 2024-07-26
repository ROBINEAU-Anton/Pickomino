package controleur

import iut.info1.pickomino.data.DICE
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import modele.PickominoGame
import vue.VuePlateau

class ControleurButtonPrendre(val vue : VuePlateau, val modele : PickominoGame) : EventHandler<ActionEvent>{
    var joueurPrecedent = modele.getFullState().current.player

    override fun handle(event: ActionEvent) {

        joueurPrecedent = modele.getFullState().current.player
        for ((key, value) in vue.pickos) {
            if (value == vue.selectedPicko && (key in modele.getAccesiblePickos() || key in modele.getPickoStackTop())  && DICE.worm in modele.getkeptDices()) {
                value.style = "-fx-background-color : transparent; -fx-border-width : 0"
                value.padding = Insets(0.0)
                value.isDisable = true
                when (modele.getFullState().current.player) {
                    0 -> vue.pile1.children.addAll(value)
                    1 -> vue.pile2.children.addAll(value)
                    2 -> vue.pile3.children.addAll(value)
                    3 -> vue.pile4.children.addAll(value)
                }
                modele.takePickomino(key)
                vue.selectedPicko?.isClick = false
                vue.selectedPicko = null
            }


        }
        vue.chosenDices.children.removeAll(vue.chosenDices.children)
        vue.buttonFinish.isDisable = true
        vue.buttonRoll.isDisable = false
        vue.totalScore.text = "0"
        vue.stateGame.text = "${modele.getNamePlayer(joueurPrecedent)} a fini son tour, c'est maintenant à ${modele.getNamePlayer(modele.getFullState().current.player)} de lancer les dés"

        vue.updateInfoPlayers(modele.getScoreVers())//Met à jour les infos des joueurs sur la vue
        vue.updatePile(modele.getPickoStackTop())

        vue.getResultParti(modele.getScoreVers())//affiche la fin de la partie si elle est terminée
    }



}