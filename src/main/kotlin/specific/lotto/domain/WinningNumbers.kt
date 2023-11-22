package specific.lotto.domain

data class WinningNumbers(val mainNumbers: MainNumbers, val bonusNumber: Number) {
    constructor(mainNumbers: List<Int>, bonusNumber: Int) : this(MainNumbers(mainNumbers), Number(bonusNumber))

    init {
        require(!mainNumbers.numbers.contains(bonusNumber)) { "'bonusNumber' should not overlap with 'mainNumber'" }
    }
}

data class MainNumbers(val numbers: Set<Number>) {

    constructor(numbers: List<Int>) : this(numbers.map { Number(it) }.toSet())

    init {
        require(numbers.size == MAIN_NUMBERS_SIZE) { "'numbers' must be $MAIN_NUMBERS_SIZE numbers" }
    }

    companion object {
        const val MAIN_NUMBERS_SIZE = 6
    }
}
