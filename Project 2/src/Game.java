import java.util.ArrayList;
import java.util.List;

/**
 * The Game class consists of methods that are the main controllers of the Game.
 */
public class Game {
    private int PLAYERS;
    int trick = 0;
    Trick tricks[];
    Hand[] hand;
    Deck deckObj = new Deck();
    int winner = 0;
    boolean hearts = false;
    boolean queenOfSpade = false;

    Game(int players) {
        this.PLAYERS = players;
        hand = new Hand[PLAYERS];
        for (int i = 0; i < PLAYERS; i++) {
            hand[i] = new Hand(i, deckObj.TOTAL_CARDS / PLAYERS);
        }
        tricks = new Trick[deckObj.TOTAL_CARDS / PLAYERS];
    }

    private void findPlayerHavingLowestClubs() {
        List<Hand> shortestHands = new ArrayList<>();
        for (int i = 0; i < PLAYERS; i++) {
            shortestHands.add(hand[i]);
        }
        if (shortestHands.size() > 0) {
            int smallNumber = 20;
            for (Hand smallHand : shortestHands) {
                int smallestHandNumber = smallHand.getCard(0).getNum();
                if (smallestHandNumber < smallNumber) {
                    smallNumber = smallestHandNumber;
                    winner = smallHand.NUM;
                }
            }
        }
    }


    void playAGame() {
        initializeTricks();
        deckObj.shuffle();
        int totalCardsInHand = deckObj.TOTAL_CARDS / PLAYERS;
        System.out.println("======================== Dealing Cards ==============================");

        dealCardsToPlayers(totalCardsInHand * PLAYERS);

        System.out.println("========================= Game Begins ==============================");

        while (trick < tricks.length) {
            System.out.println("========================== Trick-" + trick + " ==============================");


            tricks[trick].setHearts(hearts);

            if (trick == 0) {
                findPlayerHavingLowestClubs();
                int startWith = winner;
                int turn = 0;
                while (turn < PLAYERS) {
                    hand[startWith % PLAYERS].playACard(this, tricks[trick]);
                    turn++;
                    startWith++;
                }
                while (deckObj.getCurrentSize() > 0) {
                    tricks[0].update(99, deckObj.removeCard(0));
                }

            } else {
                int startWith = winner;
                int turn = 0;
                while (turn < PLAYERS) {
                    hand[startWith % PLAYERS].playACard(this, tricks[trick]);
                    turn++;
                    startWith++;
                }

            }
            winner = tricks[trick].getWinner();
            computePoints(winner);
            hearts = tricks[trick].getHearts();
            queenOfSpade = tricks[trick].queenofSpade;
            trick++;
        }
        System.out.println("========================== SCORES ==============================");
        printScores();

    }

    private void printScores() {
        for (int i = 0; i < PLAYERS; i++)
            System.out.println("Player " + i + " score " + hand[i].score);
    }

    private void computePoints(int playerNumber) {
        Trick currentTrick = tricks[trick];
        int score = 0;
        for (int i = 0; i < currentTrick.getCurrentSize(); i++) {
            if (currentTrick.getCard(i).suit == 2) {
                score++;
            }
        }
        if (currentTrick.queenofSpade) {
            score = score + 13;
        }
        hand[playerNumber].updateScore(score);
    }

    private void initializeTricks() {
        for (int i = 0; i < deckObj.TOTAL_CARDS / PLAYERS; i++) {
            tricks[i] = new Trick(PLAYERS);
        }
    }

    private void dealCardsToPlayers(int totalCardsToDeal) {
        for (int count = 0; count < totalCardsToDeal; count++) {
            hand[count % PLAYERS].addCard(deckObj.dealCard());
        }

        for (int i = 0; i < PLAYERS; i++) {
            hand[i].sort();
            System.out.println("    Player " + i + " shortest " + hand[i].getShortest());
            hand[i].display();
        }
    }
}


