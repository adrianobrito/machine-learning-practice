package recomendation

import java.io.InputStream

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest._
import util.JSONUtil

import scala.io.Source

/**
  * Created by adriano on 22/12/16.
  */
class EuclidianDistanceScoreSpec extends FlatSpec with Matchers {

  val euclidianDistanceScore:RecomendationAlgorithm = EuclidianDistanceScore

  def movieList : List[Map[String, Any]] = {
    val recomendationsInputStream:InputStream = getClass.getResourceAsStream("/recomendations.json")
    val recomendationsJson:String             = Source.fromInputStream(recomendationsInputStream).mkString
    val recomendationsMap                     = JSONUtil.toMap[Any](recomendationsJson)

    recomendationsMap("movies").asInstanceOf[List[Map[String, Any]]]
  }

  "An Euclidian Distance Score" should "calculate the similarity between two persons" in {
    val person1UserId:Int             = 1           // Existing user id
    val person2UserId:Int             = 15          // Existing user id
    val person3UserId:Int             = 3           // Existing user id

    val similarityBetween1And2:Double = euclidianDistanceScore.similarityDistance(movieList, person1UserId, person2UserId)
    val similarityBetween1And3:Double = euclidianDistanceScore.similarityDistance(movieList, person1UserId, person2UserId)
    val similarityBetween2And3:Double = euclidianDistanceScore.similarityDistance(movieList, person1UserId, person2UserId)

    similarityBetween1And2 should not equal similarityBetween1And3
    similarityBetween1And2 should not equal similarityBetween2And3
    similarityBetween1And3 should not equal similarityBetween2And3
  }

}
