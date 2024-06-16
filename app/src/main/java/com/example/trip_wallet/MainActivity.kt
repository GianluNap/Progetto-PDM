package com.example.trip_wallet

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.trip_wallet.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val numbers = (1..90).toMutableList()
    private val sortedNumbers = mutableListOf<Int>()
    private val numberPhrases = mapOf(
        //associo ad ogni numero tramite MapOf una stringa che sarebbe la frase che lo rappresenta
        1 to "L’Italia",
        2 to "La piccerella (“La bambina”)",
        3 to "‘A Gatta (“La gatta”)",
        4 to "‘O puorc (“Il Maiale”/”L’uomo poco educato”)",
        5 to "‘a mana (“La mano”)",
        6 to "Chella ca guarda ‘nterra (“Parti intime femminili”)",
        7 to "‘o Vaso (“Il vaso”)",
        8 to "a’ Maronna (“La Madonna”)",
        9 to "‘a figliata (“La prole”)",
        10 to "‘e fasule (“I fagioli”)",
        11 to "‘e suricille (“Piccoli topi”)",
        12 to "‘o surdat (“Il soldato”)",
        13 to "Sant’Antonio",
        14 to "‘o mbriaco (“L’ubriaco”)",
        15 to "‘o guaglione (“Il ragazzo”)",
        16 to "‘o culo (“Il sedere”/”La fortuna”)",
        17 to "‘a disgrazia (“La sfortuna”)",
        18 to "‘o sang (“Il sangue”)",
        19 to "‘a resata (“Una forte risata”)",
        20 to "‘a festa (“La festa”)",
        21 to "‘a femmena annura (“La donna nuda”)",
        22 to "‘o pazzo (“Il pazzo”)",
        23 to "‘o scemo (“Lo scemo”)",
        24 to "‘e gguardie (“Le forze dell’ordine”)",
        25 to "Natale",
        26 to "Nanninella (“Anna, la madre della Vergine Maria”)",
        27 to "‘o Cantero (“Vaso da notte”)",
        28 to "‘E zizze (“Le mammelle della donna”)",
        29 to "‘o pate d’e criature (“Il papà dei bimbi”)",
        30 to "‘E ppalle d’o tenente (“I colpi d’arma da fuoco”)",
        31 to "‘o padrone ‘e casa: (“Il proprietario della casa”)",
        32 to "‘o capitone: (“L’anguilla”)",
        33 to "ll’anne ‘e Cristo (“L’età del Cristo”)",
        34 to "‘a capa (“La testa”)",
        35 to "l’aucelluzz (“L’uccellino”)",
        36 to "‘e castagnelle (“Le nacchere”)",
        37 to "‘o monaco “Il monaco”)",
        38 to "‘e mmazzate (“Le botte”)",
        39 to "‘a funa n’ganna (“La corda tesa al collo”)",
        40 to "‘a paposcia (“L’ernia maschile”)",
        41 to "‘o curtiello (“Il coltello”)",
        42 to "‘o cafè (“Il caffè”)",
        43 to "‘onna pereta fore ‘o barcone (“Donna pettegola affacciata al balcone”)",
        44 to "‘e ccancelle (“Il carcere”)",
        45 to "‘o vino buono (“Il buon vino”)",
        46 to "‘e denare (“Il denaro”)",
        47 to "‘o muorto (“Il morto”)",
        48 to "‘o muorto che parla (“Il morto che parla”)",
        49 to "‘o piezzo e carne (“Il pezzo di carne”)",
        50 to "‘o ppane (“Il pane”)",
        51 to "‘o ciardino (“Il giardino”)",
        52 to "‘a mammà (“La mamma”)",
        53 to "‘o viecchio (“L’anziano”)",
        54 to "‘o cappiello (“Il cappello”)",
        55 to "‘a museca (“La musica”)",
        56 to "‘a caruta (“La caduta”)",
        57 to "‘o scartellato (“L’uomo con la gobba”)",
        58 to "‘o paccotto (“Il pacco”)",
        59 to "‘e pile (“La peluria”)",
        60 to "‘o lamiento (“Le lamentele”)",
        61 to "‘o cacciatore (“Il cacciatore”)",
        62 to "‘o muorto acciso (“Persona assassinata”)",
        63 to "‘a sposa (“La sposa”)",
        64 to "‘a sciammeria (“La giacca per gli eventi importanti”)",
        65 to "‘o chianto (“Il pianto”)",
        66 to "‘e doie zetelle (“Due ragazze nubili”)",
        67 to "‘o totaro int’a chitarra (“Totano nella chitarra”)",
        68 to "‘a zuppa cotta (“Zuppa cotta”)",
        69 to "sott’e ‘ncoppa (“Sottosopra”)",
        70 to "‘o palazzo (“La casa”)",
        71 to "l’ommo ‘e mmer*a",
        72 to "‘a maraviglia (“La meraviglia”)",
        73 to "‘o spitale (“L’ospedale”)",
        74 to "‘a rotta (“La grotta in cui nacque Gesù di Nazareth”)",
        75 to "Pulcinella",
        76 to "‘a funtana (“Fontana”/”Acqua”)",
        77 to "‘e riavulille (“I diavoletti”)",
        78 to "‘a bella figliola (“Una bella ragazza”)",
        79 to "‘o mariuolo (“Il ladro”)",
        80 to "‘a vocca (“La bocca”)",
        81 to "‘e sciure (“I fiori”)",
        82 to "‘a tavula ‘mbandita (“La tavola apparecchiata con sopra molte prelibatezze”)",
        83 to "‘o maletiempo (“Maltempo”)",
        84 to "‘a cchiesa (“Chiesa”)",
        85 to "ll’aneme ‘o priatorio (“Le anime che risiedono nel purgatorio”)",
        86 to "‘a puteca (“La bottega”)",
        87 to "‘e perucchie (“I pidocchi”)",
        88 to "‘e casecavalle (“Il caciocavallo”)",
        89 to "‘a vecchia (“Una donna anziana”)",
        90 to "‘a paura (“La paura”)"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Avevo creato il progetto con un action bar, quindi inserisco questa parte per Nasconderla
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Creo le variabili e le collego ai button nell'xml
        val sorteggia = findViewById<Button>(R.id.button)
        val sorteggiati = findViewById<Button>(R.id.button2)
        val riavviapartita = findViewById<Button>(R.id.button3)

        sorteggia.setOnClickListener {
            // Ho inserito un controllo per controllare se tutti i numeri sono già usciti dalla tombola, se si vengono reinseriti tutti e la partita riinizia
            if (numbers.isEmpty()) {

                numbers.addAll(1..90)
                sortedNumbers.clear()
            }

            // Faccio generare un numero casuale per simulare il numero preso dal paniere della tombola
            val randomIndex = Random.nextInt(numbers.size)
            val randomNumber = numbers.removeAt(randomIndex)
            sortedNumbers.add(randomNumber)

            // Recupera la frase corrispondente al numero sorteggiato
            val phrase = numberPhrases[randomNumber]

            // Mostro a schermo con pop up il numero sorteggiato con la frase
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Numero sorteggiato")
            builder.setMessage("Il numero sorteggiato è: $randomNumber\n\n$phrase")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

            // Mostra il dialog
            builder.create().show()
        }

        // Cliccando su numeri sorteggiati c'è la possibilità di vedere tutti i numeri usciti fino a quel momento
        sorteggiati.setOnClickListener {
            val sortedNumbersString = sortedNumbers.joinToString(", ")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Numeri sorteggiati")
            builder.setMessage("I numeri sorteggiati finora sono: $sortedNumbersString")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }

        // setonclicklistener per il  bottone di riavvio partita che pulisce i numeri sorteggiati
        riavviapartita.setOnClickListener {

            numbers.clear()
            numbers.addAll(1..90)
            sortedNumbers.clear()

            // mostro un pop up per confermare il reset della partita
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Partita Riavviata")
            builder.setMessage("La partita è stata riavviata. Puoi iniziare a sorteggiare di nuovo.")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }
}


