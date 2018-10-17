package controle;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.common.RandomUtils;

public class Avaliador {
	public Double avaliador(String path, RecommenderBuilder builder) {
		Double d = 0.0;
		try {
			RandomUtils.useTestSeed();
			File file = new File(path);
			FileDataModel model = new FileDataModel(file);

			RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
			double erro = evaluator.evaluate(builder, null, model, 0.9, 1.0);
			d = erro;
		} catch (TasteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return d;

	}
}