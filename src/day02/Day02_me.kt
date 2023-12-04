package day02

import println
import readInput
import java.lang.RuntimeException
import java.util.*

fun main() {

    fun parseGames(input: List<String>): List<Game> {
        val games = input.map { line ->
            val gameNumber = line.substringAfter("Game").substringBefore(":").trim()

            val sets = line.substringAfter(":").split(';')
            val colorsets = sets.map { cubeset ->
                val separatedSets = cubeset.split(',')

                val buckets = separatedSets.map {
                    val separated = it.trim().split(" ")
                    Bucket(separated[0].toInt(), Color.valueOf(separated[1].uppercase(Locale.getDefault())))
                }

                ColorSet(buckets)
            }

            Game(gameNumber.toInt(), colorsets)
        }

        return games
    }

    fun part1(input: List<String>): Int {
        val games = parseGames(input)

        // posible games 12 R, 13 G, 14 blue
        val ids = games.filter { game ->
            game.colorSets.all { colorSet ->
                colorSet.buckets.all { bucket ->
                     when (bucket.color) {
                         Color.BLUE -> bucket.amount <= 14
                         Color.GREEN -> bucket.amount <= 13
                         Color.RED -> bucket.amount <= 12
                     }
                }
            }
        }.map { it.id }

        return ids.sum()
    }

    fun part2(input: List<String>): Int {
        val games = parseGames(input)
        games[0].colorSets.forEach {
            println("amount: ${it.buckets.size}")
        }

        val powers = games.map { game ->
            val amounts = game.colorSets.map { colorSet ->

                val redAmount = colorSet.buckets.find { it.color == Color.RED }?.amount ?: 0
                val greenAmount = colorSet.buckets.find { it.color == Color.GREEN }?.amount ?: 0
                val blueAmount = colorSet.buckets.find { it.color == Color.BLUE }?.amount ?: 0
                listOf(redAmount, greenAmount, blueAmount)
            }
            val maxRed = amounts.map { it[0] }.max()
            val maxGreen = amounts.map { it[1] }.max()
            val maxBlue = amounts.map { it[2] }.max()

            maxRed * maxGreen * maxBlue
        }
        return powers.sum()
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("/day02/Day02_me_test")
//    check(part1(testInput) == 5)

//    val input = readInput("/day02/Day02_me_test")
    val input = readInput("/day02/Day02_me_real_test")
//    part1(input).println()
    part2(input).println()
}





//                separatedSets.map { bucket ->
//                    println("bucket $bucket")
//
//                    val inputLineRegex = """\d+\s+\w+""".toRegex()
////                    val matches = inputLineRegex.findAll(bucket)
////                    for (match in matches) {
////                        println("value: ${match.value}")
////                    }
//
//                    val (number, word) = inputLineRegex.matchEntire(bucket.trim())?.destructured ?: throw RuntimeException("review: $bucket")
//                    println("number: $number, workd: $word")
//                }
