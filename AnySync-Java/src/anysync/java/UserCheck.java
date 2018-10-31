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

import java.io.File;

/**
 * @author Francesco Metta
 * @version 1
 */
public class UserCheck {
    //Attributes
    private String os;
    private String home;
    private String unix;
    private String win;
    private File asd; //AnySync director
    
    /**
     * CONSTRUCTOR
     */
    public UserCheck() {
        unix = new String("/anysync");
        win = new String("\\anysync");
        /*This line is used for checking the OS on where AnySync is running on*/
        os = System.getProperty("os.name");
        /*This line help us know what is the home path*/
        home = System.getProperty("user.home");
    }
    
    /*The method checkDirectory know with system you are using and choose what to do*/
    public void checkDirectory(){
        switch(os){
            case "Mac OS X":
                asd = new File(home+unix);
                if(!asd.exists())asd.mkdir();
            break;
            case "Windows":
                asd = new File(home+win);
                if(!asd.exists())asd.mkdir();
            break;
            default:
                asd = new File(home+unix);
                if(!asd.exists())asd.mkdir();
            break;
        }
        
    }
    
}