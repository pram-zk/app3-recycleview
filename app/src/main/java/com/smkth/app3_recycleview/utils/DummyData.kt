package com.smkth.app3_recycleview.utils

import com.smkth.app3_recycleview.model.Student

object DummyData {
    fun getStudentList(): List<Student> {
        return listOf(
            Student("pram", "191919", "XII TKJ 1"),
            Student("iam", "202020", "XII TKJ 1"),
            Student("nopal", "303030", "XII TKJ 1"),
            Student("rm", "121212", "XII TKJ 1"),
            Student("arya", "909090", "XII TKJ 1"),
            Student("alpian", "404040", "XII TKJ 2"),
            Student("depa", "505050", "XII TKJ 2"),
            Student("roy", "606060", "XII TKJ 4"),
            Student("radit", "707070", "XII TKJ 4"),
            Student("edgar", "808080", "XII TKJ 4")
        )
    }
}