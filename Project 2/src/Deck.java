/**
 * The Deck class consists of methods that define actions for a Deck.
 */
public class Deck extends GroupOfCards {
    int TOTAL_CARDS = 52;

    Deck() {
        super(52);
        initializeDeck();
    }

    void initializeDeck() {
        for (int cardNumber = 2; cardNumber <= 14; cardNumber++) {
            for (int suitNumber = 0; suitNumber <= 3; suitNumber++) {
                addCard(new Card(cardNumber, suitNumber));
            }
        }
    }

    void shuffle() {
        for (int unshuffled = getCurrentSize(); unshuffled > 0; unshuffled--) {
            int index = (int) (Math.random() * unshuffled);
            Card cardToBeAdded = removeCard(index);
            addCard(cardToBeAdded);
        }
    }

    Card dealCard() {
        return removeCard(0);
    }


}
