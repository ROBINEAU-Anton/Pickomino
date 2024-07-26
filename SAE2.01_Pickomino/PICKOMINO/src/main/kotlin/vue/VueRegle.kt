package vue

import composant.Button
import controleur.ControleurBoutonRetour
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import javafx.stage.Stage

class VueRegle(private val stage : Stage ): StackPane() {

    val borderPane = BorderPane()

    // Créez un ImageView avec l'image de fond
    val backgroundImage = Image("file:PICKOMINO/assets/bg.jpg")
    val backgroundView = ImageView(backgroundImage)

    // Creation des pages règles
    val ruleImages = listOf(
        Image("file:PICKOMINO/assets/rules/page1.png"),
        Image("file:PICKOMINO/assets/rules/page2.png"),
        Image("file:PICKOMINO/assets/rules/page3.png"),
        Image("file:PICKOMINO/assets/rules/page4.png"),
        Image("file:PICKOMINO/assets/rules/page5.png"),
        Image("file:PICKOMINO/assets/rules/page6.png"),
        Image("file:PICKOMINO/assets/rules/page7.png"),
        Image("file:PICKOMINO/assets/rules/page8.png"),
    )
    val ruleImageViews: MutableList<ImageView> = mutableListOf()


    // Indice de la page actuelle
    var currentPageIndex = 0

    // Création des boutons retour, avant, apres, ...
    val buttonBack = Button("Retour", "yellow", "red", "red")

    val buttonBefore = Button("<", "yellow", "green", "green", 80.0, 60.0)
    val pageNumber = Text(" 1/8")
    val buttonNext = Button(">", "yellow", "green", "green", 80.0, 60.0)

    val movePageContainer = HBox(buttonBefore, pageNumber, buttonNext)
    val space = Region()
    val buttonContainer = HBox(buttonBack, space, movePageContainer)

    init {
        // Définir la mise à l'échelle de l'ImageView pour ajuster l'image à la taille de la fenêtre
        backgroundView.fitWidthProperty().bind(widthProperty())
        backgroundView.fitHeightProperty().bind(heightProperty())

        // Modifier le texte de pageNumber
        pageNumber.font = Font.font("Arial", FontWeight.BOLD, 30.0)

        // Modifier la position des éléments dans les containers
        movePageContainer.alignment = Pos.CENTER
        movePageContainer.spacing = 20.0
        buttonContainer.alignment = Pos.CENTER

        // Modifier la position du container
        buttonContainer.alignment = Pos.CENTER
        buttonContainer.padding = Insets(0.0, 20.0, 20.0, 20.0)

        // Espacer le bouton retour et le movePageContainer
        HBox.setHgrow(space, Priority.ALWAYS)

        // Ajoutez les éléments  au borderPane et les placer
        borderPane.children.add(backgroundView)
        borderPane.bottom = buttonContainer

        // Définir le borderPane comme contenu de la Vue
        children.add(borderPane)

        // Créez les ImageView correspondant aux images des règles
        ruleImages.forEach {
            val imageView = ImageView(it)
            ruleImageViews.add(imageView)
        }

        // Affiche la page 1 au démarrage
        currentPageIndex = 0
        borderPane.center = ruleImageViews[currentPageIndex]
        pageNumber.text = " ${currentPageIndex + 1}/${ruleImages.size}"

        // Ajout police
        this.styleClass.add("police")
    }

    fun fixeControleurBoutonPrecedant(controleur : EventHandler<ActionEvent>) {
        buttonBefore.addEventHandler(ActionEvent.ACTION,controleur)
    }

    fun fixeControleurBoutonSuivant(controleur : EventHandler<ActionEvent>) {
        buttonNext.addEventHandler(ActionEvent.ACTION,controleur)
    }

    fun fixeControleurBoutonRetour(controleur : EventHandler<ActionEvent>) {
        buttonBack.addEventHandler(ActionEvent.ACTION,controleur)
    }
}