package snowman;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;


public class WinterScenePanel extends JPanel implements Runnable
{
	private List<AbstractShape> shapes;
	private AbstractShape sMan;

	public WinterScenePanel()
	{
		setVisible(true);
		//refer shapes to a new ArrayList of AbstractShape
                shapes = new ArrayList<AbstractShape>();
                
		//populate the list with 50 unique snowflakes
                for(int i = 0; i < 50; i++){
                    
                    int y = (int)(Math.random() * 600); 
                    int s = (int)(Math.random() * 60) + 20; 
                    
                    shapes.add(new FancySnowFlake(i*16, y, s, s));
                }
                
		//instantiate a snowman
                sMan = new SnowMan(50, 50, 30, 30);
                
		new Thread(this).start();
	}

	public void update(Graphics window)
	{
            paint(window);
	}

	public void paint(Graphics window)
	{
		window.setColor(Color.BLUE);
		window.fillRect(0,0,getWidth(), getHeight());
		window.setColor(Color.WHITE);
		window.drawRect(20,20,getWidth()-40,getHeight()-40);
		window.setFont(new Font("TAHOMA",Font.BOLD,18));
		window.drawString("MAKE A WINTER SCENE!",40,40);

		//make the snowman appear
                sMan.draw(window);
		//make the snowflakes appear and move down the screen
                for(AbstractShape snow : shapes){
                    snow.moveAndDraw(window);
                    if(snow.getYPos() >= getHeight()){
                        snow.setYPos(0);
                        snow.setXPos((int)(Math.random() * 600));
                    }
                }
		//check to see if any of the snowflakes need to be reset to the top of the screen
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(35);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}
