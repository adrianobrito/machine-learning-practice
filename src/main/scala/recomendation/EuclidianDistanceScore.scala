package recomendation

import scala.math._

/**
  * Created by adriano on 10/12/16.
  */
object EuclidianDistanceScore extends RecomendationAlgorithm {

  override def similarityDistance(movies:List[Map[String, Any]], person1:Int, person2:Int) : Double = {
    val byUserId = (userId:Int) => {
      (movie:Map[String, Any]) => {
        val currentUserId = movie("userId").asInstanceOf[Int]
        userId == currentUserId
      }
    }

    // We have to know if there's some similar movies between the users
    val person1Movies   = movies.filter(byUserId(person1))
    val person2Movies   = movies.filter(byUserId(person2))
    val bySimilarMovies = (person1Movie:Map[String,_]) => {
      person2Movies.exists(person2Movie => person1Movie("userId") == person2Movie("userId"))
    }

    val hasSimilarMovies:Boolean = person1Movies
      .filter(bySimilarMovies)
      .isEmpty

    if(!hasSimilarMovies){
      return 0
    }

    val commonMovies = (for {
      person1Movie <- person1Movies;
      person2Movie <- person2Movies
      if (person1Movie("movieId") == person2Movie("movieId"))
    } yield {
      pow(person1Movie("rating").asInstanceOf[Double] - person2Movie("rating").asInstanceOf[Double],2)
    })

    // Here we apply the Euclidian Distance Calculation
    1/((for {
        person1Movie <- person1Movies;
        person2Movie <- person2Movies
        if (person1Movie("movieId") == person2Movie("movieId"))
      } yield pow(person1Movie("rating").asInstanceOf[Double] - person2Movie("rating").asInstanceOf[Double],2)).sum
    )
  }

}
