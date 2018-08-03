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

public class MyAdmin extends JFrame
{

JMenuBar mb;
JMenu mFile;
JMenuItem mAbout,mSignOut,mExit;

JPanel UsersPanel=new JPanel();
JPanel FoodTypePanel=new JPanel();
JPanel FoodItemPanel=new JPanel();
JPanel DaysPanel=new JPanel();

JComboBox UsersG = new JComboBox();
JLabel LabelUsersG = new JLabel("Choose User Group");

JComboBox Users = new JComboBox();
JLabel LabelUsers = new JLabel("Choose User Name");

JLabel labelUserName1 = new JLabel("User Name");
JTextField textUserName1 =new JTextField(15);

JLabel labelPassword1 = new JLabel("Password");
JTextField textPassword1=new JTextField(15);

JLabel labelUserFirst = new JLabel("First Name");
JTextField textUserFirst=new JTextField(15);

JLabel labelUserLast = new JLabel("Last Name");
JTextField textUserLast=new JTextField(15);

JButton buttonUserAddNew = new JButton("New");
JButton buttonUserDelete = new JButton("Delete");
JButton buttonUserUpdate = new JButton("Update");


JComboBox FoodType = new JComboBox();
JLabel LabelFoodType = new JLabel("Choose Food Type");

JLabel labelTypeName = new JLabel("Type Name");
JTextField textTypeName=new JTextField(15);

JButton buttonTypeUpdate = new JButton("Update");


JComboBox FoodType1 = new JComboBox();
JComboBox FoodName = new JComboBox();

JLabel LabelFoodType1 = new JLabel("Choose Food Type");
JLabel LabelFoodItem = new JLabel("Choose Food Item");

JLabel labelFoodName = new JLabel("Food Name");
JTextField textFoodName=new JTextField(15);

JLabel labelServing = new JLabel("Serving");
JTextField textServing=new JTextField(15);

JLabel labelCalories = new JLabel("Calories");
JTextField textCalories=new JTextField(4);

JLabel labelGlucose = new JLabel("Glucose");
JTextField textGlucose=new JTextField(4);

JLabel labelCarbs = new JLabel("Carbs");
JTextField textCarbs=new JTextField(4);

JButton buttonItemAddNew = new JButton("New");
JButton buttonItemDelete = new JButton("Delete");
JButton buttonItemUpdate = new JButton("Update");


JComboBox FoodDay = new JComboBox();
JLabel LabelFoodDay = new JLabel("Choose Day");

JLabel labelDay = new JLabel("Day");
JTextField textDay=new JTextField(5);

JButton buttonDayAddNew = new JButton("New");
JButton buttonDayDelete = new JButton("Delete");
JButton buttonDayUpdate = new JButton("Update");

GridBagLayout gbl;
GridBagConstraints gbc;

Container c;


MyAdmin()
{

mb = new JMenuBar();
setJMenuBar(mb);
mFile = new JMenu("File");
mb.add(mFile);

mAbout = new JMenuItem ("About");
mSignOut = new JMenuItem ("Sign Out");
mExit = new JMenuItem ("Exit");
mFile.add(mAbout);
mFile.add(mSignOut);
mFile.addSeparator();
mFile.add(mExit);

c = getContentPane();

JTabbedPane tabbedPane=new JTabbedPane();

gbl=new GridBagLayout();
gbc=new GridBagConstraints();

createUsersPanel();
createFoodTypePanel();
createFoodItemPanel();
createDaysPanel();

c.add(tabbedPane);


tabbedPane.addTab("Update Users",null,UsersPanel,"Update users for admin");
tabbedPane.addTab("Update Food Types",null,FoodTypePanel,"Update FoodType for admin");
tabbedPane.addTab("Update Food Items",null,FoodItemPanel,"Update FoodItem for admins");
tabbedPane.addTab("Update Days",null,DaysPanel,"Update Days for admin");

ButtonListener blisten = new ButtonListener();


mAbout.addActionListener(blisten);
mSignOut.addActionListener(blisten);
mExit.addActionListener(blisten);

UsersG.addActionListener(blisten);
Users.addActionListener(blisten);
buttonUserAddNew.addActionListener(blisten);
buttonUserDelete.addActionListener(blisten);
buttonUserUpdate.addActionListener(blisten);

FoodType.addActionListener(blisten);
buttonTypeUpdate.addActionListener(blisten);

FoodType1.addActionListener(blisten);
FoodName.addActionListener(blisten);
buttonItemAddNew.addActionListener(blisten);
buttonItemDelete.addActionListener(blisten);
buttonItemUpdate.addActionListener(blisten);

FoodDay.addActionListener(blisten);
buttonDayAddNew.addActionListener(blisten);
buttonDayDelete.addActionListener(blisten);
buttonDayUpdate.addActionListener(blisten);




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
setSize(650,380);
setVisible(true);

}


public void createUsersPanel(){

UsersPanel.setLayout(gbl);

{
String typeURL ="Jdbc:odbc:FoodExchangeLists";
try

{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
ResultSet rcord=null;
Connection ConType=DriverManager.getConnection(typeURL,"","");
Statement stmt = ConType.createStatement(rcord.TYPE_SCROLL_INSENSITIVE,rcord.CONCUR_UPDATABLE);

rcord=stmt.executeQuery("Select * from UsersGroup");

while (rcord.next()) {
UsersG.addItem(rcord.getString("groupName"));
}



stmt.close();
ConType.close();

} catch(Exception e){
System.out.println("Connection Not Successfull"+e.toString());
}
}

gbc.insets = new Insets(5,5,0,0);

UsersPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(LabelUsersG, gbc);
UsersPanel.add(LabelUsersG);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(UsersG,gbc);
UsersPanel.add(UsersG);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=2;
gbl.setConstraints(LabelUsers, gbc);
UsersPanel.add(LabelUsers);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=2;
gbl.setConstraints(Users,gbc);
UsersPanel.add(Users);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=3;
gbl.setConstraints(labelUserName1, gbc);
UsersPanel.add(labelUserName1);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=3;
gbl.setConstraints(textUserName1,gbc);
UsersPanel.add(textUserName1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=4;
gbl.setConstraints(labelPassword1, gbc);
UsersPanel.add(labelPassword1);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=4;
gbl.setConstraints(textPassword1,gbc);
UsersPanel.add(textPassword1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=5;
gbl.setConstraints(labelUserFirst, gbc);
UsersPanel.add(labelUserFirst);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=5;
gbl.setConstraints(textUserFirst,gbc);
UsersPanel.add(textUserFirst);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=6;
gbl.setConstraints(labelUserLast, gbc);
UsersPanel.add(labelUserLast);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=6;
gbl.setConstraints(textUserLast,gbc);
UsersPanel.add(textUserLast);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=3;
gbc.gridy=7;
gbl.setConstraints(buttonUserAddNew, gbc);
UsersPanel.add(buttonUserAddNew);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=4;
gbc.gridy=7;
gbl.setConstraints(buttonUserDelete,gbc);
UsersPanel.add(buttonUserDelete);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=5;
gbc.gridy=7;
gbl.setConstraints(buttonUserUpdate,gbc);
UsersPanel.add(buttonUserUpdate);




}


public void createFoodItemPanel(){

FoodItemPanel.setLayout(gbl);

{
String typeURL ="Jdbc:odbc:FoodExchangeLists";
try

{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
ResultSet rcordType=null;
Connection ConType=DriverManager.getConnection(typeURL,"","");
Statement stmt = ConType.createStatement(rcordType.TYPE_SCROLL_INSENSITIVE,rcordType.CONCUR_UPDATABLE);

rcordType=stmt.executeQuery("Select * from FoodType");

while (rcordType.next()) {
FoodType1.addItem(rcordType.getString("TypeName"));
}



stmt.close();
ConType.close();

} catch(Exception e){
System.out.println("Connection Not Successfull"+e.toString());
}
}

gbc.insets = new Insets(5,5,0,0);

FoodItemPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(LabelFoodType1, gbc);
FoodItemPanel.add(LabelFoodType1);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(FoodType1,gbc);
FoodItemPanel.add(FoodType1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=2;
gbl.setConstraints(LabelFoodItem, gbc);
FoodItemPanel.add(LabelFoodItem);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=2;
gbl.setConstraints(FoodName,gbc);
FoodItemPanel.add(FoodName);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=3;
gbl.setConstraints(labelFoodName, gbc);
FoodItemPanel.add(labelFoodName);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=3;
gbl.setConstraints(textFoodName,gbc);
FoodItemPanel.add(textFoodName);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=4;
gbl.setConstraints(labelServing, gbc);
FoodItemPanel.add(labelServing);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=4;
gbl.setConstraints(textServing,gbc);
FoodItemPanel.add(textServing);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=5;
gbl.setConstraints(labelCalories, gbc);
FoodItemPanel.add(labelCalories);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=5;
gbl.setConstraints(textCalories,gbc);
FoodItemPanel.add(textCalories);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=6;
gbl.setConstraints(labelGlucose, gbc);
FoodItemPanel.add(labelGlucose);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=6;
gbl.setConstraints(textGlucose,gbc);
FoodItemPanel.add(textGlucose);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=7;
gbl.setConstraints(labelCarbs, gbc);
FoodItemPanel.add(labelCarbs);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=7;
gbl.setConstraints(textCarbs,gbc);
FoodItemPanel.add(textCarbs);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=3;
gbc.gridy=8;
gbl.setConstraints(buttonItemAddNew, gbc);
FoodItemPanel.add(buttonItemAddNew);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=4;
gbc.gridy=8;
gbl.setConstraints(buttonItemDelete,gbc);
FoodItemPanel.add(buttonItemDelete);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=5;
gbc.gridy=8;
gbl.setConstraints(buttonItemUpdate,gbc);
FoodItemPanel.add(buttonItemUpdate);

}

public void createFoodTypePanel(){

FoodTypePanel.setLayout(gbl);

{
String typeURL ="Jdbc:odbc:FoodExchangeLists";
try

{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
ResultSet rcordType=null;
Connection ConType=DriverManager.getConnection(typeURL,"","");
Statement stmtType = ConType.createStatement(rcordType.TYPE_SCROLL_INSENSITIVE,rcordType.CONCUR_UPDATABLE);

rcordType=stmtType.executeQuery("Select * from FoodType");

while (rcordType.next()) {
FoodType.addItem(rcordType.getString("TypeName"));
}


stmtType.close();
ConType.close();

} catch(Exception e){
System.out.println("Connection Not Successfull"+e.toString());
}
}

gbc.insets = new Insets(5,5,0,0);

FoodTypePanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(LabelFoodType, gbc);
FoodTypePanel.add(LabelFoodType);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(FoodType,gbc);
FoodTypePanel.add(FoodType);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=3;
gbl.setConstraints(labelTypeName, gbc);
FoodTypePanel.add(labelTypeName);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=3;
gbl.setConstraints(textTypeName,gbc);
FoodTypePanel.add(textTypeName);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=3;
gbc.gridy=4;
gbl.setConstraints(buttonTypeUpdate,gbc);
FoodTypePanel.add(buttonTypeUpdate);

}

public void createDaysPanel(){

DaysPanel.setLayout(gbl);

{
String typeURL ="Jdbc:odbc:FoodExchangeLists";
try

{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
ResultSet rcordDay=null;
Connection ConDay=DriverManager.getConnection(typeURL,"","");
Statement stmtDay = ConDay.createStatement(rcordDay.TYPE_SCROLL_INSENSITIVE,rcordDay.CONCUR_UPDATABLE);

rcordDay=stmtDay.executeQuery("Select * from Days");

while (rcordDay.next()) {
FoodDay.addItem(rcordDay.getString("dayName"));
}



stmtDay.close();
ConDay.close();

} catch(Exception e){
System.out.println("Connection Not Successfull"+e.toString());
}
}

gbc.insets = new Insets(5,5,0,0);

DaysPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(LabelFoodDay, gbc);
DaysPanel.add(LabelFoodDay);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(FoodDay,gbc);
DaysPanel.add(FoodDay);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=3;
gbl.setConstraints(labelDay, gbc);
DaysPanel.add(labelDay);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=3;
gbl.setConstraints(textDay,gbc);
DaysPanel.add(textDay);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=3;
gbc.gridy=4;
gbl.setConstraints(buttonDayAddNew, gbc);
DaysPanel.add(buttonDayAddNew);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=4;
gbc.gridy=4;
gbl.setConstraints(buttonDayDelete,gbc);
DaysPanel.add(buttonDayDelete);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=5;
gbc.gridy=4;
gbl.setConstraints(buttonDayUpdate,gbc);
DaysPanel.add(buttonDayUpdate);

}


class ButtonListener implements ActionListener
{
public void actionPerformed(ActionEvent e1)
{
Object obj = e1.getSource();

if (obj == mAbout){
JOptionPane.showMessageDialog(null,
("Diabetes Daily Hajj Care Program\n")+
("Smarter Diabetes Management for Digital Hajj Care\n")+
("Presently, diabetes is a becoming a common health problem worldwide.\n")+
("By taking steps to prevent this, it can save countless lives, help economic and promote well-being.\n")+
("Diabetes Daily Hajj Care Program is used to measure the sugar level in food items by following simple steps.\n")+
("Diabetes Daily Hajj Care Program is used to measure the sugar level in food items by following simple steps.\n")+
("It is furthermore a safe, easy and cost effective way.\n")+
("Invented and Designed by Saeed Zuhier Hasanain\n"));
}

if (obj == mSignOut){
new MyUser();
dispose();
}

if (obj == mExit){
System.exit(0);
}




// START Users ADMIN --------------------------------------------------------------------

if (obj== UsersG) {
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
String StringUserGroup=null;
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String UG=UsersG.getSelectedItem().toString();

ResultSet rcordIDUser=null;
Connection ConUser=DriverManager.getConnection(URL,"","");
Statement stmtUser = ConUser.createStatement(rcordIDUser.TYPE_SCROLL_INSENSITIVE,rcordIDUser.CONCUR_UPDATABLE);
rcordIDUser=stmtUser.executeQuery("Select ID from UsersGroup where groupName= '" + UG + "'");
rcordIDUser.absolute(1);
StringUserGroup = Integer.toString(rcordIDUser.getInt("ID"));

ResultSet rcord=null;
Connection Con1=DriverManager.getConnection(URL,"","");
Statement stmtfooditem = Con1.createStatement(rcord.TYPE_SCROLL_INSENSITIVE,rcord.CONCUR_UPDATABLE);
rcord = stmtfooditem.executeQuery("Select * from Users where userGroupID =" + StringUserGroup );

Users.removeAllItems();
while (rcord.next())
{

Users.addItem(rcord.getString("userName"));
}

stmtUser.close();
ConUser.close();

}
catch (Exception e)
{
System.out.println("Failed "+e.toString());
}


}

if (obj==Users)
{
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

ResultSet rcordUser=null;
Connection ConUser=DriverManager.getConnection(URL,"","");
Statement stmtUser = ConUser.createStatement(rcordUser.TYPE_SCROLL_INSENSITIVE,rcordUser.CONCUR_UPDATABLE);

String iiString = Users.getSelectedItem().toString();


rcordUser = stmtUser.executeQuery("Select * from Users where userName = '" + iiString + "'" );
rcordUser.absolute(1);

textUserName1.setText(rcordUser.getString("userName"));
textPassword1.setText(rcordUser.getString("userPass"));
textUserFirst.setText(rcordUser.getString("userFirst"));
textUserLast.setText(rcordUser.getString("userLast"));

stmtUser.close();
ConUser.close();

}
catch(Exception e)
{
System.out.println("Failed "+e.toString());
}

}
if (obj== buttonUserAddNew) {

String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
String UN = textUserName1.getText();
String UP = textPassword1.getText();
String UF = textUserFirst.getText();
String UL = textUserLast.getText();
String Uid = null;

String UG = UsersG.getSelectedItem().toString();

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection ConAdd=DriverManager.getConnection(URL,"","");

ResultSet recordAdd = null;
Statement stmtAdd = ConAdd.createStatement(recordAdd.TYPE_SCROLL_INSENSITIVE,recordAdd.CONCUR_UPDATABLE);

recordAdd = stmtAdd.executeQuery("Select ID from UsersGroup where groupName = '" + UG + "'" );
recordAdd.absolute(1);

Uid = recordAdd.getString("ID");


recordAdd = stmtAdd.executeQuery("Select * from Users where userName = '" + UN + "'" );
recordAdd.absolute(1);
if (recordAdd.absolute(1))
{
JOptionPane.showMessageDialog(null, "User Name ( " + UN + " ) exist in the database" );
}
else
{

String sQ = "Insert into Users" +
"(userName, userGroupID, userPass, userFirst, userLast) " +
"values('" + UN + "'," + Uid + ",'" + UP + "','" + UF + "','" + UL + "')";

PreparedStatement st = ConAdd.prepareStatement(sQ);

st.executeUpdate();


recordAdd = stmtAdd.executeQuery("Select * from Users where userName = '" + UN + "'");
recordAdd.absolute(1);

int newuserid = recordAdd.getInt("ID");

PreparedStatement stattarget = ConAdd.prepareStatement("Insert into totalNOMeals(totalCal,totalMilk,totalBread,totalMeat,totalVeg,totalFruit,totalFat,uID) values(0,0,0,0,0,0,0," + newuserid + ")");
stattarget.executeUpdate();

JOptionPane.showMessageDialog(null, "User (" + UN + ") has been created successfuly" );

recordAdd = stmtAdd.executeQuery("Select * from Users where userGroupID =" + Uid );
Users.removeAllItems();

while (recordAdd.next())
{

Users.addItem(recordAdd.getString("userName"));
}



}
stmtAdd.close();
ConAdd.close();
}

catch(Exception e)
{
System.out.println("Connection Not Successfull"+e.toString());

}

}

if (obj== buttonUserDelete) {
String URL ="Jdbc:odbc:FoodExchangeLists";

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String UN =Users.getSelectedItem().toString();

ResultSet rcordDelete=null;
Connection ConDelete=DriverManager.getConnection(URL,"","");
Statement stmtDelete = ConDelete.createStatement(rcordDelete.TYPE_SCROLL_INSENSITIVE,rcordDelete.CONCUR_UPDATABLE);

stmtDelete.executeUpdate("DELETE from Users where userName = '" + UN + "'" );

JOptionPane.showMessageDialog(null, "Delete succsesful");

String Uid = null;
String UG = UsersG.getSelectedItem().toString();


rcordDelete = stmtDelete.executeQuery("Select ID from UsersGroup where GroupName = '" + UG + "'" );
rcordDelete.absolute(1);

Uid = rcordDelete.getString("ID");

rcordDelete = stmtDelete.executeQuery("Select * from Users where userGroupID =" + Uid );
Users.removeAllItems();

while (rcordDelete.next())
{

Users.addItem(rcordDelete.getString("userName"));
}

stmtDelete.close();
ConDelete.close();

}
catch(Exception e)
{
JOptionPane.showMessageDialog(null, "You can't delete this user due to it has some related data with it!!");
System.out.println("Failed "+e.toString());
}

}

if (obj== buttonUserUpdate) {
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String UN =Users.getSelectedItem().toString();

ResultSet rcordUpdate=null;
Connection ConUpdate=DriverManager.getConnection(URL,"","");
Statement stmtUpdate = ConUpdate.createStatement(rcordUpdate.TYPE_SCROLL_INSENSITIVE,rcordUpdate.CONCUR_UPDATABLE);


rcordUpdate = stmtUpdate.executeQuery("Select * from Users where userName = '" + textUserName1.getText() + "'");

if (rcordUpdate.absolute(1))
{
JOptionPane.showMessageDialog(null, "Food Item ( " + textFoodName.getText() + " ) exist in the database" );

}

else
{
String sQ = "UPDATE Users " +
"SET userName='" + textUserName1.getText() + "', " +
"userPass='" + textPassword1.getText() + "' , " +
"userFirst='" + textUserFirst.getText() + "', " +
"userLast='" + textUserLast.getText() + "'" +
" where userName='" + UN + "'";

stmtUpdate.executeUpdate(sQ);
JOptionPane.showMessageDialog(null, "Update succsesful");

}

String UG = UsersG.getSelectedItem().toString();

rcordUpdate = stmtUpdate.executeQuery("Select ID from UsersGroup where groupName = '" + UG + "'" );
rcordUpdate.absolute(1);

String Uid = null;
Uid = rcordUpdate.getString("ID");

rcordUpdate = stmtUpdate.executeQuery("Select * from Users where userGroupID =" + Uid );
Users.removeAllItems();

while (rcordUpdate.next())
{

Users.addItem(rcordUpdate.getString("userName"));
}


stmtUpdate.close();
ConUpdate.close();

}

catch(Exception e)
{

System.out.println("Failed "+e.toString());
}


}



// End Users ADMIN --------------------------------------------------------------------

// START Food Type ADMIN --------------------------------------------------------------------


if (obj== FoodType)
{
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String test=FoodType.getSelectedItem().toString();

ResultSet rcordIDType=null;
Connection ConTypeID=DriverManager.getConnection(URL,"","");
Statement stmtType = ConTypeID.createStatement(rcordIDType.TYPE_SCROLL_INSENSITIVE,rcordIDType.CONCUR_UPDATABLE);
rcordIDType=stmtType.executeQuery("Select ID from FoodType where TypeName= '" + test + "'");
rcordIDType.absolute(1);

rcordIDType = stmtType.executeQuery("Select * from FoodType where TypeName = '" + test + "'" );
rcordIDType.absolute(1);

textTypeName.setText(rcordIDType.getString("TypeName"));


stmtType.close();
ConTypeID.close();

}
catch (Exception e)
{
System.out.println("Failed "+e.toString());
}

}


