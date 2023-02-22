package PresentationLayer;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministratorFrame {

    private String userName = "admin";
    private String password = "admin";

    private JFrame frameAdministrator;
    private JTable table;
    private JScrollPane pane;
    private JButton butonImportFromFile;
    private  JButton butonAddProduct;
    private JButton butonDeleteProduct;
    private JButton butonModifyProduct;
    private JButton butonCreateComposedProduct;
    private JButton butonGenerateTimeReport;
    private  JButton butonGenerateNumberOfProducts;
    private  JButton butonGenerateClientsReport;
    private  JButton btnGenerateproductsreport;
    private JButton butonBack;

    /**
     * Launch the application.
     */
/*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdministratorFrame window = new AdministratorFrame();
                    window.frameAdministrator.setVisible(true);
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
    public AdministratorFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frameAdministrator = new JFrame();
        frameAdministrator.setTitle("Hello, Administrator");
        frameAdministrator.setBounds(100, 100, 918, 797);
        frameAdministrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameAdministrator.getContentPane().setLayout(null);

        JLabel labelWelcomeAdministrator = new JLabel("Welcome Administrator");
        labelWelcomeAdministrator.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelWelcomeAdministrator.setBounds(24, 28, 199, 58);
        frameAdministrator.getContentPane().add(labelWelcomeAdministrator);

        table = new JTable();
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {  "Title","Rating","Calories","Proteins","Fat","Sodium","Price"
                }
        ));
        table.setBounds(219, 96, 675, 654);


        pane = new JScrollPane(table);
        pane.setBounds(219, 96, 675, 654);

        frameAdministrator.setResizable(false);
        frameAdministrator.getContentPane().add(pane);

        butonImportFromFile = new JButton("Import_Products");
        butonImportFromFile.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonImportFromFile.setBounds(10, 121, 199, 33);
        frameAdministrator.getContentPane().add(butonImportFromFile);

        butonAddProduct = new JButton("Add_New_Product");
        butonAddProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonAddProduct.setBounds(10, 174, 199, 33);
        frameAdministrator.getContentPane().add(butonAddProduct);

        butonDeleteProduct = new JButton("Delete_Product");
        butonDeleteProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonDeleteProduct.setBounds(10, 227, 199, 33);
        frameAdministrator.getContentPane().add(butonDeleteProduct);

        butonModifyProduct = new JButton("Modify_Product");
        butonModifyProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonModifyProduct.setBounds(10, 277, 199, 33);
        frameAdministrator.getContentPane().add(butonModifyProduct);

        butonCreateComposedProduct = new JButton("Create_Composed_Product");
        butonCreateComposedProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonCreateComposedProduct.setBounds(10, 335, 199, 33);
        frameAdministrator.getContentPane().add(butonCreateComposedProduct);

        butonGenerateTimeReport = new JButton("Generate_Time_Report");
        butonGenerateTimeReport.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonGenerateTimeReport.setBounds(10, 393, 199, 33);
        frameAdministrator.getContentPane().add(butonGenerateTimeReport);

        butonGenerateNumberOfProducts = new JButton("Generate_Number_Report");
        butonGenerateNumberOfProducts.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonGenerateNumberOfProducts.setBounds(10, 450, 199, 33);
        frameAdministrator.getContentPane().add(butonGenerateNumberOfProducts);

        butonGenerateClientsReport = new JButton("Generate_Clients_Report");
        butonGenerateClientsReport.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonGenerateClientsReport.setBounds(10, 507, 199, 33);
        frameAdministrator.getContentPane().add(butonGenerateClientsReport);

        btnGenerateproductsreport = new JButton("Generate_Products_Report");
        btnGenerateproductsreport.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGenerateproductsreport.setBounds(10, 564, 199, 33);
        frameAdministrator.getContentPane().add(btnGenerateproductsreport);

        butonBack = new JButton("Back");
        butonBack.setFont(new Font("Tahoma", Font.BOLD, 11));
        butonBack.setBounds(10, 607, 199, 33);
        frameAdministrator.getContentPane().add(butonBack);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JFrame getFrameAdministrator() {
        return frameAdministrator;
    }

    public void setFrameAdministrator(JFrame frameAdministrator) {
        this.frameAdministrator = frameAdministrator;
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

    public JButton getButonImportFromFile() {
        return butonImportFromFile;
    }

    public void setButonImportFromFile(JButton butonImportFromFile) {
        this.butonImportFromFile = butonImportFromFile;
    }

    public JButton getButonAddProduct() {
        return butonAddProduct;
    }

    public void setButonAddProduct(JButton butonAddProduct) {
        this.butonAddProduct = butonAddProduct;
    }

    public JButton getButonDeleteProduct() {
        return butonDeleteProduct;
    }

    public void setButonDeleteProduct(JButton butonDeleteProduct) {
        this.butonDeleteProduct = butonDeleteProduct;
    }

    public JButton getButonModifyProduct() {
        return butonModifyProduct;
    }

    public void setButonModifyProduct(JButton butonModifyProduct) {
        this.butonModifyProduct = butonModifyProduct;
    }

    public JButton getButonCreateComposedProduct() {
        return butonCreateComposedProduct;
    }

    public void setButonCreateComposedProduct(JButton butonCreateComposedProduct) {
        this.butonCreateComposedProduct = butonCreateComposedProduct;
    }

    public JButton getButonGenerateTimeReport() {
        return butonGenerateTimeReport;
    }

    public void setButonGenerateTimeReport(JButton butonGenerateTimeReport) {
        this.butonGenerateTimeReport = butonGenerateTimeReport;
    }

    public JButton getButonGenerateNumberOfProducts() {
        return butonGenerateNumberOfProducts;
    }

    public void setButonGenerateNumberOfProducts(JButton butonGenerateNumberOfProducts) {
        this.butonGenerateNumberOfProducts = butonGenerateNumberOfProducts;
    }

    public JButton getButonGenerateClientsReport() {
        return butonGenerateClientsReport;
    }

    public void setButonGenerateClientsReport(JButton butonGenerateClientsReport) {
        this.butonGenerateClientsReport = butonGenerateClientsReport;
    }

    public JButton getBtnGenerateproductsreport() {
        return btnGenerateproductsreport;
    }

    public void setBtnGenerateproductsreport(JButton btnGenerateproductsreport) {
        this.btnGenerateproductsreport = btnGenerateproductsreport;
    }

    public JButton getButonBack() {
        return butonBack;
    }

    public void setButonBack(JButton butonBack) {
        this.butonBack = butonBack;
    }
}
