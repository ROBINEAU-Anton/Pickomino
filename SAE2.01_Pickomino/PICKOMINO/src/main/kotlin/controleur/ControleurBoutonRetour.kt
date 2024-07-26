package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import vue.VueAccueil
import vue.VueCreationPartie

class ControleurBoutonRetour(private val stage: Stage, private val previousScene : Scene) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        stage.scene = previousScene
    }
}