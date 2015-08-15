package ru.albemuth.samples.scalala

import org.scalatest.FunSuite

import scala.io.Source

/**
 * @author vovan.
 */
class TestDataLoader extends FunSuite {

  test("test load from source 1") {
    val source = Source.fromString("65888,14.78\n51021,11.69")
    assert(List(List(65888.0, 14.78), List(51021.0, 11.69)) === DataLoader.load(source).toList)
  }

  test("test load from source 2") {
    val source = Source.fromString("65888,14.78\n51021,aaa")
    assert(List(List(65888.0, 14.78)) === DataLoader.load(source).toList)
  }

  test("load from file") {
    val values = DataLoader.load("data/data.txt")
    assert(24 === values.length)
//    println(values.length)
//    for (numbers <- values) println(numbers)
  }

}
