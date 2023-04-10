import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class firstPage extends JFrame implements ActionListener{
    JLabel l,l1,l2,l3; JButton b1,b2,b3,next;
    firstPage()
    {
        JLayeredPane pane=getLayeredPane();
        ImageIcon i= new ImageIcon("71.png");
        l2= new JLabel(i);
        l2.setBounds(0,0,800,600);
        l2.setVisible(true);
        b1=new JButton("Start");
        b1.setBounds(360,140,80,35); 
        b1.setBackground(Color.DARK_GRAY);
        b1.setFocusable(false);
        b1.setForeground(Color.red);
        b2= new JButton("Sign in");
        b2.setBounds(250,250,100,50);
        b2.setVisible(false);
        b3= new JButton("Sign up");
        b3.setBounds(450,250,100,50);
        b3.setVisible(false);
        l=new JLabel("Welcome to Build IT!!!");
        l.setBounds(300,5,250,30);
        l.setVisible(false);
        l.setFont(new Font("Serif", Font.BOLD, 24));
        l.setForeground(Color.CYAN);
        l1= new JLabel("Let's build your dream PC!");
        l1.setBounds(310,30,300,30);
        l1.setFont(new Font("Serif", Font.BOLD, 18));
        l1.setForeground(Color.CYAN);
        
        
        l3= new JLabel();
        l3.setBounds(350,200,200,100);
        l3.setVisible(false);
        l3.setFont(new Font("Serif", Font.BOLD, 18));
        
        l1.setVisible(false);
        b1.addActionListener(this);
       b2.addActionListener(this);
       b3.addActionListener(this);
       
       pane.add(l2,new Integer(1));
       pane.add(b1,new Integer(2));
       pane.add(l,new Integer(2));
       pane.add(l1,new Integer(2));
       pane.add(b2,new Integer(2));
       pane.add(b3,new Integer(2));
       
       
       
        
        
        setVisible(true);
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e){
       try{ if(e.getSource()==b1)
        {
            l.setVisible(true);
            
            l1.setVisible(true);
           

        }
        
        b2.setVisible(true);
        b3.setVisible(true);
        
        if(e.getSource()==b3)
        {
            new signupPage();
            
        }
        else if(e.getSource()==b2)
        {
            new signinPage();
            
        }
        
         }
            catch(Exception ex){
        
            }
        
    }
    public static void main(String[] args){
        new firstPage();
    }


}
class signupPage extends JFrame implements ActionListener
{
    JButton b;
    signupPage()
    {
        
    
     JLabel label = new JLabel();            
     label.setBounds(20,150, 200,50);  
      JPasswordField value = new JPasswordField();   
     value.setBounds(100,140,100,30);   
     JLabel l1=new JLabel("Username:");    
        l1.setBounds(20,20, 80,20);  
        JLabel l2=new JLabel("Phone no.:");    
        l2.setBounds(20,50, 80,20); 
        JLabel l3=new JLabel("E-mail:");    
        l3.setBounds(20,80, 80,20); 
        JLabel l4=new JLabel("Age:");    
        l4.setBounds(20,110, 80,20);   
        JLabel l5=new JLabel("Password:");    
        l5.setBounds(20,140, 80,20);       
        JTextField t1 = new JTextField();  
        t1.setBounds(100,20, 200,20);   
        JTextField t2 = new JTextField();  
        t2.setBounds(100,50, 200,20); 
        JTextField t3 = new JTextField();  
        t3.setBounds(100,80, 200,20); 
        JTextField t4 = new JTextField();  
        t4.setBounds(100,110, 200,20); 
        
       b= new JButton("Login");  
        b.setBounds(100,170, 80,20); 
        

        add(value); add(l1); add(label); add(l2); add(b); add(t1);  add(l3); add(l4); add(l5); add(t2); add(t3); add(t4); 
        b.addActionListener(this);
        setSize(500,500);    
        setLayout(null);    
        setVisible(true);
        setLocationRelativeTo(null);
    }
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource()==b)
            {
                new secondPage();
                this.dispose();
            }
        }  


}
class signinPage extends JFrame{
    JButton b;
    JPasswordField value;
    JTextField text;
    signinPage(){
        
        final JLabel label = new JLabel();            
     label.setBounds(20,150, 200,50);  
      value = new JPasswordField();   
     value.setBounds(100,75,100,30);   
     JLabel l1=new JLabel("Username:");    
        l1.setBounds(20,20, 80,30);    
        JLabel l2=new JLabel("Password:");    
        l2.setBounds(20,75, 80,30);    
        b = new JButton("Login");  
        b.setBounds(100,120, 80,30); 
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String email = text.getText();
                String password = String.valueOf(value.getPassword());
                User user = getAuthenticatedUser(email, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(signinPage.this,
                            "Welcome back " +email+ " to Build IT!!",
                            "Login Succesfull",
                            JOptionPane.PLAIN_MESSAGE);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(signinPage.this,
                            "username or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
                new secondPage();
                dispose();

            }

            private User getAuthenticatedUser(String username, String password) {
                User user=null;

                final String DB_URL = "jdbc:mysql://localhost:3306/user";
                final String USERNAME = "sqluser";
                final String PASSWORD = "password";

                try{
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    // Connected to database successfully...
        
                    String sql = "SELECT * FROM login WHERE username=? AND password=?";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
        
                    ResultSet resultSet = preparedStatement.executeQuery();
        
                    if (resultSet.next()) {
                        user = new User();
                        user.username = resultSet.getString("username");
                        user.password = resultSet.getString("password");
                        
                    }
                    
        
                    preparedStatement.close();
                    conn.close();

        
                }catch(Exception e){
                    System.out.println("Database connexion failed!");
                }
                return user;
            }
            
        });
        

