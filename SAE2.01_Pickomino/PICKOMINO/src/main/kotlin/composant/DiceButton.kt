package composant

import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class DiceButton(value:String,var off : Boolean = false): Button() {

    val value : String

    init {
        isDisable = off

        this.value = value
        val bg = ImageView(Image("file:PICKOMINO/assets/dices/$value.png", 159.0, 202.0, false, false))

        bg.fitHeight = 45.0
        bg.fitWidth = 45.0

        graphic = bg
        border = null
        background = null
    }



}