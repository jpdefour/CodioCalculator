package riis.training.codiocalculator;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 8/3/2016.
 */
public class CalcButtonListener implements View.OnClickListener{
    InputFieldInterface inputFieldInterface;
    ResultFieldInterface resultFieldInterface;
    EquationListInterface equationListInterface;

    public CalcButtonListener(InputFieldInterface _inputFieldInterface, ResultFieldInterface _resultFieldInterface, EquationListInterface _equationListInterface){
        inputFieldInterface = _inputFieldInterface;
        resultFieldInterface = _resultFieldInterface;
        equationListInterface = _equationListInterface;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String operationString = (String) v.getTag();
        findCalculation(operationString);
    }

    private void findCalculation(String operationString) {
        String numberString = inputFieldInterface.getInputFieldText();
        ArrayList<String> equationList = equationListInterface.getList();

        if (operationString.equals("clear")) {
            resultFieldInterface.clearResults();
            inputFieldInterface.clearInput();
            equationListInterface.clearList();
        } else if (lastPartIsOperator(equationList) && numberString.isEmpty() && !operationString.equals("equal")) {
            resultFieldInterface.replaceLastOperator(operationString);
        }
        else if (!resultFieldInterface.getResultText().equals("") || !numberString.isEmpty()){
            switch (operationString) {
                case "+":
                    submitOperation(numberString, "+", equationList);
                    break;
                case "-":
                    submitOperation(numberString, "-", equationList);
                    break;
                case "*":
                    submitOperation(numberString, "*", equationList);
                    break;
                case "/":
                    submitOperation(numberString, "/", equationList);
                    break;
                case "equal":
                    if (!numberString.equals("") && isAnOperator(equationList.get(equationList.size() - 1))) {
                        resultFieldInterface.addToEquations(numberString);
                    }
                    inputFieldInterface.clearInput();
                    resultFieldInterface.calculate();
                    break;
            }
        }
    }

    private boolean isAnOperator(String lastOperation) {
        return lastOperation.equals("+") || lastOperation.equals("-")
                || lastOperation.equals("*") || lastOperation.equals("/");
    }

    private void submitOperation(String numberString, String equationString, ArrayList<String> equationList) {
        if (equationList.size() > 0 && !isAnOperator(equationList.get(equationList.size() - 1)) && !numberString.equals("")){
            resultFieldInterface.addToEquations(equationString);
            resultFieldInterface.addToEquations(numberString);
            inputFieldInterface.clearInput();
        } else {
            if (!numberString.equals("")) {
                resultFieldInterface.addToEquations(numberString);
            }
            resultFieldInterface.addToEquations(equationString);
            inputFieldInterface.clearInput();
        }
    }

    private boolean lastPartIsOperator(List<String> equationList) {
        String lastItem = "";

        if (equationList.size() > 0) {
            lastItem = equationList.get(equationList.size() - 1);
        }
        else if (equationList.size() == 0) {
            return false;
        } if (lastItem.equals("+") || lastItem.equals("-") || lastItem.equals("*") || lastItem.equals("/") ){
            return true;
        }
        return false;
    }
}
