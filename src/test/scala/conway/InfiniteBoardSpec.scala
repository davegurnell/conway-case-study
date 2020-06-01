package conway

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import unindent._

/** Some half-hearted tests for InfiniteBoard. */
class InfiniteBoardSpec extends AnyWordSpec with Matchers {
  val triangle =
    InfiniteBoard.parse {
      i"""
      ..*
      .**
      """
    }

  "parse" should {
    "work" in {
      triangle shouldBe InfiniteBoard(Set((2, 0), (1, 1), (2, 1)))
    }
  }

  "map" should {
    "work" in {
      triangle.map { case (x, y) => (x + 1, y) } shouldBe InfiniteBoard(
        Set((3, 0), (2, 1), (3, 1))
      )
    }
  }

  "union" should {
    "work" in {
      triangle.union(triangle.map { case (x, y) => (x + 1, y) }) shouldBe InfiniteBoard(
        Set((2, 0), (3, 0), (1, 1), (2, 1), (3, 1))
      )
    }
  }

  "isAlive" should {
    "work in-bounds" in {
      triangle.isAlive(0, 0) shouldBe false
      triangle.isAlive(2, 0) shouldBe true
    }

    "work out-of-bounds" in {
      triangle.isAlive(20, 0) shouldBe false
      triangle.isAlive(0, -20) shouldBe false
    }
  }

  "neighbours" should {
    "work" in {
      triangle.neighbours(0, 0) shouldBe Set(
        (-1, -1),
        (-1, 0),
        (-1, +1),
        (0, -1),
        (0, +1),
        (+1, -1),
        (+1, 0),
        (+1, +1)
      )
    }
  }
}
