// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CSLauncher.java

package applet;

import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class CSLauncher  extends JApplet{

	public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                	//C:\Program Files (x86)\Adobe\Reader 11.0\Reader
                	String exec = "C:\\Program Files (x86)\\Adobe\\Reader 11.0\\Reader\\AcroRd32.exe";
                	try {
						Process p = Runtime.getRuntime().exec(exec);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    /*JLabel lbl = new JLabel("Hello World");
                    add(lbl);*/
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }
 
}

