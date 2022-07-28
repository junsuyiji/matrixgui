import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class Matrix extends JPanel implements ActionListener, Serializable {
    protected static int col;
    protected static int row;
    protected static int lastCol;
    protected static int lastRow;
    protected static double myMatrix[][];
    protected static double tempMatrix[][];
    protected static JTextField inputField[][];
    protected static int result;
    protected static JButton minusB, plusB, inverseB, multiplyB, nMultiplyB, nDivisionB, getValueB, showMatrix,
            transposing, newMatrix, iconImage;
    protected static JPanel choosePanel[] = new JPanel[8];

    public Matrix() {
        col = 0;
        row = 0;
        myMatrix = new double[0][0];
        tempMatrix = new double[0][0];
        lastCol = 0;
        lastRow = 0;
    }

    public Matrix(JTextField field[][]) {
        for (int temp = 0; temp < field.length; temp++) {
            for (int temp1 = 0; temp1 < field[0].length; temp1++) {
                if (field[temp][temp1].getText().equals(""))
                    field[temp][temp1].setText("0");
            }
        }
    }

    public Matrix(int c, int r) {
        col = c;
        row = r;
    }

    private static void Dimension() {
        JTextField lField = new JTextField(5);
        JTextField wField = new JTextField(5);

        JPanel choosePanel[] = new JPanel[2];
        choosePanel[0] = new JPanel();
        choosePanel[1] = new JPanel();
        choosePanel[0].add(new JLabel(""));
        choosePanel[1].add(new JLabel("行:"));
        choosePanel[1].add(lField);
        choosePanel[1].add(Box.createHorizontalStrut(15));
        choosePanel[1].add(new JLabel("列:"));
        choosePanel[1].add(wField);

        result = JOptionPane.showConfirmDialog(null, choosePanel,
                null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        lastCol = col;
        lastRow = row;

        if (result == 0) {
            if (wField.getText().equals(""))
                col = 0;
            else {
                if (isInt(wField.getText())) {
                    col = Integer.parseInt(wField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "error");
                    col = lastCol;
                    row = lastRow;
                    return;
                }

                if (isInt(lField.getText())) {
                    row = Integer.parseInt(lField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "error");
                    col = lastCol;
                    row = lastRow;
                    return;
                }

            }
            if (col < 1 || row < 1) {
                JOptionPane.showConfirmDialog(null, "输错了",
                        "Error", JOptionPane.PLAIN_MESSAGE);
                col = lastCol;
                row = lastRow;

            } else {
                tempMatrix = myMatrix;
                myMatrix = new double[row][col];
                if (!getValue(myMatrix, "新矩阵")) {

                    myMatrix = tempMatrix;
                }
            }
        } else if (result == 1) {
            col = lastCol;
            row = lastRow;
        }
    }

    private static boolean getValue(double matrix[][], String title) {
        int temp = 0;
        int temp1 = 0;
        String tempString = null;

        PrintWriter outputStream = null;

        JPanel choosePanel[] = new JPanel[row + 2];
        choosePanel[0] = new JPanel();
        choosePanel[0].add(new Label(title));
        choosePanel[choosePanel.length - 1] = new JPanel();
        choosePanel[choosePanel.length - 1].add(new Label("空格默认为0"));
        inputField = new JTextField[matrix.length][matrix[0].length];

        for (temp = 1; temp <= matrix.length; temp++) {
            choosePanel[temp] = new JPanel();

            for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                inputField[temp - 1][temp1] = new JTextField(3);
                choosePanel[temp].add(inputField[temp - 1][temp1]);

                if (temp1 < matrix[0].length - 1) {
                    choosePanel[temp].add(Box.createHorizontalStrut(15));
                }
            }
        }

        result = JOptionPane.showConfirmDialog(null, choosePanel,
                null, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == 0) {
            Matrix checkTextField = new Matrix(inputField);

            for (temp = 0; temp < matrix.length; temp++) {
                for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                    tempString = inputField[temp][temp1].getText();

                    if (isDouble(tempString)) {
                        matrix[temp][temp1] = Double.parseDouble(inputField[temp][temp1].getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "输错了");
                        col = lastCol;
                        row = lastRow;

                        return false;
                    }
                }
            }

            outputStream.close();
            return true;
        } else
            return false;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showMatrix) {
            showMatrix(myMatrix, "你的矩阵");
        }
        if (e.getSource() == plusB) {
            matrixPlusMatrix();
        }

        else if (e.getSource() == minusB) {
            matrixMinusMatrix();
        }

        else if (e.getSource() == multiplyB) {
            multiplyByMatrix();
        } else if (e.getSource() == inverseB) {
            inverse();
        }

        else if (e.getSource() == nMultiplyB) {
            multliplyByScalar();
        } else if (e.getSource() == nDivisionB) {
            divideByScaler();
        }

        else if (e.getSource() == transposing) {
            guiTransposing(myMatrix);
        }

        else if (e.getSource() == getValueB) {
            guiGetValue();
        }

        else if (e.getSource() == newMatrix) {
            newMatrix();
        }
    }

    protected static void showMatrix(double[][] matrix, String title) {
        int temp, temp1;
        double result;

        JPanel choosePanel[] = new JPanel[matrix.length + 1];
        choosePanel[0] = new JPanel();
        choosePanel[0].add(new JLabel(title));

        for (temp = 1; temp <= matrix.length; temp++) {
            choosePanel[temp] = new JPanel();

            for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                if (matrix[temp - 1][temp1] == -0) {
                    result = (matrix[temp - 1][temp1] = 0);
                }
                choosePanel[temp].add(new JLabel(String.format("%.2f", matrix[temp - 1][temp1])));

                if (temp1 < matrix[0].length - 1) {
                    choosePanel[temp].add(Box.createHorizontalStrut(15));
                }
            }
        }

        if (col == 0 || row == 0) {
            JOptionPane.showMessageDialog(null, "没输矩阵");
        } else {
            JOptionPane.showMessageDialog(null, choosePanel, null,
                    JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    private static void matrixPlusMatrix() {
        if (myMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "啥都没输啊");
        } else {
            double m2[][] = new double[row][col];
            double sum[][] = new double[row][col];

            if (getValue(m2, "加法")) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        sum[i][j] = myMatrix[i][j] + m2[i][j];
                    }
                }
                showMatrix(sum, "结果");
            }
        }
    }

    private static void matrixMinusMatrix() {
        if (myMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "啥都没输啊");
        } else {
            double m2[][] = new double[row][col];
            double sub[][] = new double[row][col];
            double temp[][] = new double[row][col];

            if (getValue(m2, "除法")) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        temp[i][j] = (-1 * m2[i][j]);
                        sub[i][j] = myMatrix[i][j] + temp[i][j];
                    }
                }
                showMatrix(sub, "结果");
            }
        }
    }

    private static void multiplyByMatrix() {
        JTextField wField = new JTextField(5);
        int col2 = 0;
        double m2[][], results[][];
        int sum;

        if (myMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "啥都没输啊");
        } else {

            JPanel choosePanel[] = new JPanel[2];
            choosePanel[0] = new JPanel();
            choosePanel[1] = new JPanel();
            choosePanel[0].add(new JLabel("输入尺寸: "));
            choosePanel[1].add(new JLabel("行:"));
            choosePanel[1].add(new JLabel("" + col));
            choosePanel[1].add(Box.createHorizontalStrut(15));
            choosePanel[1].add(new JLabel("列:"));
            choosePanel[1].add(wField);

            result = JOptionPane.showConfirmDialog(null, choosePanel,
                    null, JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == 0) {
                if (wField.getText().equals("")) {
                    col2 = 0;
                } else {
                    if (isInt(wField.getText())) {
                        col2 = Integer.parseInt(wField.getText());
                    }
                }

                m2 = new double[col][col2];
                results = new double[row][col2];
                if (getValue(m2, "乘法")) {
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col2; j++) {
                            sum = 0;
                            for (int k = 0; k < col; k++) {
                                sum += myMatrix[i][k] * m2[k][j];
                            }
                            results[i][j] = sum;
                        }
                    }
                    showMatrix(results, "结果");
                }
            } else
                return;
        }
    }

    private static void multliplyByScalar() {
        double[][] results = new double[row][col];
        double x;
        String tempString;

        if (myMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "啥都没输啊");
            return;
        }

        tempString = JOptionPane.showInputDialog(null,
                "相乘");

        if (tempString == null) {
            return;
        }

        else if (!tempString.equals(""))
            x = Double.parseDouble(tempString);
        else {
            JOptionPane.showMessageDialog(null, "没输向量");
            return;
        }
        results = getMultliplyByScaler(myMatrix, x);
        showMatrix(results, "结果");
    }

    private static double[][] getMultliplyByScaler(double[][] matrix, double x) {
        double[][] results = new double[row][col];
        int i, j;

        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                results[i][j] = x * matrix[i][j];
            }
        }
        return results;
    }

    private static void divideByScaler() {
        double[][] results = new double[row][col];
        int i, j;
        double x;
        String tempString;

        if (myMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "啥都没输啊");
            return;
        }

        tempString = JOptionPane.showInputDialog(" ");

        if (tempString == null) {
            return;
        }

        else if (!tempString.equals(""))
            x = Double.parseDouble(tempString);

        else {
            JOptionPane.showMessageDialog(null, "没输一个向量");
            return;
        }

        if (x == 0) {
            JOptionPane.showMessageDialog(null, "不能被0除啊");
            return;
        }

        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                results[i][j] = myMatrix[i][j] / x;
            }
        }
        showMatrix(results, "结果");
    }

    private static void guiGetValue() {
        if (myMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "啥都没输啊");
        } else if (col != row) {
            JOptionPane.showMessageDialog(null, "得输方阵");
        } else {
            double result = getValue(myMatrix);

            JOptionPane.showMessageDialog(null, String.format("值 = %.2f",
                    getValue(myMatrix)), null, JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    private static void swap(double[] res1, double[] res2) {
        int temp;
        double tempDouble;

        for (temp = 0; temp < res1.length; temp++) {
            tempDouble = res1[temp];
            res1[temp] = res2[temp];
            res2[temp] = tempDouble;
        }
    }

    private static double getValue(double[][] matrix) {
        int temp, temp1, temp2;
        double coeficient;
        double result = 1;
        int sign = 1;
        int zeroCounter;

        double res[][] = new double[matrix.length][matrix[0].length];

        for (temp = 0; temp < matrix.length; temp++) {
            for (temp1 = 0; temp1 < matrix[0].length; temp1++) {
                res[temp][temp1] = matrix[temp][temp1];
                ;
            }
        }

        for (temp = 0; temp < res.length; temp++) {
            if (res[temp][temp] != 0)
                continue;

            for (temp1 = 1; temp1 < res.length - 1; temp1++) {
                if (res[(temp1 + temp) % matrix.length][temp] != 0) { // swapping
                    swap(res[temp], res[(temp1 + temp) % res.length]);
                    sign *= -1;
                    break;
                }
            }
        }

        for (temp = 1; temp < res.length; temp++) {
            for (temp1 = 0; temp1 < temp; temp1++) {

                if (res[temp][temp1] == 0 || res[temp1][temp1] == 0)
                    continue;
                else {
                    zeroCounter = 0;
                    coeficient = res[temp][temp1] / res[temp1][temp1];
                }

                for (temp2 = 0; temp2 < res.length; temp2++) {
                    res[temp][temp2] = res[temp][temp2]
                            - res[temp1][temp2] * coeficient;

                    if (res[temp][temp2] == 0)
                        zeroCounter++;
                }

                if (temp < res.length - 1 && zeroCounter > temp) {
                    swap(res[temp], res[temp + 1]);
                    sign *= -1;
                    temp--;
                }
            }
        }

        for (temp = 0; temp < res.length; temp++) {
            result *= res[temp][temp];
        }
        return result * sign;
    }

    private static void guiTransposing(double[][] matrix) {
        if (myMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "啥都没输啊");
            return;
        }

        double[][] transMatrix = new double[matrix[0].length][matrix.length];
        transMatrix = getTranspose(matrix);
        showMatrix(transMatrix, "转置矩阵");
    }

    private static double[][] getTranspose(double[][] matrix) {
        double[][] transportMatrix = new double[matrix[0].length][matrix.length];
        int temp1, temp;

        for (temp = 0; temp < matrix[0].length; temp++) {
            for (temp1 = 0; temp1 < matrix.length; temp1++) {
                transportMatrix[temp][temp1] = matrix[temp1][temp]; // swap (temp, temp1)
            }
        }
        return transportMatrix;
    }

    private static double[][] getMinor(int i, int j) {
        double[][] results = new double[row - 1][col - 1];
        int row_count = 0, col_count = 0;
        int temp, temp1;

        for (temp = 0; temp < row; temp++) {
            for (temp1 = 0; temp1 < col; temp1++) {
                if (temp != i && temp1 != j) {
                    results[row_count][col_count] = myMatrix[temp][temp1];
                    col_count++;
                }
            }
            col_count = 0;
            if (i != temp)
                row_count++;
        }

        return results;
    }

    private static void inverse() {
        if (myMatrix.length < 1) {
            JOptionPane.showMessageDialog(null, "啥都没输啊");
            return;
        } else if (col != row) {
            JOptionPane.showMessageDialog(null, "你得输个方阵");
            return;
        }

        else if (getValue(myMatrix) == 0) {
            JOptionPane.showMessageDialog(null, "你的矩阵"
                    + "没有相反\n\n" + "因为值 = 0");
            return;
        }

        double[][] inverseMatrix = new double[row][col];
        double[][] minor = new double[row - 1][col - 1];
        double[][] cofactor = new double[row][col];
        double delta;
        int temp, temp1;

        for (temp = 0; temp < row; temp++) {
            for (temp1 = 0; temp1 < col; temp1++) {
                minor = getMinor(temp, temp1);
                double minorValue = getValue(minor);
                cofactor[temp][temp1] = Math.pow(-1.0, temp + temp1) * getValue(minor);
            }
        }

        cofactor = getTranspose(cofactor);
        delta = getValue(myMatrix);

        for (temp = 0; temp < row; temp++) {
            for (temp1 = 0; temp1 < col; temp1++) {
                inverseMatrix[temp][temp1] = cofactor[temp][temp1] / delta;
            }
        }
        showMatrix(inverseMatrix, "逆矩阵");
    }

    private static boolean isInt(String str) {
        int temp;
        if (str.length() == '0')
            return false;

        for (temp = 0; temp < str.length(); temp++) {
            if (str.charAt(temp) != '+' && str.charAt(temp) != '-'
                    && !Character.isDigit(str.charAt(temp))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDouble(String str) {
        int temp;
        if (str.length() == '0')
            return false;

        for (temp = 0; temp < str.length(); temp++) {
            if (str.charAt(temp) != '+' && str.charAt(temp) != '-' && str.charAt(temp) != '.'
                    && !Character.isDigit(str.charAt(temp))) {
                return false;
            }
        }
        return true;
    }

    private static void newMatrix() {
        Dimension();
    }
}