ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.2.2"

resolvers += "Zext packages" at "https://maven.pkg.github.com/pyromuffin/_"

lazy val root = (project in file("."))
  .settings(
    name := "Zext-Template",

    autoCompilerPlugins := true,
    libraryDependencies += "com.pyromuffin" %% "zext" % "0.1.0",
    addCompilerPlugin("com.pyromuffin" %% "zobjectifier" % "1.0.6"),
  )