if (obj== buttonTypeUpdate){
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String TN =FoodType.getSelectedItem().toString();

ResultSet rcordUpdate=null;
Connection ConUpdate=DriverManager.getConnection(URL,"","");
Statement stmtUpdate = ConUpdate.createStatement(rcordUpdate.TYPE_SCROLL_INSENSITIVE,rcordUpdate.CONCUR_UPDATABLE);


rcordUpdate = stmtUpdate.executeQuery("Select * from FoodType where TypeName = '" + textTypeName.getText() + "'");

if (rcordUpdate.absolute(1))
{
JOptionPane.showMessageDialog(null, "Type Name ( " + textTypeName.getText() + " ) exist in the database" );

}

else
{
String sQ = "UPDATE FoodType " +
"SET TypeName='" + textTypeName.getText() + "'"
+" where TypeName='" + TN + "'";

stmtUpdate.executeUpdate(sQ);

JOptionPane.showMessageDialog(null, "update succsesful");


FoodType.removeAllItems();

rcordUpdate = stmtUpdate.executeQuery("Select * from FoodType");


while (rcordUpdate.next())
{

FoodType.addItem(rcordUpdate.getString("TypeName"));
}

}

stmtUpdate.close();
ConUpdate.close();

}
catch(Exception e)
{

System.out.println("Failed "+e.toString());
}


}


