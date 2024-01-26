
import kotlin.random.Random

// Класс, представляющий пиццерию
class PizzaCity(private val cityName: String) {
    private var discounts: Int = 0
    private var totalDiscountSum: Double = 0.0
    private var coffeeSold: Int = 0
    private var coffeeRevenue: Double = 0.0
    private var checksShown: Int = 0
    private var checksNotShown: Int = 0
    private var totalSaucesSold: Int = 0
    private var sauceRevenue: Double = 0.0
    private val saucePrice: Map<String, Double> = mapOf("cheese" to 1.5, "tomato" to 1.2)

    fun addDiscounts(discount: Int, sum: Double) {
        discounts += discount
        totalDiscountSum += sum
    }

    fun addCoffeeSold(quantity: Int, revenue: Double) {
        coffeeSold += quantity
        coffeeRevenue += revenue
    }

    fun addChecksShown(quantity: Int) {
        checksShown += quantity
    }

    fun addChecksNotShown(quantity: Int) {
        checksNotShown += quantity
    }

    fun addSauceSold(sauceType: String, quantity: Int) {
        totalSaucesSold += quantity
        sauceRevenue += saucePrice.getValue(sauceType) * quantity
    }

    fun calculateTotalRevenue(): Double {
        return discounts - totalDiscountSum + coffeeRevenue + sauceRevenue
    }

    fun calculateCheckPercentage(): Pair<Double, Double> {
        val totalChecks = checksShown + checksNotShown
        val shownPercent = (checksShown.toDouble() / totalChecks) * 100
        val notShownPercent = (checksNotShown.toDouble() / totalChecks) * 100
        return Pair(shownPercent, notShownPercent)
    }

    fun calculateCoffeePercentage(): Pair<Double, Double> {
        val totalCoffee = coffeeSold
        val coffeePercent = (coffeeSold.toDouble() / totalCoffee) * 100
        val refusalPercent = 100 - coffeePercent
        return Pair(coffeePercent, refusalPercent)
    }

    fun calculateSauceStatistics(): Map<String, Int> {
        val statistics = mutableMapOf<String, Int>()
        for (sauce in saucePrice.keys) {
            statistics[sauce] = totalSaucesSold / saucePrice.getValue(sauce).toInt()
        }
        return statistics
    }

    fun showStatistics(pizzaType: String) {
        println("Статистика для города $cityName:")
        println("Процент показанных чеков: ${calculateCheckPercentage().first}%")
        println("Процент не показанных чеков: ${calculateCheckPercentage().second}%")
        println("Процент проданных кофе: ${calculateCoffeePercentage().first}%")
        println("Процент отказов от кофе: ${calculateCoffeePercentage().second}%")
        println("Самая популярная пицца с кофе: $pizzaType")


        val sauceStatistics = calculateSauceStatistics()
        println("Статистика продаж соусов:")
        for ((sauce, quantity) in sauceStatistics) {
            println("$sauce: $quantity шт.")
        }
        println("Итоговая выручка: ${calculateTotalRevenue()}")

    }
}

fun main() {
    val pizzeria1 = PizzaCity("City1")
    pizzeria1.addDiscounts(5, 50.0)
    pizzeria1.addCoffeeSold(100, 250.0)
    pizzeria1.addChecksShown(80)
    pizzeria1.addChecksNotShown(20)
    pizzeria1.addSauceSold("cheese", 30)
    pizzeria1.addSauceSold("tomato", 20)
    pizzeria1.showStatistics("Margherita")
    println("\n")
    val pizzeria2 = PizzaCity("City2")
    pizzeria2.addDiscounts(8, 70.0)
    pizzeria2.addCoffeeSold(120, 300.0)
    pizzeria2.addChecksShown(90)
    pizzeria2.addChecksNotShown(10)
    pizzeria2.addSauceSold("cheese", 40)
    pizzeria2.addSauceSold("tomato", 25)
    pizzeria2.showStatistics("Pepperoni")
    println("\n")
    // расчет статистики без кофе
    val pizzeria3 = PizzaCity("City3")
    pizzeria3.addDiscounts(6, 60.0)
    pizzeria3.addChecksShown(70)
    pizzeria3.addChecksNotShown(30)
    pizzeria3.addSauceSold("cheese", 35)
    pizzeria3.addSauceSold("tomato", 28)
    pizzeria3.showStatistics("Vegetariana")
}


