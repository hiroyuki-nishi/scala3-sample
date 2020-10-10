import sbt._

object Dependencies {
  // aws
  lazy val awsSdkV2Version = "2.15.7"
  lazy val awsLambdaJavaCore = "com.amazonaws" % "aws-lambda-java-core" % "1.2.1"
  lazy val awsJavaEvents = "com.amazonaws" % "aws-lambda-java-events" % "2.2.7"
  lazy val awsDynamoDB = "software.amazon.awssdk" % "dynamodb" % awsSdkV2Version

  // log
//  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

  // test
  lazy val scalaTestVersion = "3.2.2"
  lazy val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    "org.scalatest" %% "scalatest-wordspec" % scalaTestVersion % "test"
  )

  // cdk
  lazy val awsCdkVersion = "1.67.0"
  lazy val cdkDependencies = Seq(
    "software.amazon.awscdk" % "core" % awsCdkVersion,
    "software.amazon.awscdk" % "lambda" % awsCdkVersion,
    "software.amazon.awscdk" % "dynamodb" % awsCdkVersion
  )

  // presentation
  lazy val presentationDependencies: Seq[ModuleID] = Seq(
    awsLambdaJavaCore,
    awsJavaEvents,
    awsDynamoDB
  ) ++ testDependencies

  // usecase
  lazy val usecaseDependencies: Seq[ModuleID] = Seq(
//    scalaLogging
  ) ++ testDependencies

  // infrastructure
  lazy val dynamoDBDependencies: Seq[ModuleID] = Seq(
    awsDynamoDB
//    scalaLogging
  ) ++ testDependencies
}
