package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.application.Platform
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType

class ControleurBoutonQuitter : EventHandler<ActionEvent> {

    // Crée une instance de la boîte de dialogue de confirmation
    private val alert : Alert = Alert(AlertType.CONFIRMATION)

    override fun handle(event: ActionEvent) {

        // Définit et affiche la boîte de dialogue de confirmation
        alert.title = "Fenetre de confirmation"
        alert.headerText = "Est vous sur de vouloir quitter le jeu ?"
        alert.showAndWait()

        //Ferme l'application si l'utilisateur clique sur "OK"
        if (alert.result == ButtonType.OK) {
            Platform.exit() // Ferme l'application JavaFX
            System.exit(0) // Stop le processus
        }
    }
}