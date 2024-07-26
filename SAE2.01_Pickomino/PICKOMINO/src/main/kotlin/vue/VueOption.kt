package vue

import composant.Button
import controleur.ControleurBoutonRetour
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.CheckBox
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.stage.Stage

class VueOption(val stage : Stage ): StackPane() {

    val borderPane = BorderPane()

    // Créez un ImageView avec l'image de fond
    val backgroundImage = Image("file:PICKOMINO/assets/bg.jpg")
    val backgroundView = ImageView(backgroundImage)

    // Créer une option plein écran
    val labelPleinEcran = Label("Mode plein écran")
    val pleinEcranChekbox = CheckBox()

    // Créer un choix de la langue
    val labelLangue = Label("Langue")
    val langueCombobox = ComboBox<String>()

    // Créer une barre de volume
    val labelSon = Label("Son")
    val volumeSlider = Slider(0.0, 100.0, 50.0)
    val labelVolume = TextField("50")

    // Créer les bouton
    val boutonRetour = Button("Retour", "yellow", "red", "red", 80.0, 180.0)
    val bouttonAppliquer = Button("Appliquer", "yellow", "green", "green", 80.0, 180.0)

    val Space = Region()
    val Space2 = Region()

    val gridPane = GridPane()
    val buttonContainer = HBox(boutonRetour, Space, Space2, bouttonAppliquer)

    init {
        // Définir la mise à l'échelle de l'ImageView pour ajuster l'image à la taille de la fenêtre
        backgroundView.fitWidthProperty().bind(widthProperty())
        backgroundView.fitHeightProperty().bind(heightProperty())

        // Modifier la position des éléments
        buttonContainer.alignment = Pos.CENTER
        buttonContainer.padding = Insets(0.0, 20.0, 20.0, 20.0)

        gridPane.padding = Insets(20.0, 20.0, 0.0, 20.0)

        // Définir le comportement d'espacement pour les espaces réservés
        HBox.setHgrow(Space, Priority.ALWAYS)
        HBox.setHgrow(Space2, Priority.ALWAYS)

        // Modifier la position des éléments
        gridPane.alignment = Pos.CENTER
        gridPane.padding = Insets(20.0)

        // Configurer les colonnes du GridPane
        val columnConstraints = ColumnConstraints()
        columnConstraints.hgrow = Priority.ALWAYS
        gridPane.columnConstraints.addAll(columnConstraints, columnConstraints, columnConstraints)

        // Configurer les lignes du GridPane
        val rowConstraints = RowConstraints()
        rowConstraints.vgrow = Priority.ALWAYS
        gridPane.rowConstraints.addAll(rowConstraints, rowConstraints)

        // Ajouter les éléments au GridPane
        gridPane.add(labelPleinEcran, 0, 0)
        gridPane.add(Space, 1, 0)
        gridPane.add(pleinEcranChekbox, 3, 0)
        gridPane.add(labelLangue, 0, 1)
        gridPane.add(langueCombobox, 3, 1)
        gridPane.add(labelSon, 0, 2)
        gridPane.add(volumeSlider, 2, 2)
        gridPane.add(labelVolume, 3, 2)

        // Ajouter les options de langue à la ComboBox
        langueCombobox.items = FXCollections.observableArrayList("Français", "Anglais", "Espagnol")

        // Definir la taille du texte field
        labelVolume.prefColumnCountProperty().bind(labelVolume.textProperty().length())

        // Espacement entre les éléments du GridPane
        gridPane.hgap = 20.0
        gridPane.vgap = 20.0

        // Ajoutez les éléments au borderPane et les placer
        borderPane.children.add(backgroundView)
        borderPane.top = gridPane
        borderPane.bottom = buttonContainer

        // Définir le borderPane comme contenu de la Vue
        children.add(borderPane)

        // Ajout police
        this.styleClass.add("police")

    }
    fun fixeControleurBoutonRetour(controleur: EventHandler<ActionEvent>) {
        boutonRetour.addEventHandler(ActionEvent.ACTION, controleur)
    }
}
