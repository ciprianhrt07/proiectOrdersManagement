package PresentationLayer;
import java.awt.EventQueue;



import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ClientFrame {

    private JFrame clientFrame;
    private  final String username = "client";
    private  final String password = "clientpass";
    private JTable table;
    private JScrollPane pane;
    private JLabel labelSearch;
    private JTextField textSearchProducts;
    private JButton btnPlaceorder;
    private JButton butonVizualizareProduse;
    private JButton butonBack;
    private JButton butonSearch;
    private JRadioButton rdbtnKeyWord;
    private JRadioButton rdbtnRating;
    private JRadioButton rdbtnCalories;
    private  JRadioButton rdbtnProteins;
    private  JRadioButton rdbtnFat;
    private JRadioButton rdbtnSodium;
    private JRadioButton rdbtnSearchbyprice;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientFrame window = new ClientFrame();
                    window.clientFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ClientFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        clientFrame = new JFrame();
        clientFrame.setTitle("Welcome, Client");
        clientFrame.setBounds(100, 100, 776, 684);
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.getContentPane().setLayout(null);

        JLabel labelWelcomeClient = new JLabel("Welcome Client");
        labelWelcomeClient.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelWelcomeClient.setBounds(33, 30, 189, 43);
        clientFrame.getContentPane().add(labelWelcomeClient);

        butonVizualizareProduse = new JButton("SeeProducts");
        butonVizualizareProduse.setBounds(10, 195, 145, 33);
        clientFrame.getContentPane().add(butonVizualizareProduse);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {  "Title","Rating","Calories","Proteins","Fat","Sodium","Price"
                }
        ));
        table.setBounds(176, 61, 576, 551);
        // clientFrame.getContentPane().add(table);


        pane = new JScrollPane(table);
        pane.setBounds(165, 147, 572, 490);



        clientFrame.setResizable(false);
        clientFrame.getContentPane().add(pane);

        labelSearch = new JLabel("Search :");
        labelSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelSearch.setBounds(80, 88, 82, 27);
        clientFrame.getContentPane().add(labelSearch);

        textSearchProducts = new JTextField();
        textSearchProducts.setBounds(165, 83, 572, 34);
        clientFrame.getContentPane().add(textSearchProducts);
        textSearchProducts.setColumns(10);

        btnPlaceorder = new JButton("PlaceOrder");
        btnPlaceorder.setBounds(10, 238, 145, 33);
        clientFrame.getContentPane().add(btnPlaceorder);

        butonBack = new JButton("Back");
        butonBack.setBounds(10, 281, 145, 33);
        clientFrame.getContentPane().add(butonBack);

        rdbtnKeyWord = new JRadioButton("SearchByKeyWord");
        rdbtnKeyWord.setBounds(17, 328, 138, 33);
        clientFrame.getContentPane().add(rdbtnKeyWord);

        rdbtnRating = new JRadioButton("SearchByRating");
        rdbtnRating.setBounds(17, 363, 138, 33);
        clientFrame.getContentPane().add(rdbtnRating);

        rdbtnCalories = new JRadioButton("SearchByCalories");
        rdbtnCalories.setBounds(17, 400, 138, 33);
        clientFrame.getContentPane().add(rdbtnCalories);

         rdbtnProteins = new JRadioButton("SearchByProteins");
        rdbtnProteins.setBounds(17, 435, 138, 33);
        clientFrame.getContentPane().add(rdbtnProteins);

         rdbtnFat = new JRadioButton("SearchByFat");
        rdbtnFat.setBounds(17, 470, 138, 33);
        clientFrame.getContentPane().add(rdbtnFat);

        rdbtnSodium = new JRadioButton("SearchBySodium");
        rdbtnSodium.setBounds(17, 505, 138, 33);
        clientFrame.getContentPane().add(rdbtnSodium);

         rdbtnSearchbyprice = new JRadioButton("SearchByPrice");
        rdbtnSearchbyprice.setBounds(17, 540, 138, 33);
        clientFrame.getContentPane().add(rdbtnSearchbyprice);

        butonSearch = new JButton("Search");
        butonSearch.setBounds(10, 579, 145, 33);
        clientFrame.getContentPane().add(butonSearch);
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public JFrame getClientFrame() {
        return clientFrame;
    }

    public void setClientFrame(JFrame clientFrame) {
        this.clientFrame = clientFrame;
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

    public JLabel getLabelSearch() {
        return labelSearch;
    }

    public void setLabelSearch(JLabel labelSearch) {
        this.labelSearch = labelSearch;
    }

    public JTextField getTextSearchProducts() {
        return textSearchProducts;
    }

    public void setTextSearchProducts(JTextField textSearchProducts) {
        this.textSearchProducts = textSearchProducts;
    }

    public JButton getBtnPlaceorder() {
        return btnPlaceorder;
    }

    public void setBtnPlaceorder(JButton btnPlaceorder) {
        this.btnPlaceorder = btnPlaceorder;
    }

    public JButton getButonVizualizareProduse() {
        return butonVizualizareProduse;
    }

    public void setButonVizualizareProduse(JButton butonVizualizareProduse) {
        this.butonVizualizareProduse = butonVizualizareProduse;
    }

    public JButton getButonBack() {
        return butonBack;
    }

    public void setButonBack(JButton butonBack) {
        this.butonBack = butonBack;
    }

    public JButton getButonSearch() {
        return butonSearch;
    }

    public void setButonSearch(JButton butonSearch) {
        this.butonSearch = butonSearch;
    }

    public JRadioButton getRdbtnKeyWord() {
        return rdbtnKeyWord;
    }

    public void setRdbtnKeyWord(JRadioButton rdbtnKeyWord) {
        this.rdbtnKeyWord = rdbtnKeyWord;
    }

    public JRadioButton getRdbtnRating() {
        return rdbtnRating;
    }

    public void setRdbtnRating(JRadioButton rdbtnRating) {
        this.rdbtnRating = rdbtnRating;
    }

    public JRadioButton getRdbtnCalories() {
        return rdbtnCalories;
    }

    public void setRdbtnCalories(JRadioButton rdbtnCalories) {
        this.rdbtnCalories = rdbtnCalories;
    }

    public JRadioButton getRdbtnProteins() {
        return rdbtnProteins;
    }

    public void setRdbtnProteins(JRadioButton rdbtnProteins) {
        this.rdbtnProteins = rdbtnProteins;
    }

    public JRadioButton getRdbtnFat() {
        return rdbtnFat;
    }

    public void setRdbtnFat(JRadioButton rdbtnFat) {
        this.rdbtnFat = rdbtnFat;
    }

    public JRadioButton getRdbtnSodium() {
        return rdbtnSodium;
    }

    public void setRdbtnSodium(JRadioButton rdbtnSodium) {
        this.rdbtnSodium = rdbtnSodium;
    }

    public JRadioButton getRdbtnSearchbyprice() {
        return rdbtnSearchbyprice;
    }

    public void setRdbtnSearchbyprice(JRadioButton rdbtnSearchbyprice) {
        this.rdbtnSearchbyprice = rdbtnSearchbyprice;
    }
}
