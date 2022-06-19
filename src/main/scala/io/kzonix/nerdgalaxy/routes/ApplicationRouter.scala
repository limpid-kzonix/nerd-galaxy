package io.kzonix.nerdgalaxy.routes

import akka.http.scaladsl.server.Route

trait ApplicationRouter {
  def routes: Route
}
