package controle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecomendadorCursos {

	private DataModel model;
	private String path;

	
	
	public DataModel getModel() {
		return model;
	}

	public void setModel(DataModel model) {
		this.model = model;
	}

	public DataModel createDataModel(String path) {
		try {
			this.model = new FileDataModel(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;

	}
	
	public Double avalia(Double avaliacao, Double teste) {
		Recomendador r = new Recomendador();
		
		Avaliador a = new Avaliador();
		a.setPercentualAvaliacao(avaliacao); //0.9
		a.setPercentualTeste(teste); //1.0
		return a.avaliador(path, r);
		
	}

	public List<RecommendedItem> recomenda(String path, Integer user, Integer quantidade) {
		
		this.path = path;
		List<RecommendedItem> recommendations = null;

		try {
			DataModel model = this.createDataModel(path);

			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

			GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			recommendations = recommender.recommend(user, quantidade);

		} catch (TasteException e) {
			e.printStackTrace();
		}
		return recommendations;

	}
}
