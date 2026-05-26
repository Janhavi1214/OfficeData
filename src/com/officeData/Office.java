package com.officeData;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Office extends JFrame implements ActionListener {

    JTextField tfEmpId, tfEmpName, tfManagerId, tfDeptId;

    JButton btnInsert, btnSelfJoin, btnCrossJoin,
            btnCompositeJoin, btnMultiTableJoin;

    JTable table;
    DefaultTableModel model;

    Connection con;
    Statement st;

    Office() {

        setTitle("Employee Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= TOP PANEL =================

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));

        // ================= INPUT PANEL =================

        JPanel inputPanel = new JPanel();

        inputPanel.add(new JLabel("Emp ID"));
        tfEmpId = new JTextField(8);
        inputPanel.add(tfEmpId);

        inputPanel.add(new JLabel("Emp Name"));
        tfEmpName = new JTextField(8);
        inputPanel.add(tfEmpName);

        inputPanel.add(new JLabel("Manager ID"));
        tfManagerId = new JTextField(8);
        inputPanel.add(tfManagerId);

        inputPanel.add(new JLabel("Dept ID"));
        tfDeptId = new JTextField(8);
        inputPanel.add(tfDeptId);

        btnInsert = new JButton("INSERT");
        inputPanel.add(btnInsert);

        // ================= BUTTON PANEL =================

        JPanel buttonPanel = new JPanel();

        btnSelfJoin = new JButton("Self Join");
        btnCrossJoin = new JButton("Cross Join");
        btnCompositeJoin = new JButton("Composite Join");
        btnMultiTableJoin = new JButton("Multi Table Join");

        buttonPanel.add(btnSelfJoin);
        buttonPanel.add(btnCrossJoin);
        buttonPanel.add(btnCompositeJoin);
        buttonPanel.add(btnMultiTableJoin);

        topPanel.add(inputPanel);
        topPanel.add(buttonPanel);

        add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================

        model = new DefaultTableModel();

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);

        add(sp, BorderLayout.CENTER);

        // ================= ACTION EVENTS =================

        btnInsert.addActionListener(this);
        btnSelfJoin.addActionListener(this);
        btnCrossJoin.addActionListener(this);
        btnCompositeJoin.addActionListener(this);
        btnMultiTableJoin.addActionListener(this);

        // ================= DATABASE CONNECTION =================

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/office",
                    "root",
                    "janhavi@2004"
            );

            st = con.createStatement();

            JOptionPane.showMessageDialog(this,
                    "Database Connected Successfully");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Database Error : " + e.getMessage());
        }

        setVisible(true);
    }

    public static void main(String[] args) {

        new Office();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        try {

            // ==================================================
            // INSERT
            // ==================================================

            if (ae.getSource() == btnInsert) {

                String q =
                        "INSERT INTO employee "
                      + "(empId, empName, managerId, deptId) "
                      + "VALUES ("
                      + tfEmpId.getText() + ", '"
                      + tfEmpName.getText() + "', "
                      + tfManagerId.getText() + ", "
                      + tfDeptId.getText() + ")";

                st.executeUpdate(q);

                JOptionPane.showMessageDialog(this,
                        "Record Inserted Successfully");
            }

            // ==================================================
            // SELF JOIN
            // ==================================================

            else if (ae.getSource() == btnSelfJoin) {

                String q =
                        "SELECT e1.empId, e1.empName, "
                      + "e2.empName AS managerName "
                      + "FROM employee e1 "
                      + "JOIN employee e2 "
                      + "ON e1.managerId = e2.empId";

                ResultSet rs = st.executeQuery(q);

                model.setRowCount(0);
                model.setColumnCount(0);

                model.addColumn("Emp ID");
                model.addColumn("Employee Name");
                model.addColumn("Manager Name");

                while (rs.next()) {

                    model.addRow(new Object[] {

                            rs.getInt("empId"),
                            rs.getString("empName"),
                            rs.getString("managerName")
                    });
                }
            }

            // ==================================================
            // CROSS JOIN
            // ==================================================

            else if (ae.getSource() == btnCrossJoin) {

                String q =
                        "SELECT e.empName, d.deptName "
                      + "FROM employee e "
                      + "CROSS JOIN dept d";

                ResultSet rs = st.executeQuery(q);

                model.setRowCount(0);
                model.setColumnCount(0);

                model.addColumn("Employee Name");
                model.addColumn("Department Name");

                while (rs.next()) {

                    model.addRow(new Object[] {

                            rs.getString("empName"),
                            rs.getString("deptName")
                    });
                }
            }

            // ==================================================
            // COMPOSITE JOIN
            // ==================================================

            else if (ae.getSource() == btnCompositeJoin) {

                String q =
                        "SELECT e.empName, d.deptName "
                      + "FROM employee e "
                      + "JOIN dept d "
                      + "ON e.deptId = d.deptId "
                      + "AND d.deptId > 202";

                ResultSet rs = st.executeQuery(q);

                model.setRowCount(0);
                model.setColumnCount(0);

                model.addColumn("Employee Name");
                model.addColumn("Department Name");

                while (rs.next()) {

                    model.addRow(new Object[] {

                            rs.getString("empName"),
                            rs.getString("deptName")
                    });
                }
            }

            // ==================================================
            // MULTI TABLE JOIN
            // ==================================================

            else if (ae.getSource() == btnMultiTableJoin) {

                String q =
                        "SELECT e.empName, d.deptName, p.projectName "
                      + "FROM employee e "
                      + "JOIN dept d "
                      + "ON e.deptId = d.deptId "
                      + "JOIN project p "
                      + "ON d.deptId = p.deptId";

                ResultSet rs = st.executeQuery(q);

                model.setRowCount(0);
                model.setColumnCount(0);

                model.addColumn("Employee Name");
                model.addColumn("Department");
                model.addColumn("Project");

                while (rs.next()) {

                    model.addRow(new Object[] {

                            rs.getString("empName"),
                            rs.getString("deptName"),
                            rs.getString("projectName")
                    });
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Error : " + e.getMessage());
        }
    }
}