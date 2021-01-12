package com.example.recyclerview_dunets_l14

import com.example.recyclerview_dunets_l14.family_tree.PeopleGenerator
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val me = PeopleGenerator().getMe()
        println(me.simpleListLeveledTree())
        println(me.listLeveledTree())
        println(me.oldNumOfRelatives)
        println(me.numOfRelatives)
    }
}