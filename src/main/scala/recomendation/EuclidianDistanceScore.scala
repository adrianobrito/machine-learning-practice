package recomendation

/**
  * Created by adriano on 10/12/16.
  */
object EuclidianDistanceScore {

  def similarityDistance[T](movies:List[Map[String, Any]], person1:Int, person2:Int) : Double = {
    val filterByUserId     = (userId:Int) => {
      (movie:Map[String, Any]) => {
        val userId = movie("userId").asInstanceOf[Int]
        userId == userId
      }
    }

    val person1Movies = movies.filter(filterByUserId(person1))
    val person2Movies = movies.filter(filterByUserId(person2))

    val similarMovies:List[Map[String, Any]] = person1Movies
      .filter( person1Movie => person2Movies.exists(person2Movie => person1Movie("userId") == person2Movie("userId")))
      .map( similarMovie => Map("movieId" -> similarMovie("movieId"), "similarity" -> 1))

    if(similarMovies.length == 0){
      return 0.0
    }

    0.1
  }

}
