package composant

import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import vue.VuePlateau

class PickominoButton(val value : String, val vue: VuePlateau) : Button() {
    var isClick : Boolean = false
    init {
        val bg = ImageView(Image("file:PICKOMINO/assets/pickominos/$value.png", 263.0, 518.0, true, false))
        bg.fitHeight = 137.0
        bg.fitWidth = 70.0
        graphic = bg
        isDisable = true
        // Appliquer le style par défaut
        setDefaultStyle()

        // Appliquer le style au survol du bouton
        setOnMouseEntered { setHoverStyle() }

        //Appliquer le style lorsque le bouton est cliqué

        setOnAction { setClickStyle() }

        // Rétablir le style par défaut lorsque la souris quitte le bouton
        setOnMouseExited { setDefaultStyle() }
    }

    // Définir le style par défaut du bouton
    private fun setDefaultStyle() {
        style = if (isClick) {
            "-fx-border-width : 2px; -fx-border-color : red; -fx-background-color: transparent;"
        } else {
            "-fx-background-color: transparent; -fx-border-width: 0;"
        }
    }

    // Définir le style lors du survol du bouton avec une surbrillance légère
    private fun setHoverStyle() {
        style = if (isClick) {
            "-fx-border-width : 2px; -fx-border-color : red;-fx-background-color: #ffffff;-fx-effect: innershadow(gaussian, rgba(0, 0, 0, 1), 0.2, 0, 0, 0);-fx-background-radius: 5px;-fx-margin: 20px;"
        } else {
            "-fx-background-color: #ffffff;-fx-effect: innershadow(gaussian, rgba(0, 0, 0, 1), 0.2, 0, 0, 0);-fx-background-radius: 5px;-fx-margin: 20px;"
        }
    }

    private fun setClickStyle() {
        if (!isClick) {
            style = "-fx-border-width : 2px; -fx-border-color : red"
            isClick = true
            vue.selectedPicko = vue.pickos[value.toInt()]
        } else{
            isClick = false
            setHoverStyle()
            vue.selectedPicko = null
        }
    }
}