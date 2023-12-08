import java.io.File

val lines = File("inputs/day6.in").readLines()

fun toIntList(s: String): List<Long> {
    return s.split(": ")[1].trim().split("\\s+".toRegex()).map { it.toLong() }
}

fun toInt(s: String): List<Long> {
    return listOf(s.split(": ")[1].trim().replace("\\s+".toRegex(), "").toLong())
}

fun parse(fn: (s: String) -> List<Long>): List<Pair<Long, Long>> {
    val time = fn(day7.lines[0])
    val dist = fn(day7.lines[1])
    return time.zip(dist)
}

fun ways(time: Long, dist: Long): Int {
    return (1 until time).map { (time - it) * it }.count { it > dist }
}

fun main() {
    val races1 = day7.parse(::toIntList)
    val part1 = races1.map { day7.ways(it.first, it.second) }.reduce(Int::times)
    println(part1)

    val races2 = day7.parse(::toInt)
    val part2 = races2.map { day7.ways(it.first, it.second) }.reduce(Int::times)
    println(part2)
}