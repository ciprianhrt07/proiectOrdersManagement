package PresentationLayer;

import BusinessLayer.DeliveryService;

import java.util.Observable;
import java.util.Observer;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class EmployeeFrame implements  Observer{

    private JFrame frameEmployee;
    private JTable table;
    private JScrollPane pane;
    private  JButton btnSeeOrders;
    private  JButton btnBack;
    private DeliveryService deliveryService;

    /**
     * Launch the application.
     */
/*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeFrame window = new EmployeeFrame();
                    window.frameEmployee.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
*/

    /**
     * Create the application.
     */
    public EmployeeFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frameEmployee = new JFrame();
        frameEmployee.setTitle("Welcome Employee");
        frameEmployee.setBounds(100, 100, 565, 495);
        frameEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameEmployee.getContentPane().setLayout(null);

        JLabel labelWelcome = new JLabel("Welcome,  Employee");
        labelWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelWelcome.setBounds(32, 29, 185, 50);
        frameEmployee.getContentPane().add(labelWelcome);

        table = new JTable();
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {"Id","Data Plasarii Comenzii","Cantitate","Pret"

                }
        ));
        table.setBounds(126, 89, 401, 359);


        pane = new JScrollPane(table);
        pane.setBounds(126, 89, 401, 359);

        frameEmployee.setResizable(false);
        frameEmployee.getContentPane().add(pane);


        btnBack = new JButton("Back");
        btnBack.setBounds(10, 405, 106, 29);
        frameEmployee.getContentPane().add(btnBack);


    }

    @Override
    public void update(Observable o, Object arg) {

        deliveryService= (DeliveryService) o;
        JOptionPane.showMessageDialog(null,"New Order has been added");

    }

    public JFrame getFrameEmployee() {
        return frameEmployee;
    }

    public void setFrameEmployee(JFrame frameEmployee) {
        this.frameEmployee = frameEmployee;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getPane() {
        return pane;
    }

    public void setPane(JScrollPane pane) {
        this.pane = pane;
    }

    public JButton getBtnSeeOrders() {
        return btnSeeOrders;
    }

    public void setBtnSeeOrders(JButton btnSeeOrders) {
        this.btnSeeOrders = btnSeeOrders;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }
}
