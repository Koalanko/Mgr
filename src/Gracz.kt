class gracz(Nazwa:String,Gotowka:Int,Bot:Int)
{
    var Nazwa=Nazwa
    var Gotowka=Gotowka
    var Bot=Bot
    var Majatek_brutto=0.0
    var Procesy= Array<Int>(20) { 0 }
    var Kosz_utrzymania=0
    var Materialy=Array<Int>(20) { 0 }
    var Materialy_zarezerwowane=Array(20) { 0 }
    var Zlecenia=Array(500){0}
    var Produkty_predykcyjne=Array(20){0}
    var Koszt_staly_ostatniej_rundy=0
    var Zasoby_gracz=List(20){Zasoby_gracza()}
    var Produkty_gracz=List(20){Produkty_gracza()}
    var Produkty_w_zleceniach=Array(2,{0})
    //parametry bota
    var Posiada_wszystkie_procesy=false
    var Stale_zlecenia=Array<Int>(20){0}

    //parametry do optymalizacji
    var Wspolczynnik_kwoty_licytacji=1.0
    var Maksymalna_produkcja=0
    var Minimalna_gotowka_kupno_procesu=-9999999
    var Minimalna_gotowka_zwiekszanie_wydajnosci=-140000
    var Wspolczynniki_maksymlanej_produkcji=Array(20){0}

    //parametry wejsciowe
    // Oplacalnosc produktow - po ilu sztukach inwestycja sie zwroci
    // Aktualna gotowka
    // Aktualna gotowka brutto
    // Ile tur do końca
    // Aktualna Wydajnosc
    // Popyt

    class Produkty_gracza():produkty(){
        var Ilosc=0
        var Wydajnosc_produkcji=0
    }
    class Zasoby_gracza():zasoby(){
        var Ilosc=0
        var Wlaczonych=0
    }
    var ID:Int=0

    fun Bot_ustal_maksymalna_produkcje(Min_produktu_w_zleceniu:Int,Max_produktu_w_zleceniu:Int,liczba_graczy:Int,Min_liczba_zlecen:Int,Max_liczba_zlecen:Int):Int{
        val Srednia_produktu_w_zleceniu=(Max_produktu_w_zleceniu+Min_produktu_w_zleceniu).toDouble()/2
        val Srednia_liczba_ofert=(Max_liczba_zlecen+Min_liczba_zlecen).toDouble()/2
        var wynik=(Srednia_produktu_w_zleceniu*((Srednia_liczba_ofert)/(liczba_graczy+1).toDouble())).toInt()
        //println("wynik = ${wynik}")
        return wynik
    }
    fun Policz_produkty_w_zleceniach(Ile_zlecen:Int,Zlecenia_info:List<Zlecenia>,Ile_produktow: Int){
        for(i in 0..Ile_produktow){
            Produkty_w_zleceniach[i]=0

        }
        for(i in 0..Ile_zlecen){
            if(Zlecenia[i]==1){
                ///println("ZLECCENIE\nZLECENIE\nZLECENEII")
                for(ktory_produkt in 0..Ile_produktow)
                Produkty_w_zleceniach[ktory_produkt]+=Zlecenia_info[i].Zlecenie[ktory_produkt]
            }
        }
       // for(i in 0..Ile_produktow){
        //    println("Produkty w zleceniach: ${Produkty_w_zleceniach[i]}")
       // }
    }
    fun AI_kup_procesy(Procesy_info: List<procesy>,ile_procesow:Int){
        for(ktory_proces in 0..ile_procesow) {
            if (Procesy[ktory_proces] == 0) {
                Kup_proces(Procesy_info, ktory_proces)
            }
        }
    }
    fun AI_kup_zasoby(Ile_zasobow: Int,Produkty_info: List<produkty>,Zasoby_info: List<zasoby>,Ile_produktow: Int,wydajnosc:Array<Int>){
        for(ktory_produkt in 0..Ile_produktow){
            if(Produkty_gracz[ktory_produkt].Wydajnosc_produkcji<wydajnosc[ktory_produkt]){
                for(ktory_zasob in 0..Ile_zasobow){
                    if (Produkty_info[ktory_produkt].zasoby[ktory_zasob]!=0){
                        while(Zasoby_gracz[ktory_zasob].Ilosc*Zasoby_info[ktory_zasob].Wydajnosc<wydajnosc[ktory_produkt]){
                            Kup_zasoby(Zasoby_info,ktory_zasob,1)
                            //println("Zakupiono zasob ${Zasoby_info[ktory_zasob].Nazwa} teraz ilosc tego zasobu to${Zasoby_gracz[ktory_zasob].Ilosc} " +
                              //      "a jego aktualna wydajnosc to ${Zasoby_gracz[ktory_zasob].Ilosc*Zasoby_info[ktory_zasob].Wydajnosc}")
                        }
                        if(Produkty_gracz[ktory_produkt].Wydajnosc_produkcji==0){
                            Produkty_gracz[ktory_produkt].Wydajnosc_produkcji=Zasoby_gracz[ktory_zasob].Ilosc*Zasoby_info[ktory_zasob].Wydajnosc
                        }
                        if(Produkty_gracz[ktory_produkt].Wydajnosc_produkcji>Zasoby_gracz[ktory_zasob].Ilosc*Zasoby_info[ktory_zasob].Wydajnosc){
                            Produkty_gracz[ktory_produkt].Wydajnosc_produkcji=Zasoby_gracz[ktory_zasob].Ilosc*Zasoby_info[ktory_zasob].Wydajnosc
                        }
                    }
                }
            }
        }
        //for(ktory_produkt in 0..Ile_produktow){
          //  println("Aktualna mozliwosc produkcyjna dla produktu ${Produkty_info[ktory_produkt].Nazwa} to ${Produkty_gracz[ktory_produkt].Wydajnosc_produkcji}")
        //}
    }
    fun AI_czy_licytowac(siec: siec,Wymagana_ilosc_prod1:Int,Wymagana_ilosc_prod2:Int,Termin:Int):Boolean{
        return siec.czy_licytowac((Produkty_gracz[0].Ilosc-Produkty_w_zleceniach[0]),Produkty_gracz[0].Wydajnosc_produkcji,Wymagana_ilosc_prod1,
            (Produkty_gracz[1].Ilosc-Produkty_w_zleceniach[1]),Produkty_gracz[1].Wydajnosc_produkcji,Wymagana_ilosc_prod2,Termin)
    }
    fun reset(Majatek_poczatkowy:Int){
        Gotowka=Majatek_poczatkowy
        var Majatek_brutto=0.0
        Procesy= Array<Int>(20) { 0 }
        Kosz_utrzymania=0
        Materialy=Array<Int>(20) { 0 }
        Materialy_zarezerwowane=Array(20) { 0 }
        Zlecenia=Array(500){0}
        Produkty_predykcyjne=Array(20){0}
        Koszt_staly_ostatniej_rundy=0
        Zasoby_gracz=List(20){Zasoby_gracza()}
        Produkty_gracz=List(20){Produkty_gracza()}


        //parametry bota
        Posiada_wszystkie_procesy=false
        Stale_zlecenia=Array<Int>(20){0}
    }
    fun Bot_kup_zasoby(Ile_procesow:Int,Procesy_info: List<procesy>,Ile_zasobow: Int,Produkty_info: List<produkty>,Zasoby_info: List<zasoby>,Ile_produktow: Int,Dodaj_wydajnosc:Int){
        var Zakupiono_proces=false
        var Najmniejsza_wydajnosc=999999
        var Najmniejsza_wydajnosc_ID_produktu:Int=0
            if(Posiada_wszystkie_procesy==false){
                if(Gotowka>Minimalna_gotowka_kupno_procesu){
                for(Ktory_proces in 0..Ile_procesow){
                    if(Procesy[Ktory_proces]==0){
                        Zakupiono_proces=true
                        Kup_proces(Procesy_info,Ktory_proces)
                        for(Ktory_zasob in 0..Ile_zasobow){
                            if(Produkty_info[Ktory_proces].zasoby[Ktory_zasob]!=0){
                                Kup_zasoby(Zasoby_info,Ktory_zasob,Produkty_info[Ktory_proces].zasoby[Ktory_zasob])
                            }
                        }
                    }
                    if(Gotowka<Minimalna_gotowka_kupno_procesu) {
                        break
                    }
                }
            }
                var Liczba_procesow=0
                for(Ktory_proces in 0..Ile_procesow){
                    if(Procesy[Ktory_proces]!=0){
                        Liczba_procesow++
                    }
                }
                if(Liczba_procesow-1==Ile_procesow){
                    Posiada_wszystkie_procesy=true
                }
        }
        else{
            if(Gotowka>Minimalna_gotowka_zwiekszanie_wydajnosci){
                Najmniejsza_wydajnosc=999999
                for(Ktory_produkt in 0..Ile_produktow){
                    if(Najmniejsza_wydajnosc>=Produkty_gracz[Ktory_produkt].Wydajnosc_produkcji){
                        Najmniejsza_wydajnosc_ID_produktu=Ktory_produkt
                        Najmniejsza_wydajnosc=Produkty_gracz[Ktory_produkt].Wydajnosc_produkcji
                    }
                }
                if(Najmniejsza_wydajnosc<Maksymalna_produkcja) {
                    for (Ktory_zasob in 0..Ile_zasobow) {
                        if (Produkty_info[Najmniejsza_wydajnosc_ID_produktu].zasoby[Ktory_zasob] != 0) {
                            while (Zasoby_gracz[Ktory_zasob].Ilosc * Zasoby_info[Ktory_zasob].Wydajnosc < (Najmniejsza_wydajnosc + Dodaj_wydajnosc)) {
                                Kup_zasoby(Zasoby_info, Ktory_zasob, 1)
                            }
                            Aktualizuj_stale_zlecenia(Produkty_info, Zasoby_info, Ile_produktow, Ile_zasobow)
                        }
                    }
                }
            }
        }
    }
    fun Aktualizuj_stale_zlecenia(Produkty: List<produkty>,Zasoby:List<zasoby>,Ile_produktow:Int,Ile_zasobow: Int){
        var Max_wydajnosc:Int
        for(Ktory_produkt in 0..Ile_produktow){
            Max_wydajnosc=Oblicz_maksymalna_wydajnosc_produkcji(Ktory_produkt,Ile_zasobow,Produkty,Zasoby)
            Stale_zlecenia[Ktory_produkt]=Max_wydajnosc
            Produkty_gracz[Ktory_produkt].Wydajnosc_produkcji=Max_wydajnosc
        }
    }
    fun Dodaj_stale_zlecenia_do_produkcji(Ile_produktow: Int,Ile_materialow:Int,Produkty_info: List<produkty>,Material_info: List<materialy>){
        var Material=0
        for(Ktory_produkt in 0..Ile_produktow){
            Dodaj_produkty_predykcyjne(Ktory_produkt,Stale_zlecenia[Ktory_produkt])
            for(Ktory_material in 0..Ile_materialow){
                if(Produkty_info[Ktory_produkt].materialy[Ktory_material]!=0){
                    Material=Produkty_info[Ktory_produkt].materialy[Ktory_material]*Stale_zlecenia[Ktory_produkt]
                    Dodaj_materialy(Ktory_material,-Material)
                    if(Materialy[Ktory_material]<Material){
                        Kup_materialy(Ktory_material,(Material-Materialy[Ktory_material]),Material_info)
                    }
                    Dodaj_zarezerwowane_materialy(Ktory_material,Material)
                }
            }
        }
    }
    fun Zmien_koszt_utrzymania(Ile:Int){
        Kosz_utrzymania+=Ile
    }
    fun Kup_proces(Procesy_info:List<procesy>,Ktory: Int){
        Dodaj_proces(Ktory)
        Gotowka-=Procesy_info[Ktory].Cena
    }
    fun Kup_zasoby(Zasoby_info:List<zasoby>,Ktory: Int,Ile: Int){
        Dodaj_zasoby(Ktory,Ile)
        Gotowka-=Zasoby_info[Ktory].Cena*Ile
    }
    fun Kup_materialy(Ktory:Int,Ile: Int,Material_info:List<materialy>){
        Dodaj_materialy(Ktory,Ile)
        Gotowka-=Material_info[Ktory].Cena*Ile
    }
    fun Oblicz_maksymalna_wydajnosc_produkcji(Ktory_produkt:Int,Ile_zasobow: Int,Produkty: List<produkty>,Zasoby: List<zasoby>):Int{
        var Max_wydajnosc=999999
        var Max_wydajnosc2=999999
        for(Ktory_zasob in 0..Ile_zasobow){
            if(Produkty[Ktory_produkt].zasoby[Ktory_zasob]!=0){
                Max_wydajnosc2=Zasoby_gracz[Ktory_zasob].Ilosc*Zasoby[Ktory_zasob].Wydajnosc/Produkty[Ktory_produkt].zasoby[Ktory_zasob]
            }
            if(Max_wydajnosc>Max_wydajnosc2){
                Max_wydajnosc=Max_wydajnosc2
            }
        }
        return Max_wydajnosc
    }
    fun Pokaz_stale_zlecenia(Produkty: List<produkty>,Ile_produktow: Int){
        println("Stale zlecenia:")
        for(Ktory_produkt in 0..Ile_produktow){
            println("Produkt ${Produkty[Ktory_produkt].Nazwa} ilosc ${Stale_zlecenia[Ktory_produkt]}")
        }
    }
    fun Wyzeruj_koszt_utrzymania(){
        Kosz_utrzymania=0
    }
    fun Wyswietl_koszt_utrzymania():Int{
        return Kosz_utrzymania
    }
    fun Wlacz_odpowiednia_liczbe_maszyn(Zasoby_info: List<zasoby>,Produkty:List<produkty>,Ile_produktow_do_wyprodukowania:Int,Ile_zasobow: Int,Ktory_produkt: Int){
        for (Ktory_zasob in 0..Ile_zasobow) {
            if (Produkty[Ktory_produkt].zasoby[Ktory_zasob] != 0) {
                while((Podaj_ilosc_wlaczonych_maszyn(Ktory_zasob) * Zasoby_info[Ktory_zasob].Wydajnosc) / Produkty[Ktory_produkt].zasoby[Ktory_zasob] < Ile_produktow_do_wyprodukowania) {
                    if (Zasoby_gracz[Ktory_zasob].Ilosc > Podaj_ilosc_wlaczonych_maszyn(Ktory_zasob)) {
                        Wlacz_maszyne(Ktory_zasob)
                    }
                    else{
                        break
                    }
                }
            }
        }
    }
    fun Podlicz_koszt_staly(Zasoby_info:List<zasoby>,Ile_zasobow:Int):Int{
        Koszt_staly_ostatniej_rundy=0
        for(Ktory_zasob in 0..Ile_zasobow){
            if(Zasoby_gracz[Ktory_zasob].Ilosc!=0){
                Koszt_staly_ostatniej_rundy+=Zasoby_gracz[Ktory_zasob].Ilosc*Zasoby_info[Ktory_zasob].Koszt_staly
            }
        }
        return  Koszt_staly_ostatniej_rundy
    }
    fun Odejmij_koszt_od_gotowki(Zasoby_info: List<zasoby>,Ile_zasobow: Int,Kara_za_debet:Int){
        if(Gotowka<0){
            Gotowka=Gotowka+Gotowka/Kara_za_debet
        }
        Gotowka=Gotowka-(Podlicz_koszt_staly(Zasoby_info,Ile_zasobow)+Kosz_utrzymania)
    }
    fun Wlacz_maszyne(Ktora:Int){
            Zasoby_gracz[Ktora].Wlaczonych++
        }
    fun Wylacz_maszyne(Ktora: Int){
        Zasoby_gracz[Ktora].Wlaczonych--
    }
    fun Wylacz_wszystkie(Ktora: Int){
        Zasoby_gracz[Ktora].Wlaczonych=0
    }
    fun Wylacz_wszystkie_maszyny_kazdego_typu(Ile_maszyn_na_swiecie: Int){
        for(Ktory_zasob in 0..Ile_maszyn_na_swiecie){
            Zasoby_gracz[Ktory_zasob].Wlaczonych=0
        }
    }
    fun Podaj_ilosc_wlaczonych_maszyn(Ktora: Int):Int{
        return Zasoby_gracz[Ktora].Wlaczonych
    }
    fun Oblicz_koszt_wlaczonych_maszyn(Zasoby_info: List<zasoby>,Ile_zasobow: Int):Int{
        var Oplata=0
        for(Ktory_zasob in 0..Ile_zasobow) {
            if (Zasoby_gracz[Ktory_zasob].Ilosc != 0) {
                Oplata += Podaj_ilosc_wlaczonych_maszyn(Ktory_zasob) * Zasoby_info[Ktory_zasob].Koszt_zmienny
            }
        }
        return Oplata
    }
    fun Oplac_wlaczone_maszyny(Zasoby_info: List<zasoby>,Ile_zasobow: Int){
        Gotowka-=Oblicz_koszt_wlaczonych_maszyn(Zasoby_info,Ile_zasobow)
    }
    fun Podaj_ilosc_maszyn(Ktora: Int):Int{
        return Zasoby_gracz[Ktora].Ilosc
    }
    fun produkuj(Ktory_produkt:Int,Ile: Int){
        Dodaj_Produkty(Ktory_produkt,Ile)
        Dodaj_produkty_predykcyjne(Ktory_produkt,-Ile)
    }
    fun Dodaj_zlecenie(Ktore:Int){
        Zlecenia[Ktore]=1
    }
    fun Dodaj_proces(Proces:Int) {
        if(Procesy[Proces]!=1) {
            Procesy[Proces] = 1
        }
        else
            println("Gracz posiada już proces")
    }
    fun Dodaj_zasoby(Zasob:Int,Ile:Int) {
        Zasoby_gracz[Zasob].Ilosc=Zasoby_gracz[Zasob].Ilosc+Ile
    }
    fun Dodaj_materialy(Material:Int,Ile: Int) {
        Materialy[Material]=Materialy[Material]+Ile
    }
    fun Dodaj_Produkty(Produkt:Int,Ile: Int) {
        Produkty_gracz[Produkt].Ilosc=Produkty_gracz[Produkt].Ilosc+Ile
    }
    fun Dodaj_zarezerwowane_materialy(Material:Int,Ile:Int){
        Materialy_zarezerwowane[Material]=Materialy_zarezerwowane[Material]+Ile
    }
    fun Dodaj_produkty_predykcyjne(Produkt: Int,Ile: Int){
        Produkty_predykcyjne[Produkt]=Produkty_predykcyjne[Produkt]+Ile
    }
    fun Policz_wartosc_procesow(Przedmiot_info:List<przedmiot>,Ile_przedmiotu:Int,Wspolczynnik:Double):Double{
        var Wartosc=0.0
        for (Ktory_proces in 0..Ile_przedmiotu) {
            if (Procesy[Ktory_proces] != 0) {
                Wartosc += Przedmiot_info[Ktory_proces].Cena * Wspolczynnik
            }
        }
        return Wartosc
    }
    fun Policz_wartosc_zasobow(Przedmiot_info:List<przedmiot>,Ile_przedmiotu:Int,Wspolczynnik:Double):Double{
        var Wartosc=0.0
        for (Ktory_zasob in 0..Ile_przedmiotu) {
            if (Zasoby_gracz[Ktory_zasob].Ilosc != 0) {
                Wartosc += Przedmiot_info[Ktory_zasob].Cena*Zasoby_gracz[Ktory_zasob].Ilosc * Wspolczynnik
            }
        }
        return Wartosc
    }
    fun Policz_wartosc_produktow(Przedmiot_info:List<przedmiot>,Ile_przedmiotu:Int,Wspolczynnik:Double):Double{
        var Wartosc=0.0
        for (Ktory_produkt in 0..Ile_przedmiotu) {
            if (Produkty_gracz[Ktory_produkt].Ilosc != 0) {
                Wartosc += Przedmiot_info[Ktory_produkt].Cena*Produkty_gracz[Ktory_produkt].Ilosc * Wspolczynnik
            }
        }
        return Wartosc
    }
    fun Policz_wartosc_materialow(Przedmiot_info:List<przedmiot>,Ile_przedmiotu:Int,Wspolczynnik:Double):Double{
        var Wartosc=0.0
        for (Ktory_material in 0..Ile_przedmiotu) {
            if (Materialy[Ktory_material] != 0) {
                Wartosc += Przedmiot_info[Ktory_material].Cena*Materialy[Ktory_material] * Wspolczynnik
            }
        }
        return Wartosc
    }
    fun Policz_majatek_brutto(Zasoby_info:List<zasoby>,Produkty_info:List<produkty>,Materialy_info:List<materialy>,Procesy_info:List<procesy>,Ile_zasobow:Int,Ile_produktow:Int,Ile_materialow:Int,Ile_procesow:Int,Ws_zasoby:Double,Ws_mat_prod:Double) {
        Majatek_brutto = Gotowka.toDouble()
        var Wartosc=Policz_wartosc_procesow(Procesy_info,Ile_procesow,Ws_zasoby)
        Majatek_brutto+=Wartosc

        Wartosc=Policz_wartosc_zasobow(Zasoby_info,Ile_zasobow,Ws_zasoby)
        Majatek_brutto+=Wartosc

        Wartosc=Policz_wartosc_produktow(Produkty_info,Ile_produktow,Ws_mat_prod)
        Majatek_brutto+=Wartosc

        Wartosc=Policz_wartosc_materialow(Materialy_info,Ile_materialow,Ws_mat_prod)
        Majatek_brutto+=Wartosc

       // println("Majatek brutto gracza $Nazwa: $Majatek_brutto")
    }
}
