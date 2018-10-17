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
	
	private Double percentualTeste;
	private Double percentualAvaliacao;
	
	public Double getPercentualTeste() {
		return percentualTeste;
	}


	public void setPercentualTeste(Double percentualTeste) {
		this.percentualTeste = percentualTeste;
	}


	public Double getPercentualAvaliacao() {
		return percentualAvaliacao;
	}

	public void setPercentualAvaliacao(Double percentualAvaliacao) {
		this.percentualAvaliacao = percentualAvaliacao;
	}




	public Double avaliador(String path, RecommenderBuilder builder) {
		Double d = 0.0;
		try {
			RandomUtils.useTestSeed();
			File file = new File(path);
			FileDataModel model = new FileDataModel(file);

			RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
			double erro = evaluator.evaluate(builder, null, model, percentualAvaliacao, percentualTeste);
			d = erro;
		} catch (TasteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return d;

	}
}