// END Food Type ADMIN --------------------------------------------------------------------


// START FOOD ITEM ADMIN --------------------------------------------------------------------
if (obj== FoodType1)
{
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
String StringType=null;
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String Type=FoodType1.getSelectedItem().toString();

ResultSet rcordIDType=null;
Connection ConTypeID=DriverManager.getConnection(URL,"","");
Statement stmtType = ConTypeID.createStatement(rcordIDType.TYPE_SCROLL_INSENSITIVE,rcordIDType.CONCUR_UPDATABLE);
rcordIDType=stmtType.executeQuery("Select ID from FoodType where TypeName= '" + Type + "'");
rcordIDType.absolute(1);
StringType = Integer.toString(rcordIDType.getInt("ID"));

rcordIDType = stmtType.executeQuery("Select * from FoodItem where foodTypeID =" + StringType );

FoodName.removeAllItems();
while (rcordIDType.next())
{

FoodName.addItem(rcordIDType.getString("foodName"));
}

stmtType.close();
ConTypeID.close();
}
catch (Exception e)
{
System.out.println("Failed "+e.toString());
}

}

if(obj==FoodName)
{

String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

ResultSet rcordItem=null;
Connection ConItem=DriverManager.getConnection(URL,"","");
Statement stmtItem = ConItem.createStatement(rcordItem.TYPE_SCROLL_INSENSITIVE,rcordItem.CONCUR_UPDATABLE);

String StringItem = FoodName.getSelectedItem().toString();


rcordItem = stmtItem.executeQuery("Select * from FoodItem where foodName = '" + StringItem + "'" );
rcordItem.absolute(1);

textFoodName.setText(rcordItem.getString("foodName"));
textServing.setText(rcordItem.getString("foodServing"));
textGlucose.setText(rcordItem.getString("foodGlucose"));
textCarbs.setText(rcordItem.getString("foodCarbs"));
textCalories.setText(rcordItem.getString("foodCalories"));

stmtItem.close();
ConItem.close();

}
catch(Exception e)
{
System.out.println("Failed "+e.toString());
}


}


