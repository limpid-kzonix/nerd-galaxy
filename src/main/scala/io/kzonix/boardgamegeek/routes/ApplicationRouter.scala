package io.kzonix.boardgamegeek.routes

import akka.http.scaladsl.server.Route

trait ApplicationRouter {
  def routes: Route
}
