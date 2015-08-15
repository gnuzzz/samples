package ru.albemuth.samples.scalala

import scala.io.Source

/**
 * @author vovan.
 */
object DataLoader {

  def load(source: Source): Iterator[List[Double]] = {
    source.getLines().map(s =>
      s.split(',').toList.map(
        v => try {v.toDouble} catch {case e: NumberFormatException => Double.NaN}
      )
    ).filter(_.forall(!_.isNaN))
  }

  def load(path: String): Iterator[List[Double]] = load(Source.fromFile(path))

}