if (obj== buttonItemAddNew)
{

String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
String FN = textFoodName.getText();
String FS = textServing.getText();
String FG = textGlucose.getText();
String FC = textCarbs.getText();
String FCal = textCalories.getText();
String FTid = null;

String FT = FoodType1.getSelectedItem().toString();

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection ConAdd=DriverManager.getConnection(URL,"","");

ResultSet rcordAdd = null;
Statement stmtAdd = ConAdd.createStatement(rcordAdd.TYPE_SCROLL_INSENSITIVE,rcordAdd.CONCUR_UPDATABLE);

rcordAdd = stmtAdd.executeQuery("Select ID from FoodType where TypeName = '" + FT + "'" );
rcordAdd.absolute(1);

FTid = rcordAdd.getString("ID");

rcordAdd = stmtAdd.executeQuery("Select * from FoodItem where foodName = '" + FN + "'" );
rcordAdd.absolute(1);
if (rcordAdd.absolute(1))
{
JOptionPane.showMessageDialog(null, "Food Item ( " + FN + " ) exist in the database" );
}
else
{

String sQ = "Insert into FoodItem" +
"(foodName, foodTypeID, foodServing, foodGlucose, foodCarbs, foodCalories) " +
"values('" + FN + "'," + FTid + ",'" + FS + "'," + FG + "," + FC + "," + FCal + ")";



PreparedStatement st = ConAdd.prepareStatement(sQ);

st.executeUpdate();
JOptionPane.showMessageDialog(null, "Add succsesful");

FoodName.removeAllItems();

rcordAdd = stmtAdd.executeQuery("Select * from FoodItem where foodTypeID =" + FTid );


while (rcordAdd.next())
{

FoodName.addItem(rcordAdd.getString("foodName"));
}

}

stmtAdd.close();
ConAdd.close();

}

