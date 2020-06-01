data class Card(
    val pip: String,
    val suit: Char
)

fun main() {
    /*
    [x] Create a program that creates a deck of cards,
    [x] deals two cards from that deck into a hand and
    [x] evaluates that hand of cards by finding the sum of the pips.
    [x] Display the cards in the hand and the total of the pips in the hand.
    [x] Utilize classes,
    [x] collections and
    [x] functions.
    */

    fun createDeck(): MutableSet<Card> {
        val deck = mutableSetOf<Card>()
        val suits = arrayOf('\u2663', '\u2660', '\u2666', '\u2665')
        for (pip in 1..13) {
            for (suit in suits) {
                deck.add(Card(pip.toString(), suit))
            }
        }
        return deck
    }

    val currentDeck= createDeck()

    fun dealHand(deck: MutableSet<Card> = currentDeck, numOfCards: Int = 2): MutableList<Card> {
        val hand = mutableListOf<Card>()
        for (i in 1..numOfCards) {
            val card = deck.random()
            deck.remove(card)
            hand.add(card)
        }
        return hand
    }

    val myHand = dealHand(currentDeck)

    fun evaluateHand(hand: MutableList<Card>): Int {
        var total = 0
        hand.forEach {
            when {
                it.pip.toInt() >= 10 -> total += 10
                it.pip.toInt() == 1 -> total += 11
                it.pip.toInt() in 2..9 -> total += it.pip.toInt()
            }
        }
        return total
    }

    fun printResults(total: Int, hand: MutableList<Card>) {
        println("You hand was:")
        hand.forEach {
            when {
                it.pip.toInt() == 1 -> println("A${it.suit}")
                it.pip.toInt() in 2..10 -> println("${it.pip}${it.suit}")
                it.pip.toInt() == 11 -> println("J${it.suit}")
                it.pip.toInt() == 12 -> println("Q${it.suit}")
                it.pip.toInt() == 13 -> println("K${it.suit}")
            }
        }
        println("For a total of: $total")
        if (total == 21) println("You win!")
    }
    printResults(evaluateHand(myHand), myHand)
}
