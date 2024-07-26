import controleur.*
import modele.*
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import vue.*

class Main : Application() {

    override fun start(stage: Stage) {

        //Création des vues
        val vueAccueil = VueAccueil(stage)
        val vueCreationPartie = VueCreationPartie(stage)
        val vuePlateau = VuePlateau(stage)
        val vueRegle = VueRegle(stage)
        val vueOption = VueOption(stage)

        //Création des scenes
        val sceneAccueil = Scene(vueAccueil,500.0,800.0)
        val sceneCreationPartie = Scene(vueCreationPartie,500.0,800.0)
        val scenePlateau = Scene(vuePlateau,500.0,800.0)
        val sceneRegle = Scene(vueRegle,500.0,800.0)
        val sceneOption = Scene(vueOption,500.0,800.0)

        //Création du modele
        val modele = PickominoGame()

        //Ajout des controleurs au vue
        vueAccueil.fixeControleurBoutonJouer(ControleurBoutonJouer(stage, sceneCreationPartie))
        vueAccueil.fixeControleurBoutonRegles(ControleurBoutonRegles(stage, sceneRegle))
        vueAccueil.fixeControleurBoutonOptions(ControleurBoutonOption(stage, sceneOption))
        vueAccueil.fixeControleurBoutonQuitter(ControleurBoutonQuitter())

        vueCreationPartie.fixeControleurBoutonAjouterJoueur(ControleurBoutonAjouterJoueur(modele, vueCreationPartie))
        vueCreationPartie.fixeControleurBoutonRetirerJoueur(ControleurBoutonRetirerJoueur(modele, vueCreationPartie))
        vueCreationPartie.fixeControleurBoutonRetour(ControleurBoutonRetour(stage, sceneAccueil))
        vueCreationPartie.fixeControleurBoutonStart(ControleurBoutonStart(stage, vueCreationPartie, scenePlateau, modele, vuePlateau))

        vueRegle.fixeControleurBoutonRetour(ControleurBoutonRetour(stage, sceneAccueil))
        vueRegle.fixeControleurBoutonPrecedant(ControleurBoutonPrecedant(vueRegle))
        vueRegle.fixeControleurBoutonSuivant(ControleurBoutonSuivant(vueRegle))

        vueOption.fixeControleurBoutonRetour(ControleurBoutonRetour(stage, sceneAccueil))

        vuePlateau.fixeControleurBoutonLancer(ControleurBoutonLancer(modele,vuePlateau))
        vuePlateau.fixeControleurPickominos(ControleurPickominos(vuePlateau, modele))

        //Ajout d'un lien vers le fichier css contenant la police personalisée
        vueAccueil.stylesheets.add("file:PICKOMINO/src/main/resources/style.css")
        vueCreationPartie.stylesheets.add("file:PICKOMINO/src/main/resources/style.css")
        vueRegle.stylesheets.add("file:PICKOMINO/src/main/resources/style.css")
        vueOption.stylesheets.add("file:PICKOMINO/src/main/resources/style.css")
        vuePlateau.stylesheets.add("file:PICKOMINO/src/main/resources/style.css")

        //Lancement de la fenetre avec la scène d'acceuil et le titre Pickomino
        stage.title = "Pickomino"
        stage.scene=sceneAccueil
        stage.show()
    }
}

fun main(){
    Application.launch(Main::class.java)
}