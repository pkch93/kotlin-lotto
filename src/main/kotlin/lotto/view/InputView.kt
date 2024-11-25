package lotto.view

import lotto.BonusNumber
import lotto.BoughtLotto
import lotto.Lotto
import lotto.LottoCost
import lotto.WinningLotto

class InputView {
    fun input(): BoughtLotto {
        val lottos = inputMoney()
        val winningLotto = inputWinningLotto()
        println()
        return BoughtLotto(
            lottos = lottos,
            winningLotto = winningLotto,
        )
    }

    private fun inputMoney(): List<Lotto> {
        println("구입금액을 입력해 주세요.")
        val maybeMoney = readlnOrNull()
        val lottoCost = LottoCost(maybeMoney)
        return generateLottos(lottoCost)
    }

    private fun generateLottos(lottoCost: LottoCost): List<Lotto> {
        val boughtLottoAmount = lottoCost.calculateBoughtLottoAmount()
        val lottos = (1..boughtLottoAmount).map { Lotto.auto() }
        printBoughtLottos(lottos)
        return lottos
    }

    private fun printBoughtLottos(lottos: List<Lotto>) {
        lottos.forEach {
            val lottoNumbersString = it.numbers.joinToString(", ") {
                lottoNumber -> lottoNumber.value.toString()
            }
            println("[$lottoNumbersString]")
        }
    }

    private fun inputWinningLotto(): WinningLotto {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val bonusNumber = inputBonusNumber()
        val winningLotto = try {
            val maybeWinningNumbers = readlnOrNull()
            val winningNumbers = maybeWinningNumbers
                ?.split(", ")
                ?.map { it.toInt() }
                ?: throw IllegalArgumentException("지난 주 당첨 번호는 필수입니다.")
            Lotto(winningNumbers)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("지난 주 당첨 번호는 숫자만 입력 가능합니다.")
        }

        return WinningLotto(
            lotto = winningLotto,
            bonusNumber = bonusNumber,
        )
    }

    private fun inputBonusNumber(): BonusNumber {
        println("보너스 볼을 입력해 주세요.")
        val maybeBonusBall = readln()
        return BonusNumber(maybeBonusBall)
    }
}
