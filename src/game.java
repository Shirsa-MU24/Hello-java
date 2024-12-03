/**
 * Detta program implementerar spelet Tre i rad (Tic Tac Toe).
 * Spelet spelas på en 3x3 spelplan där två spelare turas om att placera sina symboler ('X' eller 'O') på spelplanen.
 * Den första spelaren som får tre av sina symboler i rad (horisontellt, vertikalt eller diagonalt) vinner.
 * Om alla rutor är fyllda utan att någon har vunnit, resulterar spelet i oavgjort.
 * Programmet tillåter spelare att spela flera omgångar genom att starta om spelet när en omgång är avslutad.
 * Steg i programmet:
 * 1. Klassdefinition: TreIEnRad-klassen innehåller hela spelets logik, inklusive spelplanen, turordning, dragval och kontroll av vinnare.
 * 2. Huvudmetodens deklaration: main-metoden är programmets ingångspunkt. Den startar spelet och hanterar omgångarna i en oändlig loop, vilket gör att spelet kan starta om efter varje omgång.
 * 3. Initialisering av spelplanen: Metoden initieraSpelplan() nollställer spelplanen till ett tomt tillstånd innan varje ny omgång.
 * 4. Spelmetodens logik: Metoden spelaSpelet() hanterar själva spelmekaniken, inklusive turordning, dragval, och kontroll om någon vunnit eller om det är oavgjort.
 * 5. Kontroll av giltiga drag: Metoden arGiltigtDrag() kontrollerar om spelaren valt en tom ruta och om draget är inom det giltiga intervallet (1-9).
 * 6. Placering av drag: Metoden laggTillDrag() uppdaterar spelplanen med spelarens symbol (antingen 'X' eller 'O') i den valda rutan.
 * 7. Vinnarkontroll: Metoden kollaVinnare() kollar om någon spelare har fått tre av sina symboler i rad på spelplanen (horisontellt, vertikalt eller diagonalt).
 * 8. Utskrift av spelplanen: Metoden skrivUtSpelplan() skriver ut spelplanens aktuella tillstånd till konsolen så att spelarna kan se det.

 * Programmet använder en oändlig loop i main-metoden för att möjliggöra att spelarna kan spela flera omgångar utan att behöva starta om programmet manuellt.
 *
 * @author Safiullah shir Ali (MU24)
 * @version 1.0
 */


import java.util.Scanner; // Importerar Scanner-klassen för att ta emot användarinput.

public class game { // Huvudklassen som implementerar logiken för tre-i-rad-spelet.

    // Konstanter och variabler
    private static final char EMPTY = ' ';
    private static final int GRID_SIZE = 3;
    private static final char[][] grid = new char[GRID_SIZE][GRID_SIZE];
    private static final Scanner scanner = new Scanner(System.in);

    // Metod: main
    public static void main(String[] args) {
        // Syfte: Startar spelet och ser till att det börjar om efter varje omgång.
        while (true) { // Oändlig loop för att tillåta flera omgångar.
            initializeGrid(); // Nollställer spelplanen inför en ny omgång.
            playGame(); // Kör själva spelets logik.
            System.out.println("Spelet är över! Startar ett nytt spel...");
        }
    }

    // Metod: initializeGrid
    private static void initializeGrid() {
        // Syfte: Nollställer spelplanen till sitt ursprungliga tomma tillstånd.
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = EMPTY; // Sätt varje ruta till konstanten EMPTY (' ').
            }
        }
    }

    // Metod: playGame
    private static void playGame() {
        // Syfte: Hanterar spelets logik, turordning mellan spelarna och kontroll av vinnare.
        char currentPlayer = 'X'; // Första spelaren är 'X'.
        int moves = 0; // Håller koll på antalet gjorda drag.

        while (true) { // Kör tills spelet är slut (antingen vinst eller oavgjort).
            printGrid(); // Visar spelplanens nuvarande tillstånd.
            System.out.print("Spelare " + currentPlayer + ", välj en ruta (1-9): ");
            int choice = scanner.nextInt(); // Läser spelarens val.

            if (!isValidMove(choice)) { // Kontrollera om draget är ogiltigt.
                System.out.println("Ogiltigt drag. Försök igen."); // Meddelar spelaren om felet.
                continue;
            }

            makeMove(choice, currentPlayer); // Gör spelarens drag genom att uppdatera spelplanen.
            moves++; // Ökar räknaren för antal drag.

            if (checkWinner(currentPlayer)) { // Kontrollerar om den aktuella spelaren har vunnit.
                printGrid(); // Visa slutgiltiga spelplanen.
                System.out.println("Spelare " + currentPlayer + " vinner!"); // Meddela om vinst.
                return; // Avsluta metoden och börja om spelet.
            }

            if (moves == 9) { // Om alla rutor är fyllda och ingen har vunnit.
                printGrid(); // Visa slutgiltiga spelplanen.
                System.out.println("Oavgjort!"); // Meddela om oavgjort.
                return; // Avsluta metoden och börja om spelet.
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Växla till nästa spelare.
        }
    }

    // Metod: isValidMove
    private static boolean isValidMove(int choice) {
        // Syfte: Kontrollerar om ett drag är giltigt.
        if (choice < 1 || choice > 9) { 
            return false; // Ogiltigt om det är utanför intervallet.
        }
        int row = (choice - 1) / GRID_SIZE; // Beräkna vilken rad draget motsvarar.
        int col = (choice - 1) % GRID_SIZE; // Beräkna vilken kolumn draget motsvarar.
        return grid[row][col] == EMPTY; // Draget är giltigt om rutan är tom.
    }

    // Metod: makeMove
    private static void makeMove(int choice, char player) {
        // Syfte: Uppdaterar spelplanen med spelarens drag.
        int row = (choice - 1) / GRID_SIZE; // Beräkna rad från spelarens val.
        int col = (choice - 1) % GRID_SIZE; // Beräkna kolumn från spelarens val.
        grid[row][col] = player; // Placera spelarens symbol ('X' eller 'O') i den valda rutan.
    }

    // Metod: checkWinner
    private static boolean checkWinner(char player) {
        // Syfte: Kontrollerar om den angivna spelaren har vunnit.
        for (int i = 0; i < GRID_SIZE; i++) { // Kontrollera rader och kolumner.
            if ((grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) || // Kontrollera rad.
                    (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player)) { // Kontrollera kolumn.
                return true; // Spelaren har vunnit.
            }
        }
        // Kontrollera diagonalerna.
        if ((grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) || // Diagonal från vänster till höger.
                (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player)) { // Diagonal från höger till vänster.
            return true; // Spelaren har vunnit.
        }
        return false; // Ingen vinst hittad.
    }

    // Metod: printGrid
    private static void printGrid() {
        // Syfte: Skriver ut spelplanen i ett lättläst format.
        for (int i = 0; i < GRID_SIZE; i++) { // Loopa genom varje rad.
            for (int j = 0; j < GRID_SIZE; j++) { // Loopa genom varje kolumn i en rad.
                System.out.print(" " + grid[i][j] + " "); // Skriv ut rutan med mellanslag.
                if (j < GRID_SIZE - 1) { // Skriv ut en vertikal avdelare mellan kolumner.
                    System.out.print("|");
                }
            }
            System.out.println(); // Gå till nästa rad.
            if (i < GRID_SIZE - 1) { // Skriv ut en horisontell avdelare mellan rader.
                System.out.println("---+---+---");
            }
        }
    }
}
