package controle;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Recomendador implements RecommenderBuilder {
	private UserBasedRecommender recommender;
	
	public UserBasedRecommender getRecommender() {
		return recommender;
	}
	public void setRecommender(UserBasedRecommender recommender) {
		this.recommender = recommender;
	}


	public UserBasedRecommender buildRecommender(DataModel model) throws TasteException {
		
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		
		return recommender;
	}

}
