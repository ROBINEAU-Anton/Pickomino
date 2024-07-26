package controleur

import composant.PickominoButton
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.PickominoGame
import vue.VuePlateau

class ControleurPickominos(val vue : VuePlateau, val modele : PickominoGame) : EventHandler<ActionEvent>{
    override fun handle(event: ActionEvent) {
        vue.buttonFinish.isDisable = !vue.buttonFinish.isDisable
        if (!vue.buttonFinish.isDisable) {
            vue.selectedPicko = event.source as PickominoButton
        } else {
            vue.selectedPicko = null
        }
    }

}