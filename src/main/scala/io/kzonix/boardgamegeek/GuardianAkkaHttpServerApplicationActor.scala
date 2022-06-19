package io.kzonix.boardgamegeek

object GuardianAkkaHttpServerApplicationActor {

  import akka.actor.typed.scaladsl.Behaviors
  import akka.actor.typed.Behavior
  import akka.actor.typed.SpawnProtocol

  trait Messages
  case object Stop extends Messages

  def apply(): Behavior[SpawnProtocol.Command] =
    Behaviors.setup { context =>
      // Start initial tasks
      // context.spawn(...)
      context.log.info(s"Setup guardian actor '${ this.getClass.getSimpleName }''")

      SpawnProtocol()
    }

}
