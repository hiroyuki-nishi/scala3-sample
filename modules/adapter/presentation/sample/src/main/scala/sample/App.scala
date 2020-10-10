package sample

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import software.amazon.awssdk.regions.Region

class App extends RequestHandler[Object, Unit] {
  lazy val REGION: Region = Region.of(sys.env.getOrElse("REGION", ""))

  override def handleRequest(input: Object, context: Context): Unit = {
    println("START")
    (for {
      _ <- Right("Hello World")
    } yield ()) match {
      case Right(_) => println("END")
      case Left(e)  => println("ERROR")
    }
  }
}
