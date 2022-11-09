import com.sun.jdi.BooleanType
import java.util.function.IntToDoubleFunction
import java.util.function.ToDoubleFunction
import kotlin.random.Random

class Swiat()
{
    var Zlecenia= List(900) {Zlecenia(0)}
    //Parametry swiata
    val Ilosc_rozgrywek=999
    var Majatek_poczatkowy=40000
    val Dlugosc_nauki=499999
    val Termin_kara=4       //Dzielnik kwoty kary, ktora ponosi gracz za przekroczenie terminu
    val Ws_zasoby=0.8      //Wspolczynnik wartosci koncowej procesow i maszyn
    val Ws_mat_prod=0.6     //Wspolczynnik wartosci koncowej produktow i materialow
    var Ile_tur=30     //Ile tur ma trwac rogrywka
    val Oplacalnosc_zlecenia_min=0.0
    val Oplacalnosc_zlecenia_max=1.0
    var Ile_max_zlecen_co_ture=8
    val Ile_min_zlecen_co_ture=1
    val Min_produktu_w_zleceniu=199
    var Max_produktu_w_zleceniu=200
    val Termin_zlecenia_min=2
    val Termin_zlecenia_max=6
    val Przedzial_losowej_kwoty_licytacji_min=0.9
    val Przedzial_losowej_kwoty_licytacji_max=1.0
    val Kara_za_debet=10 //if(Gotowka<0)  Gotowka=Gotowka+Gotowka/Kara_za_debet
    val Bot_rok_zwiekszenia_wydajnosci=100

    var Scenariusz=""
    var Ile_procesow=0
    var Ile_zasobow=0
    var Ile_materialow=0
    var Ile_produktow=0
    var Ile_zlecen=-1
    var Ile_graczy=0
    var Aktualna_tura=0

    var Przyjete_zlecenia=Array(10,{0})
    var Uplyw_terminu=Array(10,{0})
    val Ilosc_zwyciestw=Array(10){0}
    val Staratna_rozgrywka=Array(10){0}
    val Zyskowna_rozgrywka=Array(10){0}
    val Procesy= List(20) {procesy()}
    val Zasoby=List(20) {zasoby()}
    val Materialy=List(20) {materialy()}
    val Produkty=List(20) {produkty()}
    val Gracze=List(10) {gracz("pierwszy",0,0)}
    var Sr_il_prod=Array(10,{0.0})
    var siec_wydajnosc=siec()
    var siec_swiat=siec()
    var siec_licytacje=siec()
    var Najgorsza_rozgrywka=Array(10,{0.0})
    var Najlepsza_rozgrywka=Array(10,{0.0})

