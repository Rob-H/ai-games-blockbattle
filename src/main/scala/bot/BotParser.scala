package bot

import java.io.{PrintWriter, OutputStream, InputStream}
import java.util.Scanner

import util.Utils

/**
 * BotParser class
 *
 * Main class that will keep reading output from the engine.
 * Will either update the bot state or get actions.
 *
 * User: goodg_000
 * Date: 12.07.2015
 * Time: 2:22
 */
class BotParser(val bot: Bot, private val inputStream: InputStream, private val outputStream: OutputStream) {

  private val currentState: BotState = new BotState

  private val scanner: Scanner = new Scanner(inputStream)
  private val outputWriter = new PrintWriter(outputStream)

  def this(botStarter: Bot) {
    this(botStarter, System.in, System.out)
  }

  def run(): Unit = {
    while (scanner.hasNextLine) {
      parseLine(scanner.nextLine().trim)
    }
  }

  def parseLine(line: String) : Unit = {
    if (line.length == 0) return
    val parts = line.split(" ")

    parts(0) match {
      case "settings" =>
        currentState.updateSettings(parts(1), parts(2))
      case "update" =>
        currentState.updateState(parts(1), parts(2), parts(3))
      case "action" =>
        val moves = bot.getMoves(currentState, java.lang.Long.parseLong(parts(2)))

        if (moves.nonEmpty) {
          outputWriter.println(moves.map(m => Utils.getEnumName(m)).mkString(","))
        }
        else {
          outputWriter.println("no_moves")
        }
        outputWriter.flush()
      case _ =>
        System.err.printf("Unable to parse line '%s'\n", line);
    }

  }
}
