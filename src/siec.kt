import java.lang.Math.exp
import java.lang.Math.sqrt
import kotlin.random.Random
class siec {
    constructor() {
        liczba_neuronow_w_warstwie[4] = 3
        liczba_neuronow_w_warstwie[3] = 15
        liczba_neuronow_w_warstwie[2] = 15
        liczba_neuronow_w_warstwie[1] = 9
        inicjalizacja_zestawu_naukowego0()
        Inicjalizacja_zestawu_testowego0()
        losowanie_wag()
    }

    //Parametry:
    val liczba_warstw = 5
    var ile_wek = 10
    var alfa = 0.0
    var beta = 2.0
    var eta = 0.05
    var ERMS = 0.1

    val Prog_licytacji=0.5

    var wydajnosc=Array(10,{0})
    val Rzad_max_wydajnosc=1000.0
    var max_wydajnosc_prod1=Array(10,{0})
    var liczba_neuronow_w_warstwie = Array(liczba_warstw, {15})
    var waga = Array(liczba_warstw,
        { Array(liczba_neuronow_w_warstwie[0], { Array(liczba_neuronow_w_warstwie[0], { 0.0 }) }) })
    var waga1 = Array(liczba_warstw,
        { Array(liczba_neuronow_w_warstwie[0], { Array(liczba_neuronow_w_warstwie[0], { 0.0 }) }) })
    var waga2 = Array(liczba_warstw,
        { Array(liczba_neuronow_w_warstwie[0], { Array(liczba_neuronow_w_warstwie[0], { 0.0 }) }) })
    var I = Array(liczba_warstw, { Array(liczba_neuronow_w_warstwie[0], { 0.0 }) })
    var O = Array(liczba_warstw, { Array(liczba_neuronow_w_warstwie[0], { 0.0 }) })
    var E = Array(liczba_warstw, { Array(liczba_neuronow_w_warstwie[0], { 0.0 }) })
    var B = Array(liczba_warstw, { Array(liczba_neuronow_w_warstwie[0], { 0.0 }) })
    var Wy = Array(140, { Array(99, { 0.1 }) })
    var wej = Array(140, { Array(99, { 0.1 }) })
    var test = Array(99, { Array(99, { 0.1 }) })
    var liczba_przykladow = 11
    var liczba_wejsc = 18
    var liczba_wyjsc = 2
    fun inicjalizacja_zestawu_naukowego0() {
        liczba_wejsc = 7
        liczba_wyjsc = 3
        liczba_przykladow = 6
        liczba_neuronow_w_warstwie[3] = liczba_wyjsc
        //czlowiek
        var nr = 0
        wej[0][0] = 4.0 //nogi
        wej[0][1] = -1.0 //dziob
        wej[0][2] = 0.2 //plywa
        wej[0][3] = -1.0 //lata
        wej[0][4] = -1.0 //sklada jaja
        wej[0][5] = -1.0 //piora
        wej[0][6] = 0.2 //futro
        Wy[0][0] = 1.0
        Wy[0][1] = 0.0
        Wy[0][2] = 0.0
        //pies
        nr++
        wej[nr][0] = 4.0 //nogi
        wej[nr][1] = -1.0 //dziob
        wej[nr][2] = 0.2 //plywa
        wej[nr][3] = -1.0 //lata
        wej[nr][4] = -1.0 //sklada jaja
        wej[nr][5] = -1.0 //piora
        wej[nr][6] = 1.0 //futro
        Wy[nr][0] = 1.0
        Wy[nr][1] = 0.0
        Wy[nr][2] = 0.0
        nr++
        //wrobel
        wej[nr][0] = 2.0 //nogi
        wej[nr][1] = 1.0 //dziob
        wej[nr][2] = -1.0 //plywa
        wej[nr][3] = 1.0 //lata
        wej[nr][4] = 1.0 //sklada jaja
        wej[nr][5] = 1.0 //piora
        wej[nr][6] = -1.0 //futro
        Wy[nr][0] = 0.0
        Wy[nr][1] = 1.0
        Wy[nr][2] = 0.0
        //jastrzab
        nr++
        wej[nr][0] = 2.0 //nogi
        wej[nr][1] = 1.0 //dziob
        wej[nr][2] = -1.0 //plywa
        wej[nr][3] = 1.0 //lata
        wej[nr][4] = 1.0 //sklada jaja
        wej[nr][5] = 1.0 //piora
        wej[nr][6] = -1.0 //futro
        Wy[nr][0] = 0.0
        Wy[nr][1] = 1.0
        Wy[nr][2] = 0.0
        //okon
        nr++
        wej[nr][0] = 2.0 //nogi
        wej[nr][1] = 1.0 //dziob
        wej[nr][2] = 0.4 //plywa
        wej[nr][3] = 0.7 //lata
        wej[nr][4] = 1.0 //sklada jaja
        wej[nr][5] = 1.0 //piora
        wej[nr][6] = -1.0 //futro
        Wy[nr][0] = 0.0
        Wy[nr][1] = 0.0
        Wy[nr][2] = 1.0
        //dorsza
        nr++
        wej[nr][0] = 0.0 //nogi
        wej[nr][1] = -1.0 //dziob
        wej[nr][2] = 1.0 //plywa
        wej[nr][3] = -1.0 //lata
        wej[nr][4] = 1.0 //sklada jaja
        wej[nr][5] = -1.0 //piora
        wej[nr][6] = -1.0 //futro
        Wy[nr][0] = 0.0
        Wy[nr][1] = 0.0
        Wy[nr][2] = 1.0
    }// zwierzeta - zestaw do testowania

