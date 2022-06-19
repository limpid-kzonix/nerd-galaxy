package io.kzonix.nerdgalaxy.routes

import sttp.capabilities.akka.AkkaStreams
import sttp.capabilities.WebSockets
import sttp.tapir.server.ServerEndpoint
import scala.concurrent.Future

trait ServerEndpoints {
  def endpoints: List[ServerEndpoint[AkkaStreams with WebSockets, Future]]
}
