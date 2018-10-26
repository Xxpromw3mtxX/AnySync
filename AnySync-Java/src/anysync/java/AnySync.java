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
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;

/**
 * @author Francesco Metta
 * @version 1
 */
public class AnySync extends JFrame {
    //Attributes
    private JPanel panel;
    private JPanel sidebar;
    
    //Menu attributes
    private JMenuBar menubar;
    private JMenu menu;
    private JMenu help;
    private JMenu service;
    private JMenuItem exit;
    private JMenuItem update;
    private JMenuItem about;
    private JMenuItem issue;
    private JMenuItem login;
    private JMenuItem anilist;
    private JSplitPane split;
    /**
     * CONSTRUCTOR
     */
    public AnySync ()throws IOException{
        this.panel = new JPanel();
        this.sidebar = new JPanel();
        //Menu creation
        this.menubar = new JMenuBar();
        this.menu = new JMenu("File");
        this.help = new JMenu("Help");
        this.service = new JMenu("Service");
        this.exit = new JMenuItem ("Exit", new ImageIcon ("src/res/theme/16px/cross.png"));
        this.update = new JMenuItem("Update", new ImageIcon ("src/res/theme/16px/arrow-circle-315.png"));
        this.about = new JMenuItem("About", new ImageIcon("src/res/theme/16px/about.png"));
        this.issue = new JMenuItem("Report issue...", new ImageIcon("src/res/theme/16px/application-small.png"));
        this.login = new JMenuItem("Login", new ImageIcon("src/res/theme/16px/if_login_59481.png"));
        this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, panel){
            private final int location = 175;
            {
                setDividerLocation(location);
                setDividerSize(1);
            }
            @Override
            public int getDividerLocation() {
                return location ;
            }
            @Override
            public int getLastDividerLocation() {
                return location ;
            }
        };
    }
    
    // Create the window
    public void build() throws IOException{
        add(split);
        setJMenuBar(menubar);
        init();
        setDefaultCloseOperation(3);
        setPreferredSize(new Dimension(900, 600));
        ImageIcon img = new ImageIcon("src/res/icon.png");
//        Application application = Application.getApplication(); //Apple icon
        Image image = Toolkit.getDefaultToolkit().getImage("src/res/icon.png");
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
        //Add menu to menu bar
        menubar.add(menu);
        menubar.add(service);
        menubar.add(help);
        //Add items to menu
        menu.add(login);
        menu.add(new JSeparator()); // SEPARATOR
        menu.add(exit);
        //Add items to help
        help.add(issue);
        help.add(new JSeparator()); // SEPARATOR
        help.add(about);
        //Add items to Service
        service.add(update);

    }
    
    //ActionListener
    private void actions() {
        exit.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
}
