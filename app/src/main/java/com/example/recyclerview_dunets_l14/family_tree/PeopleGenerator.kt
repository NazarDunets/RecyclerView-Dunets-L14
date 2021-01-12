package com.example.recyclerview_dunets_l14.family_tree

class PeopleGenerator {

    fun getMe(): Person {

        val mMM = Person("MMM", 234)
        val pMM = Person("PMM", 245)

        val mPM = Person("MPM", 233)
        val pPM = Person("PPM", 231)

        val mMP = Person("MMP", 239)
        val pMP = Person("PMP", 242)

        val mPP = Person("MPP", 227)
        val pPP = Person("PPP", 229)

        val mM = Person("MM", 99, mMM, pMM)
        val pM = Person("PM", 101, mPM, pPM)

        val mP = Person("MP", 98, mMP, pMP)
        val pP = Person("PP", 102, mPP, pPP)

        val pSM = Person("PSM", 102)
        val sM = Person("SM", 40, mM, pSM)

        val m = Person("M", 43, mM, pM, mutableSetOf(sM))
        val p = Person("P", 45, mP, pP)

        val b1 = Person("b1", 21, m, p)
        val b2 = Person("b2", 9, sM, p)
        val s1 = Person("s1", 14)

        return Person("Me", 16, m, p, mutableSetOf(b1, b2, s1))
    }

}