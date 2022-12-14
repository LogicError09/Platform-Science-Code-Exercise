package com.example.platformscienceexercise.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

private const val SS_EVEN_MULTIPLIER = 1.5
private const val SS_ODD_MULTIPLIER = 1

class Utils {
    /**
     * Formats bigDecimals to 2 decimals for accuracy
     */
    private fun formatBigDecimal(number: BigDecimal): BigDecimal {
        return number.setScale(2, RoundingMode.FLOOR)
    }

    /**
     * Determines whether a length of a shipment address is even or odd
     */
    private fun isEven(destination: String): Boolean {
        return (destination.length % 2) == 0
    }

    /**
     * Counts the number of vowels in a street name
     */
    private fun vowelsCount(driver: String): Int {
        var vowels = 0
        for (i in driver.lowercase(Locale.getDefault()).indices) {
            val ch = driver[i]
            if (ch == 'a' || ch == 'e' || ch == 'i'
                || ch == 'o' || ch == 'u'
            ) {
                ++vowels
            }
        }
        return vowels
    }

    /**
     * Counts the number of consonants in a street name
     */
    private fun consonantsCount(driver: String): Int {
        var consonants = 0
        for (i in driver.lowercase(Locale.getDefault()).indices) {
            val ch = driver[i]
            if (!(ch == 'a' || ch == 'e' || ch == 'i'
                        || ch == 'o' || ch == 'u')
            ) {
                ++consonants
            }
        }
        return consonants
    }

    /**
     * Extracts the street name from the address provided by combining the second and 3rd word of a shipment address
     */
    private fun extractStreetName(destination: String): String {
        return destination.split(" ")[1] + " " + destination.split(" ")[2]
    }

    /**
     * Get factors of the length of a word leaving out 1
     */
    private fun getFactors(word: String): List<Int> {
        val factors = ArrayList<Int>()
        for (i in 1..word.length) {
            if (word.length % i == 0) {
                if (i != 1) {
                    factors.add(i)
                }
            }
        }
        return factors
    }

    /**
     * Compares 2 lists of factors to determine if they have any factors in common.
     */
    private fun haveCommonFactors(first: List<Int>, second: List<Int>): Boolean {
        return first.intersect(second.toSet()).isNotEmpty()
    }

    /**
     * this method pairs up destinations with their respective sustainability scores,
     * then returns the destination string with the best score
     */
    private fun getDestinationWithBestSS(
        destinations: List<String>,
        scores: ArrayList<BigDecimal>
    ): String {
        var maxScoreDestination: String? = null

        val destinationMap: Map<String, BigDecimal> = destinations.zip(scores).toMap()
        for (key in destinationMap.keys) {
            if ((maxScoreDestination == null) || (destinationMap[key]!! > destinationMap[maxScoreDestination]!!)) {
                maxScoreDestination = key
            }
        }
        return maxScoreDestination.toString()
    }

    /**
     * TOP: SECRET ALGORITHM:
     * Determines the suitability score for each address and returns the right one for the right driver
     */
    fun getDestinationAddressForDriver(driver: String, destinations: List<String>): String {
        val scores = ArrayList<BigDecimal>()
        for (i in destinations.indices) {
            // check if the street name length is even
            if (isEven(extractStreetName(destinations[i]))) {
                // check if they have any factors in common
                if (haveCommonFactors(
                        getFactors(driver),
                        getFactors(destinations[i])
                    )
                ) {
                    //add score x 1.5 + 50% of score x 1.5
                    scores.add(
                        formatBigDecimal((vowelsCount(driver) * SS_EVEN_MULTIPLIER).toBigDecimal()) + formatBigDecimal(
                            (vowelsCount(
                                driver
                            ) * SS_EVEN_MULTIPLIER.div(2)).toBigDecimal()
                        )
                    )
                } else {
                    //add score
                    scores.add(formatBigDecimal((vowelsCount(driver) * SS_EVEN_MULTIPLIER).toBigDecimal()))
                }
            } else {
                // check if they have any factors in common
                if (haveCommonFactors(
                        getFactors(driver),
                        getFactors(destinations[i])
                    )
                ) {
                    //add score x 1.5  + 50% of score x 1.5
                    scores.add(
                        formatBigDecimal((vowelsCount(driver) * SS_EVEN_MULTIPLIER).toBigDecimal()) + formatBigDecimal(
                            (consonantsCount(
                                driver
                            ) * SS_ODD_MULTIPLIER / 2).toBigDecimal()
                        )
                    )
                } else {
                    //add score x 1
                    scores.add(formatBigDecimal((consonantsCount(driver) * SS_ODD_MULTIPLIER).toBigDecimal()))
                }
            }
        }
        return getDestinationWithBestSS(destinations, scores)
    }
}
