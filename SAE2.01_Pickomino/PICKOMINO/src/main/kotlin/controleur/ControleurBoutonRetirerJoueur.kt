package controleur

import composant.Profil
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.PickominoGame
import vue.VueCreationPartie

class ControleurBoutonRetirerJoueur(modele : PickominoGame, vue : VueCreationPartie) : EventHandler<ActionEvent> {
    val modele : PickominoGame
    val vue : VueCreationPartie

    init {
        this.modele = modele
        this.vue = vue
    }
    override fun handle(event: ActionEvent) {
        if (modele.getNbPlayer() > 2) {
            modele.delPlayer()
            vue.playersVBox.children.removeAt(vue.playersVBox.children.size - 1)
            vue.arrayjoueurprofil[vue.playersVBox.children.size] = null
            if (modele.getNbPlayer() == 2) {
                vue.boutonRetirerJoueur.isDisable = true
            }
            if (modele.getNbPlayer() < 4) {
                vue.boutonAjouterJoueur.isDisable = false
            }
        }
    }

}