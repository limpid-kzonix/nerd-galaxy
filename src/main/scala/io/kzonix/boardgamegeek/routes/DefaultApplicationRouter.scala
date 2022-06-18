package io.kzonix.boardgamegeek.routes

import akka.http.scaladsl.server.Route

class DefaultApplicationRouter(provider: RouteProvider) {
  def routes: Route = provider.routes
}
