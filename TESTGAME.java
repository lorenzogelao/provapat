package it.uniba.app;
import it.uniba.app.Ships.Ship;
import it.uniba.exceptions.gameExcs.IllegalDifficultyException;
import it.uniba.exceptions.gridExcs.SvelaGrigliaException;
import it.uniba.exceptions.gameExcs.InexistentGameException;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Rappresenta la partita da giocare.
 */
public class Game {
    /**
     * Livello di difficoltà della partita.
     */
    private int levelDifficulty;
    /**
     * Round (turno) della partita.
     */
    private int round;
    /**
     * True se la partita è stata avviata, false altrimenti.
     */
    private boolean inGame;
    /**
     * Nome della difficoltà.
     */
    private String nameDifficulty;
    /**
     * Numero di tentativi.
     */
    private int attempts;
    /**
     * Difficoltà di default.
     */
    static final int DEFAULT_DIFFICULTY = 0;
    /**
     * Difficoltà facile.
     */
    static final int EASY_DIFFICULTY = 1;
    /**
     * Difficoltà media.
     */
    static final int MEDIUM_DIFFICULTY = 2;
    /**
     * Difficoltà difficile.
     */
    static final int HARD_DIFFICULTY = 3;
    /**
     * Numero di tentativi massimo in difficoltà facile.
     */
    public static final int EASY_ATTEMPTS = 50;
    /**
     * Numero di tentativi massimo in difficoltà media.
     */
    public static final int MEDIUM_ATTEMPTS = 30;
    /**
     * Numero di tentativi massimo in difficoltà difficile.
     */
    public static final int HARD_ATTEMPTS = 10;
    /**
     * Grandezza griglia standard.
     */
    public static final int STANDARD_GRID_SIZE = 10;
    /**
     * Grandezza griglia large.
     */
    public static final int LARGE_GRID_SIZE = 18;
    /**
     * Grandezza griglia extralarge.
     */
    public static final int EXTRALARGE_GRID_SIZE = 26;

    /**
     * Costruttore che inizializza {@link #round}, {@link #inGame}, e imposta
     * {@link #levelDifficulty}, {@link #attempts}, {@link #nameDifficulty}
     * a un livello di difficoltà di default.
     */
    public Game() {
        this.round = 0;
        this.inGame = false;
        this.levelDifficulty = DEFAULT_DIFFICULTY;
        this.attempts = EASY_DIFFICULTY;
        this.nameDifficulty = "Facile";
    }

    /**
     * Restituisce il numero del {@link #round turno}.
     * N.B.: il ritorno ha un intervallo ]0, {@link #attempts}].
     * @return Numero del round
     */
    public int getRound() {
        return this.round + 1;
    }

    /**
     * Imposta il numero del {@link #round turno}.
     * @param gameRound Numero del round da impostare
     */
    public void setRound(final int gameRound) {
        this.round = gameRound;
    }

    /**
     * Restituisce il numero rappresentante il {@link #levelDifficulty livello di difficoltà} attualmente selezionato.
     * @return Numero del livello di difficoltà
     */
    public int getLevelDifficulty() {
        return this.levelDifficulty;
    }

    /**
     * Restituisce il {@link #nameDifficulty nome del livello di difficoltà} attualmente selezionato.
     * @return Nome del livello di difficoltà attualmente selezionato
     */
    public String getNameDifficulty() {
        return this.nameDifficulty;
    }

    /**
     * Imposta il {@link #nameDifficulty nome livello di difficoltà} di gioco.
     * @param gameDifficulty Nome della difficoltà scelta
     */
    public void setNameDifficulty(final String gameDifficulty) {
        this.nameDifficulty = gameDifficulty;
    }

    /**
     * Imposta il {@link #attempts numero massimo di tentativi} disponibili per il giocatore.
     * @param numberOfAttempts Numero di tentativi massimo
     */
    public void setAttempts(final int numberOfAttempts) {
        this.attempts = numberOfAttempts;
    }

    /**
     * Restituisce il {@link #attempts numero attuale massimo di tentativi}.
     * @return Tentativi massimi.
     */
    public int getAttempts() {
        return this.attempts;
    }

    /**
     * Imposta lo {@link #inGame stato} della partita.
     * @param gameStatus Stato della partita
     */
    public void setInGame(final boolean gameStatus) {
        this.inGame = gameStatus;
    }

    /**
     * Verifica se la partita è stata avviata o meno.
     * @return True se la partita è stata avviata, false altrimenti
     * @see #inGame
     */
    public boolean getInGame() {
        return this.inGame;
    }
    /**
     * Verifica se il {@link #levelDifficulty livello di difficoltà} è stato impostato.
     * @return true se il livello di difficoltà è stato impostato, false altrimenti
     */
    public boolean levelIsSet() {
        return this.levelDifficulty != 0;
    }

