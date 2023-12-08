import java.io.File

private val lines = File("inputs/day8.in").readLines()
private val dirs = lines[0]

private val LR = "LR"
private val START = "AAA"
private val END = "ZZZ"

private fun parse(): Map<String, List<String>> {
    return lines.subList(2, lines.size).map {
        val parts = it.split(" = ")
        val lr = parts[1].split(", ")
        Pair(parts[0], listOf(lr[0].substring(1), lr[1].substring(0, lr[1].length - 1)))
    }.associate { it.first to it.second }
}

private fun part1(network: Map<String, List<String>>) {
    var cur = START
    var k = 0
    while (cur != END) {
        cur = network[cur]!![LR.indexOf(dirs[k % dirs.length])]
        k += 1
    }
    println(k)
}

private fun each(start: String, network: Map<String, List<String>>): Long {
    var cur = start
    var k = 0L
    while (!cur.endsWith("Z")) {
        val index = (k % dirs.length).toInt()
        cur = network[cur]!![LR.indexOf(dirs[index])]
        k += 1
    }
    return k
}

fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}

fun lcm(a: Long, b: Long): Long {
    return a * b / gcd(a, b)
}

private fun part2(network: Map<String, List<String>>) {
    val cur = network.keys.filter { it.endsWith("A") }.map {
        each(it, network)
    }
    println(cur.reduce(::lcm))
}

private fun main() {
    val network = parse()
    part1(network)
    part2(network)
}
