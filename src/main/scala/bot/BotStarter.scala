package bot

/**
 * User: goodg_000
 * Date: 12.07.2015
 * Time: 3:09
 */
object BotStarter {
  def main(args: Array[String]) {
    new BotParser(new Bot()).run()
  }
}
