package Example

import Zext.*
import Zext.exports.*


object flavorBlast extends Player {

  override val name: String = "Flavor Blast"
  override val description: StringExpression = "Little more than sentient fur"

}

object LivingRoom extends Room with StartingRoom {

  override val name: String = "Living Room"
  override val description: StringExpression = "The walls are slowly flexing like they're breathing. A violently colorful door leads east."

  val door = ~"The frame is splotched with a car-crash of conflicting hues." is scenery
  val walls = ~"The rhythmic pulse of the flesh colored walls is oddly soothing" are scenery

  Connect(east, DyingRoom)
}


object DyingRoom extends Room {

  override val name: String = "Dying Room"
  override val description: StringExpression = "Pots of dye are hapahazardly strewn throughout the room."

  val clothes_rack = new Supporter {

    this is fixed
    this aka "rack"

    automaticallyListContents = false

    override val name: String = "Clothes Rack"

    val shirt = ~"Solidified dye in the shape of a tank top"

    override val description = s"${
      if (contents.contains(shirt))
        "A shirt is pinned to a line strewn between two very serious pots."
      else
        "The line hangs limply"
     }"


    report(taking, shirt, this had shirt) Say "Trying not to disturb the pots, you carefully unclip the shirt from the line"
  }

}



object main extends App {


  Zext.Parser.StartInterpreter(flavorBlast, "Example")

}
