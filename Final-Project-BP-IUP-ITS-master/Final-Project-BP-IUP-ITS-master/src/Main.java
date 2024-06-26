import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to Snakes and Ladders!");
        System.out.println();
        System.out.println("The objective of this game is to reach the 100th tile first!");
        System.out.println("Be careful! If you step on a snake tile, you will fall down...");
        System.out.println("But if you step on a ladder tile, you will go up!");
        System.out.println("The snake tiles are colored in RED and ladder tiles in GREEN.");
        System.out.println("The players will be displayed in their respective colors.");
        System.out.println();
        System.out.println("Now, please enter the number of players (max 4).");

        int numPlayers = 0;
        while (true) {
            numPlayers = read.nextInt();
            if (numPlayers > 4) {
                System.out.println("Maximum number of players is 4!");
            } else if (numPlayers <= 0) {
                System.out.println("Number of players cannot be less than 1!");
            } else {
                break;
            }
            System.out.println("Please enter the number of players (max 4).");
        }

        Game game = new Game(numPlayers, 1);
        Dice dice = new Dice(6);

        System.out.println(
                "\n\n" +
                        "Players: " + numPlayers + "\n" +
                        "Board Width: " + game.getBoardWidth() + "\n" +
                        "Board Height: " + game.getNumberOfTiles() / game.getBoardWidth() + "\n" +
                        "Tile Size: " + game.getTileSize()
        );

        game.printTable();

        while (game.isActive()) {
            for (int i = 0; i < numPlayers; i++) {
                Player currPlayer = game.getPlayer(i);
                // Get input
                System.out.println(currPlayer.getName() + "'s turn! \nEnter anything to continue.");
                String rolling = read.next();

                Functions.printLoop("\n", 15);

                // Roll dice and move
                int moveSteps = dice.roll();

                System.out.println("\n" + currPlayer.getName() + " has rolled " + moveSteps);
                int previousCase = currPlayer.getCurrentCase();

                game.movePlayer(i, moveSteps);
                System.out.println(currPlayer.getName() + " has moved from " + (previousCase) + " to " + (currPlayer.getCurrentCase()));

                // Check if player has stepped on a special tile
                while (true) {
                    previousCase = currPlayer.getCurrentCase();
                    int playerPosition = currPlayer.getCurrentCase();
                    Tile tileAtPosition = game.getTile(playerPosition);
                    String tileName = tileAtPosition.getName();

                    System.out.println(
                            game.getTile(currPlayer.getCurrentCase()).getColour() + currPlayer.getName() + " has stepped on a " + game.getTile(currPlayer.getCurrentCase()).getName() + " (Tile: " + (game.getTileIndex(game.getTile(currPlayer.getCurrentCase()))) + ")" + Pallette.ANSI_RESET
                    );

                    if (tileName.equals(TileNames.ladder) || tileName.equals(TileNames.snake)) {
                        game.setPlayerPosition(i, tileAtPosition.getJumpIndex());
                        System.out.println(currPlayer.getName() + " has moved from " + (previousCase) + " to " + (currPlayer.getCurrentCase()));
                    } else if (tileName.equals(TileNames.stunMine)) {
                        currPlayer.setStunned(true);
                        System.out.println(currPlayer.getName() + " is stunned and will miss the next turn.");
                        break;
                    } else {
                        break;
                    }
                }

                // Check if anyone has won
                game.setWinner();

                Player winner = game.getWinner();

                if (winner != null) {
                    System.out.println("The winner is: " + winner.getName() + "!");
                    game.endGame();
                    game.printTable();
                    break;
                }

                System.out.println();
                game.printTable();
            }
        }
    }
}
