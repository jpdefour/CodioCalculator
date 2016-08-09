package riis.training.codiocalculator;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by John on 8/3/2016.
 */
public class Calculation {

    public ArrayList<String> calculate(ArrayList<String> equationString) {
        equationString = removeOperationAtEnd(equationString);
        ArrayList<String> partiallySolved = ResolveMultiplyDivide(equationString);
        partiallySolved = ResolveAdditionSubtraction(partiallySolved);
        return partiallySolved;
    }

    private ArrayList<String> removeOperationAtEnd(ArrayList<String> equationString) {
        String lastString = equationString.get(equationString.size() - 1);
        if (isAnOperator(lastString)){
            equationString.remove(lastString);
        }
        return equationString;
    }

    private boolean isAnOperator(String lastOperation) {
        return lastOperation.equals("+") || lastOperation.equals("-")
                || lastOperation.equals("*") || lastOperation.equals("/");
    }

    private ArrayList<String> ResolveAdditionSubtraction(ArrayList<String> partiallySolved) {
        for (int i = 0; i < partiallySolved.size(); i++) {
            String part = partiallySolved.get(i);
            if (part.equals("+")){
                Float leftOperand = Float.parseFloat(partiallySolved.get(i - 1));
                Float rightOperand = Float.parseFloat(partiallySolved.get(i + 1));
                Float operationResult = leftOperand + rightOperand;
                partiallySolved = adjustList(partiallySolved, operationResult, i);


            } else if (part.equals("-")){
                Float leftOperand = Float.parseFloat(partiallySolved.get(i - 1));
                Float rightOperand = Float.parseFloat(partiallySolved.get(i + 1));
                Float operationResult = leftOperand - rightOperand;
                partiallySolved = adjustList(partiallySolved, operationResult, i);

            }
            i++;
        }
        return partiallySolved;
    }

    private ArrayList<String> ResolveMultiplyDivide(ArrayList<String> equationList) {

        for (int i = 0; i < equationList.size(); i++) {
            String part = equationList.get(i);
            if (part.equals("*")){
                Float leftOperand = Float.parseFloat(equationList.get(i - 1));
                Float rightOperand = Float.parseFloat(equationList.get(i + 1));
                Float operationResult = leftOperand * rightOperand;
                equationList = adjustList(equationList, operationResult, i);

            } else if (part.equals("/")){
                Float leftOperand = Float.parseFloat(equationList.get(i - 1));
                Float rightOperand = Float.parseFloat(equationList.get(i + 1));
                Float operationResult = leftOperand / rightOperand;
                equationList = adjustList(equationList, operationResult, i);

            }
            i++;
        }
        return equationList;
    }
    private ArrayList<String> adjustList(ArrayList<String> equationList, Float result, int i) {
        equationList.set(i - 1, String.valueOf(result));
        equationList.remove(i + 1);
        equationList.remove(i);

        return equationList;
    }
}
