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

import java.awt.Desktop;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 * Apple library import. 
 * Disabled on windows version
*/
//import com.apple.eawt.Application; 
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * @author Francesco Metta
 * @version 1
 */
public class Login extends JFrame implements DocumentListener, ActionListener{
    //Attributes
    public int client_id = 1231; //AnySync application ID
    public String redirectURI;
    private String response_type = "code";
    private String token;
    private String url;
    private URI finalurl;
    private JPanel panel;
    public JTextField username;
    private JButton anilogin; 
    private BufferedImage anylogo;
    private JLabel anylogoin;
    private Desktop browser;
    private Document textFieldDoc;
    
    
    /**
     * CONSTRUCTOR
     */
    public Login ()throws IOException, URISyntaxException{
        super();
        this.redirectURI = new String("https://anilist.co/api/v2/oauth/pin");
        this.finalurl = new URI("https://anilist.co/api/v2/oauth/authorize?client_id=" + client_id 
                + "&redirect_uri=" + redirectURI + "&response_type=" + response_type);
        this.panel = new JPanel();
        this.username = new JTextField(15);
        this.anilogin = new JButton("Login with AniList");
        this.anylogo = ImageIO.read(new File("src/res/login-form-logo.png"));
        this.anylogoin = new JLabel(new ImageIcon(anylogo));
        this.browser = Desktop.getDesktop();
        this.textFieldDoc = username.getDocument();
        
        //build();  
        
    }
    
    // Create the window
    public void build() throws IOException{        
        add(panel);
        init();
        setDefaultCloseOperation(3);
        setPreferredSize(new Dimension(300, 180));
        ImageIcon img = new ImageIcon("src/res/icon.png");
//        Application application = Application.getApplication(); //Apple icon
        Image image = Toolkit.getDefaultToolkit().getImage("src/res/icon.png");
        setIconImage(img.getImage());
//        application.setDockIconImage(image); //Set Apple dock icon
        setResizable(false);
        setTitle("AnySync - Login(Pre-Alpha)");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        actions();
    }
    
    // Add things to the window
    private void init() throws IOException{
        panel.add(anylogoin);
        panel.add(username);
        username.getText();
        panel.add(anilogin);
        changed();
    }
    
    //Checking function
    public void changed() throws IOException {
        if (username.getText().equals("")){
            anilogin.setEnabled(false);
        }else{
            anilogin.setEnabled(true);
        }
    }
    
    //Disable this windows after clicking login
    private void disableME() throws IOException, URISyntaxException{
        setVisible(false);
        dispose();
        //AnySync application = new AnySync();
        /*We recall the HttpPost class that send a POST reuqest to anilist.co db
        to let us login into the website*/
        HttpPost request = new HttpPost();
    }
    
    
    //DocumentListener + ActionListener
    private void actions() {
        username.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                try {
                    changed();
                } catch (IOException ex) {
                }
            }
            public void removeUpdate(DocumentEvent e) {
                try {
                    changed();
                } catch (IOException ex) {
                }
            }
            public void insertUpdate(DocumentEvent e) {
                try {
                    changed();
                } catch (IOException ex) {
                }
            }
        });
        
        anilogin.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{ 
                    browser.browse(finalurl);
                    username.setEnabled(false);
                    if(username.getText().equals("")){ 
                    }else{
                        anilogin.setEnabled(false);
                        disableME();
                    }
                }catch(IOException err){
                } catch (URISyntaxException ex) {
                    //Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    //Ignore
    @Override
    public void insertUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
}
