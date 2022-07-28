import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.awt.*;
import javax.swing.*;

public class MatrixOperation extends Matrix {
	private int temp;
	private int length;
	private int breadth;

	public MatrixOperation() {
		super();
		temp = 0;
		length = 0;
		breadth = 0;
	}

	public MatrixOperation(int t, int l, int b) {
		super();
		temp = t;
		length = l;
		breadth = b;
	}

	public MatrixOperation(int l, int b) {
		length = l;
		breadth = b;

		for (temp = 0; temp < choosePanel.length; temp++) {
			choosePanel[temp] = new JPanel();
		}

		ImageIcon logoTitle = new ImageIcon(getClass().getResource("pic.png"));
		JLabel logoLabel = new JLabel(logoTitle);
		choosePanel[1].add(logoLabel);

		JLabel credits = new JLabel("矩阵计算器");
		choosePanel[6].add(credits);

		showMatrix = new JButton("你的矩阵");
		showMatrix.setPreferredSize(new Dimension(l, b));
		showMatrix.addActionListener(this);
		choosePanel[2].add(showMatrix);

		plusB = new JButton("加法");
		plusB.setPreferredSize(new Dimension(l, b));
		plusB.addActionListener(this);
		choosePanel[2].add(plusB);

		minusB = new JButton("除法");
		minusB.setPreferredSize(new Dimension(l, b));
		minusB.addActionListener(this);
		choosePanel[2].add(minusB);

		multiplyB = new JButton("乘法");
		multiplyB.setPreferredSize(new Dimension(l, b));
		multiplyB.addActionListener(this);
		choosePanel[3].add(multiplyB);

		transposing = new JButton("转置");
		transposing.setPreferredSize(new Dimension(l, b));
		transposing.addActionListener(this);
		choosePanel[3].add(transposing);

		nMultiplyB = new JButton("向量乘法");
		nMultiplyB.setPreferredSize(new Dimension(l, b));
		nMultiplyB.addActionListener(this);
		choosePanel[4].add(nMultiplyB);

		nDivisionB = new JButton("向量除法");
		nDivisionB.setPreferredSize(new Dimension(l, b));
		nDivisionB.addActionListener(this);
		choosePanel[4].add(nDivisionB);

		if (col == row) {
			getValueB = new JButton("行列式");
			getValueB.setPreferredSize(new Dimension(l, b));
			getValueB.addActionListener(this);
			choosePanel[3].add(getValueB);

			inverseB = new JButton("逆矩阵");
			inverseB.setPreferredSize(new Dimension(l, b));
			inverseB.addActionListener(this);
			choosePanel[4].add(inverseB);
		}

		newMatrix = new JButton("输入矩阵");
		newMatrix.setPreferredSize(new Dimension(l, b));
		newMatrix.addActionListener(this);
		choosePanel[5].add(newMatrix);

		JOptionPane.showConfirmDialog(null, choosePanel, "矩阵计算器",
				JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	public void setTemp(int t) {
		temp = t;
	}

	public void setLength(int l) {
		length = l;
	}

	public void setBreadth(int b) {
		breadth = b;
	}

	public int getTemp() {
		return temp;
	}

	public int getLength() {
		return length;
	}

	public int getBreadth() {
		return breadth;
	}

	private void swap(double[] res1, double[] res2) {
		int temp;
		double tempDouble;

		for (temp = 0; temp < res1.length; temp++) {
			tempDouble = res1[temp];
			res1[temp] = res2[temp];
			res2[temp] = tempDouble;
		}
	}
}
