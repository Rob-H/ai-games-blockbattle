package util

/**
 * User: goodg_000
 * Date: 12.07.2015
 * Time: 23:56
 */
object Utils {

  def getEnumName(obj: AnyRef):String = obj.getClass.getSimpleName.split("\\$").last
}
