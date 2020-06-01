package conway

import unindent._

/**
  *
  */
object Main extends App {
  val text =
    i"""
    .......
    .......
    ...*...
    ....*..
    ..***..
    .......
    .......
    """

  val board1 = NestedBoard.parse(text)
  val board2 = FlatBoard.parse(text)
  val board3 = InfiniteBoard.parse(text)
  val board4 = board3.union(board3.mirrorX.translateX(15))

  println("Choose an example:")
  println("[1] finite board implemented with nested lists of cells")
  println("[2] finite board implemented with a flat list of cells")
  println("[3] 'infinite' board")
  println("[4] compound 'infinite' board")

  Console.in.readLine.headOption match {
    case Some('1') => Conway.runSimulation(20, board1)
    case Some('2') => Conway.runSimulation(20, board2)
    case Some('4') => Conway.runSimulation(20, board3)
    case Some('3') => Conway.runSimulation(20, board4)
    case _         => () // Do nothing
  }
}