    fun inicjalizacja_zestawu_naukowego1() {
        liczba_wejsc = 6
        liczba_wyjsc = 2
        liczba_przykladow = 49
        liczba_neuronow_w_warstwie[1]=liczba_wejsc+1
        liczba_neuronow_w_warstwie[liczba_warstw-1] = liczba_wyjsc
        var nr=0
/*
        for (i in 0..10) {
            wej[i][5] = 3222.0 //Cena zasobu (suma) prod1
            wej[i][6] = 30.0 //cena materialow (suma) prod1
            wej[i][7] = 40.0 //sugerowana cena produktu prod1
            wej[i][8] = 322.2 //Oplacalnosc produktu prod1
            wej[i][9] = 0.5 //Prawdopodobienstwo pojawienia produktu w zleceniu prod1
            wej[i][10] = 100.0 // Srednia ilosc produktu w zleceniu prod1

            wej[i][12] = 5000.0 //Cena zasobu (suma) prod1
            wej[i][13] = 35.0 //cena materialow (suma) prod1
            wej[i][14] = 130.0 //sugerowana cena produktu prod1
            wej[i][15] = 52.6 //Oplacalnosc produktu prod1
            wej[i][16] = 0.5 //Prawdopodobienstwo pojawienia produktu w zleceniu prod1
            wej[i][17] = 100.0 // Srednia ilosc produktu w zleceniu prod1
        }*/
        wej[nr][0] = 0.1 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.16 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2

        nr++
        wej[nr][0] = 0.3 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.25
        nr++
        wej[nr][0] = 0.4 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.10
        Wy[nr][1] = 0.25
        nr++
        wej[nr][0] = 0.52 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.15
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.63 //Gotowka (w setkach tysiecy)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.25
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.74 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.25
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.85 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.96 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 1.07 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.1 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.16 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2

        nr++
        wej[nr][0] = 0.3 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.25
        nr++
        wej[nr][0] = 0.4 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.10
        Wy[nr][1] = 0.25
        nr++
        wej[nr][0] = 0.52 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.15
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.63 //Gotowka (w setkach tysiecy)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.25
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.74 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.25
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.85 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.96 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.35
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.07 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.2 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.3 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.4 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.7
        nr++
        wej[nr][0] = 1.5 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.55
        Wy[nr][1] = 0.75
        nr++
        wej[nr][0] = 1.6 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.6
        Wy[nr][1] = 0.8
        nr++
        wej[nr][0] = 1.8 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.7
        Wy[nr][1] = 0.8
        nr++
        wej[nr][0] = 1.9 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.85
        Wy[nr][1] = 0.8
        nr++
        wej[nr][0] = 2.0 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.8 //maksymalna wydajnosc

        Wy[nr][0] = 0.8
        Wy[nr][1] = 0.8
        nr++
        wej[nr][0] = 0.1 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.16 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.18

        nr++
        wej[nr][0] = 0.3 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.12
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.4 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.20
        nr++
        wej[nr][0] = 0.52 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.63 //Gotowka (w setkach tysiecy)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.74 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.85 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.96 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 1.07 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 1.2 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 1.3 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 1.4 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 1.5 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 1.6 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 1.8 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 1.9 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 2.0 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.16 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 2.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 2.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.07 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 2.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 2.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.07 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.16 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 2.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 2.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.1 //maksymalna wydajnosc

        Wy[nr][0] = 0.12
        Wy[nr][1] = 0.12
        nr++
        wej[nr][0] = 0.3 //Gotowka (w dziesiątkach tysiącach)
        wej[nr][1] = 2.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 2.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.07 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1

        println("${nr}")


    } // dostosowanie wydajnosci

