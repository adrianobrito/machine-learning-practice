package exercises

import java.io.InputStream

import util.JSONUtil

import scala.io.Source

/**
  * Created by adriano.brito on 21/12/2016.
  */
object Recomendations {

  def main(args: Array[String]) {
    val recomendationsInputStream:InputStream = getClass.getResourceAsStream("/recomendations.json")
    val recomendationsJson:String             = Source.fromInputStream(recomendationsInputStream).mkString
    val recomendationsMap                     = JSONUtil.toMap[Any](recomendationsJson)
    val movies:List[Any]                      = recomendationsMap("movies").asInstanceOf[List[Any]]

    println(movies(0))
  }

}
