package controleur

import composant.Profil
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.PickominoGame
import modele.Player
import vue.VueCreationPartie

class ControleurBoutonAjouterJoueur (modele : PickominoGame, vue : VueCreationPartie) : EventHandler<ActionEvent> {
    val modele : PickominoGame
    val vue : VueCreationPartie

    init {
        this.modele = modele
        this.vue = vue
    }
    override fun handle(event: ActionEvent) {
        if (modele.getNbPlayer() < 4) {
            modele.addPlayer()
            var newprofil = Profil("Joueur${modele.getNbPlayer()}", "yellow", "black")
            vue.playersVBox.children.addAll(newprofil)
            vue.arrayjoueurprofil[modele.getNbPlayer()-1] = newprofil
            if (modele.getNbPlayer() > 2) {
                vue.boutonRetirerJoueur.isDisable = false
            }
            if (modele.getNbPlayer() == 4) {
                vue.boutonAjouterJoueur.isDisable = true
            }
        }
    }

}