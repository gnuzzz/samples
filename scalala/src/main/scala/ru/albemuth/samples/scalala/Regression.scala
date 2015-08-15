package ru.albemuth.samples.scalala

import scalala.library.LinearAlgebra
import scalala.tensor.dense.DenseMatrix

/**
 * @author vovan.
 */
object Regression {

  def createModel(data: List[List[Double]]): List[Double] = {
    val rows = data.size
    val columns = if (rows > 0) data.head.size else  0
    if (rows == 0 || columns == 0) throw new IllegalArgumentException("Invalid data: " + rows + " rows and " + columns + " columns")
    // Создаем из двумерного массива данных одномерный массив, сгруппированный по столбцам
    // Т.е. из ((1, 2), (3, 4)) создаем (1, 3, 2, 4)
    // Вот такой вот странный формат данных DenseMatrix хочет на вход
    // работает только для данных размерности M x 2
    val values = data.map(_.head) ++ data.map(_.tail.head)

    val dataMatrix = new DenseMatrix(rows, columns, values.toArray)

    // Создаем матрицу X - данные без последнего столбца (в котором хранятся значения y).
    // Первый столбец - единицы (для x0).
    val x = DenseMatrix.ones[Double](rows, columns)
    x(0 to rows - 1, 1 to columns - 1) := dataMatrix(0 to rows - 1, 0 to columns - 2)

    // Создаем вектор y
    val y = dataMatrix(0 to rows - 1, columns - 1 to columns - 1)

    // Вычисляем коэффициенты регрессионной модели - theta = inv(X' * X) * X' * y
    //val theta: DenseMatrix[Double] = LinearAlgebra.inv(x.t * x) * x.t * y
    val dm: DenseMatrix[Double] = x.t * x
    val theta: DenseMatrix[Double] = LinearAlgebra.pinv(dm) * x.t * y
    theta.data.toList
  }

}
