package com.mikedutuandu.first.demo

import org.springframework.boot.SpringApplication


object DemoApplication {
	@JvmStatic
	fun main(args: Array<String>) {
		SpringApplication.run(DemoApplication::class.java, *args)
	}
}