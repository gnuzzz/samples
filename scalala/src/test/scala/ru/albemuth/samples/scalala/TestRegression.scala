package ru.albemuth.samples.scalala

import org.scalatest.FunSuite

/**
 * @author vovan.
 */
class TestRegression extends FunSuite {

  test("normal equations") {
    val values = DataLoader.load("data/data.txt").toList
    val theta = Regression.createModel(values)
    println(theta)
  }

}
