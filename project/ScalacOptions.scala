object ScalacOptions {

  val Common: Seq[String] =
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
      "-Ycache-macro-class-loader:last-modified",  // and macro definitions. This can lead to performance improvements.
    )

}