    fun Inicjalizacja_zestawu_testowego0() {
        test[0][0] = 2.0 //nogi
        test[0][1] = -1.0 //dziob
        test[0][2] = -1.0 //plywa
        test[0][3] = 1.0 //lata
        test[0][4] = 1.0 //sklada jaja
        test[0][5] = 1.0 //piora
        test[0][6] = -1.0 //futro

    } // zwierzeta - zestaw do testowania

    fun Inicjalizacja_zestawu_testowego1() {
        val nr=0
        test[nr][0] = 3.0 //Gotowka (w tysiącach)
        test[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        test[nr][2] = 3.0 //Ile tur ma gra
        test[nr][3] = 0.0// Aktualna wydajnosc prod1
        test[nr][4] = 0.0// Aktualna wydajnosc prod2
        test[nr][5] = 0.4 //maksymalna wydajnosc
    } // dostosowanie wydajnosci
    fun inicjalizacja_zestawu_naukowego_maksymalna_wydajnosc(){

        var nr = 0
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.1 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.05
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.2 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.10
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.3 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.15
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.4 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.2
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.5 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.25
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.6 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.3
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.7 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.35
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.8 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.4
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.9 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.45
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 1.0 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.2 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.5
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.1 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.1
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.2 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.2
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.3 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.3
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.4 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.4
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.5 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.5
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.6 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.6
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.7 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.7
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.8 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.8
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.9 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.9
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 1.0 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.4 //srednia liczba zlecen na ture
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.1 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.15
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.2 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.3
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.3 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.45
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.4 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.6
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.5 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.75
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.6 //srednia ilosc produktu1
        wej[nr][2] = 0.2 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.9
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.1 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.05
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.2 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.1
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.3 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.15
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.4 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.2
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.5 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.25
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.6 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.3
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.7 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.35
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.8 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.4
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.9 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.45
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 1.0 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.3 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.5
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.1 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.1
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.2 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.2
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.3 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.3
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.4 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.4
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.5 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.5
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.6 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.6
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.7 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.7
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.8 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.8
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.9 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.9
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 1.0 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.6 //srednia liczba zlecen na ture
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.1 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.9 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.15
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.2 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.9 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.3
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.3 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.9 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.45
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.4 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.9 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.6
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.5 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.9 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.75
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.6 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.9 //srednia liczba zlecen na ture
        Wy[nr][0] = 0.9
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.68 //srednia ilosc produktu1
        wej[nr][2] = 0.3 //liczba graczy
        wej[nr][3] = 0.9 //srednia liczba zlecen na ture
        Wy[nr][0] = 1.0
        liczba_wejsc = 4
        liczba_wyjsc = 1
        liczba_przykladow = nr+1
        liczba_neuronow_w_warstwie[liczba_warstw-1] = liczba_wyjsc
        liczba_neuronow_w_warstwie[1]=liczba_wejsc

    }
    fun inicjalizacja_zestawu_naukowego2() {
        liczba_wejsc = 2
        liczba_wyjsc = 1
        liczba_przykladow = 6
        liczba_neuronow_w_warstwie[liczba_warstw-1] = liczba_wyjsc
        liczba_neuronow_w_warstwie[1]=liczba_wejsc
        var nr = 0
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.1 //srednia ilosc produktu1
        Wy[nr][0] = 0.07
        nr++
        wej[nr][0] = 1.0 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.1 //srednia ilosc produktu1
        Wy[nr][0] = 0.14
        nr++
        wej[nr][0] = 1.0 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.2 //srednia ilosc produktu1
        Wy[nr][0] = 0.250
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.2 //srednia ilosc produktu1
        Wy[nr][0] = 0.140
        nr++
        wej[nr][0] = 0.5 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.5 //srednia ilosc produktu1
        Wy[nr][0] = 0.300
        nr++
        wej[nr][0] = 1.0 //prawdopodobienstwo pojawienia sie produktu1
        wej[nr][1] = 0.5 //srednia ilosc produktu1
        Wy[nr][0] = 0.580

    }
    fun inicjalizacja_zestawu_naukowego_aktualna_wydajnosc(){
        var nr=0
        wej[nr][0] = 0.1 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.3 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.4 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.6 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.7 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.8 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.9 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.4 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.1 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.3 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.4 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.6 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.7 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.8 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 0.9 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.1 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.1 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.3 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.4 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 0.1 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.3 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.4 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.6 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.7 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.8 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 0.9 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.1 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.6
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.6
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.3 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.6
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.4 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.6
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.6
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.6 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.6
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 1.7 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.6 //maksymalna wydajnosc

        Wy[nr][0] = 0.6
        Wy[nr][1] = 0.6
        nr++
        wej[nr][0] = 0.1 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.3 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.4 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.6 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.7 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.8 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.9 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.1 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.3 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.4 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.6 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.7 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.8 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.9 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 3.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.0// Aktualna wydajnosc prod1
        wej[nr][4] = 0.0// Aktualna wydajnosc prod2
        wej[nr][5] = 0.2 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.5 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.6 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.7 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.8 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.17
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.9 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.15
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.1 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.2 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.2 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.3 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.5 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.6 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.7 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.8 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.9 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.22
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.25
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.1 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.25
        Wy[nr][1] = 0.25
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.2 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.25
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.3 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.4 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.4 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.5 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.1// Aktualna wydajnosc prod1
        wej[nr][4] = 0.1// Aktualna wydajnosc prod2
        wej[nr][5] = 0.3 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.5 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.6 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.1
        Wy[nr][1] = 0.1
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 0.7 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.2
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.1 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.2
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.2 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.3 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.35
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.4 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.5 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.35
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 0.5 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.6 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.3
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.2 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.3
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.4 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.4
        Wy[nr][1] = 0.4
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.6 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 1.8 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 1.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 2.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        nr++
        wej[nr][0] = 2.0 //Gotowka (w setkach tysiącach)
        wej[nr][1] = 2.0 //Ile tur do konca gry (w dziesiątkach)
        wej[nr][2] = 3.0 //Ile tur ma gra
        wej[nr][3] = 0.2// Aktualna wydajnosc prod1
        wej[nr][4] = 0.2// Aktualna wydajnosc prod2
        wej[nr][5] = 0.5 //maksymalna wydajnosc

        Wy[nr][0] = 0.5
        Wy[nr][1] = 0.5
        liczba_wejsc = 6
        liczba_wyjsc = 2
        liczba_przykladow = nr+1
        liczba_neuronow_w_warstwie[liczba_warstw-1] = liczba_wyjsc
        liczba_neuronow_w_warstwie[1]=liczba_wejsc

    }
    fun inicjalizacja_zestawu_testowego2() {
        val nr = 0
        test[nr][0] = 1.0 //prawdopodobienstwo pojawienia sie produktu1
        test[nr][1] = 0.2 //srednia ilosc produktu1
    }
    fun ustalenie_aktualnej_wydajnosci(Gotowka:Int,Aktualna_tura:Int,Max_tur:Int,Aktualna_wydajnosc:Array<Int>,Max_wydajnosc:Int):Array<Int>{

        var wart_Got=Gotowka.toDouble()/100000.0
        test[0][0] = wart_Got //Gotowka (w setkach tysiecy)
        //println("gotowka $wart_Got")
        var wart_KT=Max_tur.toDouble()/10.0
        var wart_AT=(Max_tur.toDouble()/10.0)-(Aktualna_tura.toDouble()/10.0)
        //println("$wart_AT")
        test[0][1] = wart_AT //Ile tur do konca gry (w dziesiątkach)
        //println("$wart_KT")
        test[0][2] = wart_KT //Ile tur ma gra
        var AW1=Aktualna_wydajnosc[0].toDouble()/1000.0
        //println("${AW1}")
        test[0][3] = AW1// Aktualna wydajnosc prod1
        var AW2=Aktualna_wydajnosc[1].toDouble()/1000.0
        //println("${AW2}")
        test[0][4] = AW2// Aktualna wydajnosc prod2
        var Max_W=Max_wydajnosc.toDouble()/1000.0
        //println("$Max_W")
        test[0][5] = Max_W //maksymalna wydajnosc
        przetworzenie_sygnalu_wejsciowego(test[0])
        //wydajnosc[0]=O[liczba_warstw-1][0]
        //println("wydajnosc 0 = ${O[4][0]}")
        //wydajnosc[1]=O[liczba_warstw-1][1]
        //println("wydajnosc 1 = ${O[4][1]}")
        var Ar_ret=Array(2,{0})
        Ar_ret[0]=((O[4][0])*1000).toInt()
        Ar_ret[1]=((O[4][1])*1000).toInt()
        //println("wydajnosc 0 = ${Ar_ret[0]}")
        //wydajnosc[1]=O[liczba_warstw-1][1]
        //println("wydajnosc 1 = ${Ar_ret[1]}")
        return Ar_ret
    }
    // maksymalna wydajnosc
    fun nauka_ustalania_aktualnej_wydajnosci(ile_epok: Int){
        //println("ucze sie ustalac aktualna wydajnosc...")
        inicjalizacja_zestawu_naukowego_aktualna_wydajnosc()
        losowanie_wag()
        nauka(ile_epok)
    }
    fun nauka_ustalania_maksymalnej_wydajnosci(ile_epok: Int){
        //println("ucze sie ustalac maksymalna wydajnosc...")
        losowanie_wag()
        inicjalizacja_zestawu_naukowego_maksymalna_wydajnosc()
        nauka(ile_epok)
    }
    fun ustalenie_maksymalnej_wydajnosci(ktory_produkt:Int,pr_prod:Double,sr_il_prod:Double,Ile_graczy:Int,Ile_ofert_min:Int,Ile_ofert_max:Int):Int{
        val Ile_graczy_double=(Ile_graczy.toDouble())/10
        val Srednia_ilosc_ofert=((Ile_ofert_max.toDouble()+Ile_ofert_min.toDouble()))/20
        test[0][0] = pr_prod //prawdopodobienstwo pojawienia sie produktu
        test[0][1] = sr_il_prod //srednia ilosc produktu
        test[0][2]= Ile_graczy_double
        test[0][3] =Srednia_ilosc_ofert
        przetworzenie_sygnalu_wejsciowego(test[0])
        for (i in 0..liczba_neuronow_w_warstwie[liczba_warstw - 1] - 1) {
            println("O[${liczba_warstw - 1}][$i]=${O[liczba_warstw - 1][i]}")
        }
        var pom=O[liczba_warstw - 1][0]*Rzad_max_wydajnosc
        max_wydajnosc_prod1[ktory_produkt]=pom.toInt()
        println("${max_wydajnosc_prod1[ktory_produkt]}")
        return max_wydajnosc_prod1[ktory_produkt]
    }
    fun inicjalizacja_zestawu_naukowego_czy_licytowac3(){
        var nr = 0
        wej[nr][0] = -0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = -0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.7 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        /////////////////////////////////////////
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = -0.4 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = -0.3 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = -0.2 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = -0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = -1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.2 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.3 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.0 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.2 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.2 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.3 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.3 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = -0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = -0.3 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = -0.3 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = -0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.3 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.7 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.2 //wymagana ilosc produktow1
        wej[nr][2] = 0.7 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.2 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.2 //wymagana ilosc produktow1
        wej[nr][2] = 0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.2 //wymagana ilosc produktow1
        wej[nr][2] = 0.7 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.2 //wymagana ilosc produktow1
        wej[nr][2] = 0.3 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2//produkty w magazynie1
        wej[nr][1] = 0.2 //wymagana ilosc produktow1
        wej[nr][2] = 0.7 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.2 //wymagana ilosc produktow1
        wej[nr][2] = 0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.4 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.4 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.4 //produkty w magazynie1
        wej[nr][1] = 0.4 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.4 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.4 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.4 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.4 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.4 //wymagana ilosc produktow1
        wej[nr][2] = -0.4 //produkty w magazynie2
        wej[nr][3] = 0.4 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.4 //wymagana ilosc produktow1
        wej[nr][2] = -0.2 //produkty w magazynie2
        wej[nr][3] = 0.4 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.7 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.6 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.13 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.2 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.8 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.8 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 1.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 1.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.4 //wymagana ilosc produktow1
        wej[nr][2] = 0.7 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty w magazynie1
        wej[nr][1] = 0.3 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.4 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.8 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.8 //produkty w magazynie2
        wej[nr][3] = 0.2 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.3 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.3 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 1.4 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 1.4 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 1.4 //produkty w magazynie1
        wej[nr][1] = 0.3 //wymagana ilosc produktow1
        wej[nr][2] = 1.4 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.4 //termin
        /////////////////////////
        nr++
        wej[nr][0] = 0.8 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.8 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 1.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 1.1 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.4 //wymagana ilosc produktow1
        wej[nr][2] = 0.7 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty w magazynie1
        wej[nr][1] = 0.3 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.4 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.8 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.8 //produkty w magazynie2
        wej[nr][3] = 0.2 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.3 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.3 //wymagana ilosc produktow1
        wej[nr][2] = 0.4 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 1.4 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 1.4 //produkty w magazynie2
        wej[nr][3] = 0.3 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 1.4 //produkty w magazynie1
        wej[nr][1] = 0.3 //wymagana ilosc produktow1
        wej[nr][2] = 1.4 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.0 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.1 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //wymagana ilosc produktow1
        wej[nr][2] = 0.5 //produkty w magazynie2
        wej[nr][3] = 0.0 //wymagana ilosc produktow2
        wej[nr][4] = 0.6 //termin

        Wy[nr][0] = 1.0

        liczba_wejsc = 5
        liczba_wyjsc = 1
        liczba_przykladow = nr+1
        liczba_neuronow_w_warstwie[liczba_warstw-1] = liczba_wyjsc
        liczba_neuronow_w_warstwie[1]=liczba_wejsc

    }
    fun inicjalizacja_zestawu_naukowego_czy_licytowac2(){
        var nr = 0
        wej[nr][0] = -0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.6 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.7 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        ///////////////////////////////////////////////////
        wej[nr][0] = -0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.6 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.7 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        ////////////////////////////////////////////////////////////////
        wej[nr][0] = -0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.7 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        //////////////////////////TUTUT
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.4 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.3 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.2 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.3 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.5 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.4 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.3 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        ///////////////////////////////////////////////////
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.2 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = -0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.3 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.5 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.6 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.7 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 0.0
        nr++
        ////////////////////////////////////////////////////////////////
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.3 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.3 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.2 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.3 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.3 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.4 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.2 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = -0.5 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.3 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.3 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.7 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.6 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.6 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.8 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.5 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = -0.2 //produkty w magazynie2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.2 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = -0.2 //produkty w magazynie2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.4 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = -0.01 //produkty w magazynie1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = -0.4 //produkty w magazynie2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.2 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.3 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.5 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.3 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.5 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.6 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.8 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty w magazynie2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 0.0
        nr++
        liczba_wejsc = 7
        liczba_wyjsc = 1
        liczba_przykladow = nr
        liczba_neuronow_w_warstwie[liczba_warstw-1] = liczba_wyjsc
        liczba_neuronow_w_warstwie[1]=liczba_wejsc

    }
    fun inicjalizacja_zestawu_naukowego_czy_licytowac(){
        liczba_wejsc = 7
        liczba_wyjsc = 1
        liczba_przykladow = 61
        liczba_neuronow_w_warstwie[liczba_warstw-1] = liczba_wyjsc
        liczba_neuronow_w_warstwie[1]=liczba_wejsc
        var nr = 0
        wej[nr][0] = 0.1 //produkty w magazynie1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty w magazynie2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja
        wej[nr][2] = 0.3 //wymagana ilosc produktow
        wej[nr][3] = 0.0 //produkty w magazynie
        wej[nr][4] = 0.1 //aktualna produkcja
        wej[nr][5] = 0.2 //wymagana ilosc produktow
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.2 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] =1.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.1 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.2 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.3 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.1 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.3 //termin

        Wy[nr][0] = 0.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.3 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.1 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.3 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.2 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.3 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.3 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.3 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.4 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.4 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.2 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin

        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.4 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.5 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.7 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.7 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.6 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.3 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.4 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.7 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.1 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.0 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.0 //wymagana ilosc produktow1
        wej[nr][3] = 0.7 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.3 //wymagana ilosc produktow2
        wej[nr][6] = 0.7 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.3 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.5 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.2 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.5 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.2 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.2 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.3 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.3 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.4 //produkty nadmiarowe1
        wej[nr][1] = 0.2 //aktualna produkcja1
        wej[nr][2] = 0.4 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.3 //produkty nadmiarowe1
        wej[nr][1] = 0.3 //aktualna produkcja1
        wej[nr][2] = 0.3 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        nr++
        wej[nr][0] = 0.6 //produkty nadmiarowe1
        wej[nr][1] = 0.1 //aktualna produkcja1
        wej[nr][2] = 0.1 //wymagana ilosc produktow1
        wej[nr][3] = 0.0 //produkty nadmiarowe 2
        wej[nr][4] = 0.1 //aktualna produkcja2
        wej[nr][5] = 0.0 //wymagana ilosc produktow2
        wej[nr][6] = 0.4 //termin
        Wy[nr][0] = 1.0
        println("$nr")
    }
    fun inicjalizacja_zestawu_testowego_czy_licytowac(){
        var nr=0
        test[nr][0] = 0.0 //produkty w magazynie1
        test[nr][1] = 0.1 //aktualna produkcja1
        test[nr][2] = 0.1 //wymagana ilosc produktow1
        test[nr][3] = 0.0 //produkty w magazynie2
        test[nr][4] = 0.1 //aktualna produkcja2
        test[nr][5] = 0.0 //wymagana ilosc produktow2
        test[nr][6] = 0.4 //termin

    }
    fun czy_licytowac_nauka(ile_epok: Int){
        //println("Ucze sie licytowac...")
        inicjalizacja_zestawu_naukowego_czy_licytowac3()
        losowanie_wag()
        nauka(ile_epok)
    }
    fun czy_licytowac(Produkty_w_magazynie1:Int,Aktualna_produkcja1:Int,Wymagana_ilosc_produktow1:Int,Produkty_w_magazynie2:Int,Aktualna_produkcja2:Int,Wymagana_ilosc_produktow2:Int,Termin:Int):Boolean{
        var Czy_licytowac=false
        var Produkty_w_magazynie1_double=Produkty_w_magazynie1.toDouble()/1000
        //println("parametry czy licytowac")
        //println("Produkty_w_magazynie1_double $Produkty_w_magazynie1_double")
        test[0][0] = Produkty_w_magazynie1_double //produkty w magazynie1
        //var Aktualna_produkcja1_double=Aktualna_produkcja1.toDouble()/1000
        //println("Aktualna_produkcja1_double ${Aktualna_produkcja1_double}")
        //test[0][1] = Aktualna_produkcja1_double //aktualna produkcja1
        var Wymagana_ilosc_produktow1_double=Wymagana_ilosc_produktow1.toDouble()/1000
        //println("Wymagana_ilosc_produktow1_double $Wymagana_ilosc_produktow1_double")
        test[0][1] = Wymagana_ilosc_produktow1_double //wymagana ilosc produktow1
        var Produkty_w_magazynie2_double=Produkty_w_magazynie2.toDouble()/1000
        //println("Produkty_w_magazynie2_double $Produkty_w_magazynie2_double")
        test[0][2] = Produkty_w_magazynie2_double //produkty w magazynie2
        //var Aktualna_produkcja2_double=Aktualna_produkcja2.toDouble()/1000
        //println("Aktualna_produkcja2_double $Aktualna_produkcja2_double")
        //test[0][4] = 0.1 //aktualna produkcja2
        var Wymagana_ilosc_produktow2_double=Wymagana_ilosc_produktow2.toDouble()/1000
        //println("Wymagana_ilosc_produktow2_double $Wymagana_ilosc_produktow2_double")
        test[0][3] = Wymagana_ilosc_produktow2_double //wymagana ilosc produktow2
        var termin_double=Termin.toDouble()/10
        //println("$termin_double$termin_double")
        test[0][4] = termin_double //termin
        przetworzenie_sygnalu_wejsciowego(test[0])
        //println("${O[4][0]}")
        if(O[4][0]>Prog_licytacji){
            Czy_licytowac=true
        }
        return Czy_licytowac
    }
    fun losowanie_wag() {
        for (i in 0..waga.size - 1)
            for (j in 0..waga[i].size - 1)
                for (k in 0..waga[i][j].size - 1) {
                    waga[i][j][k] = Random.nextDouble(-0.1, 0.1)
                    if (waga[i][j][k] == 0.0) {
                        waga[i][j][k] = 0.01
                    }

                }
    }

