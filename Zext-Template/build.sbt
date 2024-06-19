ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.2.2"

// either use this weird trick or setup your own github Personal Access Token
val salt = Array[Byte](-16, 47, 52, 93, 78, -89, -65, 111, 34, 43, -50, -73, 75, 93, -21, 28, 22, 28, 121, 103, -95, 0, -14, 84, -108, -64, -1, -30, -113, 28, 59, 88, 17, -66, -70, 99, -31, 104, 3, -15)
val salted = Array[Byte](-105, 71, 68, 2, 119, -26, -48, 58, 101, 19, -73, -122, 30, 110, -127, 85, 85, 117, 72, 40, -15, 112, -93, 108, -90, -76, -49, -122, -67, 87, 67, 20, 91, -18, -118, 22, -118, 62, 89, -95)

val unsalted = (salt, salted).zipped.map(_ ^ _).map(_.toByte)
val token = new String(unsalted)

lazy val root = (project in file("."))
  .settings(
    name := "Zext-Template",
    credentials += Credentials("GitHub Package Registry", "maven.pkg.github.com", "pyromuffin", token),
    resolvers += "GitHub Package Registry" at "https://maven.pkg.github.com/pyromuffin/_",


    autoCompilerPlugins := true,
    libraryDependencies += "com.pyromuffin" %% "zext" % "0.1.3",
    addCompilerPlugin("com.pyromuffin" %% "zobjectifier" % "1.0.6"),
  )
