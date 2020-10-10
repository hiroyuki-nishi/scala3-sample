import Dependencies._

lazy val commonSettings = Seq(
  scalaVersion := "0.27.0-RC1",
  scalacOptions := Seq(
    "-deprecation",
    "-feature",
//    "-Ywarn-dead-code",
//    "-Ywarn-unused",
    "-Xfatal-warnings"
  ),
  scalafmtOnCompile in ThisBuild := true,
  test in assembly := {}
)

val assemblySettings = Seq(
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
    case _ => MergeStrategy.first
  },
  assemblyJarName in assembly := s"${name.value}.jar",
  publishArtifact in(Compile, packageBin) := false,
  publishArtifact in(Compile, packageSrc) := false,
  publishArtifact in(Compile, packageDoc) := false
)

lazy val root = (project in file("."))
  .aggregate(
    presentation,
    usecase,
    infrastructure
  )
  .settings(commonSettings: _*)
  .settings(
    commonSettings,
    publishArtifact := false
  )
  .settings(
    commands += Command.command("assemblyAll") { state =>
      "notifyDaysShift / assembly" ::
        state
    }
  )
// domain
lazy val domain = (project in file("modules/domain"))
  .settings(commonSettings: _*)

// presentation
lazy val presentation = (project in file("modules/adapter/presentation"))
  .aggregate(sample)
  .settings(
    publishArtifact := false
  )

lazy val sample = (project in file("modules/adapter/presentation/sample"))
  .dependsOn(usecase, service)
  .settings(commonSettings: _*)
  .settings(assemblySettings: _*)
  .settings(
    libraryDependencies ++= presentationDependencies
  )

// usecase
lazy val usecase = (project in file("modules/usecase"))
  .dependsOn(service)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= usecaseDependencies
  )

// service
lazy val service = (project in file("modules/service"))
  .settings(commonSettings: _*)
  .dependsOn(domain)

// infrastructure
lazy val infrastructure = (project in file("modules/adapter/infrastructure"))
  .settings(commonSettings: _*)
  .aggregate(
    infraDynamoDB
  )

lazy val infraDynamoDB = (project in file("modules/adapter/infrastructure/dynamodb"))
  .settings(commonSettings: _*)
  .settings(assemblySettings: _*)
  .settings(libraryDependencies ++= dynamoDBDependencies)
  .settings(
    parallelExecution in Test := false
  )
  .dependsOn(domain)