catch(Exception e)
{
System.out.println("Connection Not Successfull"+e.toString());

}


}

if (obj== buttonItemDelete)
{

String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String FN =FoodName.getSelectedItem().toString();
String FTid = null;

ResultSet rcordDelete=null;
Connection ConDelete=DriverManager.getConnection(URL,"","");
Statement stmtDelete = ConDelete.createStatement(rcordDelete.TYPE_SCROLL_INSENSITIVE,rcordDelete.CONCUR_UPDATABLE);

stmtDelete.executeUpdate("DELETE from FoodItem where foodName = '" + FN + "'" );

JOptionPane.showMessageDialog(null, "Delete succsesful");



String FT = FoodType1.getSelectedItem().toString();

rcordDelete = stmtDelete.executeQuery("Select ID from FoodType where TypeName = '" + FT + "'" );
rcordDelete.absolute(1);

FTid = rcordDelete.getString("ID");

rcordDelete = stmtDelete.executeQuery("Select * from FoodItem where foodTypeID =" + FTid );
FoodName.removeAllItems();

while (rcordDelete.next())
{

FoodName.addItem(rcordDelete.getString("foodName"));
}

stmtDelete.close();
ConDelete.close();

}

catch(Exception e)
{
JOptionPane.showMessageDialog(null, "You can't delete it because some user use it in his Database");
System.out.println("Failed "+e.toString());
}


}

