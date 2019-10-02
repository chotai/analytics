package controllers

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class AnalyticsGatlingTest extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:9000")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .userAgentHeader("curl/7.35.0")
    .warmUp("http://localhost:9000/analytics")

  private val analyticsScenario = scenario("Post analytics events performance call")
    .exec(http("Post through Squid")
      .post("/analytics")
      .header("Content-Type", "application/json")
      .check(status is 200)
    )

  setUp(
    analyticsScenario.inject(
      constantUsersPerSec(100) during 20.seconds,
      nothingFor(2.seconds),
      constantUsersPerSec(100) during 20.seconds,
      nothingFor(2.seconds),
      constantUsersPerSec(100) during 20.seconds,
      nothingFor(2.seconds),
      constantUsersPerSec(100) during 20.seconds,
      nothingFor(2.seconds),
      constantUsersPerSec(100) during 20.seconds,
      nothingFor(2.seconds),
      constantUsersPerSec(100) during 20.seconds
    ))
    .protocols(httpProtocol)
}
