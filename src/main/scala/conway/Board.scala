package conway

trait Board {
  def isAlive(x: Int, y: Int): Boolean

  def transform(func: (Board, Int, Int) => Boolean): Board

  final def neighbours(x: Int, y: Int): Set[(Int, Int)] = {
    val neighbours = for {
      j <- y - 1 to y + 1
      i <- x - 1 to x + 1 if i != x || j != y
    } yield (i, j)

    neighbours.toSet
  }

  def stringify: String
}
