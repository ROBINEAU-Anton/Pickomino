package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import modele.PickominoGame
import vue.VueCreationPartie
import vue.VuePlateau

class ControleurBoutonStart (private val stage: Stage,private val vue : VueCreationPartie, private val nextScene : Scene,modele : PickominoGame, val vuePlateau: VuePlateau) : EventHandler<ActionEvent> {

    private var modele : PickominoGame

    init {
        this.modele = modele
    }

    override fun handle(event: ActionEvent) {
        stage.scene = nextScene
        stage.isFullScreen = true
        modele.startGame()

        for (i in 0 until modele.getNbPlayer()) {
            modele.changepseudoPlayer(i ,vue.arrayjoueurprofil[i]?.textField?.text)
            when (i) {
                0 -> {
                    vuePlateau.text1.text = vue.arrayjoueurprofil[i]?.textField?.text
                    modele.changePseudo(1, vuePlateau.text1.text)
                }
                1 -> {
                    vuePlateau.text2.text = vue.arrayjoueurprofil[i]?.textField?.text
                    modele.changePseudo(2, vuePlateau.text2.text)
                }
                2 -> {
                    vuePlateau.text3.text = vue.arrayjoueurprofil[i]?.textField?.text
                    modele.changePseudo(3, vuePlateau.text3.text)
                }
                3 -> {
                    vuePlateau.text4.text = vue.arrayjoueurprofil[i]?.textField?.text
                    modele.changePseudo(4, vuePlateau.text4.text)
                }
            }
        }

        when (modele.getNbPlayer()) {
            2 -> {
                vuePlateau.player3.isVisible = false
                vuePlateau.player4.isVisible = false
            }
            3 -> vuePlateau.player4.isVisible = false
        }

        vuePlateau.stateGame.text = "${vue.arrayjoueurprofil[modele.getFullState().current.player]?.textField?.text} doit lancer les dés"
        vuePlateau.fixeControleurButtonFinish(ControleurButtonPrendre(vuePlateau, modele))
    }
}