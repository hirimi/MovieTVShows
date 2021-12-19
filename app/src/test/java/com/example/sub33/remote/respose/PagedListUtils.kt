package com.example.sub33.remote.respose

import androidx.paging.PagedList
import org.mockito.Mockito

object PagedListUtils {
    @Suppress("UNCHECKED_CAST")
    fun <T: Any> mockingPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        pagedList.apply {
            Mockito.`when`(this[Mockito.anyInt()]).then { invocation ->
                invocation.arguments.first().also {
                    list[it as Int]
                }
            }
            Mockito.`when`(size).thenReturn(list.size)

            return this
        }
    }

}