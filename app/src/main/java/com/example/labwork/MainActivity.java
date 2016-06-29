package com.example.labwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mainLine; //Переменная, которая отвечает за Edit text, в который мы вводим данные
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLine = (EditText)findViewById(R.id.editText); //Связываем переменную с объектом
    }

    public void onOneClick(View view) {
        mainLine.setText(mainLine.getText() + "1"); //При нажатии на цифры и знаки операций они просто добавляются к уже существующей строке
    }

    public void onTwoClick(View view) {
        mainLine.setText(mainLine.getText()+"2");
    }

    public void onThreeClick(View view) {
        mainLine.setText(mainLine.getText()+"3");
    }

    public void onFourClick(View view) {
        mainLine.setText(mainLine.getText()+"4");
    }

    public void onFiveClick(View view) {
        mainLine.setText(mainLine.getText()+"5");
    }

    public void onSixClick(View view) {
        mainLine.setText(mainLine.getText()+"6");
    }

    public void onSevenClick(View view) {
        mainLine.setText(mainLine.getText()+"7");
    }

    public void onEightClick(View view) {
        mainLine.setText(mainLine.getText()+"8");
    }

    public void onNineClick(View view) {
        mainLine.setText(mainLine.getText()+"9");
    }

    public void onZeroClick(View view) {
        mainLine.setText(mainLine.getText()+"0");
    }

    public void onPlusClick(View view) {
        mainLine.setText(mainLine.getText()+"+");
    }

    public void onMinusClick(View view) {
        mainLine.setText(mainLine.getText()+"-");
    }

    public void onTimesClick(View view) {
        mainLine.setText(mainLine.getText()+"*");
    }

    public void onDivideClick(View view) {
        mainLine.setText(mainLine.getText()+"/");
    }

    public void onDeleteClick(View view) {
        mainLine.setText("");
    }

    public void onBackSpaceClick(View view) { //При нажатии на ackSpace удаляем последний символ
        String str = mainLine.getText().toString();
        if (str.length()>0)
            mainLine.setText(str.substring(0, str.length()-1));
    }
    public boolean isDigit(char ch) //Проверка символа на принадлежность к цифрам, 1 - принадлежит, 0 - не принадлежит
    {
        String str = "1234567890";
        if (str.indexOf(ch)==-1)
            return false;
        else
            return true;
    }

    public void onResultClick(View view) //Обработка результата при нажатии клавиши '='
    {

        String mainStr = mainLine.getText().toString();
        while((mainStr.indexOf('*') != -1) ||(mainStr.indexOf('/') != -1)) //Сначала идём по всем операциям сложения и деления
        {
            int znakPos = 0;
            char znak = 0;
            String leftOper = "", rightOper = "";
            for (int i = 0;i<mainStr.length(); i++ ) //Ищем позицию вхождения первого знака
            {
                if ((mainStr.charAt(i)=='*')||(mainStr.charAt(i)=='/'))
                {
                    znakPos = i;
                    znak = mainStr.charAt(i);
                    break;
                }

            }
            int k = znakPos-1;
            while((k >= 0) && isDigit(mainStr.charAt(k)) || ((k!=-1) && (mainStr.charAt(k)=='-'))) //Записываем левый аргумент в leftOper
            {
                leftOper +=mainStr.charAt(k);
                if (mainStr.charAt(k)=='-') {
                    k--;
                    break;
                }
                k--;

            }
            int leftPos = k+1;
            leftOper = new StringBuffer(leftOper).reverse().toString(); //инвертиуем его
            int leftOperand = Integer.parseInt(leftOper); //Конвертируем его в int

            k = znakPos + 1;
            while(((k < mainStr.length()) && isDigit(mainStr.charAt(k))) || ((k<mainStr.length()) && (mainStr.charAt(k)=='-') && (k==znakPos+1))) //Записываем правый аргумент в rightOper
            {
                rightOper +=mainStr.charAt(k);
                k++;
            }
            int rightPos = k;
            int rightOperand = Integer.parseInt(rightOper);  //Конвертируем его в int

            int result = 0;
            if (znak == '*') //Высчитываем результат
                result = rightOperand * leftOperand;
            else
                result = leftOperand / rightOperand;

            String resStr = Integer.toString(result); //Конвертируем результат в string
            if ((mainStr.charAt(leftPos)=='-') && (result>0))
                resStr = '+'+resStr;
            mainStr = mainStr.substring(0, leftPos) + resStr + mainStr.substring(rightPos); //Собираем строку обратно, заменив посчитанное выражение результатом

        }

        boolean check = false; // Если 1 - то единственный знак минус в начале строки
        if ((mainStr.charAt(0) == '-') && (mainStr.indexOf('-', 1)==-1) && (mainStr.indexOf('+', 1)==-1))
            check = true;

        while(((mainStr.indexOf('-') != -1) ||(mainStr.indexOf('+') != -1)) && (!check)) //Аналогично операциям умножения и деления обрабатываем сложение и вычитание
        {
            int znakPos = 0;
            char znak = 0;
            String leftOper = "", rightOper = "";
            for (int i = 1;i<mainStr.length(); i++ )
            {
                if ((mainStr.charAt(i)=='+')||(mainStr.charAt(i)=='-'))
                {
                    znakPos = i;
                    znak = mainStr.charAt(i);
                    break;
                }

            }
            int k = znakPos-1;
            while((k >= 0) && isDigit(mainStr.charAt(k)) || ((k!=-1) && (mainStr.charAt(k)=='-')))
            {
                leftOper +=mainStr.charAt(k);
                k--;
            }
            int leftPos = k+1;
            leftOper = new StringBuffer(leftOper).reverse().toString();
            int leftOperand = Integer.parseInt(leftOper);

            k = znakPos + 1;
            while(((k < mainStr.length()) && isDigit(mainStr.charAt(k))) || ((k<mainStr.length()) && (mainStr.charAt(k)=='-') && (k==znakPos+1))) //Записываем правый аргумент в rightOper
            {
                rightOper +=mainStr.charAt(k);
                k++;
            }
            int rightPos = k;
            int rightOperand = Integer.parseInt(rightOper);

            int result = 0;
            if (znak == '+')
                result = rightOperand + leftOperand;
            else
                result = leftOperand - rightOperand;

            String resStr = Integer.toString(result);

            mainStr = mainStr.substring(0, leftPos) + resStr + mainStr.substring(rightPos);

            if ((mainStr.charAt(0) == '-') && (mainStr.indexOf('-', 1)==-1) && (mainStr.indexOf('+', 1)==-1))
                check = true;

        }



        mainLine.setText(mainStr); //Записываем результат в Edit Text
    }
}
