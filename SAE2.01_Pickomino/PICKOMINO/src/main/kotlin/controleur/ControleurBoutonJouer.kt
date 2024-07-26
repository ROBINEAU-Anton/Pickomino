package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import vue.VueCreationPartie

class ControleurBoutonJouer(private val stage: Stage, private val nextScene : Scene) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        stage.scene = nextScene
        //stage.isFullScreen = true
    }
}