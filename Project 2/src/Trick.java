/**
 * The Trick class consists of methods that keep track of every round on the table.
 */
public class Trick extends GroupOfCards {
    int winner;
    Card winningCard;
    boolean hearts = false;
    boolean queenofSpade = false;


    public void setSuitBroken(boolean suitBroken) {
        this.suitBroken = suitBroken;
    }

    boolean suitBroken = false;
    int currentSuit;

    public int getCurrentSuit() {
        return currentSuit;
    }

    public void setCurrentSuit(int currentSuit) {
        this.currentSuit = currentSuit;
    }


    Trick(int players) {
        super((2 * players) - 1);
    }

    int getWinner() {
        return winner;
    }


    Boolean getHearts() {
        return hearts;
    }

    void setHearts(Boolean value) {
        hearts = value;
    }


    void update(int playerNum, Card card) {
        addCard(card);
        if (isWinner(card) && playerNum != 99) {
            winner = playerNum;
            winningCard = card;
        }
        if (card.getSuit() == 2 && !hearts) {
            hearts = true;
            System.out.println("Hearts is now Broken");
        }
        if (card.getSuit() == 3 && card.getNum() == 12) {
            queenofSpade = true;
        }

        if (card.getSuit() != getCurrentSuit()) {
            setSuitBroken(true);
        }
        if (playerNum == 99) {
            System.out.print("Undelt card       ");
        } else {
            System.out.print("Player " + playerNum + "         ");
        }
        card.display();
    }

    private boolean isWinner(Card card) {
        if (winningCard != null) {
            if ((card.getNum() < winningCard.getNum()) || card.getSuit() != getCurrentSuit()) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

}