    fun Licytacje_bot_losowa_kwota(Ktory_gracz: Int){
        var Czy_produkty_ujemne=false
        var Bilans_produktu=Array(2,{0})
        for(ktory_produkt in 0..Ile_produktow){
            Bilans_produktu[ktory_produkt]=Gracze[Ktory_gracz].Produkty_gracz[ktory_produkt].Ilosc-Gracze[Ktory_gracz].Produkty_w_zleceniach[ktory_produkt]
            if(Bilans_produktu[ktory_produkt]<0){
                Czy_produkty_ujemne=true
            }
        }

        for(Ktore_zlecenie in 0..Ile_zlecen) {
            for (ktory_produkt in 0..Ile_produktow) {
                Bilans_produktu[ktory_produkt] =
                    Gracze[Ktory_gracz].Produkty_gracz[ktory_produkt].Ilosc - Gracze[Ktory_gracz].Produkty_w_zleceniach[ktory_produkt]
                if (Bilans_produktu[ktory_produkt] < 0) {
                    Czy_produkty_ujemne = true
                }
            }
            if (Czy_produkty_ujemne == false) {
                if (Zlecenia[Ktore_zlecenie].Aktywne) {
                    var Kwota = Zlecenia[Ktore_zlecenie].Nagroda_netto.toDouble() * Random.nextDouble(
                        Przedzial_losowej_kwoty_licytacji_min,
                        Przedzial_losowej_kwoty_licytacji_max
                    )
                    var Kwota2 = Kwota.toInt()
                    if (Zlecenia[Ktore_zlecenie].Nagroda_netto <= Kwota2) {
                        Kwota2 = Zlecenia[Ktore_zlecenie].Nagroda_netto - 1
                    }
                    Zlecenia[Ktore_zlecenie].Dodaj_oferte(Gracze[Ktory_gracz], Kwota2)
                    // println("Do zlecenia $Ktore_zlecenie Dodano oferte $Kwota2 przez gracza ${Gracze[Ktory_gracz].Nazwa}")
                }
            }
        }
    }
    fun AI_licytacje_bot_losowa_kwota(Ktory_gracz: Int){
        for(Ktore_zlecenie in 0..Ile_zlecen){
            if (Zlecenia[Ktore_zlecenie].Aktywne) {
            if(Gracze[Ktory_gracz].AI_czy_licytowac(siec_licytacje,Zlecenia[Ktore_zlecenie].Zlecenie[0],Zlecenia[Ktore_zlecenie].Zlecenie[1],(Zlecenia[Ktore_zlecenie].Termin-Aktualna_tura)))
                {

                var Kwota = Zlecenia[Ktore_zlecenie].Nagroda_netto.toDouble() * Random.nextDouble(
                    Przedzial_losowej_kwoty_licytacji_min,
                    Przedzial_losowej_kwoty_licytacji_max
                )
                var Kwota2 = Kwota.toInt()
                if (Zlecenia[Ktore_zlecenie].Nagroda_netto <= Kwota2) {
                    Kwota2 = Zlecenia[Ktore_zlecenie].Nagroda_netto - 1
                }
                Zlecenia[Ktore_zlecenie].Dodaj_oferte(Gracze[Ktory_gracz], Kwota2)
               // println("Do zlecenia $Ktore_zlecenie Dodano oferte $Kwota2 przez gracza ${Gracze[Ktory_gracz].Nazwa}")
            }
            }
        }
    }
    fun Licytacje_bot_wspolczynnik_kwota(Ktory_gracz: Int){
        for(Ktore_zlecenie in 0..Ile_zlecen){
            if (Zlecenia[Ktore_zlecenie].Aktywne){
                var Kwota=Zlecenia[Ktore_zlecenie].Nagroda_netto.toDouble()*Gracze[Ktory_gracz].Wspolczynnik_kwoty_licytacji
                var Kwota2=Kwota.toInt()
                if(Zlecenia[Ktore_zlecenie].Nagroda_netto<=Kwota2) {
                    Kwota2 = Zlecenia[Ktore_zlecenie].Nagroda_netto - 1
                }
                Zlecenia[Ktore_zlecenie].Dodaj_oferte(Gracze[Ktory_gracz],Kwota2)
               // println("Do zlecenia $Ktore_zlecenie Dodano oferte $Kwota2 przez gracza ${Gracze[Ktory_gracz].Nazwa}")
            }
        }
    }
    fun Bot_tura(ktory:Int) {
        Gracze[ktory].Bot_kup_zasoby(Ile_procesow,Procesy,Ile_zasobow,Produkty,Zasoby,Ile_produktow,Bot_rok_zwiekszenia_wydajnosci)
        Gracze[ktory].Aktualizuj_stale_zlecenia(Produkty,Zasoby,Ile_produktow,Ile_zasobow)
        Gracze[ktory].Dodaj_stale_zlecenia_do_produkcji(Ile_produktow,Ile_materialow,Produkty,Materialy)
/*
var obj=99
              while (obj!=0)
              {
                  println("0->Kolejna tura\n" +
                          "1->Sklep\n" +
                          "2->Statystyki gracza\n" +
                          "7->Pokaz zlecenia stale\n")
                  when(Wprowadz_liczbe())
                  {
                      0->obj=0
                      2-> Statystyki(ktory)
                      7->Gracze[ktory].Pokaz_stale_zlecenia(Produkty,Ile_produktow)
                      else -> println("Nieprawidłowa operacja")
                  }
              }
*/
        Sprawdz_terminy_zlecen_gracza(ktory)
        Wykonaj_zlecenie_bot(ktory)
        Gracze[ktory].Policz_produkty_w_zleceniach(Ile_zlecen,Zlecenia,Ile_produktow)
        Licytacje_bot_losowa_kwota(ktory)
    }
    fun AI_bot_tura(ktory: Int){
        var Ak_wydajnosc=Array(2){0}
        Sprawdz_terminy_zlecen_gracza(ktory)
        Wykonaj_zlecenie_bot(ktory)
        Gracze[ktory].Policz_produkty_w_zleceniach(Ile_zlecen,Zlecenia,Ile_produktow)
        Ak_wydajnosc[0]=Gracze[ktory].Produkty_gracz[0].Wydajnosc_produkcji
        Ak_wydajnosc[1]=Gracze[ktory].Produkty_gracz[1].Wydajnosc_produkcji
        Ak_wydajnosc=siec_wydajnosc.ustalenie_aktualnej_wydajnosci(Gracze[ktory].Gotowka,Aktualna_tura,Ile_tur,Ak_wydajnosc,siec_swiat.max_wydajnosc_prod1[0])
        //println("wyd1 ${Ak_wydajnosc[0]}, wyd2 ${Ak_wydajnosc[1]}")
        Gracze[ktory].AI_kup_procesy(Procesy,Ile_procesow)
        Gracze[ktory].AI_kup_zasoby(Ile_zasobow,Produkty,Zasoby,Ile_produktow,Ak_wydajnosc)
        Gracze[ktory].Aktualizuj_stale_zlecenia(Produkty,Zasoby,Ile_produktow,Ile_zasobow)
        Gracze[ktory].Dodaj_stale_zlecenia_do_produkcji(Ile_produktow,Ile_materialow,Produkty,Materialy)
        AI_licytacje_bot_losowa_kwota(ktory)
/*
        var obj=99
        while (obj!=0)
        {
            println("0->Kolejna tura\n1->Sklep\n2->Statystyki gracza\n3->Informacje o świecie\n4->Zleć produkcję\n5->Zlecenia\n6->Wykonaj zlecenie\n-1->Operacja testowa")
            when(Wprowadz_liczbe())
            {
                -1->Utworz_zlecenie(1)
                0->obj=0
                1-> Sklep(ktory)
                2-> Statystyki(ktory)
                3-> Informacje()
                4-> Zlec_produkcje(ktory)
                5-> Licytacje(ktory)
                6-> Wykonaj_zlecenie(ktory)
                else -> println("Nieprawidłowa operacja")
            }
        }
*/

    }
    fun Wyswietl_nazwy(przedmiot:List<przedmiot>,Ile: Int,Z_indeksem: Boolean){
        for (i in 0..Ile){
            if(Z_indeksem==true)
            print("$i: ")
            przedmiot[i].Wyswietl_nazwy()
        }
    }
    fun Wyswietl_informacje(przedmiot: List<przedmiot>,Ile: Int,Z_indeksem:Boolean){
        for(i in 0..Ile){
            if(Z_indeksem==true)
                print("$i: ")
            przedmiot[i].Wyswietl_opis()
        }
    }
    fun Statystyki(Ktory_gracz: Int) {
        print("Imię: ")
        println(Gracze[Ktory_gracz].Nazwa)
        print("Gotówka: ")
        println(Gracze[Ktory_gracz].Gotowka)
        println("\nProcesy: ")
        for (i in 0..Ile_procesow)
        {
            print(Procesy[i].Nazwa)
            print("   ilość:  ")
            print(Gracze[Ktory_gracz].Procesy[i])
            println()
        }
        println("\nMateriały:  ")
        for (i in 0..Ile_materialow)
        {
            print(Materialy[i].Nazwa)
            print("   ilość:  ")
            print(Gracze[Ktory_gracz].Materialy[i])
            println()
        }
        println("Materiały zarezerwowane do produkcji")
        for(i in 0..Ile_materialow)
        {
            print(Materialy[i].Nazwa)
            print("   ilość:  ")
            print(Gracze[Ktory_gracz].Materialy_zarezerwowane[i])
            println()
        }
        println("\nZasoby:  ")
        for (i in 0..Ile_zasobow)
        {
            print(Zasoby[i].Nazwa)
            print("   ilość:  ")
            print(Gracze[Ktory_gracz].Zasoby_gracz[i].Ilosc)
            println()
        }
        println("\nProdukty: ")
        for(i in 0..Ile_produktow)
        {
            print(Produkty[i].Nazwa)
            print("   ilość:  ")
            print(Gracze[Ktory_gracz].Produkty_gracz[i].Ilosc)
            println()
        }
        println()
        println("Produkty w produkcji: ")
        for(i in 0..Ile_produktow)
        {
            print(Produkty[i].Nazwa)
            print("   ilość:  ")
            print(Gracze[Ktory_gracz].Produkty_predykcyjne[i])
            println()
        }
        println()
        println("Zlecenia: ")
        Wyswietl_zlecenia_gracza(Ktory_gracz)
    }
    fun Wyswietl_zlecenia_gracza(Ktory_gracz: Int):Boolean{
    var a=false
    for(i in 0..Ile_zlecen){
        if (Gracze[Ktory_gracz].Zlecenia[i]==1){
            Zlecenia[i].Wyswietl_opis(Produkty,Ile_produktow)
            a=true
        }
    }
    return a
}
    fun Zaplac(Ktory_gracz: Int,Przedmiot: Int,Nr_przedmiotu: Int,Ile: Int) {

        var Kwota=0
        if (Przedmiot==1)
            Kwota=Procesy[Nr_przedmiotu].Cena
        if (Przedmiot==2)
            Kwota=Zasoby[Nr_przedmiotu].Cena*Ile
        if (Przedmiot==3)
            Kwota=Materialy[Nr_przedmiotu].Cena*Ile

        Gracze[Ktory_gracz].Gotowka=Gracze[Ktory_gracz].Gotowka-Kwota

    }
    fun Dodaj(Przedmiot:Int,Nr_przedmiotu:Int,Ile:Int,Ktory_gracz: Int){
        if (Przedmiot==1)
            Gracze[Ktory_gracz].Dodaj_proces(Nr_przedmiotu)
        if (Przedmiot==2)
            Gracze[Ktory_gracz].Dodaj_zasoby(Nr_przedmiotu,Ile)
        if (Przedmiot==3)
            Gracze[Ktory_gracz].Dodaj_materialy(Nr_przedmiotu,Ile)
        if(Przedmiot==4)
            Gracze[Ktory_gracz].Dodaj_Produkty(Nr_przedmiotu,Ile)
    }
    fun Kup(obj:Int,Ktory_gracz:Int){
        if(obj==1)
        {
            println("Podaj nr procesu")
            for(i in 0..Ile_procesow){
                println("$i -> ${Procesy[i].Nazwa}")
            }
            val Nr_procesu=Wprowadz_liczbe()
            print("Przed: ")
            println(Gracze[Ktory_gracz].Procesy[Nr_procesu])
            Dodaj(obj,Nr_procesu,1,Ktory_gracz)
            Zaplac(Ktory_gracz,obj,Nr_procesu,1)
            print("Po: ")
            println(Gracze[Ktory_gracz].Procesy[Nr_procesu])
        }
        if(obj==2)
        {
            println("Podaj nr zasobu")
            for(i in 0..Ile_zasobow){
                println("$i -> ${Zasoby[i].Nazwa}")
            }
            val Nr_zasobu:Int=Wprowadz_liczbe()
            println("Podaj Ilosc zasobu")
            val Ile_zasobu:Int=Wprowadz_liczbe()
            print("Przed: ")
            println(Gracze[Ktory_gracz].Zasoby_gracz[Nr_zasobu].Ilosc)
            Dodaj(obj,Nr_zasobu,Ile_zasobu,Ktory_gracz)
            Zaplac(Ktory_gracz,obj,Nr_zasobu,Ile_zasobu)
            print("Po: ")
            println(Gracze[Ktory_gracz].Zasoby_gracz[Nr_zasobu].Ilosc)
        }
        if(obj==3)
        {
            println("Podaj Nr materiału")
            for(i in 0..Ile_materialow){
                println("$i -> ${Materialy[i].Nazwa}")
            }
            val Nr_materialu:Int=Wprowadz_liczbe()
            println("Podaj ilosc materialu")
            val Ile_materialu:Int=Wprowadz_liczbe()
            print("Przed: ")
            println(Gracze[Ktory_gracz].Materialy[Nr_materialu])
            Dodaj(obj,Nr_materialu,Ile_materialu,Ktory_gracz)
            Zaplac(Ktory_gracz,obj,Nr_materialu,Ile_materialu)
            print("Po: ")
            println(Gracze[Ktory_gracz].Materialy[Nr_materialu])

        }

    }
    fun Sklep(ktory_gracz: Int) {

        println("~~~~Sklep~~~~\n1 - Kup proces\n2 - Kup Zasob\n3 - Kup Material")
        when(Wprowadz_liczbe())
        {
            1->Kup(1,ktory_gracz)
            2->Kup(2,ktory_gracz)
            3->Kup(3,ktory_gracz)
            else -> println("Nieprawidłowa operacja")
        }
    }
    fun Informacje() {
        println("~~~~~~Informacje o grze~~~~~~")
        println("Scenariusz: ${Scenariusz}")
        println("Aktualna tura: ${Aktualna_tura}")
        println("Ilość tur: ${Ile_tur}")
        println(" ")
        var obiekt: przedmiot
        Wyswietl_informacje(Procesy,Ile_procesow,false)
        println()
        Wyswietl_informacje(Materialy,Ile_materialow,false)
        println()
        Wyswietl_informacje(Zasoby,Ile_zasobow,false)
        println()
        for (i in 0..Ile_produktow) {
            println()
            obiekt = Produkty[i]
            obiekt.Wyswietl_opis()
            println("   Potrzebne Procesy: ")
            for (j in 0..Ile_procesow) {
                if (Produkty[i].procesy[j] != 0) {
                    println("     ${Procesy[j].Nazwa}  x${Produkty[i].procesy[j]}")
                }
            }

            println("   Potrzebne maszyny: ")
            for (j in 0..Ile_zasobow) {
                if (Produkty[i].zasoby[j] != 0) {
                    println("     ${Zasoby[j].Nazwa}  x${Produkty[i].zasoby[j]}")
                }
            }

            println("   Potrzebne materiały: ")
            for (j in 0..Ile_materialow) {
                if (Produkty[i].materialy[j] != 0) {
                    println("     ${Materialy[j].Nazwa}  x${Produkty[i].materialy[j]}")
                }
            }
        }
    }
    fun Zlec_produkcje(Ktory_gracz: Int):Int{
        println("Ktory produkt chcesz wyprodukować: ")
        var obiekt=Produkty
        Wyswietl_nazwy(obiekt,Ile_produktow,true)
        var Ktory_produkt=Wprowadz_liczbe()
        println("Ile produktów chcesz wyprodukować: ")
        var Ile=Wprowadz_liczbe()
        var Ile_materialu:Int
        val Czy_posiada_materialy=Czy_dostepne_materialy_dla_produktu(Ktory_gracz,Ktory_produkt,Ile)
        if(Czy_posiada_materialy==-1) {
            //Dodaj_zarezerwowane_materialy(Ktory_gracz, Ktory_produkt, Ile)
            Dodaj_materialy(Ktory_gracz, Ktory_produkt, -Ile)
            Dodaj_zarezerwowane_materialy(Ktory_gracz,Ktory_produkt,Ile)
            Gracze[Ktory_gracz].Dodaj_produkty_predykcyjne(Ktory_produkt, Ile)
        }
        else{
            println("Nie można zlecić produkcji ${Produkty[Ktory_produkt].Nazwa} z powodu braku materiału ${Materialy[Czy_posiada_materialy].Nazwa}")
        }
        return Ile
    }
    fun Dodaj_zarezerwowane_materialy(Ktory_gracz: Int,Ktory_produkt:Int,Ile:Int){
        for (i in 0..Ile_materialow){
            if(Produkty[Ktory_produkt].materialy[i]!=0){
                var Ile_materialu=Produkty[Ktory_produkt].materialy[i]*Ile

                Gracze[Ktory_gracz].Dodaj_zarezerwowane_materialy(i,Ile_materialu)
            }
        }
    }
    fun Czy_dostepne_materialy_dla_produktu(Ktory_gracz: Int,Ktory_produkt:Int,Ile:Int):Int{
        for (Ktory_material in 0..Ile_materialow){
            if(Produkty[Ktory_produkt].materialy[Ktory_material]!=0){
                var Ile_materialu=Produkty[Ktory_produkt].materialy[Ktory_material]*Ile
                var Material_gracza=Gracze[Ktory_gracz].Materialy[Ktory_material]
                if((Material_gracza-Ile_materialu)<0){
                    return Ktory_material
                }

            }
        }
        return -1
    }
    fun Dodaj_materialy(Ktory_gracz: Int,Ktory_produkt:Int,Ile:Int){
        for (i in 0..Ile_materialow){
            if(Produkty[Ktory_produkt].materialy[i]!=0){
                var Ile_materialu=Produkty[Ktory_produkt].materialy[i]*Ile
                Gracze[Ktory_gracz].Dodaj_materialy(i,Ile_materialu)
            }
        }

    }
    fun Wykonaj_produkcje():Boolean {
        var blad=false
        for (Ktory_gracz in 0..Ile_graczy) {
            for (Ktory_produkt in 0..Ile_produktow) {
                for(Ktory_proces in 0..Ile_procesow){
                    if(Gracze[Ktory_gracz].Produkty_predykcyjne[Ktory_produkt]!=0)
                    if(Produkty[Ktory_produkt].procesy[Ktory_proces]==1 && Gracze[Ktory_gracz].Procesy[Ktory_proces]==0){
                        println("Gracz nie posiada procesu ${Procesy[Ktory_proces].Nazwa} aby rozpocząć produkcję ${Produkty[Ktory_produkt].Nazwa}")
                        return false
                    }
                } //Sprawdz czy gracz posiada proces
                var Ile = Gracze[Ktory_gracz].Produkty_predykcyjne[Ktory_produkt]
                if (Ile != 0) {
                    for (Ktory_zasob in 0..Ile_zasobow) {
                        if (Produkty[Ktory_produkt].zasoby[Ktory_zasob] != 0) {
                            if(Produkty[Ktory_produkt].zasoby[Ktory_zasob]<=Gracze[Ktory_gracz].Zasoby_gracz[Ktory_zasob].Ilosc) {
                                var Wydajnosc =
                                    (Gracze[Ktory_gracz].Zasoby_gracz[Ktory_zasob].Ilosc * Zasoby[Ktory_zasob].Wydajnosc) / Produkty[Ktory_produkt].zasoby[Ktory_zasob]
                                if (Wydajnosc < Ile) {
                                        Ile = Wydajnosc
                                    println("Niewystarczająca liczba maszyn: ${Zasoby[Ktory_zasob].Nazwa} ogranicza produkcję: ${Produkty[Ktory_produkt].Nazwa}")
                                }
                            }
                            else{
                                println("Zbyt mało zasobu ${Zasoby[Ktory_zasob].Nazwa} aby rozpocząć produkcję ${Produkty[Ktory_produkt].Nazwa}")
                                return false
                            }
                        }
                    }
                }
                Gracze[Ktory_gracz].Wlacz_odpowiednia_liczbe_maszyn(Zasoby,Produkty,Ile,Ile_zasobow,Ktory_produkt)
                if(blad==false) {
                    Gracze[Ktory_gracz].produkuj(Ktory_produkt, Ile)
                    Dodaj_zarezerwowane_materialy(Ktory_gracz, Ktory_produkt, -Ile)
                }
                blad=false
            }
        }
        return true
    }
    fun Pokaz_zlecenia(index:Boolean):Boolean{
        var pusty=true
        for (i in 0..Ile_zlecen) {
            if (Zlecenia[i].Aktywne == true) {
                if (index) print("$i ")
                Zlecenia[i].Wyswietl_opis(Produkty, Ile_produktow)
                pusty=false
            }
        }
        return pusty
    }
    fun Zakoncz_licytacje(){
    for (i in 0..Ile_zlecen){
        if (Zlecenia[i].Ktos_licytuje) {
            Zlecenia[i].Zakoncz_licytacje()
            Przydziel_zlecenie(Zlecenia[i].Zwyciezca.ID,i)
            Zlecenia[i].Ktos_licytuje=false
            Przyjete_zlecenia[Zlecenia[i].Zwyciezca.ID]++
        }
    }
}
    fun Przydziel_zlecenie(Ktory_gracz:Int,Ktore_zlecenie:Int){
        Gracze[Ktory_gracz].Dodaj_zlecenie(Ktore_zlecenie)
        Zlecenia[Ktore_zlecenie].Aktywne=false
    }
    fun Zrealizuj_zlecenie(Ktory_gracz: Int,Ktore_zlecenie: Int) {
        var blad=false
        for (i in 0..Ile_produktow) {
            if (Zlecenia[Ktore_zlecenie].Zlecenie[i] != 0) {
                if (Gracze[Ktory_gracz].Produkty_gracz[i].Ilosc - Zlecenia[Ktore_zlecenie].Zlecenie[i] < 0) {
                    //println("Za mało produktu ${Produkty[i].Nazwa}")
                    blad = true
                }
            }
        }
        if(blad==false) {
            for (i in 0..Ile_produktow) {
                if (Zlecenia[Ktore_zlecenie].Zlecenie[i] != 0) {
                    Gracze[Ktory_gracz].Produkty_gracz[i].Ilosc =
                        Gracze[Ktory_gracz].Produkty_gracz[i].Ilosc - Zlecenia[Ktore_zlecenie].Zlecenie[i]
                }
            }
            Gracze[Ktory_gracz].Gotowka = Gracze[Ktory_gracz].Gotowka + Zlecenia[Ktore_zlecenie].Nagroda
            Gracze[Ktory_gracz].Zlecenia[Ktore_zlecenie]=0
            //println("Zrealizowano zlecenie $Ktore_zlecenie")
        }
    }
    fun Licytacje(ktory: Int){
        println("0->Pokaż zlecenia\n1->Zalicytuj")
        var wybor=Wprowadz_liczbe()
        var wybor2:Int
        if(wybor==0){
            Pokaz_zlecenia(false)
        }
        if (wybor==1) {
            if (!Pokaz_zlecenia(false)) {
                println("Wybierz licytację w której chcesz wziąć udział:")
                wybor = Wprowadz_liczbe()
                println("Podaj kwotę jaką chcesz zalicytować:")
                wybor2 = Wprowadz_liczbe()
                Zlecenia[wybor].Dodaj_oferte(Gracze[ktory], wybor2)
            }
            else{
                println("Aktualnie nie ma żadnych ofert")
            }
        }
    }
    fun Wykonaj_zlecenie(Ktory_gracz: Int){
        if(Wyswietl_zlecenia_gracza(Ktory_gracz)==true) {
            var Ktore_zlecenie = Wprowadz_liczbe()
            Zrealizuj_zlecenie(Ktory_gracz, Ktore_zlecenie)
        }
        else
            println("Gracz nie posiada żadnych zleceń")
    }
    fun Wykonaj_zlecenie_bot(Ktory_gracz: Int){
        for(Ktore_zlecenie in 0..Ile_zlecen){
            if(Gracze[Ktory_gracz].Zlecenia[Ktore_zlecenie]==1)
            Zrealizuj_zlecenie(Ktory_gracz,Ktore_zlecenie)
        }
    }
    fun Utworz_zlecenie(Ile: Int){
        for(i in 0..Ile) {
            Ile_zlecen++
            Zlecenia[Ile_zlecen].Utworz_zlecenie(Aktualna_tura, Ile_produktow, Produkty, Ile_zlecen,Termin_zlecenia_min,Termin_zlecenia_max,Oplacalnosc_zlecenia_min,Oplacalnosc_zlecenia_max,Min_produktu_w_zleceniu,Max_produktu_w_zleceniu)
        }
    }
    fun Oplac_koszta_graczy(){
        for(Ktory_gracz in 0..Ile_graczy){
            Gracze[Ktory_gracz].Odejmij_koszt_od_gotowki(Zasoby,Ile_zasobow,Kara_za_debet)
            Gracze[Ktory_gracz].Wyzeruj_koszt_utrzymania()
        }
        Oplac_wlaczone_maszyny_wszystkich_graczy()
    }
    fun Oplac_wlaczone_maszyny_wszystkich_graczy(){
        for(Ktory_gracz in 0..Ile_graczy){
                Gracze[Ktory_gracz].Oplac_wlaczone_maszyny(Zasoby,Ile_zasobow)
        }
    }
    fun Wylacz_wszystkie_maszyny(){
        for(Ktory_gracz in 0..Ile_graczy){
            Gracze[Ktory_gracz].Wylacz_wszystkie_maszyny_kazdego_typu(Ile_zasobow)
        }
    }
    fun Sprawdz_terminy_zlecen_gracza(ktory: Int){
        for(Ktore_zlecenie in 0..Ile_zlecen){
            if(Gracze[ktory].Zlecenia[Ktore_zlecenie]==1){
                if(Zlecenia[Ktore_zlecenie].Termin<Aktualna_tura){
                    Gracze[ktory].Zlecenia[Ktore_zlecenie]=0
                    Gracze[ktory].Gotowka-=Zlecenia[Ktore_zlecenie].Nagroda/Termin_kara
                    Zlecenia[Ktore_zlecenie].Aktywne=false
                    //Komunikat_uplyw_terminu(Ktore_zlecenie)
                    Uplyw_terminu[ktory]++
                }
            }
        }
    }
    fun Komunikat_uplyw_terminu(ktore:Int){
        println("Zlecenie nr $ktore zostało zarchiwizowane z powodu upływu terminu")
    }
    fun Sprawdz_terminy_zlecen(){
        for(Ktore_zlecenie in 0..Ile_zlecen){
            if(Zlecenia[Ktore_zlecenie].Termin<Aktualna_tura && Zlecenia[Ktore_zlecenie].Aktywne){
                Zlecenia[Ktore_zlecenie].Aktywne=false
                //Komunikat_uplyw_terminu(Ktore_zlecenie)
            }
        }
    }
    fun Gracz_tura(ktory:Int) {
        var obj=99
        while (obj!=0)
        {
            println("0->Kolejna tura\n1->Sklep\n2->Statystyki gracza\n3->Informacje o świecie\n4->Zleć produkcję\n5->Przetargi\n6->Wykonaj zlecenie")
            when(Wprowadz_liczbe())
            {
                -1->Utworz_zlecenie(1)
                0->obj=0
                1-> Sklep(ktory)
                2-> Statystyki(ktory)
                3-> Informacje()
                4-> Zlec_produkcje(ktory)
                5-> Licytacje(ktory)
                6-> Wykonaj_zlecenie(ktory)
                else -> println("Nieprawidłowa operacja")
            }
        }
        Sprawdz_terminy_zlecen_gracza(ktory)
    }
    fun Rozgrywka(){
        Inicjalizacja()
        //println("Gramy")
        val Srednie=Array(Ile_graczy+1){0.0}
        for (i in 0..Ilosc_rozgrywek) {
            reset_statystyk()
            Aktualna_tura=0
            Ile_zlecen=0
            Zlecenia= List(999) {Zlecenia(0)}
            while (Aktualna_tura != Ile_tur) {
                for (Ktory_gracz in 0..Ile_graczy) {
                    if (Gracze[Ktory_gracz].Bot == 0) {
                        Gracz_tura(Ktory_gracz)
                    }
                    if(Gracze[Ktory_gracz].Bot==1) {
                        Bot_tura(Ktory_gracz)
                    }
                    if(Gracze[Ktory_gracz].Bot==2){
                        AI_bot_tura(Ktory_gracz)
                    }
                }
                Swiat_tura()
            }
            Koniec_gry()
            for (j in 0..Ile_graczy) {
                Srednie[j] += Gracze[j].Majatek_brutto / ((Ilosc_rozgrywek+1).toDouble())
                if(Najgorsza_rozgrywka[j]>Gracze[j].Majatek_brutto){
                    Najgorsza_rozgrywka[j]=Gracze[j].Majatek_brutto
                }
                if(Najlepsza_rozgrywka[j]<Gracze[j].Majatek_brutto){
                    Najlepsza_rozgrywka[j]=Gracze[j].Majatek_brutto
                }
            }
        }
        for(i in 0..Ile_graczy){
            //Statystyki(i)
            println("Gracz ${Gracze[i].Nazwa}\n" +
                    "ilosc zwyciestw ${Ilosc_zwyciestw[i]} \n" +
                    "ilosc zyskownych rozgrywek ${Zyskowna_rozgrywka[i]} \n" +
                    "ilosc stratnych rozgrywek ${Staratna_rozgrywka[i]} \n" +
                    "najlepsza rozgrywka ${Najlepsza_rozgrywka[i]} \n" +
                    "najgorsza rozgrywka ${Najgorsza_rozgrywka[i]}\n" +
                    "wartosc srednia gotowki ${Srednie[i]}\n" +
                    "ilosc przegapionych terminow ${Uplyw_terminu[i]} \n" +
                    "ilosc przyjetych zlecen ${Przyjete_zlecenia[i]}")
        }
    }
    fun Koniec_gry(){
        //println("KONIEC GRY")
        var Zwyciezca:String
        var Koncowy_majatek=0.0
        var Zwycieski_majatek=-9999999999.0
        var Id_zwyciezcy=-1
        for(Ktory_gracz in 0..Ile_graczy){
            //Statystyki(Ktory_gracz)
            Gracze[Ktory_gracz].Policz_majatek_brutto(Zasoby,Produkty,Materialy,Procesy,Ile_zasobow,Ile_produktow,Ile_materialow,Ile_procesow,Ws_zasoby,Ws_mat_prod)
            Koncowy_majatek=Gracze[Ktory_gracz].Majatek_brutto
            if(Zwycieski_majatek<Koncowy_majatek) {
                Zwycieski_majatek = Koncowy_majatek
                Id_zwyciezcy=Ktory_gracz
            }
        }
        Ilosc_zwyciestw[Id_zwyciezcy]++
        for(ktory_gracz in 0..Ile_graczy){
            if(Gracze[ktory_gracz].Majatek_brutto>Majatek_poczatkowy){
                Zyskowna_rozgrywka[ktory_gracz]++
            }
            else{
                Staratna_rozgrywka[ktory_gracz]++
            }
        }
        //println("ZWYCIEZYL GRACZ :${Gracze[Id_zwyciezcy].Nazwa} !")
        //for(Ktory_gracz in 0..Ile_graczy){
          //  println("Majatek koncowy gracza ${Gracze[Ktory_gracz].Nazwa} : ${Gracze[Ktory_gracz].Majatek_brutto}")
        //}
    }
    fun Swiat_tura(){
    Zakoncz_licytacje()
    Aktualna_tura++
    //println("Zaczyna się tura: $Aktualna_tura ")
    Wykonaj_produkcje()
    Oplac_koszta_graczy()
    Utworz_zlecenie(Random.nextInt(Ile_min_zlecen_co_ture,Ile_max_zlecen_co_ture))
    Sprawdz_terminy_zlecen()
    Wylacz_wszystkie_maszyny()
}
    fun handicap(Ktory_gracz:Int){
        Gracze[Ktory_gracz].Zasoby_gracz[0].Ilosc=1
        Gracze[Ktory_gracz].Zasoby_gracz[1].Ilosc=1
        Gracze[Ktory_gracz].Zasoby_gracz[2].Ilosc=2
        Gracze[Ktory_gracz].Procesy[0]=1
        Gracze[Ktory_gracz].Procesy[1]=1
        Gracze[Ktory_gracz].Materialy[0]=500
        Gracze[Ktory_gracz].Materialy[1]=500
        Gracze[Ktory_gracz].Materialy[2]=500
    }
    fun Inicjalizacja() {
//println("Wybierz scenariusz")
       // println("1 - Słodycze\n2 - Napoje")
        val obj=2
        if (obj == 1) {
            Scenariusz="Słodycze"
            Procesy[0].Cena = 500
            Procesy[0].Nazwa = "Badania nad wytworzeniem czekolady"
            Procesy[1].Cena = 1000
            Procesy[1].Nazwa = "Badania nad wytworzeniem żelków"
            Procesy[1].ID = 1
            Ile_procesow = 1

            Produkty[0].materialy[0] = 1
            Produkty[0].materialy[1] = 1
            Produkty[0].procesy[0] = 1
            Produkty[0].Nazwa = "Czekolada"
            Produkty[0].zasoby[0] = 1
            Produkty[0].zasoby[1] = 1
            Produkty[0].Cena = 40

            Produkty[1].materialy[1] = 1
            Produkty[1].materialy[2] = 2
            Produkty[1].Nazwa = "Żelki"
            Produkty[1].zasoby[1] = 1
            Produkty[1].zasoby[2] = 1
            Produkty[1].procesy[1] = 1
            Produkty[1].Cena = 70
            Produkty[1].ID = 1

            Ile_produktow = 1

            Materialy[0].Nazwa = "Kakao"
            Materialy[0].Cena = 10
            Materialy[1].Nazwa = "Cukier"
            Materialy[1].Cena = 20
            Materialy[1].ID = 1
            Materialy[2].Nazwa = "Żelatyna"
            Materialy[2].Cena = 25
            Materialy[2].ID = 2
            Ile_materialow = 2

            Zasoby[0].Nazwa = "Maszyna do czekolady"
            Zasoby[0].Koszt_staly = 2
            Zasoby[0].Koszt_zmienny = 10
            Zasoby[0].Cena = 2000
            Zasoby[0].Wydajnosc = 40
            Zasoby[1].Nazwa = "Maszyna do cukru"
            Zasoby[1].Koszt_staly = 2
            Zasoby[1].Koszt_zmienny = 10
            Zasoby[1].Cena = 1222
            Zasoby[1].Wydajnosc = 100
            Zasoby[1].ID = 1
            Zasoby[2].Nazwa = "Maszyna do żelków"
            Zasoby[2].Koszt_staly = 2
            Zasoby[2].Koszt_zmienny = 10
            Zasoby[2].Cena = 1400
            Zasoby[2].Wydajnosc = 34
            Zasoby[2].ID = 2
            Ile_zasobow = 2

            Gracze[0].Bot = 0
            Gracze[0].Gotowka = 9000
            Gracze[0].Nazwa = "Bunia"
            Gracze[0].ID=0
            Ile_graczy = 0
        }
        if (obj == 2) {
            Scenariusz="Napoje"
            Procesy[0].Cena = 500
            Procesy[0].Nazwa = "Badania nad wytworzeniem soku jabłkowego"
            Procesy[1].Cena = 1000
            Procesy[1].Nazwa = "Badania nad wytworzeniem soku gruszkowego"
            Procesy[1].ID = 1
            Ile_procesow = 1

            Produkty[0].materialy[0] = 1
            Produkty[0].materialy[1] = 1
            Produkty[0].procesy[0] = 1
            Produkty[0].Nazwa = "Sok jabłkowy"
            Produkty[0].zasoby[0] = 1
            Produkty[0].zasoby[1] = 1
            Produkty[0].Cena = 40

            Produkty[1].materialy[1] = 1
            Produkty[1].materialy[2] = 2
            Produkty[1].Nazwa = "Sok gruszkowy"
            Produkty[1].zasoby[2] = 1
            Produkty[1].zasoby[3] = 1
            Produkty[1].procesy[1] = 1
            Produkty[1].Cena = 130
            Produkty[1].ID = 1

            Ile_produktow = 1

            Materialy[0].Nazwa = "Jabłko"
            Materialy[0].Cena = 10
            Materialy[1].Nazwa = "Butelka"
            Materialy[1].Cena = 20
            Materialy[1].ID = 1
            Materialy[2].Nazwa = "Gruszka"
            Materialy[2].Cena = 25
            Materialy[2].ID = 2
            Ile_materialow = 2

            Zasoby[0].Nazwa = "Wyciskarka do jabłek"
            Zasoby[0].Koszt_staly = 20
            Zasoby[0].Koszt_zmienny = 100
            Zasoby[0].Cena = 2000
            Zasoby[0].Wydajnosc = 70
            Zasoby[1].Nazwa = "Rozlewarka do soku jabłkowego"
            Zasoby[1].Koszt_staly = 33
            Zasoby[1].Koszt_zmienny = 50
            Zasoby[1].Cena = 1222
            Zasoby[1].Wydajnosc = 200
            Zasoby[1].ID = 1
            Zasoby[2].Nazwa = "Wyciskarka do gruszek"
            Zasoby[2].Koszt_staly = 10
            Zasoby[2].Koszt_zmienny = 80
            Zasoby[2].Cena = 2400
            Zasoby[2].Wydajnosc = 60
            Zasoby[2].ID = 2
            Zasoby[3].Nazwa="Rozlewarka do soku gruszkowego"
            Zasoby[3].Koszt_staly = 33
            Zasoby[3].Koszt_zmienny = 50
            Zasoby[3].Cena = 2600
            Zasoby[3].Wydajnosc = 200
            Zasoby[3].ID=3

            Ile_zasobow = 3

            Gracze[0].Bot = 2
            Gracze[0].Gotowka = Majatek_poczatkowy
            Gracze[0].Nazwa = "Bot Monika"
            Gracze[0].ID=0
            Gracze[1].Bot=1
            Gracze[1].Gotowka=Majatek_poczatkowy
            Gracze[1].Nazwa="Bot Adam"
            Gracze[1].ID=1
            Gracze[1].Maksymalna_produkcja=Gracze[1].Bot_ustal_maksymalna_produkcje(Min_produktu_w_zleceniu,Max_produktu_w_zleceniu,(Ile_graczy+1),Ile_min_zlecen_co_ture,Ile_max_zlecen_co_ture)
           // Gracze[0].Maksymalna_produkcja=Gracze[1].Maksymalna_produkcja
/*
            Gracze[2].Bot=1
            Gracze[2].Gotowka=Majatek_poczatkowy
            Gracze[2].Nazwa="Bot Tymon"
            Gracze[2].ID=2
            Gracze[3].Bot=1
            Gracze[3].Gotowka=Majatek_poczatkowy
            Gracze[3].Nazwa="Bot Marek"
            Gracze[3].ID=3

            Gracze[3].Bot=true
            Gracze[3].Gotowka=160000
            Gracze[3].Nazwa="Adam3"
            Gracze[3].ID=3
            Gracze[4].Bot=true
            Gracze[4].Gotowka=160000
            Gracze[4].Nazwa="Adam4"
            Gracze[4].ID=4
            Gracze[5].Bot=true
            Gracze[5].Gotowka=160000
            Gracze[5].Nazwa="Adam5"
            Gracze[5].ID=5
            Gracze[6].Bot=true
            Gracze[6].Gotowka=160000
            Gracze[6].Nazwa="Adam6"
            Gracze[6].ID=6
            Gracze[7].Bot=true
            Gracze[7].Gotowka=160000
            Gracze[7].Nazwa="Adam7"
            Gracze[7].ID=7*/

            Ile_graczy = 1
            Utworz_zlecenie(2)
            //handicap(0)
            //handicap(1)
            Produkty[0].Policz_oplacalnosc(Procesy,Zasoby,Materialy,Ile_procesow,Ile_zasobow,Ile_materialow)
            Produkty[1].Policz_oplacalnosc(Procesy,Zasoby,Materialy,Ile_procesow,Ile_zasobow,Ile_materialow)
            Sr_il_prod[0]=(Max_produktu_w_zleceniu+Min_produktu_w_zleceniu).toDouble()/2000
            //println("dd ${Sr_il_prod[0]}")
            siec_swiat.nauka_ustalania_maksymalnej_wydajnosci(Dlugosc_nauki)
            siec_swiat.max_wydajnosc_prod1[0]=siec_swiat.ustalenie_maksymalnej_wydajnosci(0,0.5,Sr_il_prod[0],Ile_graczy,Ile_min_zlecen_co_ture,Ile_max_zlecen_co_ture)
            Sr_il_prod[1]=(Max_produktu_w_zleceniu+Min_produktu_w_zleceniu).toDouble()/2000
            //println("${Sr_il_prod[1]}")
            siec_swiat.max_wydajnosc_prod1[1]=siec_swiat.ustalenie_maksymalnej_wydajnosci(1,0.5,Sr_il_prod[1],Ile_graczy,Ile_min_zlecen_co_ture,Ile_max_zlecen_co_ture)
            siec_wydajnosc.nauka_ustalania_aktualnej_wydajnosci(Dlugosc_nauki)
            siec_licytacje.czy_licytowac_nauka(Dlugosc_nauki)
        }

    }
    fun reset_statystyk(){
        for(i in 0..Ile_graczy){
            Gracze[i].reset(Majatek_poczatkowy)
        }
    }
}