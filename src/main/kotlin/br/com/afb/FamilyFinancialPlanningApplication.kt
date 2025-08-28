package br.com.afb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FamilyFinancialPlanningApplication

fun main(args: Array<String>) {
	runApplication<FamilyFinancialPlanningApplication>(*args)
}
