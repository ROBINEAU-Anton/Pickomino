package composant

import javafx.scene.control.Button

class Button(private val text: String, private val bgColor: String, private val borderColor: String, private val fontColor: String, private val hSize: Double = 80.0, private val wSize: Double = 180.0) : Button(text) {

    init {
        this.style = "-fx-background-color: $bgColor; -fx-border-color: $borderColor; -fx-text-fill: $fontColor; -fx-pref-width: $wSize; -fx-pref-height: $hSize; -fx-font-size: ${25.0}; -fx-border-width: 4px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 5px; -fx-background-insets: 4px;"
        this.styleClass.add("police")
    }
}

