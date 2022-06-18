package io.kzonix.boardgamegeek.routes

import io.kzonix.boardgamegeek.ControllerComponents
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.LazyLogging
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.kzonix.boardgamegeek.model.http.game.Game
import io.kzonix.boardgamegeek.model.http.ApiErrorResponse
import sttp.model.StatusCode

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class GamesRouter @Inject() (controllerComponents: ControllerComponents)(implicit ec: ExecutionContext)
    extends RouteProvider
       with LazyLogging {
  val endpointIO: EndpointIO.Body[String, Game] =
    jsonBody[Game]

  val createGame: Endpoint[String, Game, (StatusCode, ApiErrorResponse), Game, Any] = controllerComponents
    .secureEndpoint
    .in("games" / ":start")
    .post
    .in(endpointIO)
    .out(endpointIO)

  override def routes: Route = {
    logger.info(s"$ec")
    ???
  }
}
