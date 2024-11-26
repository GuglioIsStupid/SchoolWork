
import java.util.Scanner;
import java.util.ArrayList;

public class AlignGame {
    private boolean appRunning = true;
    private Scanner input = new Scanner(System.in);

    private int playerX = 0;
    private int playerY = 0;
    private int playerWidth = 5;
    private boolean playerMovingLeft = false;

    private int playerMinWidth = 1;

    private int score = 0;

    private String[][] gameBoard = new String[10][10];
    private ArrayList<int[]> blocks = new ArrayList<int[]>();

    // Input thread
    private Thread inputThread;

    // We need the thread to read input because Scanner blocks the main thread until it gets input

    private Thread createInputThread() {
        return new Thread(() -> {
            while (appRunning) {
                try {
                    if (input.hasNextLine()) {
                        String line = input.nextLine();
                        if (line.equals("exit")) {
                            appRunning = false;
                        }

                        // If blocks exceed the limit, remove the oldest one and update positions
                        if (blocks.size() >= 5) {
                            blocks.remove(0);
                            playerY++;
                            for (int i = 0; i < blocks.size(); i++) {
                                blocks.get(i)[1]++;
                            }
                        }

                        // Prepare the new block
                        int[] block = new int[3];
                        block[0] = playerX - playerWidth / 2;
                        block[1] = playerY;
                        block[2] = playerWidth;

                        // Check for supported segments
                        boolean[] supported = new boolean[playerWidth];
                        boolean hasSupport = false;
                        boolean skipSupported = false;

                        if (score != 0)
                            for (int i = 0; i < playerWidth; i++) {
                                int blockX = block[0] + i;
                                if (blockX >= 0 && blockX < gameBoard.length && block[1] + 1 < gameBoard[0].length) {
                                    if ("B".equals(gameBoard[blockX][block[1] + 1])) {
                                        supported[i] = true;
                                        hasSupport = true;
                                    }
                                }
                            }
                        else {
                            skipSupported = true;
                        }

                        // if no support and playerwidth is 1, then game over
                        if (!hasSupport && playerWidth == 1) {
                            System.out.println("Game Over! Score: " + score);
                            appRunning = false;
                            break;
                        }

                        // Trim unsupported segments
                        // Basically punishes the player for not aligning the block properly
                        if (!skipSupported)
                            if (!hasSupport) {
                                System.out.println("Game Over! Score: " + score);
                                appRunning = false;
                            } else {
                                int newLeft = block[0];
                                int newRight = block[0] + playerWidth - 1;

                                // Find the first supported segment
                                while (newLeft < block[0] + playerWidth && !supported[newLeft - block[0]]) {
                                    newLeft++;
                                }

                                // Find the last supported segment
                                while (newRight >= block[0] && !supported[newRight - block[0]]) {
                                    newRight--;
                                }

                                block[0] = newLeft;
                                block[2] = newRight - newLeft + 1;

                                playerWidth = block[2];

                                // Check if the block is too small, if so, we game over :(((
                                if (block[2] < playerMinWidth) {
                                    System.out.println("Game Over! Score: " + score);
                                    appRunning = false;
                                    break;
                                }
                            }

                        blocks.add(block);
                        score++;
                        playerY--;

                        if (score % 7 == 0 && playerWidth > playerMinWidth) {
                            playerWidth--;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error reading input");
                }
            }
        });
    }

    public void alignGame() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = " ";
            }
        }

        playerX = gameBoard.length / 2;
        playerY = gameBoard[0].length - 1;
    }

    public void update() {
        // Sleep for 5 frames
        int dt = 1000 / 5;

        if (playerMovingLeft) {
            playerX--;

            if (playerX < 0) {
                playerX = 0;
                playerMovingLeft = false;
            }
        } else {
            playerX++;

            if (playerX >= gameBoard.length) {
                playerX = gameBoard.length - 1;
                playerMovingLeft = true;
            }
        }

        if (inputThread == null) {
            inputThread = createInputThread();
            inputThread.start();
        } else
            if (!inputThread.isAlive()) {
                inputThread = createInputThread();
                inputThread.start();
            }
    
        try {
            Thread.sleep(dt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        // Clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int i = 0; i < gameBoard[0].length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[j][i] = " ";
            }
        }

        for (int i = 0; i < playerWidth; i++) {
            int x = playerX - playerWidth / 2 + i;
            if (x >= 0 && x < gameBoard.length) {
                gameBoard[x][playerY] = "P";
            }
        }

        // Render the blocks
        for (int i = 0; i < blocks.size(); i++) {
            int[] block = blocks.get(i);
            int x = block[0];
            int y = block[1];
            int width = block[2];

            for (int j = 0; j < width; j++) {
                if (x + j >= 0 && x + j < gameBoard.length) {
                    int newY = y;
                    if (y >= 10)
                        newY = 9;
                    gameBoard[x + j][newY] = "B";
                }
            }
        }

        for (int i = 0; i < gameBoard[0].length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print(gameBoard[j][i]);
            }
            System.out.println();
        }

        // Render the score
        System.out.println("Score: " + score);
    }

    public void run() {
        while (appRunning) {
            update();
            render();
        }
    }

    public void reset() {
        playerX = 0;
        playerY = 0;
        playerWidth = 5;
        playerMovingLeft = false;
        score = 0;
        blocks.clear();

        appRunning = true;
    }

    public static void main(String[] args) {
        AlignGame game = new AlignGame();

        char playAgain = 'y';
        while (playAgain == 'y') {
            game.alignGame();
            game.run();

            // stop thread
            game.inputThread.interrupt();

            System.out.println("Game Over! Score: " + game.score);
            System.out.println("Would you like to play again? (y/n)");
            playAgain = game.input.next().charAt(0);

            if (playAgain == 'y') {
                game = new AlignGame();
            }
        }

        game.input.close();

        System.out.println("Thanks for playing!");
    }
}