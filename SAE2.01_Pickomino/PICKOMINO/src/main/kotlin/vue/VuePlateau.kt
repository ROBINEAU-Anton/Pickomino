package vue

import composant.Button
import composant.DiceButton
import composant.PickominoButton
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import javafx.scene.control.TitledPane
import javafx.scene.effect.Effect
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Screen
import javafx.stage.Stage

class VuePlateau(val stage : Stage) : StackPane() {
    var selectedPicko : PickominoButton? = null
    //Background
    val background = ImageView(Image("file:PICKOMINO/assets/bgPlateau2.jpg",Screen.getPrimary().bounds.width,Screen.getPrimary().bounds.height,false,false))
    val plateau = BorderPane()

    //Top's elements
    val topArea = GridPane()
    val stateGame = Label("Joueur 1 lance les dés")

    //First Player's element
    val player1 = HBox()
    val profile1 = StackPane()
    val circle1 = Circle(60.0)
    val text1 = Label("Joueur 1")
    val rec1 = Rectangle(70.0, 137.0)
    val pile1 = StackPane(rec1)
    val score1 = VBox()
    val nbPickos1 = Label("0 Pickominos")
    val space1 = Region()
    val points1 = Label("Points : 0")

    //Second Player's element
    val player2 = HBox()
    val profile2 = StackPane()
    val circle2 = Circle(60.0)
    val text2 = Label("Joueur 2")
    val rec2 = Rectangle(70.0, 137.0)
    val pile2 = StackPane(rec2)
    val score2 = VBox()
    val nbPickos2 = Label("0 Pickominos")
    val space2 = Region()
    val points2 = Label("Points : 0")

    //Bottom's elements
    val bottomArea = GridPane()
    val buttonArea = HBox()
    val buttonRoll = Button("Lancer", "yellow", "blue", "blue", 100.0, 250.0)
    val buttonFinish = Button("Prendre", "yellow", "green", "green", 100.0, 250.0).apply {
        isDisable = true
    }

    //Third Player's element
    val player3 = HBox()
    val profile3 = StackPane()
    val circle3 = Circle(60.0)
    val text3 = Label("Joueur 3")
    val rec3 = Rectangle(70.0, 137.0)
    val pile3 = StackPane(rec3)
    val score3 = VBox()
    val nbPickos3 = Label("0 Pickominos")
    val space3 = Region()
    val points3 = Label("Points : 0")

    //Fourth Player's element
    val player4 = HBox()
    val profile4 = StackPane()
    val circle4 = Circle(60.0)
    val text4 = Label("Joueur 4")
    val rec4 = Rectangle(70.0, 137.0)
    val pile4 = StackPane(rec4)
    val score4 = VBox()
    val nbPickos4 = Label("0 Pickominos")
    val space4 = Region()
    val points4 = Label("Points : 0")

    //Center's elements
    val playground = VBox()

    //Pickominos' area
    val pickominoArea = FlowPane()

    //Dices' and score's area
    val diceArea = GridPane()
    val thrownDices = FlowPane()
    val thrownDicesTP = TitledPane("Dés lancés", thrownDices)
    val score = StackPane()
    val circleScore = Circle(20.0, Color.YELLOW).apply {
        stroke = Color.BLACK
    }
    val totalScore = Label("0")
    val chosenDices = FlowPane()
    val chosenDicesTP = TitledPane("Dés choisis", chosenDices)

    //Pickominos

