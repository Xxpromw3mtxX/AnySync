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
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import org.apache.commons.io.FileUtils;


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
    private InputStream source; //AnySync Source File 
    private File dest; //AnySync director
    private Login form;
    private File anyconfig;
    
    /**
     * CONSTRUCTOR
     */
    public UserCheck() throws IOException, URISyntaxException {
        this.form = new Login();
        this.source = getClass().getResourceAsStream("/client_info/anysync.bin");
        this.anyconfig = new File("anysync.bin");
        this.unix = new String("/anysync/");
        this.win = new String("\\anysync\\");
        /*This line is used for checking the OS on where AnySync is running on*/
        this.os = System.getProperty("os.name");
        /*This line help us know what is the home path*/
        this.home = System.getProperty("user.home");
    }
    
    /*The method checkDirectory know with system you are using and choose what to do*/
    public void checkDirectory() throws IOException, URISyntaxException{
        switch(os){
            case "Mac OS X":
                dest = new File(home+unix);
                if(!dest.exists()){
                    dest.mkdir();
                    FileUtils.copyInputStreamToFile(source, anyconfig);
                    FileUtils.copyFileToDirectory(anyconfig, dest);
                    form.build();
                }
                if(dest.list().length>0){
                    form.build();
                }else{
                    FileUtils.copyInputStreamToFile(source, anyconfig);
                    FileUtils.copyFileToDirectory(anyconfig, dest);
                    form.build();
                }
            break;
            case "Windows":
                dest = new File(home+win);
                if(!dest.exists()){
                    dest.mkdir();
                    FileUtils.copyInputStreamToFile(source, anyconfig);
                    FileUtils.copyFileToDirectory(anyconfig, dest);
                    form.build();
                }
                if(dest.list().length>0){
                    form.build();
                }else{
                    FileUtils.copyInputStreamToFile(source, anyconfig);
                    FileUtils.copyFileToDirectory(anyconfig, dest);
                    form.build();
                }
            break;
            default:
                dest = new File(home+unix);
                if(!dest.exists()){
                    dest.mkdir();
                    FileUtils.copyInputStreamToFile(source, anyconfig);
                    FileUtils.copyFileToDirectory(anyconfig, dest);
                    form.build();
                }
                if(dest.list().length>0){
                    form.build();
                }else{
                    FileUtils.copyInputStreamToFile(source, anyconfig);
                    FileUtils.copyFileToDirectory(anyconfig, dest);
                    form.build();
                }
            break;
        }
        
    }    
}
