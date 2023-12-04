package day02

enum class Color { RED, GREEN, BLUE}

data class Bucket(val amount: Int, val color: Color)

data class ColorSet(val buckets: List<Bucket>)

data class Game(val id: Int, val colorSets: List<ColorSet>)