    val picko21 = PickominoButton("21", this)
    val picko22 = PickominoButton("22", this)
    val picko23 = PickominoButton("23", this)
    val picko24 = PickominoButton("24", this)
    val picko25 = PickominoButton("25", this)
    val picko26 = PickominoButton("26", this)
    val picko27 = PickominoButton("27", this)
    val picko28 = PickominoButton("28", this)
    val picko29 = PickominoButton("29", this)
    val picko30 = PickominoButton("30", this)
    val picko31 = PickominoButton("31", this)
    val picko32 = PickominoButton("32", this)
    val picko33 = PickominoButton("33", this)
    val picko34 = PickominoButton("34", this)
    val picko35 = PickominoButton("35", this)
    val picko36 = PickominoButton("36", this)
    val pickos = mapOf(21 to picko21, 22 to picko22, 23 to picko23, 24 to picko24,
        25 to picko25, 26 to picko26, 27 to picko27, 28 to picko28, 29 to picko29, 30 to picko30,
        31 to picko31, 32 to picko32, 33 to picko33, 34 to picko34, 35 to picko35, 36 to picko36)

    init {
        this.children.addAll(background, plateau)

        stateGame.text = ""
        stateGame.style = "-fx-font-alignement : center; -fx-font-size : 20.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        stateGame.padding = Insets(10.0)
        stateGame.alignment = Pos.TOP_CENTER
        stateGame.prefWidth = Double.MAX_VALUE


        circle1.fill = Color.YELLOW
        circle1.stroke = Color.BLACK
        text1.style = "-fx-font-size : 20.0"
        profile1.children.addAll(circle1, text1)
        score1.children.addAll(nbPickos1, space1, points1)
        nbPickos1.padding = Insets(10.0, 33.0, 10.0, 10.0)
        points1.padding = Insets(10.0, 65.0, 10.0, 10.0)
        nbPickos1.style = "-fx-font-size : 18.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        points1.style = "-fx-font-size : 18.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        VBox.setVgrow(space1, Priority.ALWAYS)
        player1.children.addAll(profile1, pile1, score1)
        rec1.fill = Color.TRANSPARENT
        rec1.stroke = Color.BLACK
        player1.spacing = 10.0
        //player1.style = "-fx-background-color : red"
        player1.padding = Insets(48.0, 10.0,10.0,10.0)

        circle2.fill = Color.YELLOW
        circle2.stroke = Color.BLACK
        text2.style = "-fx-font-size : 20.0"
        profile2.children.addAll(circle2, text2)
        score2.children.addAll(nbPickos2, space2, points2)
        nbPickos2.padding = Insets(10.0, 33.0, 10.0, 10.0)
        points2.padding = Insets(10.0, 60.0, 10.0, 10.0)
        nbPickos2.style = "-fx-font-size : 18.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        points2.style = "-fx-font-size : 18.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        VBox.setVgrow(space2, Priority.ALWAYS)
        player2.children.addAll(score2, pile2, profile2)
        rec2.fill = Color.TRANSPARENT
        rec2.stroke = Color.BLACK
        player2.spacing = 10.0
        //player2.style = "-fx-background-color : red"
        player2.alignment = Pos.CENTER_RIGHT
        player2.padding = Insets(48.0, 10.0,10.0,10.0)

        topArea.addColumn(0, player1)
        topArea.addColumn(1, stateGame)
        topArea.addColumn(2, player2)
        val column1 = ColumnConstraints()
        val column2 = ColumnConstraints()
        val column3 = ColumnConstraints()
        column1.percentWidth = 25.0
        column2.percentWidth = 50.0
        column3.percentWidth = 25.0
        topArea.columnConstraints.addAll(column1, column2, column3)
        //topArea.style = "-fx-background-color : green"
        GridPane.setHalignment(stateGame, HPos.CENTER)
        GridPane.setValignment(stateGame, VPos.TOP)
        plateau.top = topArea

        circle3.fill = Color.YELLOW
        circle3.stroke = Color.BLACK
        text3.style = "-fx-font-size : 20.0; -fx-stroke-width : 5px; -fx-stroke : black"
        profile3.children.addAll(circle3, text3)
        score3.children.addAll(nbPickos3, space3, points3)
        nbPickos3.padding = Insets(10.0, 33.0, 10.0, 10.0)
        points3.padding = Insets(10.0, 65.0, 10.0, 10.0)
        nbPickos3.style = "-fx-font-size : 18.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        points3.style = "-fx-font-size : 18.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        VBox.setVgrow(space3, Priority.ALWAYS)
        player3.children.addAll(profile3, pile3, score3)
        rec3.fill = Color.TRANSPARENT
        rec3.stroke = Color.BLACK
        player3.spacing = 10.0
        player3.padding = Insets(10.0)
        //player3.style = "-fx-background-color : red"

        circle4.fill = Color.YELLOW
        circle4.stroke = Color.BLACK
        text4.style = "-fx-font-size : 20.0; -fx-stroke-width : 5px"
        profile4.children.addAll(circle4, text4)
        score4.children.addAll(nbPickos4, space4, points4)
        nbPickos4.padding = Insets(10.0, 33.0, 10.0, 10.0)
        points4.padding = Insets(10.0, 60.0, 10.0, 10.0)
        nbPickos4.style = "-fx-font-size : 18.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        points4.style = "-fx-font-size : 18.0; -fx-border-size : 3; -fx-border-color : black; -fx-background-color: rgba(255, 255, 255, 0.5);"
        VBox.setVgrow(space4, Priority.ALWAYS)
        player4.children.addAll(score4, pile4, profile4)
        rec4.fill = Color.TRANSPARENT
        rec4.stroke = Color.BLACK
        player4.spacing = 10.0
        player4.alignment = Pos.CENTER_RIGHT
        player4.padding = Insets(10.0)
        //player4.style = "-fx-background-color : red"
        buttonArea.children.addAll(buttonRoll,buttonFinish)
        buttonArea.spacing = 100.0
        buttonArea.alignment = Pos.CENTER
        bottomArea.addColumn(0, player3)
        bottomArea.addColumn(1, buttonArea)
        bottomArea.addColumn(2, player4)
        val column4 = ColumnConstraints()
        val column5 = ColumnConstraints()
        val column6 = ColumnConstraints()
        column4.percentWidth = 25.0
        column5.percentWidth = 50.0
        column6.percentWidth = 25.0
        bottomArea.columnConstraints.addAll(column4, column5, column6)
        //bottomArea.style = "-fx-background-color : green"
        plateau.bottom = bottomArea

        circleScore.fill = Color.YELLOW
        circleScore.stroke = Color.BLACK
        score.children.addAll(circleScore, totalScore)
        totalScore.style = "-fx-font-size : 14.0"
        score.alignment = Pos.CENTER
        thrownDicesTP.isCollapsible = false
        thrownDicesTP.prefWidth = Double.MAX_VALUE
        thrownDicesTP.prefHeight = 200.0
        chosenDicesTP.isCollapsible = false
        chosenDicesTP.prefWidth = Double.MAX_VALUE
        chosenDicesTP.prefHeight = 200.0
        chosenDices.style = "-fx-background-color : #582900"
        chosenDices.hgap = 8.0
        chosenDices.vgap = 8.0
        thrownDices.style = "-fx-background-color : #582900"
        thrownDices.hgap = 8.0
        thrownDices.vgap = 8.0
        diceArea.alignment = Pos.BOTTOM_CENTER
        chosenDicesTP.alignment = Pos.CENTER
        thrownDicesTP.alignment = Pos.CENTER
        chosenDices.alignment = Pos.CENTER
        thrownDices.alignment = Pos.CENTER
        diceArea.padding = Insets(5.0)

        diceArea.addColumn(0, thrownDicesTP)
        diceArea.addColumn(1, score)
        diceArea.addColumn(2, chosenDicesTP)
        val diceColumn1 = ColumnConstraints()
        val diceColumn2 = ColumnConstraints()
        val diceColumn3 = ColumnConstraints()
        diceColumn1.percentWidth = 45.0
        diceColumn2.percentWidth = 10.0
        diceColumn3.percentWidth = 45.0
        diceArea.columnConstraints.addAll(diceColumn1, diceColumn2, diceColumn3)
        //diceArea.style = "-fx-border-color : black; -fx-border-width : 2px"

        for (i in pickos) {
            pickominoArea.children.addAll(pickos[i.key])
        }

        pickominoArea.alignment = Pos.CENTER
        //pickominoArea.style = "-fx-border-color : black; -fx-border-width : 2px"
        pickominoArea.vgap = 20.0
        pickominoArea.prefHeight = 352.0
        pickominoArea.padding = Insets(5.0)
        playground.children.addAll(pickominoArea, diceArea)
        playground.spacing = 10.0
        plateau.center = playground

        val void = Region()
        void.prefWidth = Screen.getPrimary().bounds.width * 0.25
        //void.style = "-fx-background-color : pink"
        plateau.left = void
        val void2 = Region()
        void2.prefWidth = Screen.getPrimary().bounds.width * 0.25
        //void2.style = "-fx-background-color : pink"
        plateau.right = void2

        this.styleClass.add("police")
    }


