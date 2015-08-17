// scalastyle:off
package player

import field.Field

/**
 * User: goodg_000
 * Date: 12.07.2015
 * Time: 1:15
 */
class Player(val name: String) {
  var field: Field = null
  var points: Int = 0
  var combo: Int = 0
}
