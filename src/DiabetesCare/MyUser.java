
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiabetesCare;

/**
*
* @author Saeed
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

import javax.swing.table.*;


public class MyUser extends JFrame {

JMenuBar mb;
JMenu mFile;
JMenuItem mAbout,mSignOut,mExit;

JPanel UserSignInPanel=new JPanel();
JPanel UserSignUpPanel=new JPanel();

JLabel LabelUserName = new JLabel("User Name");
JTextField TextUserName =new JTextField(15);

JLabel LabelPassword = new JLabel("Password");
JPasswordField TextPassword=new JPasswordField(15);

JButton buttonSignIn = new JButton("Sign In");

JLabel LabelNExist = new JLabel("Incorrect username or password");


JLabel LabelUserName1 = new JLabel("User Name");
JTextField TextUserName1 =new JTextField(15);

JLabel LabelPassword1 = new JLabel("Password");
JTextField TextPassword1=new JTextField(15);

JLabel LabelConfPassword = new JLabel("Confirm Password");
JTextField TextConfPassword =new JTextField(15);

JLabel LabelFirstName = new JLabel("First Name");
JTextField TextFirstName=new JTextField(15);

JLabel LabelLastName = new JLabel("Last Name");
JTextField TextLastName=new JTextField(15);

JButton buttonSignUp = new JButton("Sign Up");

JLabel LabelFill = new JLabel("Please fill all fields");
JLabel LabelPass = new JLabel("The Password doesn't matched");


GridBagLayout gbl;
GridBagConstraints gbc;

Container c;

MyUser()
{

mb = new JMenuBar();
setJMenuBar(mb);
mFile = new JMenu("File");
mb.add(mFile);

mAbout = new JMenuItem ("About");
mExit = new JMenuItem ("Exit");
mFile.add(mAbout);
mFile.addSeparator();
mFile.add(mExit);

c = getContentPane();

gbl=new GridBagLayout();
gbc=new GridBagConstraints();

createUserSignInPanel();
createUserSignUpPanel();

JTabbedPane tabbedPane=new JTabbedPane();

c.add(tabbedPane);

tabbedPane.addTab("Sign In ",null,UserSignInPanel,"Sign In");
tabbedPane.addTab("Sign Up ",null,UserSignUpPanel,"Sign Up");

ButtonListener blisten = new ButtonListener();

mAbout.addActionListener(blisten);
mExit.addActionListener(blisten);
buttonSignIn.addActionListener(blisten);
buttonSignUp.addActionListener(blisten);

addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent w)
{
System.exit(1);
}
public void windowActivated(WindowEvent w)
{

}
});

setTitle("Diabetes Daily Hajj Care");
setSize(350,350);
setVisible(true);

LabelFill.setVisible(false);
LabelPass.setVisible(false);
LabelNExist.setVisible(false);





}

public void createUserSignInPanel(){


UserSignInPanel.setLayout(gbl);

gbc.insets = new Insets (5,5,0,0);

UserSignInPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(LabelUserName, gbc);
UserSignInPanel.add(LabelUserName);


gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(TextUserName, gbc);
UserSignInPanel.add(TextUserName);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=2;
gbl.setConstraints(LabelPassword,gbc);
UserSignInPanel.add(LabelPassword);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=2;
gbl.setConstraints(TextPassword,gbc);
UserSignInPanel.add(TextPassword);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=3;
gbl.setConstraints(buttonSignIn, gbc);
UserSignInPanel.add(buttonSignIn);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=4;
gbl.setConstraints(LabelNExist, gbc);
UserSignInPanel.add(LabelNExist);



}

public void createUserSignUpPanel(){

UserSignUpPanel.setLayout(gbl);

gbc.insets = new Insets (5,5,0,0);

UserSignUpPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(LabelUserName1, gbc);
UserSignUpPanel.add(LabelUserName1);


gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(TextUserName1, gbc);
UserSignUpPanel.add(TextUserName1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=2;
gbl.setConstraints(LabelPassword1,gbc);
UserSignUpPanel.add(LabelPassword1);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=2;
gbl.setConstraints(TextPassword1,gbc);
UserSignUpPanel.add(TextPassword1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=3;
gbl.setConstraints(LabelConfPassword,gbc);
UserSignUpPanel.add(LabelConfPassword);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=3;
gbl.setConstraints(TextConfPassword,gbc);
UserSignUpPanel.add(TextConfPassword);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=4;
gbl.setConstraints(LabelFirstName,gbc);
UserSignUpPanel.add(LabelFirstName);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=4;
gbl.setConstraints(TextFirstName,gbc);
UserSignUpPanel.add(TextFirstName);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=5;
gbl.setConstraints(LabelLastName,gbc);
UserSignUpPanel.add(LabelLastName);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=5;
gbl.setConstraints(TextLastName,gbc);
UserSignUpPanel.add(TextLastName);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=6;
gbl.setConstraints(buttonSignUp, gbc);
UserSignUpPanel.add(buttonSignUp);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=7;
gbl.setConstraints(LabelFill,gbc);
UserSignUpPanel.add(LabelFill);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=8;
gbl.setConstraints(LabelPass,gbc);
UserSignUpPanel.add(LabelPass);




}

class ButtonListener implements ActionListener
{
public void actionPerformed(ActionEvent e1)
{

Object obj = e1.getSource();

if (obj == mAbout){
JOptionPane.showMessageDialog(null,
("Diabetes Daily Hajj Care Program\n")+
("Smarter Diabetes Management for Digital Hajj care\n")+
("Presently, diabetes is a becoming a common health problem worldwide.\n")+
("By taking steps to prevent this, it can save countless lives, help economic and promote well-being.\n")+
("The Diabetes Daily Hajj Care Program is used to measure the sugar level in food items by following simple steps.\n")+
("Diabetes Daily Hajj Care Program is used to measure the sugar level in food items by following simple steps.\n")+
("It is furthermore a safe, easy and cost effective way.\n")+
("Invented and Designed by Saeed Zuhier Hasanain\n"));
}

if (obj == mExit){
System.exit(0);
}

if (obj== buttonSignIn)
{
{
String typeURL ="Jdbc:odbc:FoodExchangeLists";
try

{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
ResultSet rcordSignIn=null;
Connection ConSignIn=DriverManager.getConnection(typeURL,"","");
Statement stmtSignIn = ConSignIn.createStatement(rcordSignIn.TYPE_SCROLL_INSENSITIVE,rcordSignIn.CONCUR_UPDATABLE);

rcordSignIn = stmtSignIn.executeQuery("Select * from Users where userName = '" + TextUserName.getText() + "'");

if (rcordSignIn.absolute(1)) {
LabelNExist.setVisible(false);
rcordSignIn.absolute(1);
String Upass = rcordSignIn.getString("userPass");

if (Upass.equals(TextPassword.getText())) {

LabelNExist.setVisible(false);

Global.UID = rcordSignIn.getInt("ID");
Global.UN = rcordSignIn.getString("userName");
Global.UF = rcordSignIn.getString("userFirst");
Global.UL = rcordSignIn.getString("UserLast");
int UGID = rcordSignIn.getInt("userGroupID");



if (UGID==2){
new MyFrame();
dispose();
}

else
{

new MyAdmin();

dispose();
}


}
else
{
LabelNExist.setVisible(true);
}
}
else {
LabelNExist.setVisible(true);
}

stmtSignIn.close();
ConSignIn.close();

}

catch(Exception e)
{
System.out.println("Connection Not Successfull"+e.toString());


}
}


}

if (obj== buttonSignUp)
{

String userN = TextUserName1.getText();
String userP = TextPassword1.getText();
String userP2 = TextConfPassword.getText();
String userF = TextFirstName.getText();
String userL = TextLastName.getText();
int userG = 2;


if (userN.equals("") ||
userP.equals("") ||
userP2.equals("") ||
userF.equals("") ||
userL.equals(""))
{

LabelFill.setVisible(true);

}

else if (!userP.equals(userP2)) {
LabelPass.setVisible(true);
LabelFill.setVisible(false);

}
else
{
LabelPass.setVisible(false);
LabelFill.setVisible(false);


{
String typeURL ="Jdbc:odbc:FoodExchangeLists";
try

{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");


ResultSet rcordSignUp=null;
Connection ConSignUp=DriverManager.getConnection(typeURL,"","");
Statement stmtSignUp = ConSignUp.createStatement(rcordSignUp.TYPE_SCROLL_INSENSITIVE,rcordSignUp.CONCUR_UPDATABLE);

rcordSignUp = stmtSignUp.executeQuery("Select * from Users where userName = '" + userN + "'");

if (rcordSignUp.absolute(1))
{
JOptionPane.showMessageDialog(null, "User ( " + userN + " ) exist in the database" );

}
else
{
PreparedStatement stat = ConSignUp.prepareStatement("Insert into Users(userName,userPass,userFirst,userLast,userGroupID) values('" + userN + "','" + userP + "','" + userF + "','" + userL + "'," + userG + ")");
stat.executeUpdate();

rcordSignUp = stmtSignUp.executeQuery("Select * from Users where userName = '" + userN + "'");
rcordSignUp.absolute(1);

int newuserid = rcordSignUp.getInt("ID");

PreparedStatement stattarget = ConSignUp.prepareStatement("Insert into totalNOMeals(totalCal,totalMilk,totalBread,totalMeat,totalVeg,totalFruit,totalFat,uID) values(0,0,0,0,0,0,0," + newuserid + ")");
stattarget.executeUpdate();

JOptionPane.showMessageDialog(null, "User (" + userN + ") has been created successfuly" );


}

stmtSignUp.close();
ConSignUp.close();

}

catch(ClassNotFoundException | SQLException | HeadlessException e)
{
System.out.println("Connection Not Successfull"+e.toString());


}
}

}

}
}
}

public static void main(String[]args)
{
new MyUser();


}


}