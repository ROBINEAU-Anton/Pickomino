package controleur

import vue.VueRegle
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.PickominoGame

class ControleurBoutonSuivant(vue : VueRegle) : EventHandler<ActionEvent> {

    val vue : VueRegle

    init {
        this.vue = vue
    }
    override fun handle(event: ActionEvent) {
        if (vue.currentPageIndex < vue.ruleImageViews.size - 1) {
            vue.currentPageIndex++
            vue.borderPane.center = vue.ruleImageViews[vue.currentPageIndex]
            vue.pageNumber.text = " ${vue.currentPageIndex + 1}/${vue.ruleImageViews.size}"
        }
    }
}