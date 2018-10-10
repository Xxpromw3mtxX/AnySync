/*
** AnySync
** Copyright (C) 2018, Francesco Metta
**
** This program is free software: you can redistribute it and/or modify
** it under the terms of the GNU General Public License as published by
** the Free Software Foundation, either version 3 of the License, or
** (at your option) any later version.
**
** This program is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
** GNU General Public License for more details.
**
** You should have received a copy of the GNU General Public License
** along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package anysync.java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import com.apple.eawt.Application;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Francesco
 * @version 1
 */
public class Login extends JFrame implements ActionListener {
    //Attributes
    private int client_id = 1231; //AnySync application ID
    private String response_type = "token";
    private String token;
    String url = "https://anilist.co/api/v2/oauth/authorize?client_id=" + client_id + "&response_type=" + response_type;
    private JPanel panel;
    private JTextField username;
    private JButton anilogin; 
    private BufferedImage anylogo;
    private JLabel anylogoin;
    
    /**
     * CONSTRUCTOR
     */
    public Login ()throws IOException{
        super();
        this.panel = new JPanel();
        this.username = new JTextField(15);
        this.anilogin = new JButton("Login with AniList");
        this.anylogo = ImageIO.read(new File("src/res/login-form-logo.png"));
        this.anylogoin = new JLabel(new ImageIcon(anylogo));
        build();  
        actions();
    }
    
    // Create the window
    private void build(){        
        add(panel);
        init();
        setDefaultCloseOperation(3);
        setPreferredSize(new Dimension(300, 180));
        ImageIcon img = new ImageIcon("src/res/icon.png");
//      Application application = Application.getApplication();
        Image image = Toolkit.getDefaultToolkit().getImage("src/res/icon.png");
        setIconImage(img.getImage());
//      application.setDockIconImage(image);
        setResizable(false);
        setTitle("AnySync.v1 - Login");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Add things to the window
    private void init(){
        panel.add(anylogoin);
        panel.add(username);
        username.getText();
        panel.add(anilogin);
    }
    
    //Action listener
    private void actions() {
        anilogin.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent act) {
        
    }    
}
