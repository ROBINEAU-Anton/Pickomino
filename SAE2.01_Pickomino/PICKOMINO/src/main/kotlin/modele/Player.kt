package modele

class Player (id : Int,pseudo : String){

    private val id : Int
    private var pseudo : String?

    init {
        this.id = id
        this.pseudo = pseudo
    }

    fun getId() : Int {
        return this.id
    }

    fun  getName() : String? {
        return this.pseudo
    }

    fun changepseudo(newPseudo : String?) {
        pseudo = newPseudo
    }
    
}