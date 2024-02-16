package com.innominds.jetpackcompose.utils


fun calcuateTotalTip(totalBill: Double, tipPercent: Int): Double {

    return if (totalBill > 1 && totalBill.toString()
            .isNotEmpty()
    ) (totalBill * tipPercent) / 100 else 0.0
}

fun calculateTotalPerPerson(
    totalBill: Double,
    splitBy: Int,
    tipPercent: Int
): Double {
    val bill = calcuateTotalTip(totalBill, tipPercent) + totalBill
    return (bill / splitBy)
}