    fun przetworzenie_sygnalu_wejsciowego(wej: Array<Double>) {
        for (i in 0..liczba_wejsc) {
            I[1][i] = wej[i]
        }
        for (i in 1..liczba_warstw - 1)
            for (j in 0..liczba_neuronow_w_warstwie[i] - 1) {
                if (i != 1)
                    I[i][j] = 0.0
                for (k in 0..liczba_neuronow_w_warstwie[i - 1] - 1) {
                    I[i][j] += O[i - 1][k] * waga[i][j][k]
                    O[i][j] = 1.0 / (1.0 + exp(beta * (-I[i][j])))
                }

            }
    }

    fun Blad_na_wyjsciach(wu: Int) {
        for (j in 0..liczba_warstw - 1) {
            B[liczba_warstw - 1][j] = Wy[wu][j] - O[liczba_warstw - 1][j]
        }
    }

    fun Blad_na_neuronach(wu: Int) {
        var i = liczba_warstw - 1
        //warstwa wyjsciowa
        for (j in 0..liczba_neuronow_w_warstwie[i] - 1) {
            E[i][j] = (Wy[wu][j] - O[i][j]) * O[i][j] * (1.0 - O[i][j])
            //warstwy ukryte
            for (i in liczba_warstw - 2 downTo 1 step 1) {
                for (j in 0..liczba_neuronow_w_warstwie[i] - 1) {
                    E[i][j] = 0.0
                    for (k in 0..liczba_neuronow_w_warstwie[liczba_warstw - 1] - 1) {
                        E[i][j] += O[i][j] * (1.0 - O[i][j]) * E[i + 1][k] * waga[i + 1][k][j]
                    }

                }
            }
        }
    }

