package vue

import composant.Button
import controleur.ControleurBoutonJouer
import controleur.ControleurBoutonOption
import controleur.ControleurBoutonQuitter
import controleur.ControleurBoutonRegles
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.stage.Stage

class VueAccueil(private val stage : Stage) : StackPane() {

    // Chargement des images pour l'arrière-plan et le titre
    val backgroundImage = Image("file:PICKOMINO/assets/bg.jpg")
    val backgroundView = ImageView(backgroundImage)
    val titreImage = Image("file:PICKOMINO/assets/Title.png")
    val titreView = ImageView(titreImage)

    // Création des boutons
    val buttonJouer = Button(text = "Jouer", bgColor = "yellow", borderColor = "green", fontColor = "green")
    val buttonRegles = Button(text = "Règles", bgColor = "yellow", borderColor = "blue", fontColor = "blue")
    val buttonOptions = Button(text = "Options", bgColor = "yellow", borderColor = "gray", fontColor = "gray")
    val buttonQuitter = Button(text = "Quitter", bgColor = "yellow", borderColor = "red", fontColor = "red")

    init {
        // Paramètres de l'arrière-plan
        val backgroundSize = BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        val backgroundPosition = BackgroundPosition.DEFAULT
        val backgroundImage = BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, backgroundPosition, backgroundSize)
        val background = Background(backgroundImage)

        // Ajustement de la taille de l'arrière-plan pour correspondre à la taille de la vue
        backgroundView.fitWidthProperty().bind(widthProperty())
        backgroundView.fitHeightProperty().bind(heightProperty())

        // Création d'une VBox pour contenir le titre et les boutons
        val contentVBox = VBox(titreView, buttonJouer, buttonRegles, buttonOptions,buttonQuitter)
        contentVBox.spacing = 70.0
        contentVBox.style = "-fx-padding: 20px;"
        contentVBox.alignment = Pos.CENTER

        // Ajout de l'arrière-plan et de la boîte de contenu à la vue
        children.addAll(backgroundView, contentVBox)
        setBackground(background)

        // Fixe le contrôleur pour quitter l'application lorsque le bouton "Quitter" est cliqué

    }



    //Création des méthodes pour fixer les controleurs
    // Méthode pour ajouter un contrôleur au boutons de la page d'acceuil
    fun fixeControleurBoutonQuitter(controleur : EventHandler<ActionEvent>){
        buttonQuitter.addEventHandler(ActionEvent.ACTION,controleur)
    }

    fun fixeControleurBoutonRegles(controleur : EventHandler<ActionEvent>){
        buttonRegles.addEventHandler(ActionEvent.ACTION,controleur)
    }

    fun fixeControleurBoutonOptions(controleur : EventHandler<ActionEvent>){
        buttonOptions.addEventHandler(ActionEvent.ACTION,controleur)
    }

    fun fixeControleurBoutonJouer(controleur : EventHandler<ActionEvent>){
        buttonJouer.addEventHandler(ActionEvent.ACTION,controleur)
    }
}
