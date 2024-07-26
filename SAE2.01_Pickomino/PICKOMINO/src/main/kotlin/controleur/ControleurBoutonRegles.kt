package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage

class ControleurBoutonRegles (private val stage: Stage, private val nextScene : Scene) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        stage.scene = nextScene
    }
}