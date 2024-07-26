package controleur

import vue.VueRegle
import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.PickominoGame

class ControleurBoutonPrecedant(vue : VueRegle) : EventHandler<ActionEvent> {

    val vue : VueRegle

    init {
        this.vue = vue
    }
    override fun handle(event: ActionEvent) {
        if (vue.currentPageIndex > 0) {
            vue.currentPageIndex--
            vue.borderPane.center = vue.ruleImageViews[vue.currentPageIndex]
            vue.pageNumber.text = " ${vue.currentPageIndex + 1}/${vue.ruleImageViews.size}"
        }
    }
}