    fun fixeControleurBoutonLancer(controleur : EventHandler<ActionEvent>) {
        buttonRoll.addEventHandler(ActionEvent.ACTION,controleur)
    }

    fun fixeControleurPickominos(controleur : EventHandler<ActionEvent>){
        for ((_, value) in pickos) {
            value.addEventHandler(ActionEvent.ACTION, controleur)
        }
    }

    fun fixeControleurButtonFinish(controleur : EventHandler<ActionEvent>) {
        buttonFinish.addEventHandler(ActionEvent.ACTION, controleur)
    }

    fun removeLastPicko() {
        pickominoArea.children.removeAt(pickominoArea.children.size - 1)
    }

    fun replacePickoPile(listPicko : List<Int>) {
        pickominoArea.children.clear()
        for ((key, value) in pickos) {
            if (key in listPicko) {
                pickominoArea.children.addAll(value)
                value.isVisible = true
            }
        }
    }

    fun updatePile(listPicko: List<Int>) {
        for ((key, value) in pickos) {
            if (key !in listPicko && (value in pile1.children || value in pile2.children || value in pile3.children || value in pile4.children)) {
                value.isVisible = false
            }
            if (key in listPicko) {
                value.isVisible = true
            }
        }
    }

    fun updateInfoPlayers(tabScoreWorm :  List<Int>) {
        //Cette methode permet de mettre a jour le socre te le nombre de pickominos des joueurs dnas la vue Plateau
        for (i in tabScoreWorm.indices) {
            when(i) {
                0 -> {
                    points1.text = "Points : ${tabScoreWorm[i]}"
                    nbPickos1.text = "${pile1.children.size -1} Pickominos"
                }
                1 -> {
                    points2.text = "Points : ${tabScoreWorm[i]}"
                    nbPickos2.text = "${pile2.children.size -1} Pickominos"
                }
                2 -> {
                    points3.text = "Points : ${tabScoreWorm[i]}"
                    nbPickos3.text = "${pile3.children.size -1} Pickominos"
                }
                3 -> {
                    points4.text = "Points : ${tabScoreWorm[i]}"
                    nbPickos4.text = "${pile4.children.size -1} Pickominos"
                }

            }
        }
    }


    fun getResultParti(tabPoint : List<Int>) {
        //Cette methode renvoie le gagnant sans prendre en compte les égalités 
        if (pickominoArea.children.isEmpty()) {
            val tabpseudo = arrayListOf<Label>(text1,text2,text3,text4)
            val max = tabPoint.sorted().last()
            val i = tabPoint.indexOf(max)
            val alert : Alert = Alert(Alert.AlertType.INFORMATION)
            var res = "Le gagnant est ${tabpseudo[i].text} avec $max points ! "
            // Définit et affiche la boîte de dialogue de confirmation
            alert.title = "Fenetre de fin"
            alert.headerText = "Classement"
            alert.contentText = res
            alert.showAndWait()
            //Ferme l'application si l'utilisateur clique sur "OK"
            if (alert.result == ButtonType.OK) {
                Platform.exit() // Ferme l'application JavaFX
                System.exit(0) // Stop le processus
            }
        }
    }
}

