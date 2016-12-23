package recomendation

/**
  * Created by adriano.brito on 22/12/2016.
  */
trait RecomendationAlgorithm {

  def similarityDistance(movies:List[Map[String, Any]], person1:Int, person2:Int) : Double

}
