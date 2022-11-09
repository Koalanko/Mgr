import kotlin.random.Random

class Zlecenia(Nagroda:Int) {

    var Nagroda = Nagroda
    var Zlecenie =Array(10) {0}
    var Zarchiwizowane = false
    var Przyjete = false
    var Aktywne=false
    var Ktos_licytuje=false
    var ID_zlecenia=0
    var Zwyciezca=gracz("",0,0)
    var Ile_ofert=-1
    var Oferty= List(20) {oferta(gracz("",0,0),0)}
    var Termin=0
    var Oplacalnosc=0.0
    var Nagroda_netto=0

    class oferta(Gracz:gracz,Kwota:Int){
        var Gracz=Gracz
        var Kwota=Kwota
    }

    fun Utworz_zlecenie(Aktualna_tura:Int,Ile_produktow:Int,Produkty: List<produkty>,Ile_zlecen:Int,Termin_min:Int,Termin_max:Int,Oplacalnosc_min:Double,Oplacalnosc_max:Double,min_produktu_w_zleceniu:Int,max_produktu_w_zleceniu:Int){
        Termin=(Termin_min..Termin_max).random()
        Oplacalnosc= Random.nextDouble(Oplacalnosc_min,Oplacalnosc_max)/Termin+1
        //println("OPLACALNOSC: $Oplacalnosc")
        ID_zlecenia=Ile_zlecen
        var Produkty_do_zlecenia=Array(10) { 0 }
        var Cena=0
        var Czy_wylosowano_jakikolwiek_produkt=false
        while(Czy_wylosowano_jakikolwiek_produkt==false) {
            for (i in 0..Ile_produktow) {
                if (Random.nextBoolean()) {
                    Czy_wylosowano_jakikolwiek_produkt = true
                    Produkty_do_zlecenia[i] = (min_produktu_w_zleceniu..max_produktu_w_zleceniu).random()
                    Cena += Produkty_do_zlecenia[i] * Produkty[i].Cena
                }
            }
        }
        var CenaDouble=Cena.toDouble()
        Nagroda_netto=Cena
        CenaDouble *= Oplacalnosc
        Cena=CenaDouble.toInt()
        Zlecenie=Produkty_do_zlecenia
        Nagroda=Cena
        Aktywne=true
        Termin += Aktualna_tura
        Nagroda_netto=Nagroda
    }
    fun Dodaj_oferte(gracz: gracz,Kwota: Int){
        if (Kwota<Nagroda) {
            Ile_ofert++
            Oferty[Ile_ofert].Gracz = gracz
            Oferty[Ile_ofert].Kwota = Kwota
            Ktos_licytuje=true
        }
        else
            println("Kwota jest za duża! Zalicytuj za mniej niż $Nagroda")
    }
    fun Zakoncz_licytacje(){
    val Remis=Array(20){0}
    var Ile_remisow=0
    var Jakis_remis=false
        var Poprzednie_id=0
        var Najmniejsza_kwota = 999999999
        var Id_oferty = -1
        for (i in 0..Ile_ofert) {
            if(Najmniejsza_kwota==Oferty[i].Kwota){
                if(Ile_remisow==0) {
                    Remis[Ile_remisow] = Id_oferty
                   // println("Oferta ${ID_zlecenia} REMIS id oferty ${Remis[Ile_remisow]}")
                }
                    Ile_remisow++
                Id_oferty=i
                Remis[Ile_remisow]=Id_oferty
               // println("Oferta2 ${ID_zlecenia} REMIS id oferty ${Remis[Ile_remisow]}")
            }
            if (Najmniejsza_kwota > Oferty[i].Kwota) {
                Najmniejsza_kwota = Oferty[i].Kwota
                Id_oferty = i
                Ile_remisow=0
               // println("Oferta ${ID_zlecenia} NIE REMIS")
            }
        }
        if(Ile_remisow!=0){
            val Zwycieski_remis=Random.nextInt(0,Ile_remisow+1)
            Id_oferty=Remis[Zwycieski_remis]
           // println("Oferta ${ID_zlecenia} Rozwiazanie remisu Id oferty: ${Id_oferty}")
        }
        Zwyciezca = Oferty[Id_oferty].Gracz
        //println("Zwyciezyl gracz ${Zwyciezca.Nazwa}")
        Nagroda = Najmniejsza_kwota
        Przyjete = true

    }
    fun Wyswietl_opis(Produkty:List<produkty>,Ile: Int) {
        println()
        println("Numer zlecenia: $ID_zlecenia")
        println("Nagroda: $Nagroda")
        println("Termin: $Termin")
        println("Wymagania: ")
        for (i in 0..Ile) {
            if (Zlecenie[i] != 0) {
                Produkty[i].Wyswietl_nazwy()
                println("   x${Zlecenie[i]}")
            }
        }
    }
}