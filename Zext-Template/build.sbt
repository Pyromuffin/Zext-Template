ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.2.2"

// either use this weird link or setup your own github Personal Access Token
val decoder = java.util.Base64.getDecoder
val token = new String(decoder.decode("Z2hwX1hXa1Q0dlMxZ1k4TkRsZ3c3T0gwT2cwWXBwTXE0bzBiZ1FWaw=="))
val user = new String(decoder.decode("cHlyb211ZmZpbg=="))

val repo = "maven.pkg.github.com/pyromuffin/_"
val url = s"https://$user:$token@$repo"


lazy val root = (project in file("."))
  .settings(
    name := "Zext-Template",
    resolvers += "Zext packages" at url,
    autoCompilerPlugins := true,
    libraryDependencies += "com.pyromuffin" %% "zext" % "0.1.0",
    addCompilerPlugin("com.pyromuffin" %% "zobjectifier" % "1.0.6"),
  )
