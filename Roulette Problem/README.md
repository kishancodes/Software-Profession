This is a roulette stimulatior with certain conditions:

1. How many slots there will be in your roulette wheel; this must be an integer between 2 and 200, inclusive
2. How many of the slots will be labeled with 0 or 00; this must be an integer between 0 and 2, inclusive
3. How many times do you want to visit the casino, an integer between 1 and 100,000, inclusive; I will call that number N in the rest of this specification.
4. How many dollars you want to start with each time you simulate a visit to the casino; this must be an integer between 1 and 1,000,000, inclusive.


Implimented using: 

The Martingale strategy, described in the video linked above: you will start by betting $1. If you win your bet, you quit and go home happy. (Not that happy, for reasons that will be obvious after you watch the video.) If you lose, you double the bet and go again. You continue that process until you either run out of money (and your visit is over), or you win, and you voluntarily quit, slightly ahead.


A random strategy: each time you bet, you bet a random amount from $1 to all the money you have left. Each time you bet, each of the possible bets should be equally likely when you pick the amount to bet. You finish with this strategy when you either run out of money, or you have bet 50 times, whichever comes first.


A fixed bet strategy: each time you bet, you bet a fixed amount, from $1 to the amount of money you start with. If the user picks this strategy, you have to also get that fixed amount from the user. You finish with this strategy when you either run out of money (that is, your money dips below the fixed bet size), or you have bet 50 times.


Project Reference - https://www.youtube.com/watch?v=zTsRGQj6VT4