    /**
     * Imposta il {@link #levelDifficulty livello di difficoltà} della partita
     * e il {@link #attempts massimo numero di tentativi} fallibili per ogni difficoltà:
     * Facile ({@link #EASY_DIFFICULTY EASY}): 50 tentativi
     * Medio ({@link #MEDIUM_DIFFICULTY MEDIUM}): 30 tentativi
     * Difficile ({@link #HARD_DIFFICULTY HARD}): 10 tentativi.
     * @param gameLevelDifficulty Livello di difficoltà della partita
     * @param gameAttempts Tentativi massimi effettuabili in partita
     */
    public void setDifficulty(final int gameLevelDifficulty, final int gameAttempts) {
        if (!levelIsSet()) {

                switch (gameLevelDifficulty) {
                    case EASY_DIFFICULTY -> {
                        this.levelDifficulty = EASY_DIFFICULTY;
                        setNameDifficulty("Facile");
                        setAttempts(gameAttempts);
                    }
                    case MEDIUM_DIFFICULTY -> {
                        this.levelDifficulty = MEDIUM_DIFFICULTY;
                        setNameDifficulty("Medio");
                        setAttempts(gameAttempts);
                    }
                    case HARD_DIFFICULTY -> {
                        this.levelDifficulty = HARD_DIFFICULTY;
                        setNameDifficulty("Difficile");
                        setAttempts(gameAttempts);
                    }
                    default -> System.out.println("Comando non valido");
                }
                System.out.println("OK");

        } else {
            System.out.println("Hai già scelto un livello di difficoltà");
        }
    }

    /**
     * Mostra a video la descrizione del gioco e una lista di comandi.
     */
    public void printHelp() {
        String help = """
                \nBenvenuto in BATTLESHIP!
                Questo gioco consiste nel cercare di affondare le navi disposte automaticamente dal sistema, posizionate
                in una griglia di default 10x10.
                La griglia può essere ridimensionata (vedi /standard, /large, /extralarge).
                Il giocatore vince se affonda tutte le navi prima di esaurire tutte le mosse disponibili.
                Le navi totali da affondare sono 10 e sono di 4 tipi:
                - Cacciatorpediniere: 4 esemplari
                - Incrociatore: 3 esemplari
                - Corazzata: 2 esemplari
                - Portaerei: 1 esemplare
                Il numero massimo di tentativi sbagliati dipende dal livello che si sceglie (facile/media/difficile):
                Facile: 50 tentativi, Medio: 30 tentativi, Difficile: 10 tentativi.
                Il numero massimo di tentativi si può impostare anche manualmente (vedi /facile, /medio, /difficile)
                I comandi disponibili sono:
                /gioca: inizia una nuova partita, l'applicazione imposta casualmente le navi e mostra la griglia vuota.
                /esci: chiude l'applicazione.
                /help o /h: mostra una descrizione del gioco e l'elenco dei comandi disponibili.
                /facile o /facile numero: imposta a 50 o a numero il numero massimo di tentativi falliti.
                /medio o /medio numero: imposta a 30 o a numero il numero massimo di tentativi falliti.
                /difficile o /difficile numero: imposta a 10 o a numero il numero massimo di tentativi falliti.
                /mostralivello: mostra il livello di gioco e il numero di massimo di tentativi falliti.
                /mostranavi: mostra, per ogni tipo di nave, la dimensione e il numero di esemplari da affondare.
                /svelagriglia: mostra la griglia con le navi posizionate.
                /tentativi numero: imposta a numero il numero massimo di tentativi falliti.
                /standard: imposta a 10x10 la dimensione della griglia (default).
                /large: imposta a 18x18 la dimensione della griglia.
                /extralarge: imposta a 26x26 la dimensione della griglia.
                /tempo numero: imposta a numero il numero di minuti a disposizione per giocare.
                /mostratempo: mostra il numero di minuti trascorsi nel gioco e il numero di minuti a disposizione.
                /mostragriglia: mostra la griglia con le navi affondate e le sole parti già colpite.
                /mostratentativi: mostra il numero di tentativi già effettuati e il numero di tentativi a disposizione.
                /abbandona: rivela la griglia con le navi posizionate e termina la partita in corso.
                """;

        System.out.println(help);
    }

    /**
     * Mostra tutte le informazioni necessarie del livello, vale a dire
     * {@link #nameDifficulty "nome della difficoltà"}, {@link #levelDifficulty "numero della difficoltà"}
     * e {@link #attempts "numero massimo di tentativi"}.
     */
    public void showLevel() {
        if (levelIsSet()) {
            String level = "Difficolta' " + getNameDifficulty() + "(" + getLevelDifficulty() + ")\n"
            + "Tentativi: " + getAttempts();
            System.out.println(level);
        } else {
            System.out.println("Non hai ancora impostato il livello di difficoltà");
        }

    }

