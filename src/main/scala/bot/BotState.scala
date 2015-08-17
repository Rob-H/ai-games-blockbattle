// scalastyle:off
package bot

import field.{Field, ShapeType, Location}
import player.Player

import scala.collection.mutable

/**
 * BotState class
 *
 * In this class all the information about the game is stored.
 *
 * User: goodg_000
 * Date: 12.07.2015
 * Time: 1:20
 */
class BotState {

  private var round: Int = 0
  private var timebank: Int = 0
  private var players: Map[String, Player] = null
  private var myBot: Player = null
  private var opponent: Player = null
  private var currentShape: ShapeType = null
  private var nextShape: ShapeType = null
  private var shapeLocation: Location = null

  private var MAX_TIMEBANK: Int = 0
  private var TIME_PER_MOVE: Int = 0
  private var FIELD_WIDTH: Int = 0
  private var FIELD_HEIGHT: Int = 0

  def getOpponent = opponent
  def getMyField  = myBot.field
  def getOpponentField = opponent.field
  def getCurrentShape = currentShape
  def getNextShape = nextShape
  def getShapeLocation = shapeLocation

  def updateSettings(key: String, value: String): Unit = {
    key match {
      case "timebank" =>
        MAX_TIMEBANK = Integer.parseInt(value)
        timebank = MAX_TIMEBANK
      case "time_per_move" =>
        TIME_PER_MOVE = Integer.parseInt(value)
      case "player_names" =>
        players = value.split(",").map(p => p -> new Player(p)).toMap
      case "your_bot" =>
        myBot = players(value)
        opponent = players.values.find(player => !(player.name equals value)).get
      case "field_width" =>
        FIELD_WIDTH = Integer.parseInt(value)
      case "field_height" =>
        FIELD_HEIGHT = Integer.parseInt(value)
      case _ => System.err.printf("Cannot parse settings with key \"%s\"\n", key);
    }
  }

  def updateState(player: String, key: String, value: String): Unit = {
    key match {
      case "round" =>
        round = Integer.parseInt(value)
      case "this_piece_type" =>
        currentShape = ShapeType(value)
      case "next_piece_type" =>
        nextShape = ShapeType(value);
      case "row_points" =>
        players(player).points = Integer.parseInt(value)
      case "combo" =>
        players(player).combo = Integer.parseInt(value)
      case "field" =>
        players(player).field = new Field(this.FIELD_WIDTH, this.FIELD_HEIGHT, value)
      case "this_piece_position" =>
        val xy = value.split(",")
        shapeLocation = Location(Integer.parseInt(xy(0)), Integer.parseInt(xy(1)))
      case _ => System.err.printf("Cannot parse updates with key \"%s\"\n", key);
    }
  }

}
