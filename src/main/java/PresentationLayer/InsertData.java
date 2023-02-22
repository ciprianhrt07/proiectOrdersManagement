package PresentationLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class InsertData {

    private JFrame frameInsertData;
    private JTextField textTitle;
    private JTextField textRating;
    private JTextField textCalories;
    private JTextField textProteins;
    private JTextField textFat;
    private JTextField textSodium;
    private JTextField textPrice;
    private JButton  btnInsert;
    /**
     * Launch the application.
     */
/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InsertData window = new InsertData();
                    window.frameInsertData.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the application.
     */
    public InsertData() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frameInsertData = new JFrame();
        frameInsertData.setTitle("Insert Atributes For A New Base Product");
        frameInsertData.setBounds(100, 100, 468, 589);
        frameInsertData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInsertData.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Title");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(189, 23, 139, 28);
        frameInsertData.getContentPane().add(lblNewLabel);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblRating.setBounds(189, 90, 139, 28);
        frameInsertData.getContentPane().add(lblRating);

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCalories.setBounds(189, 168, 139, 28);
        frameInsertData.getContentPane().add(lblCalories);

        JLabel lblProtein = new JLabel("Protein");
        lblProtein.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblProtein.setBounds(189, 246, 139, 28);
        frameInsertData.getContentPane().add(lblProtein);

        JLabel lblFat = new JLabel("Fat");
        lblFat.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFat.setBounds(200, 317, 139, 28);
        frameInsertData.getContentPane().add(lblFat);

        JLabel lblSodiun = new JLabel("Sodiun");
        lblSodiun.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSodiun.setBounds(189, 380, 85, 28);
        frameInsertData.getContentPane().add(lblSodiun);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPrice.setBounds(189, 433, 139, 28);
        frameInsertData.getContentPane().add(lblPrice);

        btnInsert = new JButton("Insert");
        btnInsert.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnInsert.setBounds(167, 510, 85, 21);
        frameInsertData.getContentPane().add(btnInsert);

        textTitle = new JTextField();
        textTitle.setBounds(139, 61, 139, 28);
        frameInsertData.getContentPane().add(textTitle);
        textTitle.setColumns(10);

        textRating = new JTextField();
        textRating.setColumns(10);
        textRating.setBounds(139, 128, 139, 28);
        frameInsertData.getContentPane().add(textRating);

        textCalories = new JTextField();
        textCalories.setColumns(10);
        textCalories.setBounds(139, 206, 139, 28);
        frameInsertData.getContentPane().add(textCalories);

        textProteins = new JTextField();
        textProteins.setColumns(10);
        textProteins.setBounds(139, 284, 139, 28);
        frameInsertData.getContentPane().add(textProteins);

        textFat = new JTextField();
        textFat.setColumns(10);
        textFat.setBounds(139, 342, 139, 28);
        frameInsertData.getContentPane().add(textFat);

        textSodium = new JTextField();
        textSodium.setColumns(10);
        textSodium.setBounds(139, 405, 139, 28);
        frameInsertData.getContentPane().add(textSodium);

        textPrice = new JTextField();
        textPrice.setColumns(10);
        textPrice.setBounds(139, 459, 139, 28);
        frameInsertData.getContentPane().add(textPrice);
    }

    public JFrame getFrameInsertData() {
        return frameInsertData;
    }

    public void setFrameInsertData(JFrame frameInsertData) {
        this.frameInsertData = frameInsertData;
    }

    public JTextField getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(JTextField textTitle) {
        this.textTitle = textTitle;
    }

    public JTextField getTextRating() {
        return textRating;
    }

    public void setTextRating(JTextField textRating) {
        this.textRating = textRating;
    }

    public JTextField getTextCalories() {
        return textCalories;
    }

    public void setTextCalories(JTextField textCalories) {
        this.textCalories = textCalories;
    }

    public JTextField getTextProteins() {
        return textProteins;
    }

    public void setTextProteins(JTextField textProteins) {
        this.textProteins = textProteins;
    }

    public JTextField getTextFat() {
        return textFat;
    }

    public void setTextFat(JTextField textFat) {
        this.textFat = textFat;
    }

    public JTextField getTextSodium() {
        return textSodium;
    }

    public void setTextSodium(JTextField textSodium) {
        this.textSodium = textSodium;
    }

    public JTextField getTextPrice() {
        return textPrice;
    }

    public void setTextPrice(JTextField textPrice) {
        this.textPrice = textPrice;
    }

    public JButton getBtnInsert() {
        return btnInsert;
    }

    public void setBtnInsert(JButton btnInsert) {
        this.btnInsert = btnInsert;
    }
}
