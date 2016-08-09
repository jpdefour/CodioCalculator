package riis.training.codiocalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InputFieldInterface, ResultFieldInterface, EquationListInterface {

    private Button additionButton, subtractButton, multiplicationButton, divideButton;
    private Button clearButton, equalButton;
    private EditText inputField;
    private TextView resultField;

    private Calculation calculation;

    ArrayList<String> equationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculation = new Calculation();

        equationList = new ArrayList<>();

        additionButton = (Button) findViewById(R.id.addition);
        subtractButton = (Button) findViewById(R.id.subtract);
        multiplicationButton = (Button) findViewById(R.id.multiply);
        divideButton = (Button) findViewById(R.id.divide);
        clearButton = (Button) findViewById(R.id.clear);
        equalButton = (Button) findViewById(R.id.equal);

        additionButton.setTag("+");
        subtractButton.setTag("-");
        multiplicationButton.setTag("*");
        divideButton.setTag("/");
        clearButton.setTag("clear");
        equalButton.setTag("equal");

        inputField = (EditText) findViewById(R.id.inputField);

        resultField = (TextView) findViewById(R.id.resultField);

        CalcButtonListener calcButtonListener = new CalcButtonListener(this, this, this);

        additionButton.setOnClickListener(calcButtonListener);
        subtractButton.setOnClickListener(calcButtonListener);
        multiplicationButton.setOnClickListener(calcButtonListener);
        divideButton.setOnClickListener(calcButtonListener);
        clearButton.setOnClickListener(calcButtonListener);
        equalButton.setOnClickListener(calcButtonListener);

    }

    @Override
    public String getInputFieldText() {
        return inputField.getText().toString();
    }

    @Override
    public void clearInput() {
        inputField.setText("");
    }

    @Override
    public void clearResults() {
        resultField.setText("");
    }

    @Override
    public void calculate() {
        equationList = calculation.calculate(equationList);
        equationList.trimToSize();
        resultField.setText(equationList.get(0));

    }

    @Override
    public void addToEquations(String equationString) {
        equationList.add(equationString);
        updateResultsString();
    }

    @Override
    public String getResultText() {
        return resultField.getText().toString();
    }

    @Override
    public void replaceLastOperator(String operationString) {
        equationList.remove(equationList.size() - 1);
        equationList.add(operationString);
        updateResultsString();
    }

    private void updateResultsString() {
        String equationString = "";
        for (String part: equationList) {
            equationString = equationString + part;
        }
        resultField.setText(equationString);
    }

    @Override
    public ArrayList<String> getList() {
        return equationList;
    }

    @Override
    public void clearList() {
        equationList = new ArrayList<>();
    }
}