if (obj== buttonItemUpdate)
{
String FTid = null;
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String FN =FoodName.getSelectedItem().toString();

ResultSet rcordUpdate=null;
Connection ConUpdate=DriverManager.getConnection(URL,"","");
Statement stmtUpdate = ConUpdate.createStatement(rcordUpdate.TYPE_SCROLL_INSENSITIVE,rcordUpdate.CONCUR_UPDATABLE);


rcordUpdate = stmtUpdate.executeQuery("Select * from FoodItem where foodName = '" + textFoodName.getText() + "'");

if (rcordUpdate.absolute(1))
{
JOptionPane.showMessageDialog(null, "Food Item ( " + textFoodName.getText() + " ) exist in the database" );

}

else
{
String sQ = "UPDATE FoodItem " +
"SET foodName='" + textFoodName.getText() + "', " +
"foodServing='" + textServing.getText() + "' , " +
"foodGlucose=" + textGlucose.getText() + ", " +
"foodCarbs=" + textCarbs.getText() + ", " +
"foodCalories= " + textCalories.getText() +
" where foodName='" + FN + "'";

stmtUpdate.executeUpdate(sQ);

JOptionPane.showMessageDialog(null, "Update succsesful");


}

String FT = FoodType1.getSelectedItem().toString();

rcordUpdate = stmtUpdate.executeQuery("Select ID from FoodType where TypeName = '" + FT + "'" );
rcordUpdate.absolute(1);

FTid = rcordUpdate.getString("ID");

ResultSet rcord1=null;
Connection Con1=DriverManager.getConnection(URL,"","");
Statement stmtfooditem1 = Con1.createStatement(rcord1.TYPE_SCROLL_INSENSITIVE,rcord1.CONCUR_UPDATABLE);
rcord1 = stmtfooditem1.executeQuery("Select * from FoodItem where foodTypeID =" + FTid );
FoodName.removeAllItems();

while (rcord1.next())
{

FoodName.addItem(rcord1.getString("foodName"));
}

stmtUpdate.close();
ConUpdate.close();

}
catch(Exception e)
{

System.out.println("Failed "+e.toString());
}


}