    /**
     * Restituisce i tentativi rimanenti.
     * N.B.: il ritorno e' la differenza tra i tentativi della difficolta' selezionata
     * e il numero del turno, siccome ogni turno corrisponde ad un tentativo effettuato.
     * N.B.: Si sottrae 1 al risultato perche' il turno corrente non si calcola siccome
     * solo un turno dichiarato completato conta come tentativo commesso!
     * @return Tentativi rimanenti.
     * @throws IllegalDifficultyException Se la difficolta' impostata non corrisponde ad un valore legale
     * allora viene lanciata un'eccezione.
     */
    public int getRemainingAttempts() throws IllegalDifficultyException {
        int out = -1;
        try {
            switch (getLevelDifficulty()) {
                case EASY_DIFFICULTY -> {
                    if (getAttempts() == EASY_ATTEMPTS) {
                        out = EASY_ATTEMPTS - getDoneAttempts();
                    } else {
                        out = getAttempts() - getDoneAttempts();
                    }
                }
                case MEDIUM_DIFFICULTY -> {
                    if (getAttempts() == MEDIUM_ATTEMPTS) {
                        out = MEDIUM_ATTEMPTS - getDoneAttempts();
                    } else {
                        out = getAttempts() - getDoneAttempts();
                    }
                }
                case HARD_DIFFICULTY -> {
                    if (getAttempts() == HARD_ATTEMPTS) {
                        out = HARD_ATTEMPTS - getDoneAttempts();
                    } else {
                        out = getAttempts() - getDoneAttempts();
                    }
                }
                default -> throw new IllegalDifficultyException();
            }
        } catch (IllegalDifficultyException exc) {
            exc.printStackTrace();
        }
        return out;
    }

    /**
     * Calcola i tentativi effettauti nella partita corrente.
     * @return Tentativi effettuati.
     */
    public int getDoneAttempts() {
        return getRound() - 1;
    }

    /**
     * Mostra i tentativi massimi.
     * @throws IllegalDifficultyException Se {@link #getRemainingAttempts()}
     * lancia un'eccezione.
     */
    public void showAttempts() throws IllegalDifficultyException {
        String maxAttempts = "Tentativi massimi: "
                + getAttempts()
                + "\nTentativi rimasti: "
                + getRemainingAttempts()
                + "\nTentativi effettuati: "
                + getDoneAttempts();
        System.out.println(maxAttempts);
    }

    /**
     * Questo metodo serve per iniziare una nuova partita,
     * posizionando casualmente in una {@link Grid griglia} le {@link Ship navi} da affondare,
     * e mostrando all'utente una griglia vuota.
     * @see #setInGame(boolean) Impostare lo stato della partita
     * @see #getInGame() Ottenere lo stato della partita
     * @see Grid#showGrid() Mostrare la griglia di gioco
     */
    public void play() {
        if (!getInGame()) {
            setInGame(true);
            Grid userGrid = new Grid();     // griglia dell'utente
            Grid gridToHit = new Grid();    // griglia con le navi da colpire
            gridToHit.ensureRandomFill();   // riempie casualmente la griglia con le navi
            String command;                 // comando inserito dall'utente
            String[] splitted;              // comando diviso in due parti

            System.out.println("Griglia giocatore: ");
            userGrid.showGrid();

            do {
                System.out.println("Seleziona la difficoltà: ");
                chooseDifficulty();
            } while (!levelIsSet());

            //System.out.println("Seleziona la taglia della griglia: ");
            //choseGridSize(gridToHit);

            do {
                command = App.getInput();
                boolean isSplitted = App.checkSplit(command);
                splitted = App.getSplittedCommand(command);

                if (!App.checkSplit(command)) {
                    splitted[0] = command;
                }

                //executes the different commands
                switch (splitted[0]) {
                    case "/help" -> printHelp();
                    case "/esci" -> exit();
                    case "/mostralivello" -> showLevel();
                    case "/mostranavi" -> showShips();
                    case "/svelagriglia" -> {
                        try {
                            if (!getInGame()) {
                                throw new SvelaGrigliaException();
                            }
                            //se game.getInGame()
                            gridToHit.showGrid();
                        } catch (SvelaGrigliaException exc) {
                            exc.printStackTrace();
                        }
                    }
                    case "/mostratentativi" -> {
                        try {
                            if (!getInGame()) {
                                throw new InexistentGameException();
                            } else if (getLevelDifficulty() == -1) {
                                throw new IllegalDifficultyException();
                            }
                            //se game.getInGame() oppure game.getLevelDifficulty() != -1
                            showAttempts();
                        } catch (InexistentGameException | IllegalDifficultyException exc) {
                            exc.printStackTrace();
                        }
                    }
                    //case "/mostratempo" -> showTime();
                    default -> System.out.println("Comando non valido.");
                }

            } while (!splitted[0].equals("/esci"));
        } else {
            System.out.println("Una partita e' gia' in corso!");
        }
    }

