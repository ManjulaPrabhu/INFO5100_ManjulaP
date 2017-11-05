/**
 * The Group of Cards class consists of methods that define the actions associated with a Group of Cards and is the super class from
 * which The Deck class, Hand Class and Trick class are derived.
 */
public class GroupOfCards {
    Card[] cards;
    int currentSize = 0;

    GroupOfCards(int size) {
        cards = new Card[size];
    }

    int getCurrentSize() {
        return currentSize;
    }

    Card getCard(int index) {
        return cards[index];
    }

    void addCard(Card card) {
        cards[currentSize] = card;
        currentSize++;
    }

    Card removeCard(int index) {
        Card reference = cards[index];
        System.arraycopy(cards, index + 1, cards,
                index, currentSize - index - 1);

        cards[currentSize - 1] = null;
        currentSize--;
        return reference;
    }

    void display() {
        for (int i = 0; i < currentSize; i++) {
            cards[i].display();
        }
    }

}
