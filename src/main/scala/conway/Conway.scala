package conway

/** Conway's Game rules with no references to how the Board is implemented. */
object Conway {
  def runSimulation(numIterations: Int, initialBoard: Board): Unit = {
    (0 until numIterations).foldLeft(initialBoard) { (board, index) =>
      println("Iteration " + index)
      println(board.stringify)
      nextGeneration(board)
    }
    ()
  }

  def nextGeneration(board: Board): Board =
    board.transform(conwayRules)

  /** The rules of Conway's Game, written in a relatively clear style. */
  def conwayRules(board: Board, x: Int, y: Int): Boolean = {
    def aliveNeighbours(): Int =
      board.neighbours(x, y).count {
        case (x, y) =>
          board.isAlive(x, y)
      }

    if (board.isAlive(x, y)) {
      aliveNeighbours() match {
        case 0 | 1 => false
        case 2 | 3 => true
        case _ => false
    } else {
      aliveNeighbours() match {
        case 0 | 1 | 2 => false
        case 3 => true
        case _ => false
      }
    }
  }
}
