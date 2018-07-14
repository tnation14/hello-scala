import scala.util.parsing.combinator._


object Main extends App {
	val list: List[Any] = List(
	  "Yerrr!",
	  "a string",
	  732,  // an integer
	  'c',  // a character
	  true, // a boolean value
	  () => "an anonymous function returning a string"
	)

	list.foreach(element => println("Hello " + element))
}