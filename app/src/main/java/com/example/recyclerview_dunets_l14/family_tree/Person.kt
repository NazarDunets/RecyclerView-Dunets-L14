package com.example.recyclerview_dunets_l14.family_tree

class Person(
    val name: String,
    val age: Int,
    val mother: Person? = null,
    val father: Person? = null,
    val siblings: MutableSet<Person> = mutableSetOf()
) {
    override fun toString(): String {
        return this.name
    }

    val isAdult: Boolean
        get() = age >= 18

    /**
     * Accounts for all siblings and older relatives, but current implementation is pretty inefficient
     * Also covers some edge cases previous implementation doesn't
     */
    val numOfRelatives: Int
        get() = allRelatives().size

    /**
     * It's fast, but...
     * Doesn't account for parents of siblings, so it worked only because tree was very simple
     */
    val oldNumOfRelatives: Int
        get() {
            var result = 0
            result += siblings.size
            mother?.also { result += 1 + it.oldNumOfRelatives }
            father?.also { result += 1 + it.oldNumOfRelatives }
            return result
        }

    /**
     *  Returns list of Pair<Person, Generation gap from tree center>
     */
    fun listLeveledTree(n: Int = 0, onlyUp: Boolean = false): List<Pair<Person, Int>> {
        val result: MutableSet<Pair<Person, Int>> = mutableSetOf(Pair(this, n))
        mother?.let { result.addAll(it.listLeveledTree(n + 1)) }
        father?.let { result.addAll(it.listLeveledTree(n + 1)) }
        if (!onlyUp) {
            siblings.forEach {
                result.addAll(it.listLeveledTree(n, true))
            }
        }
        return result.toList().sortedBy { item -> item.second }
    }

    /**
     * Accounts only for DIRECT older relatives
     */
    fun simpleListLeveledTree(n: Int = 0): List<Pair<Person, Int>> {
        val result = mutableListOf(Pair(this, n))
        mother?.also { result.addAll(it.simpleListLeveledTree(n + 1)) }
        father?.also { result.addAll(it.simpleListLeveledTree(n + 1)) }
        return result
    }

    /**
     * Returns Map with all relatives divided into generations
     */
    fun mapLeveledTree(n: Int = 0, onlyUp: Boolean = false): Map<Int, MutableSet<Person>> {
        val result: MutableMap<Int, MutableSet<Person>> = mutableMapOf(n to mutableSetOf(this))
        mother?.also {
            result.anotherMergeReduce(it.mapLeveledTree(n + 1))
        }
        father?.also {
            result.anotherMergeReduce(it.mapLeveledTree(n + 1))
        }
        if (!onlyUp) {
            siblings.forEach {
                result.anotherMergeReduce(it.mapLeveledTree(n, true))
            }
        }
        return result

    }

    private fun allRelatives(onlyUp: Boolean = false): Set<Person> {
        val higherGenSet: MutableSet<Person> = mutableSetOf()
        mother?.also { higherGenSet.add(it) }
        father?.also { higherGenSet.add(it) }
        val result = mutableSetOf<Person>()
        if (!onlyUp) {
            result.addAll(siblings)
            siblings.forEach { higherGenSet.addAll(it.allRelatives(true)) }
        }
        higherGenSet.forEach {
            result.add(it)
            result.addAll(it.allRelatives())
        }
        return result
    }

    //  Unfortunately, Map.merge() requires API Level 24
    private fun <K> MutableMap<K, MutableSet<Person>>.anotherMergeReduce(
        other: Map<K, MutableSet<Person>>,
        reduce: (Set<Person>, Set<Person>) -> MutableSet<Person> = { a, b -> (a + b).toMutableSet() }
    ) {
        for ((key, value) in other) {
            this[key] = this[key]?.let { reduce(value, it) } ?: value
        }
    }

    init {
        siblings.forEach {
            it.siblings.add(this)
        }
    }
}