// END FOOD ITEM ADMIN --------------------------------------------------------------------


// START Days ADMIN --------------------------------------------------------------------

if (obj== FoodDay){
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String FDay=FoodDay.getSelectedItem().toString();

ResultSet rcordDay=null;
Connection ConDay=DriverManager.getConnection(URL,"","");
Statement stmtDay = ConDay.createStatement(rcordDay.TYPE_SCROLL_INSENSITIVE,rcordDay.CONCUR_UPDATABLE);
rcordDay=stmtDay.executeQuery("Select ID from Days where dayName= '" + FDay + "'");
rcordDay.absolute(1);

rcordDay = stmtDay.executeQuery("Select * from Days where dayName = '" + FDay + "'" );
rcordDay.absolute(1);

textDay.setText(rcordDay.getString("dayName"));

stmtDay.close();
ConDay.close();
}
catch (Exception e)
{
System.out.println("Failed "+e.toString());
}

}

if (obj== buttonDayAddNew){
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
String DN = textDay.getText();

String DN1 = FoodDay.getSelectedItem().toString();

Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

ResultSet rcordAdd = null;
Connection ConAdd=DriverManager.getConnection(URL,"","");
Statement stmtAdd = ConAdd.createStatement(rcordAdd.TYPE_SCROLL_INSENSITIVE,rcordAdd.CONCUR_UPDATABLE);

rcordAdd = stmtAdd.executeQuery("Select ID from Days where dayName = '" + DN1 + "'" );
rcordAdd.absolute(1);

rcordAdd = stmtAdd.executeQuery("Select * from Days where dayName = '" + DN + "'" );
rcordAdd.absolute(1);
if (rcordAdd.absolute(1))
{
JOptionPane.showMessageDialog(null, "Day Name ( " + DN + " ) exist in the database" );
}
else
{

String sQ = "Insert into Days" +
"(dayName) " +
"values('" + DN + "')";



PreparedStatement st = ConAdd.prepareStatement(sQ);

st.executeUpdate();

JOptionPane.showMessageDialog(null, "Add succsesful");


}

FoodDay.removeAllItems();

rcordAdd = stmtAdd.executeQuery("Select * from Days");


while (rcordAdd.next())
{

FoodDay.addItem(rcordAdd.getString("dayName"));
}

stmtAdd.close();
ConAdd.close();

}

