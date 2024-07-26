package controleur

import vue.VueOption
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage

class ControleurBoutonOption (private val stage: Stage, private val nextScene : Scene) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        stage.scene = nextScene
    }
}