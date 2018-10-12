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
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Francesco
 * @version 1
 */
public class AnySync extends JFrame{
    //Attributes
    private JPanel panel;
    
    /**
     * CONSTRUCTOR
     */
    public AnySync ()throws IOException{
        this.panel = new JPanel();
        
        
        build();
    }
    
    // Create the window
    private void build() throws IOException{
        add(panel);
        setDefaultCloseOperation(3);
        setPreferredSize(new Dimension(900, 600));
        ImageIcon img = new ImageIcon("src/res/icon.png");
//        Application application = Application.getApplication(); //Apple icon
        Image image = Toolkit.getDefaultToolkit().getImage("src/res/icon.png");
        setIconImage(img.getImage());
//        application.setDockIconImage(image); //Set Apple dock icon
        setResizable(false);
        setTitle("AnySync (Pre-Alpha)");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Add things to the window
    private void init() throws IOException{
    }
}
