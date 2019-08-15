package dmitriev.tasksForPerfomance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Scanner;

public class MainForm {

    private JComboBox listTasks;
    private JButton buttonDownloadFile;
    private JPanel MainPanel;
    private JLabel forTextResultField;
    private JLabel selectedFile;
    private JButton calcButton;
    private JLabel resultLabel;
    private JTextArea pointArea;
    FirstTask element1;
    SecondTask element2;
    ThirdTask element3;
    FourthTask element4;
    int numberOfTask = 0;

    public void setDataToComboBox() {
        listTasks.addItem("Выберите задание из списка...");
        listTasks.addItem("Задание 1 - Работа с числами");
        listTasks.addItem("Задание 2 - Расчет точки");
        listTasks.addItem("Задание 3 - Про магазин");
        listTasks.addItem("Задание 4 - Про банк");
    }

    public MainForm() {
        JFrame frame = new JFrame("Задания Java");
        frame.setBounds(100, 100, 400, 200);
        setDataToComboBox();
        buttonDownloadFile.setEnabled(false);
        calcButton.setEnabled(false);
        pointArea.setVisible(false);
        frame.setContentPane(this.MainPanel);
        buttonDownloadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (numberOfTask) {
                    case 1:
                        element1 = new FirstTask();
                        element1.parseFileFor1stTask();
                        selectedFile.setText("Выбран файл: " + element1.openedFilePath);
                    case 2:
                        element2 = new SecondTask();
                        element2.parseFileFor2ndTask();
                        selectedFile.setText("Выбран файл: " + element2.openedFilePath);
                    case 3:
                        element3 = new ThirdTask();
                        element3.parseFileFor3rdTask();
                        selectedFile.setText("Выбрана папка: " + element3.openedFilePath);
                    case 4:
                        buttonDownloadFile.setEnabled(true);
                }

            }
        });
        listTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfTask = listTasks.getSelectedIndex();
                if (numberOfTask != 0) {
                    buttonDownloadFile.setEnabled(true);
                    calcButton.setEnabled(true);
                    if (numberOfTask == 2) {
                        pointArea.setVisible(true);
                    } else {
                        pointArea.setVisible(false);
                    }
                } else {
                    buttonDownloadFile.setEnabled(false);
                    calcButton.setEnabled(false);
                    pointArea.setVisible(false);
                }

            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setVisible(true);
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (numberOfTask) {
                    case 1:
                        element1.calcMaxMin();
                        element1.calc90Percentile();
                        element1.calcAverage();
                        element1.calcMedian();
                        resultLabel.setText("<html> 90 percentile &lt;" + element1.percentile + "&gt; <br />" +
                                "median &lt;" + element1.median + "&gt; <br />" +
                                "average &lt;" + element1.average + "&gt; <br />" +
                                "min &lt;" + element1.min + "&gt; <br />" +
                                "max &lt;" + element1.max + "&gt; <br />");
                    case 2:
                        Point inputPoint = element2.scanPointFromLine(pointArea.getText());
                        element2.setLines();
                        element2.fillQuadrangle();
                        if (element2.isCorner(inputPoint)) {
                            resultLabel.setText("точка - вершина четырехугольника");
                        } else if (element2.onLine(inputPoint)) {
                            resultLabel.setText("точка лежит на сторонах четырехугольника");
                        } else if (element2.insideQuadrangle(inputPoint)) {
                            resultLabel.setText("точка внутри четырехугольника");
                        } else {
                            resultLabel.setText("точка снаружи четырехугольника");
                        }

                    case 3:
                        element3.sortList();
                        PeriodAndQueue res = element3.result();
                        resultLabel.setText("В период " + res.period + " было наибольшее количество поситителей = " + res.queue);
                    case 4:
                        buttonDownloadFile.setEnabled(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        new MainForm();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.setEnabled(true);
        resultLabel = new JLabel();
        resultLabel.setText("");
        MainPanel.add(resultLabel, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        calcButton = new JButton();
        calcButton.setText("Рассчитать");
        MainPanel.add(calcButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectedFile = new JLabel();
        selectedFile.setText("Файл не выбран");
        MainPanel.add(selectedFile, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonDownloadFile = new JButton();
        buttonDownloadFile.setText("Выбрать файл или папку");
        MainPanel.add(buttonDownloadFile, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(106, 11), null, 0, false));
        forTextResultField = new JLabel();
        forTextResultField.setText("Result: ");
        MainPanel.add(forTextResultField, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listTasks = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        listTasks.setModel(defaultComboBoxModel1);
        MainPanel.add(listTasks, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pointArea = new JTextArea();
        pointArea.setText("<координата x искомой точки>  <координата y искомой точки> ");
        pointArea.setToolTipText("Введите данные искомой точки");
        MainPanel.add(pointArea, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, 100), new Dimension(-1, 100), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }


}
