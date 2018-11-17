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

/**
 * Apple library import. 
 * Disabled on windows version
*/
//import com.apple.eawt.Application; 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.json.JSONObject;

/**
 * @author Francesco Metta
 * @version 1
 */
public class HttpPost extends JFrame{
    //Attributes
    private JPanel panel;
    private JTextField auth_pin_field;
    private BufferedImage anylogo;
    private JLabel anylogoin;
    private JButton httprequest;
    public String auth_pin;
    private String url;
    private URL finalurl;
    private String secret;
    private String access_token;
    private BufferedReader in;
    private StringBuffer response;
    private HttpsURLConnection askAuth;
    private JSONObject jsonauth;
    private Login variables;
    private String secred;
    private File anyconfig_unix;
    private File anyconfig_win;
    AnySync window;
    private FileInputStream fileInputStream;
    private byte[] bFile;
    /**
     * CONSTRUCTOR
     * 
     */
    public HttpPost() throws IOException, URISyntaxException {
        this.secret = new String();
        this.fileInputStream = null;
        this.secred = new String();
        this.anyconfig_unix = new File(System.getProperty("user.home")+"/anysync/anysync.bin");
        this.anyconfig_win = new File(System.getProperty("user.home")+"\\anysync\\anysync.bin");
        switch(System.getProperty("os.name")){
            case "Mac OS X":
                this.bFile = new byte[(int) anyconfig_unix.length()];
                try{
                    //convert file into array of bytes
                    fileInputStream = new FileInputStream(anyconfig_unix);
                    fileInputStream.read(bFile);
                    fileInputStream.close();
                    secred = new String(bFile, StandardCharsets.UTF_8);
                    secret = secred.substring(4,secred.length());
                }catch (Exception e){
                    e.printStackTrace();
                }
            break;
            case "Windows":
                this.bFile = new byte[(int) anyconfig_win.length()];
                try{
                    //convert file into array of bytes
                    fileInputStream = new FileInputStream(anyconfig_win);
                    fileInputStream.read(bFile);
                    fileInputStream.close();
                    secred = new String(bFile, StandardCharsets.UTF_8);
                    secret = secred.substring(4,secred.length());
                }catch (Exception e){
                    e.printStackTrace();
                }
            break;
            default:
                this.bFile = new byte[(int) anyconfig_unix.length()];
                try{
                    //convert file into array of bytes
                    fileInputStream = new FileInputStream(anyconfig_unix);
                    fileInputStream.read(bFile);
                    fileInputStream.close();
                    secred = new String(bFile, StandardCharsets.UTF_8);
                    secret = secred.substring(4,secred.length());
                }catch (Exception e){
                    e.printStackTrace();
                }
            break;
        }
        this.window = new AnySync();
        this.variables = new Login();
        this.panel = new JPanel();
        this.auth_pin_field = new JTextField(20);
        this.anylogo = ImageIO.read(this.getClass().getResource("/res/login-form-logo.png"));
        this.anylogoin = new JLabel(new ImageIcon(anylogo));
        this.httprequest = new JButton("Login request");
        this.auth_pin = new String();
        this.url = new String("https://anilist.co/api/v2/oauth/"
            + "token?grant_type=authorization_code&client_id=" + variables.client_id 
            + "&client_secret=" + secret + "&redirect_uri=" + variables.redirectURI 
            + "&code=" + auth_pin_field.toString());
        this.finalurl = new URL(url);
        this.response = new StringBuffer(); 
    }
    
    // Create the window
    public void build() throws IOException{
        //Adding the panel to the program
        add(panel);
        init();
        setDefaultCloseOperation(3);
        setPreferredSize(new Dimension(300, 180));
        ImageIcon img = new ImageIcon(this.getClass().getResource("/res/icon.png"));
//        Application application = Application.getApplication(); //Apple icon
        Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/res/icon.png"));
        setIconImage(img.getImage());
//        application.setDockIconImage(image); //Set Apple dock icon
        setResizable(false);
        setTitle("AnySync");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        actions();
    }
    
    // Add things to the window
    private void init() throws IOException{
        //Adding logo
        panel.add(anylogoin);
        //Adding the textfield to the program
        panel.add(auth_pin_field);
        //Adding the button
        panel.add(httprequest);
        //Checking
        changed();
    }
    
    //Checking function
    public void changed() throws IOException {
        if (auth_pin_field.getText().equals("")){
            httprequest.setEnabled(false);
        }else{
            httprequest.setEnabled(true);
        }
    }
    
    // HTTP POST request
    private void sendPost() throws Exception {
        this.askAuth = (HttpsURLConnection) finalurl.openConnection();
        this.in = new BufferedReader(new InputStreamReader(askAuth.getInputStream()));
        //add reuqest header
        askAuth.setRequestMethod("POST");
        askAuth.setRequestProperty("Content-Type", "application/json");
        askAuth.setRequestProperty("Accept", "application/json");         
        while ((access_token = in.readLine()) != null) {
            response.append(access_token);
        }
        in.close();
        this.jsonauth = new JSONObject(response.toString());
        access_token = jsonauth.getString("access_token");
    }
    
    //Disable this windows after clicking login
    private void disableME() throws IOException, URISyntaxException{
        setVisible(false);
        dispose();
        window.build();
    }
    
    //DocumentListener + ActionListener
    private void actions() {
        auth_pin_field.getDocument().addDocumentListener(new DocumentListener() {
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
        
        httprequest.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                auth_pin_field.setEnabled(false);
                if(auth_pin_field.getText().equals("")){ 
                }else{
                    httprequest.setEnabled(false);
                    try {
                        sendPost();
                        //disableME();
                    } catch (Exception ex) {
                    }
                }
            }
        });
    }
}
