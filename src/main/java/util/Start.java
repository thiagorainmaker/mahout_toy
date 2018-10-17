package util;

import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import controle.RecomendadorCursos;

public class Start {

	public static void main(String[] args) {
		

		RecomendadorCursos rc = new RecomendadorCursos();
		List<RecommendedItem> recommendations = rc.recomenda("cursos.txt", 1, 2);
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
		
		
		
//		Avaliador a = new Avaliador();
//		RecommenderBuilder r = new Recomendador();
//		
//		a.avaliador("cursos.txt", builder);
//		
//		
//		RecomendadorCursos rc = new RecomendadorCursos();
//		List<RecommendedItem> recommendations = rc.recomenda("cursos.txt", 1, 2);
//		

	}

}
