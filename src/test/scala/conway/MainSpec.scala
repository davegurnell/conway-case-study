package conway

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class MainSpec extends AnyWordSpec with Matchers {
  "this example test" should {
    "not fail" in {
      1 + 1 shouldBe 3 // dang
    }
  }
}