    /**
     * Questo metodo chiede al giocatore di selezionare la difficoltà della partita.
     * @see #setDifficulty(int, int) Impostare la difficolta' della partita
     * @see App#checkSplit(String) Controllare se il comando inserito è diviso in due parti
     * @see App#getSplittedCommand(String) Ottenere il comando diviso in due parti
     * @see App#isNumber(String[]) Controllare se la seconda parte del comando e' un numero
     */
    private void chooseDifficulty() {
        String command;
        String[] splitted;

        command = App.getInput();
        boolean isSplitted = App.checkSplit(command);
        splitted = App.getSplittedCommand(command);

        if (!App.checkSplit(command)) {
            splitted[0] = command;
        }

        switch (splitted[0]) {
            case "/facile" -> {
                if (isSplitted) {
                    try {
                        if (App.isNumber(splitted)) {
                            int inputAttempts = Integer.parseInt(splitted[1]);
                            setDifficulty(Game.EASY_DIFFICULTY, inputAttempts);
                        } else {
                            System.out.println("Dopo il comando inserire un numero.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Inserisci un numero di tentativi valido dopo lo spazio.");
                    }
                } else {
                    setDifficulty(Game.EASY_DIFFICULTY, Game.EASY_ATTEMPTS);
                }
            }
            case "/medio" -> {
                if (isSplitted) {
                    try {
                        if (App.isNumber(splitted)) {
                            int inputAttempts = Integer.parseInt(splitted[1]);
                            setDifficulty(Game.MEDIUM_DIFFICULTY, inputAttempts);
                        } else {
                            System.out.println("Dopo il comando inserire un numero.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Inserisci un numero di tentativi valido dopo lo spazio.");
                    }
                } else {
                    setDifficulty(Game.MEDIUM_DIFFICULTY, Game.MEDIUM_ATTEMPTS);
                }
            }
            case "/difficile" -> {
                if (isSplitted) {
                    try {
                        if (App.isNumber(splitted)) {
                            int inputAttempts = Integer.parseInt(splitted[1]);
                            setDifficulty(Game.HARD_DIFFICULTY, inputAttempts);
                        } else {
                            System.out.println("Dopo il comando inserire un numero.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Inserisci un numero di tentativi valido dopo lo spazio.");
                    }
                } else {
                    setDifficulty(Game.HARD_DIFFICULTY, Game.HARD_ATTEMPTS);
                }
            }
            default -> System.out.println("Comando non valido.");
        }
    }

    /**
     * Esce dal gioco e restituisce il controllo al sistema operativo.
     * @see java.util.Scanner#Scanner(java.io.InputStream)
     * @see #equals(Object)
     * @see System#exit(int)
     */
    public void exit() {
        Scanner in = new Scanner(System.in, "UTF-8");
        boolean flag = true;
        while (flag) {
            System.out.println("Sei sicuro di voler uscire dal gioco? (y/n)");
            String userInput = in.nextLine();
            if (userInput.equals("y")) {
                System.out.println("A presto!");
                System.exit(0);
            } else if (userInput.equals("n")) {
                System.out.println("Non sei uscito dal gioco.");
                flag = false;
            } else {
                System.out.println("Inserire un valore di conferma valido! (y/n)");
            }
        }
     }

    /**
     * Mostra a video le {@link Ship navi} rimanenti per ogni categoria di nave.
     * @see java.util.ArrayList
     * @see Grid#getAllShips()
     */
    public void showShips() {

        ArrayList<Ship[]> allShips = Grid.getAllShips();
        String displayShips = "";
        int i = 0;
        final int first = 0;

        displayShips += "|     Categoria      |     |   Dimensione  |     | Navi-rimanenti |\n";
        displayShips += "  PortaAerei:               [+][+][+][+][+]               "
                + allShips.get(i)[first].getShipNumber() + "\n"; i++;
        displayShips += "  Corazzata:                [+][+][+][+]                  "
                + allShips.get(i)[first].getShipNumber() + "\n"; i++;
        displayShips += "  Incrociatore:             [+][+][+]                     "
                + allShips.get(i)[first].getShipNumber() + "\n"; i++;
        displayShips += "  Cacciatorpediniere:       [+][+]                        "
                + allShips.get(i)[first].getShipNumber() + "\n";
        System.out.print(displayShips);
    }
}
