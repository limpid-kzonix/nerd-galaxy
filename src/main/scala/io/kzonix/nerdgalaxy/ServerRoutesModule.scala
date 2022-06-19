package io.kzonix.nerdgalaxy

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import io.kzonix.nerdgalaxy.routes.ServerEndpoints
import io.kzonix.nerdgalaxy.routes.GamesEndpoints
import net.codingwell.scalaguice.ScalaMultibinder

/** Register all available tAPIr endpoints. */
class ServerRoutesModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    val mBinder = ScalaMultibinder.newSetBinder[ServerEndpoints](binder)
    mBinder.addBinding.to[GamesEndpoints]
    ()
  }

}