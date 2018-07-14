import scala.util.parsing.combinator._

class ThingsToSayHiTo() {
	private var _things: List[String] =  List(
	  "Mr. Postman!",
	  "ma (Hey ma), wassup (wassup).",
	  "Ms. Parker!"
	)

	def getThings() = _things
}

object Main extends App {

	val helloList = new ThingsToSayHiTo()

	helloList.getThings().foreach(element =>
		println("Hey " + element)
	)
}