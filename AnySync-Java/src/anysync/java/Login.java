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

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.apple.eawt.Application;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Francesco
 * @version 1
 */
public class Login extends JFrame{
    //Attributes
    private int client_id = 1231; //AnySync application ID
    private String response_type = "token";
    private String token;
    String url = "https://anilist.co/api/v2/oauth/authorize?client_id=" + client_id + "&response_type=" + response_type;
    static GraphicsConfiguration gc;
    
    public Login (String title){
        super(title);
        ImageIcon img = new ImageIcon("src/res/icon.png");
        Application application = Application.getApplication();
        Image image = Toolkit.getDefaultToolkit().getImage("src/res/icon.png");
        setPreferredSize(new Dimension(300, 250));
        setDefaultCloseOperation(3);
        setResizable(false);
        setIconImage(img.getImage());
        application.setDockIconImage(image);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
