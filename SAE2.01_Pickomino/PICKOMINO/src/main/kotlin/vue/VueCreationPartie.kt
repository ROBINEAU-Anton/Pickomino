package vue

import composant.*
import controleur.*
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.stage.Stage

class VueCreationPartie(private val stage: Stage) : StackPane() {

    // Chargement des images pour l'arrière-plan et le titre
    val backgroundImage = Image("file:PICKOMINO/assets/bg.jpg")
    val backgroundView = ImageView(backgroundImage)

    // Création des composants
    val joueur1profil = Profil("Joueur1", "yellow", "black")
    val joueur2profil = Profil("Joueur2", "yellow", "black")
    val arrayjoueurprofil = arrayOfNulls<Profil>(4)
    val boutonRetour = Button("Retour", "yellow", "red", "red")
    val boutonStart = Button("Start", "yellow", "green", "green")
    val boutonAjouterJoueur = Button("Ajouter Joueur", "yellow", "green", "green", 80.0, 200.0)
    val boutonRetirerJoueur = Button("Retirer Joueur", "yellow", "red", "red", 80.0, 200.0)


    // Création des cadres
    var buttonHBox = HBox(boutonRetour, boutonStart).apply {
        spacing = 50.0
        alignment = Pos.CENTER
    }

    var buttonHBox2 = HBox(boutonRetirerJoueur, boutonAjouterJoueur).apply {
        spacing = 50.0
        alignment = Pos.CENTER
    }

    var playersVBox = VBox(joueur1profil, joueur2profil).apply {
        spacing = 20.0
        alignment = Pos.CENTER
    }
    var buttonVBox = VBox(buttonHBox2, buttonHBox).apply {
        spacing = 20.0
        alignment = Pos.CENTER
    }
    var contentVBox = VBox(playersVBox, buttonVBox).apply {
        spacing = 20.0
        alignment = Pos.CENTER
    }

    init {
        // Paramètres de l'arrière-plan
        val backgroundSize = BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        val backgroundPosition = BackgroundPosition.DEFAULT
        val backgroundImage = BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, backgroundPosition, backgroundSize)
        val background = Background(backgroundImage)

        // Ajustement de la taille de l'arrière-plan pour correspondre à la taille de la vue
        backgroundView.fitWidthProperty().bind(widthProperty())
        backgroundView.fitHeightProperty().bind(heightProperty())

        // Ajout de l'arrière-plan et VBox à la vue
        children.addAll(backgroundView, contentVBox)
        setBackground(background)

        // Bouton Retirer Joueur Désactiver
        boutonRetirerJoueur.isDisable = true

        arrayjoueurprofil[0] = joueur1profil
        arrayjoueurprofil[1] = joueur2profil
    }



    fun fixeControleurBoutonRetour(controleur : EventHandler<ActionEvent>){
        boutonRetour.addEventHandler(ActionEvent.ACTION,controleur)
    }

    fun fixeControleurBoutonStart(controleur : EventHandler<ActionEvent>){
        boutonStart.addEventHandler(ActionEvent.ACTION,controleur)
    }

    fun fixeControleurBoutonAjouterJoueur(controleur: EventHandler<ActionEvent>) {
        boutonAjouterJoueur.addEventHandler(ActionEvent.ACTION, controleur)
    }

    fun fixeControleurBoutonRetirerJoueur(controleur: EventHandler<ActionEvent>) {
        boutonRetirerJoueur.addEventHandler(ActionEvent.ACTION, controleur)
    }

}