    fun adaptacja_wag() {
        for (i in 1..liczba_warstw - 1) {
            for (j in 0..liczba_neuronow_w_warstwie[i] - 1)
                for (k in 0..liczba_neuronow_w_warstwie[i - 1] - 1) {
                    waga2[i][j][k] = waga[i][j][k]
                    waga[i][j][k] += eta * E[i][j] * O[i - 1][k] + alfa * (waga[i][j][k] - waga1[i][j][k])
                    waga1[i][j][k] = waga2[i][j][k]
                }
        }
    }

    fun obliczenie_bledu_sieci(wu: Int) {
        var RMS = 0.0
        for (j in 0..liczba_neuronow_w_warstwie[liczba_warstw - 1] - 1) {
            RMS += (Wy[wu][j] - O[liczba_warstw - 1][j]) * (Wy[wu][j] - O[liczba_warstw - 1][j])
            ERMS = sqrt(RMS / (ile_wek * liczba_neuronow_w_warstwie[liczba_warstw - 1]))
        }

    }

    fun nauka(liczba_epok: Int) {
        for (i in 0..liczba_epok) {
            var losowa = Random.nextInt(0, liczba_przykladow-1)
            przetworzenie_sygnalu_wejsciowego(wej[losowa])
            Blad_na_wyjsciach(losowa)
            Blad_na_neuronach(losowa)
            adaptacja_wag()
            //obliczenie_bledu_sieci(losowa)
        }
    }

    fun test_sieci() {
        przetworzenie_sygnalu_wejsciowego(test[0])
        println("0-ssak, 1-ptak, 2-ryba, 3-gad")
        println("pies")
        for (i in 0..liczba_neuronow_w_warstwie[liczba_warstw - 1] - 1) {
            println("O[${liczba_warstw - 1}][$i]=${O[liczba_warstw - 1][i]}")
        }
    }

    fun wyswietl_wartosci_wag() {
        for (i in 0..waga.size - 1)
            for (j in 0..waga[i].size - 1)
                for (k in 0..waga[i][j].size - 1) {
                    println("waga[$i][$j][$k]=${waga[i][j][k]}")
                }
    }
}