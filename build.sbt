ThisBuild / version := "0.1.0-SNAPSHOT"

val AkkaVersion     = "2.6.19"
val AkkaHttpVersion = "10.2.9"
val TapirVersion    = "1.0.0-RC1"
val CirceVersion    = "0.14.2"
val Http4sVersion   = "1.0.0-M32"

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / scalacOptions ++=
  Seq(
    "-Ymacro-annotations",
    "-Yrangepos",
    "-deprecation",                              // Emit warning and location for usages of deprecated APIs.
    "-encoding",
    "utf-8",                                     // Specify character encoding used by source files.
    "-explaintypes",                             // Explain type errors in more detail.
    "-feature",                                  // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials",                    // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros",             // Allow macro definition (besides implementation and application)
    "-language:higherKinds",                     // Allow higher-kinded types
    "-language:implicitConversions",             // Allow definition of implicit functions called views
    "-unchecked",                                // Enable additional warnings where generated code depends on assumptions.
    // "-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
    "-Xfatal-warnings",                          // Fail the compilation if there are any warnings.
    "-Xlint:adapted-args",                       // Warn if an argument list is modified to match the receiver.
    "-Xlint:constant",                           // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:deprecation",                        // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:delayedinit-select",                 // Selecting member of DelayedInit.
    "-Xlint:doc-detached",                       // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible",                       // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any",                          // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator",               // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-unit",                       // Warn when nullary methods return Unit.
    "-Xlint:option-implicit",                    // Option.apply used implicit view.
    "-Xlint:package-object-classes",             // Class or object defined in package object.
    "-Xlint:poly-implicit-overload",             // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow",                     // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align",                        // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow",              // A local type parameter shadows a type already in scope.
    "-Xlint:unused",                             // TODO check if we still need -Wunused below
    "-Xlint:nonlocal-return",                    // A return statement used an exception for flow control.
    "-Xlint:implicit-not-found",                 // Check @implicitNotFound and @implicitAmbiguous messages.
    "-Xlint:serial",                             // @SerialVersionUID on traits and non-serializable classes.
    "-Xlint:valpattern",                         // Enable pattern checks in val definitions.
    "-Xlint:eta-zero",                           // Warn on eta-expansion (rather than auto-application) of zero-ary method.
    "-Xlint:eta-sam",                            // Warn on eta-expansion to meet a Java-defined functional interface that is not explicitly annotated with @FunctionalInterface.
    "-Xlint:deprecation",                        // Enable linted deprecations.
    "-Werror",                                   // Fail the compilation if there are any warnings.
    "-Wdead-code",                               // Warn when dead code is identified.
    "-Wunused:explicits",                        // Warn if an explicit parameter is unused.
    "-Wextra-implicit",                          // Warn when more than one implicit parameter section is defined.
    "-Wmacros:both",                             // Lints code before and after applying a macro
    "-Woctal-literal",                           // Warn on obsolete octal syntax.
    "-Wnumeric-widen",                           // Warn when numerics are widened.
    "-Wunused:implicits",                        // Warn if an implicit parameter is unused.
    "-Wunused:imports",                          // Warn if an import selector is not referenced.
    "-Wunused:locals",                           // Warn if a local definition is unused.
    "-Wunused:params",                           // Warn if a value parameter is unused.
    "-Wunused:patvars",                          // Warn if a variable bound in a pattern is unused.
    "-Wunused:privates",                         // Warn if a private member is unused.
    "-Wvalue-discard",                           // Warn when non-Unit expression results are unused.
    "-Wunused:linted",
    "-Ybackend-parallelism",
    "8",
    "-Ycache-plugin-class-loader:last-modified", // Enables caching of classloaders for compiler plugins
    "-Ycache-macro-class-loader:last-modified"   // and macro definitions. This can lead to performance improvements.
  )

lazy val commonSettings = commonScalacOptions ++ Seq(
  update / evictionWarningOptions := EvictionWarningOptions.empty
)

lazy val commonScalacOptions = Seq(
  Compile / console / scalacOptions --= Seq(
    "-Wunused:_",
    "-Xfatal-warnings"
  ),
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    name := "board-game-geek",
    libraryDependencies ++= Seq(
      "commons-io"                   % "commons-io"               % "2.11.0",
      "org.apache.commons"           % "commons-math"             % "2.2",
      "org.scala-lang.modules"      %% "scala-java8-compat"       % "1.0.2",
      "org.slf4j"                    % "slf4j-api"                % "1.7.9",
      "org.reactivemongo"           %% "reactivemongo-scalafix"   % "1.1.0-RC4",
      "org.mongodb.scala"           %% "mongo-scala-driver"       % "4.6.0",
      "com.github.pureconfig"       %% "pureconfig"               % "0.17.1",
      "com.typesafe"                 % "config"                   % "1.4.2",
      "com.typesafe.scala-logging"  %% "scala-logging"            % "3.9.5",
      "ch.qos.logback"               % "logback-classic"          % "1.2.11",
      "io.kamon"                    %% "kamon-core"               % "2.5.3",
      "io.kamon"                    %% "kamon-system-metrics"     % "2.5.3",
      "io.kamon"                    %% "kamon-testkit"            % "2.5.3"     % Test,
      "io.circe"                    %% "circe-core"               % CirceVersion,
      "io.circe"                    %% "circe-generic"            % CirceVersion,
      "io.circe"                    %% "circe-parser"             % CirceVersion,
      "io.circe"                    %% "circe-testing"            % CirceVersion,
      "io.circe"                    %% "circe-generic-extras"     % CirceVersion,
      "io.circe"                    %% "circe-jawn"               % CirceVersion,
      "com.typesafe.akka"           %% "akka-slf4j"               % AkkaVersion,
      "com.typesafe.akka"           %% "akka-protobuf-v3"         % AkkaVersion,
      "com.typesafe.akka"           %% "akka-actor-typed"         % AkkaVersion,
      "com.typesafe.akka"           %% "akka-actor"               % AkkaVersion,
      "com.typesafe.akka"           %% "akka-discovery"           % AkkaVersion,
      "com.typesafe.akka"           %% "akka-coordination"        % AkkaVersion,
      "com.typesafe.akka"           %% "akka-cluster-typed"       % AkkaVersion,
      "com.typesafe.akka"           %% "akka-stream"              % AkkaVersion,
      "com.typesafe.akka"           %% "akka-cluster-typed"       % AkkaVersion,
      "com.typesafe.akka"           %% "akka-actor-testkit-typed" % AkkaVersion % Test,
      "com.typesafe.akka"           %% "akka-testkit"             % AkkaVersion % Test,
      "com.typesafe.akka"           %% "akka-http"                % AkkaHttpVersion,
      "com.typesafe.akka"           %% "akka-http-testkit"        % AkkaHttpVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-core"               % TapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle"  % TapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe"         % TapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server"   % TapirVersion
    )
  )
