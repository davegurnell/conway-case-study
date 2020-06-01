package conway

/** Trait representing a board covering a finite rectangle (0,0,width,height). */
trait FiniteBoard extends Board {
  def width: Int
  def height: Int

  final def inBounds(x: Int, y: Int): Boolean =
    x >= 0 && y >= 0 && x < width && y < height

  /** We can write a standard stringify function because we know the width and height. */
  override final def stringify: String =
    (0 to height)
      .map(j => (0 to width).map(i => if (isAlive(i, j)) "*" else ".").mkString)
      .mkString("\n")
}

/** Finite board that stores its cells as a 2D grid -- a nested list of lists. */
case class NestedBoard(cells: List[List[Boolean]]) extends FiniteBoard {
  override def width: Int =
    if (cells.nonEmpty) cells.head.length else 0

  override def height: Int =
    cells.length

  // We treat cells outside of the board as if they're dead:
  override def isAlive(x: Int, y: Int): Boolean =
    inBounds(x, y) && cells(y)(x)

  // Transforming a board is a bit like `map()` except that we need to know about neighbouring cells.
  // This implementation uses two nested map() methods (cf FlatBoard.transform(), which uses flatMap and map).
  override def transform(func: (Board, Int, Int) => Boolean): Board =
    NestedBoard((0 until height).toList.map { j =>
      (0 until width).toList.map { i =>
        func(this, i, j)
      }
    })
}

object NestedBoard {

  /** Quick and dirty method for creating a Board from a String. No error handling. Use with care. */
  def parse(text: String): FiniteBoard =
    NestedBoard(text.split("\n").toList.map(row => row.toList.map(_ == '*')))
}

/** Finite board that stores its cells as a flattened 1D grid. */
case class FlatBoard(width: Int, cells: List[Boolean]) extends FiniteBoard {
  override val height: Int =
    cells.length / width

  // We treat cells outside of the board as if they're dead:
  override def isAlive(x: Int, y: Int): Boolean =
    inBounds(x, y) && cells(x + y * width)

  // Transforming a board is a bit like `map()` except that we need to know about neighbouring cells.
  // This implementation uses flatMap and map to accumulate cells (cf NestedBoard.transform(), which uses two calls to map).
  override def transform(func: (Board, Int, Int) => Boolean): Board =
    FlatBoard(width, (0 until height).toList.flatMap { j =>
      (0 until width).toList.map { i =>
        func(this, i, j)
      }
    })
}

object FlatBoard {

  /** Quick and dirty method for creating a Board from a String. No error handling. Use with care. */
  def parse(text: String): FiniteBoard = {
    val rows = text.split("\n").toList
    FlatBoard(rows.head.length, rows.flatMap(row => row.toList.map(_ == '*')))
  }
}
