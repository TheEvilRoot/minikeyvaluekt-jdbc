package com.theevilroot.mkv.jdbc

sealed class MkvSelectItem {

    data class Columns(val columns: List<String>) : MkvSelectItem()

    object All : MkvSelectItem()

}