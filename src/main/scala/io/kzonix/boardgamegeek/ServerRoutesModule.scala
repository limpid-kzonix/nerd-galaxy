package io.kzonix.boardgamegeek

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import io.kzonix.boardgamegeek.routes.ServerEndpoints
import io.kzonix.boardgamegeek.routes.GamesEndpoints

class ServerRoutesModule extends AbstractModule with ScalaModule {
  import net.codingwell.scalaguice.ScalaMultibinder

  override def configure(): Unit = {
    val mBinder = ScalaMultibinder.newSetBinder[ServerEndpoints](binder)
    mBinder.addBinding.to[GamesEndpoints]
    ()
  }
}
