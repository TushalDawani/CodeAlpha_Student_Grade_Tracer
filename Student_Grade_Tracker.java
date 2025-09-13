package Code_Alpha_Projects;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_Grade_Tracker extends JFrame implements ActionListener{

    // Globally Variables:
    String [][] students = new String[100][3];
    int studentCount = 0;

    JTextField nameField, idField, scoreField;
    JTable table;
    JLabel lowLabel, highLabel, averageLabel;
    JButton addStudent;


    public Student_Grade_Tracker(){
        setTitle("Student Grade Tracker");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
       getContentPane().setBackground(Color.WHITE);


        // Input Fields:
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20,20,50,25);
        add(nameLabel);


        // Name Field
        nameField = new JTextField();
        nameField.setBounds(80, 20, 100, 25);
        add(nameField);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(200, 20, 30, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(230, 20, 80, 25);
        add(idField);

        JLabel scoreLabel = new JLabel("Score:");
        scoreLabel.setBounds(330, 20, 50, 25);
        add(scoreLabel);

        scoreField = new JTextField();
        scoreField.setBounds(380, 20, 50, 25);
        add(scoreField);

        addStudent = new JButton("Add Student");
        addStudent.setBounds(450, 20, 120, 25);
        addStudent.addActionListener(this);
        add(addStudent);


        // Table
        table = new JTable(new DefaultTableModel(new Object[]{"Name", "ID", "Score"}, 0));
        table.setBackground(Color.orange);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 550, 150);
        add(scrollPane);


        // Labels for summary
        highLabel = new JLabel("Highest: ");
        highLabel.setBounds(20, 270, 200, 25);
        add(highLabel);

        lowLabel = new JLabel("Lowest: ");
        lowLabel.setBounds(20, 300, 200, 25);
        add(lowLabel);

        averageLabel = new JLabel("Average: ");
        averageLabel.setBounds(20, 240, 200, 25);
        add(averageLabel);

        setVisible(true);

    }


    // This method runs when Add Student button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudent) {
            addStudent();
        }
    }



    private void addStudent() {
        String name = nameField.getText();
        String id = idField.getText();
        String scoreStr = scoreField.getText();

        if (name.isEmpty() || id.isEmpty() || scoreStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        int score = Integer.parseInt(scoreStr);

        // Store in array
        students[studentCount][0] = name;
        students[studentCount][1] = id;
        students[studentCount][2] = String.valueOf(score);
        studentCount++;

        // Update table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{name, id, score});

        // Update summary
        updateSummary();
    }


    private void updateSummary() {
        if (studentCount == 0) return;

        int total = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
        String highName = "", lowName = "";

        for (int i = 0; i < studentCount; i++) {
            int score = Integer.parseInt(students[i][2]);
            total += score;

            if (score > highest) {
                highest = score;
                highName = students[i][0];
            }
            if (score < lowest) {
                lowest = score;
                lowName = students[i][0];
            }
        }

        double avg = (double) total / studentCount;
        averageLabel.setText("Average: " + avg);
        highLabel.setText("Highest: " + highName + " (" + highest + ")");
        lowLabel.setText("Lowest: " + lowName + " (" + lowest + ")");
    }

    

    public static void main(String[] args) {
        Student_Grade_Tracker obj = new Student_Grade_Tracker();

        

    }
}