        text = new JTextField();  
        text.setBounds(100,20, 100,30);    
                add(value); add(l1); add(label); add(l2); add(b); add(text);  
                setSize(300,300);    
                setLayout(null);    
                setVisible(true); 
                // b.addActionListener(this);  
                setLocationRelativeTo(null);  
    }
    

}
class secondPage extends JFrame implements ActionListener
{
    JButton b;
    JCheckBox cb1,cb2,cb3;  
    
    secondPage() 
    {
        
        ImageIcon i=new ImageIcon("51.png");
        JLayeredPane p=getLayeredPane();
        JLabel l1= new JLabel(i);
        l1.setBounds(0,0,800,600);
        JLabel l = new JLabel("Select PC Type");
        l.setBounds(50,10,100,20);
        cb1=new JCheckBox("Daily Use");  
        cb1.setBounds(450,150,150,20);  
        cb1.setOpaque(false);
        cb1.setForeground(Color.WHITE);
        cb1.setFont(new Font("Serif", Font.BOLD, 18));
        cb2=new JCheckBox("Office Work");  
        cb2.setBounds(450,200,150,20); 
        cb2.setOpaque(false); 
        cb2.setForeground(Color.WHITE);
        cb2.setFont(new Font("Serif", Font.BOLD, 18));
        cb3=new JCheckBox("Gaming");  
        cb3.setBounds(450,250,150,20); 
        cb3.setOpaque(false); 
        cb3.setForeground(Color.WHITE);
        cb3.setFont(new Font("Serif", Font.BOLD, 18));
        b= new JButton("Continue");
        b.setBounds(450,300,100,40);
        p.add(l1,new Integer(1));
        p.add(b,new Integer(2));
        p.add(cb1,new Integer(2));
        p.add(cb2,new Integer(2));
        p.add(cb3,new Integer(2));
        add(l); 
        
        b.addActionListener(this);
        setVisible(true);
        setLayout(null);
        setSize(800,600);
        setLocationRelativeTo(null);
       
    }
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource()==b&&cb1.isSelected())
            {
                new dailyUse();
                this.dispose();
            }
            else if(e.getSource()==b&&cb2.isSelected())
            {
                new officeWork();
                this.dispose();
            }
            else if(e.getSource()==b&&cb3.isSelected())
            {
                new GamingType();
                this.dispose();
            }
            
        } 


}
class dailyUse extends JFrame implements ActionListener
{
    JButton b,b1,b2;
    int ch=0;
    int cost=0;
    int total;
    dailyUse()
    {
      
        ImageIcon i1=new ImageIcon("daily.png");
        JLayeredPane p=getLayeredPane();
        JLabel l3=new JLabel(i1);
        l3.setBounds(0,0,800,600);
        JLabel l=new JLabel("RAM");
        l.setBounds(50,50,100,30);
        String ram[]={"RAM","2gb","4gb","8gb"};
        JComboBox cb1=new JComboBox<>(ram);
        cb1.setBounds(30,10,80,50);
        

        String rom[]={"ROM","256GB HDD","512GB HDD","1TB HDD"};
        JComboBox cb2=new JComboBox<>(rom);
        cb2.setBounds(30,70,80,50);
        
        String proc[]={"Processor","Core i3 7th gen"," Core i3 10th gen","Core i5 9th gen","Core i5 10th gen"};
        JComboBox cb3=new JComboBox<>(proc);
        cb3.setBounds(30,130,120,50);
        
        String mot[]={"Motherboard","ASRock A320M-HDV R4.0","Asrock A520M-HDV","ASRock B450 Pro4 R2.0","ASRock B450M Pro4"};
        JComboBox cb4=new JComboBox<>(mot);
        cb4.setBounds(30,190,150,50);
       
        String acc[]={"Accessories Brand","Samsung","Acer","Redgaer"};
        JComboBox cb5=new JComboBox<>(acc);
        cb5.setBounds(30,250,150,50);

        String cool[]={"Cooling System","450W Bronze","550W Bronze","550W Gold"};
        JComboBox cb6=new JComboBox<>(cool);
        cb6.setBounds(30,310,150,50);

        String grap[]={"Graphc Card","Gigabyte rtx 1080 2gb vram","Gigabyte rtx 1650 4gb","Nvidia rtx 3050 4gb"};
        JComboBox cb7=new JComboBox<>(grap);
        cb7.setBounds(30,370,150,50);

        String extra[]={"OTHER","RAM","ROM","Cabinet","Motherboard","Extra Components"};
        JComboBox cb8=new JComboBox<>(extra);
        cb8.setBounds(30,430,150,50);
        
        b= new JButton("Price");
        b.setBounds(430,350,150,30);

        b1= new JButton("Continue");
        b1.setBounds(430,400,150,30);
        
        b2= new JButton("Exit");
        b2.setBounds(430,450,150,30);
        JTextField tff=new JTextField();
        tff.setBounds(30,490,150,50);
        tff.setVisible(false);

        cb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb1.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=1500;
                            break;
                    case 2: cost+=2000;
                    break;
                    case 3:cost+=2500;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb2.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=2500;
                    break;
                    case 2: cost+=3000;
                    break;
                    case 3:cost+=5000;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb3.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb3.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=6700;
                    break;
                    case 2: cost+=7000;
                    break;
                    case 3:cost+=8000;
                    break;
                    case 4:cost+=9500;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb4.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                
                ch=cb4.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=4000;
                    break;
                    case 2: cost+=6000;
                    break;
                    case 3:cost+=7475;
                    break;
                    case 4:cost+=6705;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb5.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=11500;
                    break;
                    case 2: cost+=12500;
                    break;
                    case 3:cost+=13500;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb6.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=3300;
                    break;
                    case 2: cost+=3800;
                    break;
                    case 3:cost+=4750;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb7.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=12000;
                    break;
                    case 2: cost+=16800;
                    break;
                    case 3:cost+=22500;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb8.getSelectedIndex();
                
                switch(ch)
                {
                    case 1: cost+=4000;
                    tff.setVisible(true);
                    break;
                    case 2: cost+=5000;
                    tff.setVisible(true);
                    break;
                    case 3:cost+=5000;
                    tff.setVisible(true);
                    break;
                    case 4:cost+=8000;
                    tff.setVisible(true);
                    break;
                    case 5:cost+=4000;
                    tff.setVisible(true);
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        p.add(l3,new Integer(1));
        p.add(b,new Integer(2));
        p.add(cb1,new Integer(2));
        p.add(cb2,new Integer(2));
        p.add(cb3,new Integer(2));
        p.add(cb4,new Integer(2));
        p.add(cb5,new Integer(2));
        p.add(cb6,new Integer(2));
        p.add(cb7,new Integer(2));
        p.add(cb8,new Integer(2));
        p.add(b1,new Integer(2));
        p.add(b2,new Integer(2));
        p.add(tff,new Integer(2));
       // p.add(l,new Integer(2));
        
       
        b.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setVisible(true);
        setLayout(null);
        setSize(800,600);
        setLocationRelativeTo(null);
    }
    
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource()==b)
            {
                JOptionPane.showMessageDialog(this,"You're pc build cost is "+total+" total"); 
               

            }
            else if(e.getSource()==b1)
            {
                new secondPage();
            }
            else if(e.getSource()==b2)
            {
                this.dispose();
            }
        
    }
    
}
class officeWork extends JFrame implements ActionListener
{
    JButton b,b1,b2;
    int ch=0;
    int cost=0;
    int total;
    officeWork()
    {
        
        JLabel l=new JLabel("RAM");
        ImageIcon i=new ImageIcon("daily2.png");
        JLabel l2=new JLabel(i);
        l2.setBounds(0,0,800,600);
        JLayeredPane p=getLayeredPane();
        l.setBounds(50,50,100,30);
        String ram[]={"RAM","4gb","6gb","8gb"};
        JComboBox cb1=new JComboBox<>(ram);
        cb1.setBounds(30,10,80,50);
        

        String rom[]={"ROM","512GB HDD","1TB SSD","1TB HDD + 256GB SSD"};
        JComboBox cb2=new JComboBox<>(rom);
        cb2.setBounds(30,70,150,50);
        
        String proc[]={"Processor","Core i3 9th gen"," Core i5 7th gen"," Core i5 9th gen","Core i5 11th gen"};
        JComboBox cb3=new JComboBox<>(proc);
        cb3.setBounds(30,130,150,50);
        
        String mot[]={"Motherboard","ASRock A320M-HDV R4.0","Asrock A520M-HDV","ASRock B450 Pro4 R2.0","ASRock B450M Pro4"};
        JComboBox cb4=new JComboBox<>(mot);
        cb4.setBounds(30,190,150,50);
       
        String acc[]={"Accessories Brand","Samsung","Acer","Redgaer"};
        JComboBox cb5=new JComboBox<>(acc);
        cb5.setBounds(30,250,150,50);

        String cool[]={"Cooling System","550W Bronze","450W Gold","550W Gold"};
        JComboBox cb6=new JComboBox<>(cool);
        cb6.setBounds(30,310,150,50);

        
        JLabel l1=new JLabel("No Need of Graphic card for Office work!");
        l1.setBounds(250,70,450,50);
        l1.setFont(new Font("Serif", Font.BOLD, 24));
        l1.setForeground(Color.WHITE);
        
        cb1.setBounds(30,10,80,50);
        
        b= new JButton("Price");
        b.setBounds(430,350,150,30);

        b1= new JButton("Continue");
        b1.setBounds(430,400,150,30);

        b2= new JButton("Exit");
        b2.setBounds(430,450,150,30);
        String extra[]={"OTHER","RAM","ROM","Cabinet","Motherboard","Extra Components"};
        JComboBox cb8=new JComboBox<>(extra);
        cb8.setBounds(30,370,150,50);


        JTextField tff=new JTextField();
        tff.setBounds(30,430,150,50);
        tff.setVisible(false);

        cb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb1.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=2500;
                            break;
                    case 2: cost+=4000;
                    break;
                    case 3:cost+=9000;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb2.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=3500;
                    break;
                    case 2: cost+=5000;
                    break;
                    case 3:cost+=7000;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb3.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb3.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=15000;
                    break;
                    case 2: cost+=18000;
                    break;
                    case 3:cost+=21000;
                    break;
                    case 4:cost+=22500;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb4.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                
                ch=cb4.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=4000;
                    break;
                    case 2: cost+=6000;
                    break;
                    case 3:cost+=7475;
                    break;
                    case 4:cost+=6705;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb5.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=11500;
                    break;
                    case 2: cost+=12500;
                    break;
                    case 3:cost+=13500;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb6.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=3300;
                    break;
                    case 2: cost+=3800;
                    break;
                    case 3:cost+=4750;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb8.getSelectedIndex();
                
                switch(ch)
                {
                    case 1: cost+=4000;
                    tff.setVisible(true);
                    break;
                    case 2: cost+=5000;
                    tff.setVisible(true);
                    break;
                    case 3:cost+=5000;
                    tff.setVisible(true);
                    break;
                    case 4:cost+=8000;
                    tff.setVisible(true);
                    break;
                    case 5:cost+=4000;
                    tff.setVisible(true);
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        
        p.add(l2,new Integer(1));
        p.add(b,new Integer(2));
        p.add(cb1,new Integer(2));
        p.add(cb2,new Integer(2));
        p.add(cb3,new Integer(2));
        p.add(cb4,new Integer(2));
        p.add(cb5,new Integer(2));
        p.add(cb6,new Integer(2));
        p.add(b1,new Integer(2));
        p.add(b2,new Integer(2));
        p.add(l1,new Integer(2));
        p.add(cb8,new Integer(2));
        p.add(tff,new Integer(2));
       // p.add(l,new Integer(2));
        
       
        b.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setVisible(true);
        setLayout(null);
        setSize(800,600);
        setLocationRelativeTo(null);

    }
    
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource()==b)
            {
                JOptionPane.showMessageDialog(this,"You're pc build cost is "+total+" total"); 
                

            }
            else if(e.getSource()==b1)
            {
                new secondPage();
            }
            else if(e.getSource()==b2)
            {
                this.dispose();
            }
        
    }
    
}
class GamingType extends JFrame implements ActionListener
{
    JButton b,b1,b2;
    int ch=0;
    int cost=0;
    int total;
    GamingType()
    {
        
       
        JLabel l=new JLabel("RAM");
        l.setBounds(50,50,100,30);
        JLayeredPane p=getLayeredPane();
        ImageIcon i= new ImageIcon("gam1.jpg");
        JLabel l1= new JLabel(i);
        l1.setBounds(0,0,800,600);
        String ram[]={"RAM","1.8gb","2.16gb","3.32gb"};
        JComboBox cb1=new JComboBox<>(ram);
        cb1.setBounds(30,10,80,50);
        
        

        String rom[]={"ROM","1TB SSD","1TB HDD + 256GB SSD","2TB SSD"};
        JComboBox cb2=new JComboBox<>(rom);
        cb2.setBounds(30,70,150,50);
        
        String proc[]={"Processor","Core i5 12th gen"," Core i7 9th gen"," Core i9 7th gen","Core i9 11th gen"};
        JComboBox cb3=new JComboBox<>(proc);
        cb3.setBounds(30,130,150,50);
        
        String mot[]={"Motherboard","ASRock A320M-HDV R4.0","Asrock A520M-HDV","ASRock B450 Pro4 R2.0","ASRock B450M Pro4"};
        JComboBox cb4=new JComboBox<>(mot);
        cb4.setBounds(30,190,150,50);
       
        String acc[]={"Accessories Brand","Samsung","Acer","Redgaer"};
        JComboBox cb5=new JComboBox<>(acc);
        cb5.setBounds(30,250,150,50);

        String cool[]={"Cooling System","550W Bronze","450W Gold","550W Gold"};
        JComboBox cb6=new JComboBox<>(cool);
        cb6.setBounds(30,310,150,50);

        String grap[]={"Graphc Card","Gigabyte rtx 1650 4gb vram","Nvidia rtx 3050 4gb vram","Nvidia rtx 3080 8gb vram","Nvidia rtx 3080ti 12gb vram"};
        JComboBox cb7=new JComboBox<>(grap);
        cb7.setBounds(30,370,150,50);
        
        b= new JButton("Price");
        b.setBounds(430,350,150,30);

        b1= new JButton("Continue");
        b1.setBounds(430,400,150,30);

        b2= new JButton("Exit");
        b2.setBounds(430,450,150,30);

        String extra[]={"OTHER","RAM","ROM","Cabinet","Motherboard","Extra Components"};
        JComboBox cb8=new JComboBox<>(extra);
        cb8.setBounds(30,430,150,50);


	  JTextField tff=new JTextField();
	  tff.setBounds(30,490,150,50);
        tff.setVisible(false);

        cb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb1.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=4500;
                            break;
                    case 2: cost+=9500;
                    break;
                    case 3:cost+=17500;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb2.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=8000;
                    break;
                    case 2: cost+=12000;
                    break;
                    case 3:cost+=23000;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb3.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                ch=cb3.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=30000;
                    break;
                    case 2: cost+=33000;
                    break;
                    case 3:cost+=38000;
                    break;
                    case 4:cost+=43500;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb4.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                
                ch=cb4.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=4000;
                    break;
                    case 2: cost+=6000;
                    break;
                    case 3:cost+=7475;
                    break;
                    case 4:cost+=6705;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb5.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=15500;
                    break;
                    case 2: cost+=2100;
                    break;
                    case 3:cost+=28000;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb6.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=4750;
                    break;
                    case 2: cost+=6400;
                    break;
                    case 3:cost+=7600;
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb7.getSelectedIndex();
                switch(ch)
                {
                    case 1: cost+=16800;
                    break;
                    case 2: cost+=22500;
                    break;
                    case 3:cost+=48000;
                    break;
                    case 4: cost+=78000;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        cb8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ch=cb8.getSelectedIndex();
                
                switch(ch)
                {
                    case 1: cost+=4000;
                    tff.setVisible(true);
                    break;
                    case 2: cost+=5000;
                    tff.setVisible(true);
                    break;
                    case 3:cost+=5000;
                    tff.setVisible(true);
                    break;
                    case 4:cost+=8000;
                    tff.setVisible(true);
                    break;
                    case 5:cost+=4000;
                    tff.setVisible(true);
                    break;
                    default : cost+=0;
                    break;
                }
                ch=0;
                total+=cost;
                cost=0;
            }
        });
        
        p.add(l1,new Integer(1));
        p.add(b,new Integer(2));
        p.add(cb1,new Integer(2));
        p.add(cb2,new Integer(2));
        p.add(cb3,new Integer(2));
        p.add(cb4,new Integer(2));
        p.add(cb5,new Integer(2));
        p.add(cb6,new Integer(2));
        p.add(cb7,new Integer(2));
        p.add(b1,new Integer(2));
        p.add(b2,new Integer(2));
        p.add(cb8,new Integer(2));
        p.add(tff,new Integer(2));
        
        //p.add(l,new Integer(2));
        
       
        b.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setVisible(true);
        setLayout(null);
        setSize(800,600);
        setLocationRelativeTo(null);
    }
    
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource()==b)
            {
                JOptionPane.showMessageDialog(this,"You're pc build cost is "+total+" total"); 
               

            }
            else if(e.getSource()==b1)
            {
                new secondPage();
            }
            else if(e.getSource()==b2)
            {
                this.dispose();
            }
        
    }
    
}

class User {
    public String username;
    public String password;
   
    
}


