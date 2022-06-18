package io.kzonix.boardgamegeek.routes

import akka.http.scaladsl.server.Route

trait RouteProvider {
  def routes: Route
}
