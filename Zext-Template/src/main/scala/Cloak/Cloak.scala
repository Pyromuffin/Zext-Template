package Cloak

import Cloak.guy.cloak
import Zext.*
import Zext.exports.*

/*
  There are just three rooms and three objects.

  The Foyer of the Opera House is where the game begins. This empty room has doors to the south and west, also an unusable exit to the north. There is nobody else around.
  The Bar lies south of the Foyer, and is initially unlit. Trying to do anything other than return northwards results in a warning message about disturbing things in the dark.
  On the wall of the Cloakroom, to the west of the Foyer, is fixed a small brass hook.
  Taking an inventory of possessions reveals that the player is wearing a black velvet cloak which, upon examination, is found to be light-absorbent. The player can drop the cloak on the floor of the Cloakroom or, better, put it on the hook.
  Returning to the Bar without the cloak reveals that the room is now lit. A message is scratched in the sawdust on the floor.
  The message reads either "You have won" or "You have lost", depending on how much it was disturbed by the player while the room was dark.
  The act of reading the message ends the game.
  And that's all there is to it...

 */


object guy extends Player {

  val cloak = ~"A black velvet cloak. It's strangely light-absorbent"

  override val name = "Guy"
  override val description = str {

    val clothes = if this contains cloak
      then "You're swaddled in a comfy cloak"
      else "It's a bit chilly. Tiny goose ridges adorn your bare arms."

    "Unassuming, but you already assumed that." + clothes
  }

}


object Foyer extends Room with StartingRoom {

  override val name: String = "Opera House Foyer"
  override val description: StringExpression = once("The floorboards creak underfoot as you enter the room. A shimmer of dust wafts from the half-lit rafters.") +
    "The unremarkable beginning to an otherwise grandiose adventure. There are doors to the east, south, and west"

  instead(going, east, here) Say "On further examination, the door heading east is merely pretending."

  Connect(south, Bar)
  Connect(west, Cloakroom)
}

object Bar extends Room {

  override val name: String = "Bar"
  override val description = "It's pitch dark. You are likely to be eaten by a grue."

}


object Cloakroom extends Room {

  override val name: String = "Cloakroom"
  override val description = "A variety of scarves, mantles, and sashes adorn the walls."

  object not_yours extends Property

  val scarves = ~"An array of zebra patterned tactical scarves" is scenery and not_yours
  val mantles = ~"How did they get all these fireplaces in here?" is scenery and not_yours
  val sashes = ~"Second place winner in the number of sashes competition" is scenery and not_yours

  instead(taking, not_yours) Say "It would be uncouth to take something that is not yours"

  val hook = new Supporter("hook") {

    this is fixed

    override val description = str {
      if this contains cloak then
        "Your cloak hangs dutifully on bifurcate prongs"
      else
        "It longs for the burden of an unspecified garment"
    }
  }

  report(putting, cloak -> hook) Say "You slip the cloak off your shoulders and drape it on the eager tines."
}


object cloak_main extends App {

  Zext.Parser.StartInterpreter(guy, "Cloak")
}