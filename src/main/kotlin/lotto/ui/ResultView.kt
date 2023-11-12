package lotto.ui

import lotto.domain.LottoWinning
import lotto.domain.Lottos
import java.text.DecimalFormat

object ResultView {
    fun showResult(lottos: Lottos, returnRate: Double) {
        val winningLottos = lottos.value
            .groupBy { it.winning }

        println("당첨 통계")
        println("---------")
        LottoWinning.values()
            .filterNot { it == LottoWinning.Nothing }
            .forEach { winning ->
                val winningCount = winningLottos[winning]?.size ?: 0

                println("${winning.correctCount}개 일치 (${winning.reward}원)- ${winningCount}개")
            }

        println("총 수익률은 ${returnRate.roundDecimal()}입니다.")
    }

    private fun Double.roundDecimal(): String {
        return DecimalFormat("#.##")
            .format(this)
    }
}
