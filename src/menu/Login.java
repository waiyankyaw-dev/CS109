package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Login extends JFrame {
    JTextField text1, text2;
    JButton button1, button2, backButton;
    JLabel label1, label2, label3;


    public String imagePath = "src/image/";
    public Login() {
        setLayout(null); //no layout, u can put flow or grid if u want
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);



        label1 = new JLabel("REGISTRATION");
        label1.setFont(new Font("Ink Free", Font.BOLD, 25));
        label1.setForeground(Color.black);
        label1.setBounds(140, 15, 300, 30);
        add(label1);


        text1 = new JTextField(60);
        text2 = new JPasswordField(60);
        button1 = new JButton("LogIn");
        button2 = new JButton("SignUp");


        text1.setBounds(180, 60, 120, 30);
        text2.setBounds(180, 100, 120, 30);
        button1.setBounds(200, 140, 80, 30);
        button2.setBounds(200, 190, 80, 30);


        button1.setFocusable(false);// these 3 lines make button transparent
        button1.setBackground(Color.white);
        button1.setOpaque(false);
        button2.setFocusable(false);// these 3 lines make button transparent
        button2.setBackground(Color.white);
        button2.setOpaque(false);


        label2 = new JLabel("Username:");
        label2.setBounds(100, 60, 300, 30);
        add(label2);

        label3 = new JLabel("Password:");
        label3.setBounds(100, 100, 300, 30);
        add(label3);


        add(text1);
        add(text2);
        add(button1);
        add(button2);
        createBackButton();
        createBackground();

        //check when log in
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //writeCode
                boolean matched = false;
                String userName = text1.getText();
                String password = text2.getText();
                try {
                    FileReader fr = new FileReader("login.txt");
                    BufferedReader br = new BufferedReader(fr); //read line by line
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.equals(userName + "\t" + password)) {
                            matched = true;
                        }
                    }
                    fr.close();
                } catch (Exception error) {
                    matched = false;
//                    throw new RuntimeException(error);
                }


                if (matched) {

                    //open gameboard
//                    label2.setText("Successful!");
                    System.out.println("Login Successful");
                    dispose();
                    OptionFrame optionFrame = new OptionFrame(userName);
                    optionFrame.setVisible(true);
                } else {
                    JFrame f = new JFrame();
//                    label2.setText("Invalid Username or Password!");
                    JOptionPane.showMessageDialog(null,"Please sign up first", "Invalid Username or Password!", JOptionPane.ERROR_MESSAGE );


                }
            }
        });

        //check when signUp
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //writeCode
                dispose();
                SignUp signup = new SignUp();
                signup.setVisible(true);

                //validate no spacing is allowed in username and password
//                if(){
//
//                if (containsWhiteSpace(text1.getText()) || containsWhiteSpace(text2.getText())) {
//                    label2.setText("Username and Password must not have space");
//
//
//                } else  //check whether account already exist!
//                    if (isAccountExist(text1.getText(), text2.getText())) {
//                        label2.setText("Account already exist!. Just login in.");
//                    } else {
//                        try {
//                            FileWriter fw = new FileWriter("login.txt", true);
//                            fw.write(text1.getText() + " " + text2.getText() + "\n");
//                            fw.close();
//                            JFrame f = new JFrame();
//                            JOptionPane.showMessageDialog(f, "Registration Completed!");
//                            //After successful registration, open game
//                            dispose();
//                        } catch (IOException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    }
            }
        });

    }
    private void createBackButton(){
        backButton = new JButton("BACK");
        backButton.setBounds(50,190,80,30);

        backButton.setFocusable(false);// these 3 lines make button transparent
        backButton.setBackground(Color.white);
        backButton.setOpaque(false);

        backButton.addActionListener(e->{
            MenuFrame menuFrame = new MenuFrame();
            menuFrame.setVisible(true);
            this.dispose();
            System.out.println("Load Menu Frame");
        });

        this.add(backButton);
    }

    private void createBackground(){
        String backgroundIconPath = imagePath + "optionFrame.jpg";
        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon(backgroundIconPath).getImage().getScaledInstance
                (900, 600, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 500, 300);
        this.add(background);
    }


}
