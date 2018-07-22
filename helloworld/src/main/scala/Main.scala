
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
import scala.util.Try
import ThingsToSayHiTo._

object Webserver {

	def SayHello(): String = {
		val helloList = new ThingsToSayHiTo()

		val response = scala.collection.mutable.ArrayBuffer.empty[String]
		response += "<h1>Things to say hi to</h1>"
		helloList.getThings().foreach(element =>
			response += "<p>Hey " + element + " </p>"
		)

		response.mkString
	}

	def main(args: Array[String]) {

		implicit val system = ActorSystem("my-system")
		implicit val materializer = ActorMaterializer()
		implicit val executionContext = system.dispatcher

		
		def terminate(): Unit = {
		    Try(system.terminate())
		}

		sys.addShutdownHook {
			terminate()
		}

		val route =
		  path("sayhello") {
		    get { 
		    	complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, SayHello()))
		    }
		  }

		val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)
		println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    	StdIn.readLine() // let it run until user presses return
	    bindingFuture
	      .flatMap(_.unbind()) // trigger unbinding from the port
	      .onComplete(_ => system.terminate()) // and shutdown when done
  	}

}