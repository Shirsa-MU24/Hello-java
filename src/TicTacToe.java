import java.util.Scanner; // Importerar Scanner-klassen för att läsa in data från användaren.

public class TicTacToe { // Definierar huvudklassen för spelet.
    // Spelbrädet representeras som en array med 9 positioner, alla initialt tomma.
    private static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}; 
    
    // Den nuvarande spelaren, startar alltid med 'X'.
    private static char currentPlayer = 'X'; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Skapar en scanner för att läsa användarinmatning.
        boolean playAgain; // Variabel för att avgöra om spelarna vill spela igen.

        do { // Upprepa hela spelet tills spelarna väljer att avsluta.
            resetBoard(); // Återställ spelbrädet inför varje nytt spel.
            boolean gameWon = false; // Flagga för att spåra om spelet är vunnet.

            // Spelet fortsätter tills någon vinner eller brädet blir fullt.
            while (!gameWon && !isBoardFull()) {
                printBoard(); // Skriv ut det aktuella spelbrädet.
                System.out.println("Spelare " + currentPlayer + ", vilken ruta (1-9)?");

                int move = getValidMove(scanner); // Läs in och validera spelarens drag.
                board[move - 1] = currentPlayer; // Uppdatera brädet med spelarens symbol ('X' eller 'O').

                if (isWinningMove()) { // Kontrollera om senaste draget vann spelet.
                    gameWon = true; // Om så är fallet, sätt flaggan för att avsluta spelet.
                    printBoard(); // Skriv ut det slutliga brädet.
                    System.out.println("Spelare " + currentPlayer + " vinner!"); // Meddela vinnaren.
                } else { // Om ingen vinst ännu, byt till nästa spelare.
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            if (!gameWon) { // Om spelet avslutades utan en vinnare, det är oavgjort.
                printBoard(); // Skriv ut det slutliga brädet.
                System.out.println("Oavgjort! Ingen vann.");
            }

            // Fråga spelarna om de vill spela igen.
            System.out.println("Vill ni spela igen? (ja/nej)");
            playAgain = scanner.next().equalsIgnoreCase("ja"); // Sätt `playAgain` till true om svaret är "ja".
        } while (playAgain); // Om spelarna vill spela igen, upprepa spelet.

        System.out.println("Tack för att ni spelade!"); // Avslutningsmeddelande.
        scanner.close(); // Stänger scannern för att frigöra resurser.
    }

    // Återställ spelbrädet till dess ursprungliga tomma tillstånd.
    private static void resetBoard() {
        for (int i = 0; i < board.length; i++) { // Loopar genom varje position i brädet.
            board[i] = ' '; // Sätter varje position till ett blankt utrymme.
        }
        currentPlayer = 'X'; // Återställ startspelaren till 'X'.
    }

    // Skriv ut det aktuella spelbrädet i terminalen.
    private static void printBoard() {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    // Läs in ett giltigt drag från användaren.
    private static int getValidMove(Scanner scanner) {
        int move; // Variabel för spelarens valda drag.
        while (true) { // Fortsätt fråga tills ett giltigt drag anges.
            if (scanner.hasNextInt()) { // Kontrollera att inmatningen är ett heltal.
                move = scanner.nextInt(); // Läs in spelarens drag.
                // Kontrollera att draget är inom intervallet 1–9 och att rutan är tom.
                if (move >= 1 && move <= 9 && board[move - 1] == ' ') {
                    return move; // Returnera det giltiga draget.
                } else {
                    System.out.println("Ogiltigt val. Försök igen."); // Ogiltigt drag.
                }
            } else { // Om inmatningen inte är ett heltal.
                System.out.println("Ange ett nummer mellan 1 och 9."); // Be om korrekt inmatning.
                scanner.next(); // Rensa felaktig inmatning.
            }
        }
    }

    // Kontrollera om det senaste draget resulterade i en vinst.
    private static boolean isWinningMove() {
        // Horisontella vinstmöjligheter (rader).
        for (int i = 0; i < 9; i += 3) { // Loopar genom varje rad.
            if (board[i] == currentPlayer && board[i] == board[i + 1] && board[i] == board[i + 2]) {
                return true; // Om alla tre matchar spelaren, är det en vinst.
            }
        }

        // Vertikala vinstmöjligheter (kolumner).
        for (int i = 0; i < 3; i++) { // Loopar genom varje kolumn.
            if (board[i] == currentPlayer && board[i] == board[i + 3] && board[i] == board[i + 6]) {
                return true; // Om alla tre i kolumnen matchar, är det en vinst.
            }
        }

        // Diagonala vinstmöjligheter.
        if (board[0] == currentPlayer && board[0] == board[4] && board[0] == board[8]) {
            return true; // Huvuddiagonalen (0, 4, 8).
        }

        if (board[2] == currentPlayer && board[2] == board[4] && board[2] == board[6]) {
            return true; // Sidodiagonalen (2, 4, 6).
        }

        return false; // Ingen vinst upptäckt.
    }

    // Kontrollera om spelbrädet är fullt.
    private static boolean isBoardFull() {
        for (char c : board) { // Gå igenom varje ruta på brädet.
            if (c == ' ') { // Om det finns en tom ruta är brädet inte fullt.
                return false;
            }
        }
        return true; // Alla rutor är fyllda.
    }
}
