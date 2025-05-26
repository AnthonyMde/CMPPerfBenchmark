package com.bc.cmpperfbenchmark

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform