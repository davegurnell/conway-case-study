package conway

/**
  * Implementation of Board that only stores the coordinates of living cells.
  * The largest board we can handle is bounded by the number of living cells, not the size of the 2D space.
  */
case class InfiniteBoard(alive: Set[(Int, Int)]) extends Board {
  override def isAlive(x: Int, y: Int): Boolean =
    alive.contains((x, y))

  override def transform(func: (Board, Int, Int) => Boolean): Board =
    InfiniteBoard(
      alive
        .flatMap { case (i, j) => neighbours(i, j) }
        .filter { case (i, j) => func(this, i, j) }
    )

  override def stringify: String = {
    val xs = alive.map(_._1)
    val ys = alive.map(_._2)
    val x0 = xs.min
    val y0 = ys.min
    val x1 = xs.max
    val y1 = ys.max
    s"$x0,$y0 to $x1,$y1\n" +
      (y0 to y1)
        .map { j =>
          (x0 to x1)
            .map(i => if (isAlive(i, j)) "*" else ".")
            .mkString
        }
        .mkString("\n")
  }

  /** Fun fact #1. Because an InfiniteBoard is essentially just a collection, we can map over it! */
  def map(func: (Int, Int) => (Int, Int)): InfiniteBoard =
    InfiniteBoard(alive.map(func.tupled))

  def translateX(dx: Int): InfiniteBoard = map { case (x, y) => (x + dx, y) }
  def translateY(dy: Int): InfiniteBoard = map { case (x, y) => (x, y + dy) }
  def mirrorX: InfiniteBoard = map { case (x, y)             => (-x, y) }
  def mirrorY: InfiniteBoard = map { case (x, y)             => (x, -y) }

  /** Fun fact #2. Because an InfiniteBoard is a set, we can do set operations on it! */
  def union(that: InfiniteBoard): InfiniteBoard =
    InfiniteBoard(this.alive ++ that.alive)
}

object InfiniteBoard {

  /** Quick and dirty method for creating a Board from a String. No error handling. Use with care. */
  def parse(text: String): InfiniteBoard =
    InfiniteBoard(for {
      (row, j) <- text.split("\n").zipWithIndex.toSet
      (chr, i) <- row.zipWithIndex.toSet if chr == '*'
    } yield (i, j))
}
