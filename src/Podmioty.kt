abstract class przedmiot() {
    open var Nazwa="Nazwa"
    open var Cena=0
    open var Opis:String=""
    abstract fun Wyswietl_opis()
    abstract fun Wyswietl_nazwy()

}
open class procesy():przedmiot() {
    var ID:Int=0
    override fun Wyswietl_opis() {
        println("Proces: $Nazwa")
        println("   Cena: $Cena")
        //println("Opis: $Opis")
    }

    override fun Wyswietl_nazwy() {
        println("Proces: $Nazwa")
    }
}
open class zasoby():przedmiot() {
    var Koszt_staly=0
    var Koszt_zmienny=0
    var Wydajnosc=0
    var ID:Int=0
    override fun Wyswietl_opis() {
        println("Zasób: $Nazwa")
        println("   Cena: $Cena")
        //println("Opis: $Opis")
        println("   Koszt stały utrzymania maszyny: $Koszt_staly")
        println("   Koszt utrzymania pracującej maszyny: $Koszt_zmienny")
        println("   Wydajność: $Wydajnosc na turę")
    }

    override fun Wyswietl_nazwy() {
        println("Zasób: $Nazwa")
    }
}
open class materialy():przedmiot() {

    var ID:Int=0
    override fun Wyswietl_opis() {
        println("Materiał: $Nazwa")
        println("   Cena: $Cena")
        //println("Opis: $Opis")
    }

    override fun Wyswietl_nazwy() {
        println("Materiał: $Nazwa")
    }
}
open class produkty():przedmiot() {
    var materialy = Array(20, { 0 })
    var zasoby = Array(20, { 0 })
    var procesy = Array(20, { 0 })
    var ID: Int = 0
    var Ile_produktow_splaci_inwestycje=0.0
    fun Policz_oplacalnosc(procesy_info:List<procesy>,zasoby_info:List<zasoby>,materialy_info:List<materialy>
    ,Ile_procesow:Int,Ile_zasobow:Int,Ile_materialow:Int):Double{
        var Cena_wynik:Double
        var Cena_procesy=0.0
        var Cena_zasoby=0.0
        var Cena_materialy=0.0
        for (Ktory_proces in 0..Ile_procesow){
            if (procesy[Ktory_proces]==1){
                Cena_procesy+=procesy_info[Ktory_proces].Cena
                //println("Cena_procesy ${Cena_procesy}")
            }
        }
        for(Ktory_zasob in 0..Ile_zasobow){
            if(zasoby[Ktory_zasob]!=0){
                Cena_zasoby+=zasoby_info[Ktory_zasob].Cena*zasoby[Ktory_zasob]
                //println("Cena_zasoby ${Cena_zasoby}")
            }
        }
        for(Ktory_material in 0..Ile_materialow){
            if(materialy[Ktory_material]!=0){
                Cena_materialy+=materialy_info[Ktory_material].Cena*materialy[Ktory_material]
                //println("Cena_materialy ${Cena_materialy}")
            }
        }
        Cena_wynik=(Cena_procesy+Cena_zasoby)/(Cena-Cena_materialy)
        //println("Oplacalnosc produktu $Nazwa wynosi $Cena_wynik")
        Ile_produktow_splaci_inwestycje=Cena_wynik
        return Cena_wynik
    }
    override fun Wyswietl_opis() {
        println("Produkt: $Nazwa")
        println("   Sugerowana cena: $Cena")
        //println("Opis: $Opis")
    }
    override fun Wyswietl_nazwy() {
        println("Produkt: $Nazwa")
    }
}