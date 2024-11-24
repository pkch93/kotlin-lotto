package lotto

class BoughtLotto(
    private val lottos: List<Lotto>,
    private val winningLotto: WinningLotto,
) {
    fun matchResult(): LottoResult =
        LottoResult(
            lottos
                .map { winningLotto.match(it) }
                .groupingBy { it }
                .eachCount()
        )
}

data class LottoResult(
    val rewards: Map<Reward, Int>,
) {
    fun calculateRateOfReturn(): Double {
        val totalRewardMoney = rewards.map { it.key.money * it.value }.sum()
        val totalBoughtMoney = rewards.values.sum() * LOTTO_PRICE
        return totalRewardMoney.toDouble() / totalBoughtMoney
    }
}
