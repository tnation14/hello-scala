import scala.util.parsing.combinator._
import ThingsToSayHiTo._

object Main extends App {

	val helloList = new ThingsToSayHiTo()

	helloList.getThings().foreach(element =>
		println("Hey " + element)
	)
}