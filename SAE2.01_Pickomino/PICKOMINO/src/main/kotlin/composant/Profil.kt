package composant

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.StrokeType
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.control.TextFormatter
import java.util.function.UnaryOperator

class Profil(private val pseudoDefaut: String, private val bgColor: String, private val fontColor: String, private val radius: Double = 60.0) : HBox() {

     val textField = TextField().apply {
        text = pseudoDefaut
         //Limite le speudo a 7caractères maximum
        textFormatter = TextFormatter<String>(UnaryOperator { textChange ->
            val maxLength = 7
            val newText = textChange.controlNewText
            if (newText.length <= maxLength) textChange else null
        })
    }

    init {
        //création d'un label circulaire et d'un text field pour les profil de joueur sur l'ecran de création de la partie
        val label = Label(pseudoDefaut)
        label.textFill = Color.web(fontColor)
        label.alignment = Pos.CENTER


        val circle = Circle(radius)
        circle.fill = Color.web(bgColor)
        circle.stroke = Color.BLACK
        circle.strokeWidth = 2.0
        circle.strokeType = StrokeType.INSIDE

        val circlePane = StackPane(circle, label)
        StackPane.setAlignment(label, Pos.CENTER)

        this.spacing = 10.0
        this.alignment = Pos.CENTER
        this.spacing = 150.0

        label.textProperty().bindBidirectional(textField.textProperty())

        this.children.addAll(circlePane, textField)

        this.textField.styleClass.add("police")
        label.styleClass.add("police-label")
    }

    fun getText(): String {
        //return le code du label  
        return textField.text
    }
}
