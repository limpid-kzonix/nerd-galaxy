package io.kzonix.boardgamegeek.routes

import akka.http.scaladsl.server.Route
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import scala.concurrent.Future
import javax.inject.Inject
import sttp.capabilities
import sttp.capabilities.akka.AkkaStreams
import sttp.tapir.server.ServerEndpoint

class DefaultApplicationRouter @Inject() (
    endpoints: Set[ServerEndpoints],
    serverInterpreter: AkkaHttpServerInterpreter,
  ) extends ApplicationRouter {
  private val allEndpoints: List[ServerEndpoint[AkkaStreams with capabilities.WebSockets, Future]] =
    endpoints.flatMap(_.endpoints).toList

  val swaggerEndpoints: Seq[ServerEndpoint[Any, Future]] = SwaggerInterpreter().fromServerEndpoints[Future](
    endpoints = allEndpoints,
    "Board Game Geek API",
    "1.0",
  )

  def routes: Route =
    serverInterpreter.toRoute(allEndpoints ++ swaggerEndpoints)
}
