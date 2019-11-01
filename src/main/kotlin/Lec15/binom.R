# Title     : Binomial Distributions
# Objective : My first R script and to learn Binomial Distribution related functions
# Created by: haleyk
# Created on: 23/10/2019

# discrete binom, input is vectorized
oneTailThreeToss = dbinom(1, size = 3, prob = 0.5)
cat("The chance to get one tail in three tosses is: ", oneTailThreeToss)

# distribution of 5 tosses
fiveTossDistribution = dbinom(0 : 5, size = 5, prob = 0.5)

# Hey! Remember what happens as n grows?
# We could do sampling instead
# Rerun this line a few times and observe the differences

barplot(table(rbinom(30, 5, 0.5)))