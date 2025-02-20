package game;

/**
* Cloud class which describes the location, size of a cloud
* @author Wen Zhong
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Cloud extends Element implements Runnable{
	
	/**
	 * constructor
	 */
	public Cloud(){
		super();
	}

	/**
	 * change the location of cloud
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			move();
			if(x + image.getIconWidth() * 0.15 < 0) 
				x = width;
		}
	}	
}
