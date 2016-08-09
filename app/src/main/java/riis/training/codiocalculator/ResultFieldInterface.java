package riis.training.codiocalculator;

/**
 * Created by John on 8/4/2016.
 */
public interface ResultFieldInterface {

    void clearResults();

    void calculate();

    void addToEquations(String equationString);

    String getResultText();

    void replaceLastOperator(String operationString);
}
