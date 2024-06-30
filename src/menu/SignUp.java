package menu;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SignUp extends JFrame {
    JTextField text1, text2;
    JButton button1, button2, button;
    JLabel label1, label2, label3;

    public String imagePath = "src/image/";


    public SignUp() {
        setLayout(null);//no layout, u can put flow or grid if u want
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);


        label1 = new JLabel("Sign Up");
        label1.setFont(new Font("Ink Free", Font.BOLD, 30));
        label1.setForeground(Color.black);
        label1.setBounds(190, 13, 300, 50);
        add(label1);


        text1 = new JTextField(60);
        text2 = new JPasswordField(60);
        button1 = new JButton("Sign Up");
        button2 = new JButton("Login");


        text1.setBounds(180, 60, 120, 30);
        text2.setBounds(180, 100, 120, 30);
        button1.setBounds(200, 140, 80, 30);
        button2.setBounds(200, 190, 80, 30);

        label2 = new JLabel("Username:");
        label2.setBounds(100, 60, 300, 30);
        add(label2);

        label3 = new JLabel("Password:");
        label3.setBounds(100, 100, 300, 30);
        add(label3);

        button1.setFocusable(false);// these 3 lines make button transparent
        button1.setBackground(Color.white);
        button1.setOpaque(false);

        button2.setFocusable(false);// these 3 lines make button transparent
        button2.setBackground(Color.white);
        button2.setOpaque(false);


        add(text1);
        add(text2);
        add(button1);
        add(button2);
        createBackButton();
        createBackground();


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //writeCode

                //validate empty username and empty password
                if (text1.getText().isEmpty() || text2.getText().isEmpty()) {
//                    label2.setText("must not be empty");
                    JOptionPane.showMessageDialog(null, "Please fill in the blank", "the box is empty", JOptionPane.WARNING_MESSAGE);
                } else

                    //validate no spacing is allowed in username and password

                    if (containsWhiteSpace(text1.getText()) || containsWhiteSpace(text2.getText())) {
//                        label2.setText("Username and Password must not have space");
                        JOptionPane.showMessageDialog(null, "Username and Password must not have space" , "Space alert", JOptionPane.WARNING_MESSAGE);
                    } else  //check whether account already exist!
                        if (isAccountExist(text1.getText(), text2.getText())) {
//                            label2.setText("Account already exist!. Just login in.");
                            JOptionPane.showMessageDialog(null, "Please just login in", "Account existed", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            try {
                                FileWriter fw = new FileWriter("login.txt", true);
                                fw.write(text1.getText() + "\t" + text2.getText() + "\n");
                                fw.close();
                                JFrame f = new JFrame();
                                JOptionPane.showMessageDialog(f, "Registration Completed!");
                                dispose();
                                OptionFrame optionFrame = new OptionFrame(text1.getText());
                                optionFrame.setVisible(true);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //writeCode
                dispose();
                Login login = new Login();
                login.setVisible(true);
                login.setBounds(400, 200, 500, 300);
            }
        });


    }

    public static boolean containsWhiteSpace(String line) {
        boolean space = false;
        if (line != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ' ') {
                    space = true;
                }
            }
        }
        return space;
    }

    public static boolean isAccountExist(String userName, String password) {
        try {
            FileReader fr = new FileReader("login.txt");
            BufferedReader br = new BufferedReader(fr); //read line by line
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(userName + "\t" + password)) {
                    return true;
                }
            }
            fr.close();
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
        return false;
    }

    private void createBackButton() {
        button = new JButton("BACK");
        button.setBounds(50, 190, 80, 30);

        button.setFocusable(false);// these 3 lines make button transparent
        button.setBackground(Color.white);
        button.setOpaque(false);

        button.addActionListener(e -> {
            MenuFrame menuFrame = new MenuFrame();
            menuFrame.setVisible(true);
            this.dispose();
            System.out.println("Load Menu Frame");
        });

        this.add(button);
    }

    private void createBackground() {
        String backgroundIconPath = imagePath + "optionFrame.jpg";
        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon(backgroundIconPath).getImage().getScaledInstance
                (900, 600, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 500, 300);
        this.add(background);
    }
}