catch(Exception e)
{
System.out.println("Connection Not Successfull"+e.toString());

}


}

if (obj== buttonDayDelete){
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String DN =FoodDay.getSelectedItem().toString();

ResultSet rcordDelete=null;
Connection ConDelete=DriverManager.getConnection(URL,"","");
Statement stmtDelete = ConDelete.createStatement(rcordDelete.TYPE_SCROLL_INSENSITIVE,rcordDelete.CONCUR_UPDATABLE);

stmtDelete.executeUpdate("DELETE from Days where dayName = '" + DN + "'" );

JOptionPane.showMessageDialog(null, "Delete succsesful");

rcordDelete = stmtDelete.executeQuery("Select * from Days");
FoodDay.removeAllItems();

while (rcordDelete.next())
{

FoodDay.addItem(rcordDelete.getString("dayName"));
}

stmtDelete.close();
ConDelete.close();

}

catch(Exception e)
{
JOptionPane.showMessageDialog(null, "You can't delete it because some user use it in his Database");
System.out.println("Failed "+e.toString());
}

}

if (obj== buttonDayUpdate){
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String DN =FoodDay.getSelectedItem().toString();

ResultSet rcordUpdate=null;
Connection ConUpdate=DriverManager.getConnection(URL,"","");
Statement stmtUpdate = ConUpdate.createStatement(rcordUpdate.TYPE_SCROLL_INSENSITIVE,rcordUpdate.CONCUR_UPDATABLE);


rcordUpdate = stmtUpdate.executeQuery("Select * from Days where dayName = '" + textDay.getText() + "'");

if (rcordUpdate.absolute(1))
{
JOptionPane.showMessageDialog(null, "Day Name ( " + textDay.getText() + " ) exist in the database" );

}

else
{
String sQ = "UPDATE Days " +
"SET dayName='" + textDay.getText() + "'"
+" where dayName='" + DN + "'";

stmtUpdate.executeUpdate(sQ);

JOptionPane.showMessageDialog(null, "Update succsesful");

rcordUpdate = stmtUpdate.executeQuery("Select * from Days");
FoodDay.removeAllItems();

while (rcordUpdate.next())
{

FoodDay.addItem(rcordUpdate.getString("dayName"));
}
}
stmtUpdate.close();
ConUpdate.close();

}
catch(Exception e)
{

System.out.println("Failed "+e.toString());
}
}

// END Days ADMIN --------------------------------------------------------------------

}



}

}