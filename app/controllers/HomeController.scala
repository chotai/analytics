package controllers

import java.util.UUID

import javax.inject._
import play.api.libs.ws.{DefaultWSProxyServer, WSClient}
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(wsClient: WSClient, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  private val url = s"https://www.google-analytics.com/batch"

  private val proxyServer = DefaultWSProxyServer(
    host = "127.0.0.1",
    port = 3128,
    principal = Some("squiduser"),
    password = Some("test")
  )

  def send(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>

    val request = s"""{"event":"${UUID.randomUUID().toString}"}"""

    val response = wsClient
      .url(url)
      .addHttpHeaders("Content-Type" -> "application/json")
      .withProxyServer(proxyServer)
      .post(request)

    response.map(r => Results.Status(r.status)(r.body))
  }

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
