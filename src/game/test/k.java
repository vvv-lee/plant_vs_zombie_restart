package game.test;

import game.config.zombie.ZombieCard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class k extends JFrame{

    private MyPanel panel ;
    public k(){
        panel = new MyPanel();
        this.getContentPane().add(panel);

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new k();
    }

    class MyPanel extends JPanel implements Runnable{
        final int Max=1000;
        Wparticle p[];
        int AppletWidth,AppletHeight,XCenter,YCenter=200;
        BufferedImage OffScreen ;//=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);

        Graphics drawOffScreen;
        Thread pThread;
        public MyPanel(){

            setBackground(Color.black);
            AppletWidth=400;//getSize().width;
            AppletHeight=400;//getSize().height;
            p=new Wparticle[Max];
            for(int i=0;i<Max;i++)
                p[i]=new Wparticle();
            //OffScreen=createImage(AppletWidth,AppletHeight);
            OffScreen =new BufferedImage(AppletWidth,AppletHeight,BufferedImage.TYPE_INT_BGR);
            drawOffScreen=OffScreen.getGraphics();
            pThread = new Thread(this);
            pThread.start();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(OffScreen,0,0,this);

        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            boolean reset=false;

            int i,t=0;
            while(true){
                drawOffScreen.clearRect(0, 0, AppletWidth, AppletHeight);
                drawOffScreen.setColor(Color.white);
                drawOffScreen.drawLine(0,15,10,15);
                for(i=0;i<Max;i++){
                    drawOffScreen.fillOval((int)p[i].X,(int)p[i].Y,3,3);

                    p[i].X=p[i].X+p[i].Vx;
                    if(p[i].X>10){

                        p[i].Y+=p[i].Vy*p[i].time/1000;
                        p[i].Vy=(int)9.8*p[i].time;
                        p[i].time++;
                    }

                    if(p[i].Y>AppletHeight){

                        p[i].reset();
                    }
                }

                repaint();

                try{

                    Thread.sleep(100);
                }catch(InterruptedException e){}
            }
        }
    }

    class Wparticle{
        double X,Y;
        double Vx,Vy;
        int time;
        public Wparticle(){
            reset();
        }
        public void reset(){
            X=(int)(Math.random()*-40);
            Y=(int)(Math.random()*5+10);
            Vx=Math.random()*3+1.0;
            Vy=0;
            time=0;

        }
    }
}