package com.grug.kotlin

/**
 * Created by feichen on 2018/5/14.
 */
fun main(args: Array<String>) {
    println("hello world")
    println(max(5, 7))
}

fun max(a: Int, b: Int) = if (a > b) a else b