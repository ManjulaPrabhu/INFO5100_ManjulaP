import java.util.*;

/**
 * Hand class objects represent the players of the game. It consists of methods that implement the actions of the player.
 */
public class Hand extends GroupOfCards {
    final int NUM;
    int shortest = 0;
    int score = 0;

    Hand(int playerNum, int numberOfCards) {
        super(numberOfCards);
        NUM = playerNum;
    }


    void updateScore(int scoreInTrick) {
        score = score + scoreInTrick;
    }


    void setShortest() {
        LinkedHashMap<Integer, Integer> suits = new LinkedHashMap<>();
        int suit0Count = count(0);
        int suit1Count = count(1);
        int suit2Count = count(2);
        int suit3Count = count(3);

        if (suit0Count != 0) suits.put(0, suit0Count);
        if (suit1Count != 0) suits.put(1, suit1Count);
        if (suit2Count != 0) suits.put(2, suit2Count);
        if (suit3Count != 0) suits.put(3, suit3Count);

        if (suits.containsKey(3) && find(14, 3) != (-1) || find(13, 3) != (-1) || find(12, 3) != (-1)) {
            suits.put(3, 100);
        }
        if (suits.containsKey(2)) {
            suits.put(2, 1000);
        }

        if (suits.containsKey(0) && suits.containsKey(1) && suits.get(0) == suits.get(1)) {
            if (suits.get(0) != 0) {
                suits.put(1, 25);
                suits.put(0, 50);
            }
        } else if (suits.containsKey(0) && suits.containsKey(1) && suits.get(1) >= suits.get(0)) {
            if (suits.get(1) != 0)
                suits.put(1, 50);
        }

        List<Integer> rank = new ArrayList<>();
        suits.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> rank.add(entry.getKey()));
        shortest = rank.get(0);


        if (suits.size() == 1) {
            shortest = suits.keySet().iterator().next();
        }


    }

    int count(int suit) {
        int suitCount = 0;
        for (int index = 0; index < getCurrentSize(); index++) {
            if (cards[index].getSuit() == suit) {
                suitCount++;
            }
        }
        return suitCount;
    }

    int find(int num, int suit) {
        for (int i = 0; i < getCurrentSize(); i++) {
            if (cards[i].getNum() == num && cards[i].getSuit() == suit) {
                return i;
            }
        }
        return -1;

    }

    void sort() {
        List<Integer> cardsValueList = new ArrayList<>();
        for (Card card : Arrays.asList(cards))
            cardsValueList.add(13 * card.getSuit() + card.getNum());
        Collections.sort(cardsValueList);
        Card[] sortedCards = new Card[cardsValueList.size()];
        for (Card card : Arrays.asList(cards)) {
            sortedCards[cardsValueList.indexOf(13 * card.getSuit() + card.getNum())] = card;
        }
        cards = sortedCards;

    }


    int getShortest() {
        setShortest();
        return shortest;
    }

    int findHighest(int suit) {
        int highest = 1;
        boolean foundHighest = false;
        for (int i = 0; i < getCurrentSize(); i++) {
            if (cards[i].getSuit() == suit) {
                if (cards[i].getNum() > highest) {
                    highest = i;
                    foundHighest = true;
                }
            }
        }
        if (foundHighest) {
            return highest;
        } else {
            return -1;
        }
    }

    int findLowest(int suit) {
        int lowest = 30;
        boolean foundLowest = false;
        for (int i = 0; i < getCurrentSize(); i++) {
            if (cards[i].getSuit() == suit) {
                if (cards[i].getNum() < lowest) {
                    lowest = i;
                    foundLowest = true;
                }
            }
        }
        if (foundLowest) {
            return lowest;
        } else {
            return -1;
        }
    }

    /**
     * This strategy is written in a way to exploit the game's trick by dropping higher cards when suit not found,
     * and is defined differently from the one prescribed.
     *
     * @param game - the game instance which needs to be updated.
     * @param trick - the current round at the table.
     */
    public void playACard(Game game, Trick trick) {
        if (game.trick == 0 && game.winner == this.NUM) {
            trick.update(NUM, removeCard(0));
            trick.setCurrentSuit(0);
        } else if (game.winner == this.NUM) {
            int shortestSuit = getShortest();
            trick.update(NUM, removeCard(findLowest(shortestSuit)));
            trick.setCurrentSuit(shortestSuit);
        } else {
            if (count(trick.getCurrentSuit()) >= 1) {
                trick.update(NUM, removeCard(findLowest(trick.getCurrentSuit())));
            } else {
                if (find(12, 3) != -1) {
                    trick.update(NUM, removeCard(find(12, 3)));
                } else if (count(2) > 0) {
                    trick.update(NUM, removeCard(findHighest(2)));
                } else {
                    trick.update(NUM, removeCard(0));
                }
            }

        }
    }
}
