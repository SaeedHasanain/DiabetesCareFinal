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
import java.math.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import javax.swing.table.*;




public class MyFrame extends JFrame {

JMenuBar mb;
JMenu mFile;
JMenuItem mAbout,mSignOut,mExit;

JPanel DayPanel=new JPanel();
JPanel ReviewPanel=new JPanel();
JPanel CheckPanel=new JPanel();
JPanel DailyEatenPanel=new JPanel();

JLabel labelServing=new JLabel("Enter Serving Size(g)");
JTextField textServing=new JTextField(15);

JLabel labelGlucose=new JLabel("Enter Glucose(g)");
JTextField textGlucose=new JTextField(15);

JLabel match = new JLabel("Matched");
JLabel nmatch = new JLabel("Not matched");

JLabel LabelFill = new JLabel("Please fill serving size and glucose content");

JButton buttonCompare = new JButton("Check");
JButton buttonResetCompare = new JButton("Reset Values");


JComboBox FoodType = new JComboBox();
JComboBox FoodItem = new JComboBox();
JComboBox FoodDay = new JComboBox();

JLabel LabelFoodType = new JLabel("Choose Food Type");
JLabel LabelFoodItem = new JLabel("Choose Food Item");
JLabel LabelFoodDay = new JLabel("Choose Current Day");

JLabel labelServing1 = new JLabel("Serving Size(g)");
JTextField textServing1=new JTextField(4);

JLabel labelGlucose1 = new JLabel("Glucose(g)");
JTextField textGlucose1=new JTextField(4);

JLabel labelCarbs1 = new JLabel("Carbs(g)");
JTextField textCarbs1=new JTextField(4);

JLabel labelCalories1 = new JLabel("Calories");
JTextField textCalories1=new JTextField(4);

JButton buttonAdd = new JButton("Add to dailly");
JButton buttonResetAdd = new JButton("Reset values");


JLabel labelCal = new JLabel("Total Calories per day");
JTextField textCal=new JTextField(4);

JLabel labelMilk = new JLabel("Serving No from Milk");
JTextField textMilk=new JTextField(4);

JLabel labelBread = new JLabel("Serving No from Bread");
JTextField textBread=new JTextField(4);

JLabel labelMeat = new JLabel("Seving No from Meat");
JTextField textMeat=new JTextField(4);

JLabel labelVeg = new JLabel("Serving No from Vegetable");
JTextField textVeg=new JTextField(4);

JLabel labelFruit = new JLabel("Serving No from Friut");
JTextField textFruit=new JTextField(4);

JLabel labelFat = new JLabel("Serving No from Fat");
JTextField textFat=new JTextField(4);

JLabel labelMixed = new JLabel("Serving No from Mixed");
JTextField textMixed=new JTextField(4);

JButton buttonUpdate = new JButton("Update");
JButton buttonResetUpdate = new JButton("Reset Values");

JComboBox DayTable = new JComboBox();

JLabel LabelDayTable = new JLabel("Choose Current Day To Review");

JLabel labelTotalCalories = new JLabel("Total Calories");

String data[][] = {{"","","","","",""}};
String col[] = {"Food","Type","Serving","Glucose","Carbs","Calories"};

DefaultTableModel model = new DefaultTableModel(data,col);
JTable tableJ = new JTable(model);

JScrollPane myScroll = new JScrollPane(tableJ);
JButton buttonClearSelectedDay = new JButton("Clear All Food From Selected Day");

GridBagLayout gbl;
GridBagConstraints gbc;


Container c;


MyFrame()
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

tableJ.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

myScroll.setMinimumSize(new Dimension(500,100));
TableColumn col1 = tableJ.getColumnModel().getColumn(0);
col1.setPreferredWidth(160);
TableColumn col2 = tableJ.getColumnModel().getColumn(1);
col2.setPreferredWidth(100);
TableColumn col3 = tableJ.getColumnModel().getColumn(2);
col3.setPreferredWidth(70);
TableColumn col4 = tableJ.getColumnModel().getColumn(3);
col4.setPreferredWidth(50);
TableColumn col5 = tableJ.getColumnModel().getColumn(4);
col5.setPreferredWidth(50);
TableColumn col6 = tableJ.getColumnModel().getColumn(5);
col6.setPreferredWidth(50);

c = getContentPane();


JTabbedPane tabbedPane=new JTabbedPane();

gbl=new GridBagLayout();
gbc=new GridBagConstraints();

createDayPanel();
createReviewPanel();
createCheckPanel();
createDailyEatenPanel();

c.add(tabbedPane);

tabbedPane.addTab("Daily Target",null,DayPanel,"Review ur daily intake needs");
tabbedPane.addTab("Food Eeaten",null,ReviewPanel,"Review the food exchange lists");
tabbedPane.addTab("Food Checking",null,CheckPanel,"Check the Samples");
tabbedPane.addTab("Daily Eaten",null,DailyEatenPanel,"Review ur daily intake have been eaten");


ButtonListener blisten = new ButtonListener();


mAbout.addActionListener(blisten);
mSignOut.addActionListener(blisten);
mExit.addActionListener(blisten);
buttonUpdate.addActionListener(blisten);
buttonResetUpdate.addActionListener(blisten);
FoodType.addActionListener(blisten);
FoodItem.addActionListener(blisten);
FoodDay.addActionListener(blisten);
buttonAdd.addActionListener(blisten);
buttonResetCompare.addActionListener(blisten);
buttonCompare.addActionListener(blisten);
buttonResetAdd.addActionListener(blisten);
DayTable.addActionListener(blisten);
buttonClearSelectedDay.addActionListener(blisten);


addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent w)
{
System.exit(1);
}
public void windowActivated(WindowEvent w)
{

}
});

setTitle("Diabetes Daily Hajj Care | Welcome " + Global.UF + " " + Global.UL + " [" + Global.UN + "]" );
setSize(600,350);
setVisible(true);

LabelFill.setVisible(false);
match.setVisible(false);
nmatch.setVisible(false);
}



public void createDayPanel(){

DayPanel.setLayout(gbl);

{
String typeURL ="Jdbc:odbc:FoodExchangeLists";

try

{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
ResultSet rcordDay=null;
Connection ConP=DriverManager.getConnection(typeURL,"","");
Statement stmtDay = ConP.createStatement(rcordDay.TYPE_SCROLL_INSENSITIVE,rcordDay.CONCUR_UPDATABLE);

rcordDay=stmtDay.executeQuery("Select * from totalNoMeals where uID=" + Global.UID);

rcordDay.absolute(1);
textCal.setText(rcordDay.getString("totalCal"));
textMilk.setText(rcordDay.getString("totalMilk"));
textBread.setText(rcordDay.getString("totalBread"));
textMeat.setText(rcordDay.getString("totalMeat"));
textVeg.setText(rcordDay.getString("totalVeg"));
textFruit.setText(rcordDay.getString("totalFruit"));
textFat.setText(rcordDay.getString("totalFat"));
textMixed.setText(rcordDay.getString("totalMixed"));

stmtDay.close();
ConP.close();

}


catch(Exception e){
System.out.println("Connection Not Successfull"+e.toString());
}
}

gbc.insets = new Insets (5,5,0,0);

DayPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(labelCal, gbc);
DayPanel.add(labelCal);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(textCal,gbc);
DayPanel.add(textCal);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=2;
gbl.setConstraints(labelMilk, gbc);
DayPanel.add(labelMilk);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=2;
gbl.setConstraints(textMilk,gbc);
DayPanel.add(textMilk);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=3;
gbl.setConstraints(labelBread, gbc);
DayPanel.add(labelBread);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=3;
gbl.setConstraints(textBread,gbc);
DayPanel.add(textBread);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=4;
gbl.setConstraints(labelMeat, gbc);
DayPanel.add(labelMeat);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=4;
gbl.setConstraints(textMeat,gbc);
DayPanel.add(textMeat);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=5;
gbl.setConstraints(labelVeg, gbc);
DayPanel.add(labelVeg);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=5;
gbl.setConstraints(textVeg,gbc);
DayPanel.add(textVeg);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=6;
gbl.setConstraints(labelFruit, gbc);
DayPanel.add(labelFruit);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=6;
gbl.setConstraints(textFruit,gbc);
DayPanel.add(textFruit);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=7;
gbl.setConstraints(labelFat, gbc);
DayPanel.add(labelFat);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=7;
gbl.setConstraints(textFat,gbc);
DayPanel.add(textFat);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=8;
gbl.setConstraints(labelMixed, gbc);
DayPanel.add(labelMixed);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=8;
gbl.setConstraints(textMixed,gbc);
DayPanel.add(textMixed);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=3;
gbc.gridy=9;
gbl.setConstraints( buttonUpdate, gbc);
DayPanel.add(buttonUpdate);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=4;
gbc.gridy=9;
gbl.setConstraints(buttonResetUpdate,gbc);
DayPanel.add(buttonResetUpdate);



}

public void createReviewPanel()
{
ReviewPanel.setLayout(gbl);

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


gbc.insets = new Insets (5,5,0,0);

ReviewPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(LabelFoodType, gbc);
ReviewPanel.add(LabelFoodType);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(FoodType,gbc);
ReviewPanel.add(FoodType);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=2;
gbl.setConstraints(LabelFoodItem, gbc);
ReviewPanel.add(LabelFoodItem);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=2;
gbl.setConstraints(FoodItem,gbc);
ReviewPanel.add(FoodItem);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=3;
gbl.setConstraints(LabelFoodDay, gbc);
ReviewPanel.add(LabelFoodDay);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=3;
gbl.setConstraints(FoodDay,gbc);
ReviewPanel.add(FoodDay);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=4;
gbl.setConstraints(labelServing1, gbc);
ReviewPanel.add(labelServing1);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=4;
gbl.setConstraints(textServing1,gbc);
ReviewPanel.add(textServing1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=5;
gbl.setConstraints(labelGlucose1, gbc);
ReviewPanel.add(labelGlucose1);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=5;
gbl.setConstraints(textGlucose1,gbc);
ReviewPanel.add(textGlucose1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=6;
gbl.setConstraints(labelCarbs1, gbc);
ReviewPanel.add(labelCarbs1);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=6;
gbl.setConstraints(textCarbs1,gbc);
ReviewPanel.add(textCarbs1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=7;
gbl.setConstraints(labelCalories1, gbc);
ReviewPanel.add(labelCalories1);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=7;
gbl.setConstraints(textCalories1,gbc);
ReviewPanel.add(textCalories1);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=3;
gbc.gridy=8;
gbl.setConstraints(buttonAdd,gbc);
ReviewPanel.add(buttonAdd);


gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=4;
gbc.gridy=8;
gbl.setConstraints(buttonResetAdd,gbc);
ReviewPanel.add(buttonResetAdd);

}

public void createCheckPanel()
{

CheckPanel.setLayout(gbl);

gbc.insets = new Insets(5,5,0,0);

CheckPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=1;
gbl.setConstraints(labelServing, gbc);
CheckPanel.add(labelServing);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=1;
gbl.setConstraints(textServing,gbc);
CheckPanel.add(textServing);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=1;
gbc.gridy=2;
gbl.setConstraints(labelGlucose, gbc);
CheckPanel.add(labelGlucose);
gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=2;
gbl.setConstraints(textGlucose,gbc);
CheckPanel.add(textGlucose);

gbc.gridx=2;
gbc.gridy=4;
gbl.setConstraints(match, gbc);
CheckPanel.add(match);

gbc.gridx=2;
gbc.gridy=4;
gbl.setConstraints(nmatch, gbc);
CheckPanel.add(nmatch);

gbc.anchor=GridBagConstraints.EAST;
gbc.gridx=3;
gbc.gridy=5;
gbl.setConstraints( buttonCompare, gbc);
CheckPanel.add(buttonCompare);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=4;
gbc.gridy=5;
gbl.setConstraints(buttonResetCompare,gbc);
CheckPanel.add(buttonResetCompare);

gbc.anchor=GridBagConstraints.WEST;
gbc.gridx=2;
gbc.gridy=6;
gbl.setConstraints(LabelFill,gbc);
CheckPanel.add(LabelFill);



}

public void createDailyEatenPanel()

{

DailyEatenPanel.setLayout(gbl);


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
DayTable.addItem(rcordDay.getString("dayName"));
}

stmtDay.close();
ConDay.close();
}
catch(Exception e){
System.out.println("Connection Not Successfull"+e.toString());
}
}


gbc.insets = new Insets(5,5,0,0);

DailyEatenPanel.setAlignmentX(TOP_ALIGNMENT);

gbc.gridx=1;
gbc.gridy=3;
gbl.setConstraints(LabelDayTable, gbc);
DailyEatenPanel.add(LabelDayTable);
gbc.gridx=1;
gbc.gridy=4;
gbl.setConstraints(DayTable,gbc);
DailyEatenPanel.add(DayTable);


gbc.gridx=1;
gbc.gridy=6;
gbl.setConstraints(myScroll, gbc);
DailyEatenPanel.add(myScroll);


gbc.gridx=1;
gbc.gridy=7;
gbl.setConstraints(labelTotalCalories,gbc);
DailyEatenPanel.add(labelTotalCalories);

gbc.gridx=1;
gbc.gridy=8;
gbl.setConstraints(buttonClearSelectedDay,gbc);
DailyEatenPanel.add(buttonClearSelectedDay);

}


class ButtonListener implements ActionListener
{



public void actionPerformed(ActionEvent e1)
{

Object obj = e1.getSource();

if (obj == mAbout){
JOptionPane.showMessageDialog(null,
("Diabetes Daily Hajj Care Program\n")+
("Smarter Diabetes Management for Digital Hajj Care \n")+
("Presently, diabetes is a becoming a common health problem worldwide.\n")+
("By taking steps to prevent this, it can save countless lives, help economic and promote well-being.\n")+
("Diabetes Daily Hajj Care program is used to measure the sugar level in food items by following simple steps.\n")+
("The Diabetes Daily Hajj Care Program is used to measure the sugar level in food items by following simple steps.\n")+
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

// START DayPanel --------------------------------------------------------------------
if(obj == buttonUpdate)
{

{
String typeURL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

Connection ConUpdate=DriverManager.getConnection(typeURL,"","");
ResultSet DayUpdate=null;
Statement stmtUpdate = ConUpdate.createStatement(DayUpdate.TYPE_SCROLL_INSENSITIVE,DayUpdate.CONCUR_UPDATABLE);


String sQ = "UPDATE totalNoMeals " +
"SET totalCal=" + textCal.getText() + ", " +
"totalMilk=" + textMilk.getText() + ", " +
"totalBread=" + textBread.getText() + ", " +
"totalMeat=" + textMeat.getText() + ", " +
"totalVeg=" + textVeg.getText() + ", " +
"totalFruit=" + textFruit.getText() + ", " +
"totalFat=" + textFat.getText() +  ", " +
"totalMixed=" + textMixed.getText() +
" where uID=" + Global.UID;

stmtUpdate.executeUpdate(sQ);
stmtUpdate.close();
ConUpdate.close();

JOptionPane.showMessageDialog(null, "Update succsesful");

}
catch(Exception e)
{
System.out.println("Connection Not Successfull"+e.toString());
}
}

}

if (obj==buttonResetUpdate)
{
textCal.setText("0");
textMilk.setText("0");
textBread.setText("0");
textMeat.setText("0");
textVeg.setText("0");
textFruit.setText("0");
textFat.setText("0");
textMixed.setText("0");

}

// END DayPanel --------------------------------------------------------------------


// START ReviewPanel --------------------------------------------------------------------
if(obj == FoodType)
{
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
String Type=FoodType.getSelectedItem().toString();
ResultSet rcordType=null;
Connection ConType=DriverManager.getConnection(URL,"","");
Statement stmtType = ConType.createStatement(rcordType.TYPE_SCROLL_INSENSITIVE,rcordType.CONCUR_UPDATABLE);

rcordType=stmtType.executeQuery("Select ID from FoodType where TypeName= '" + Type + "'");
rcordType.absolute(1);
String TypeString=null;
TypeString = Integer.toString(rcordType.getInt("ID"));

rcordType = stmtType.executeQuery("Select * from FoodItem where foodTypeID =" + TypeString );
FoodItem.removeAllItems();
while (rcordType.next())
{
FoodItem.addItem(rcordType.getString("foodName"));
}

rcordType = stmtType.executeQuery("select * from  Days");
FoodDay.removeAllItems();
while (rcordType.next())
{
FoodDay.addItem(rcordType.getString("dayName"));
}

stmtType.close();
ConType.close();
}
catch(Exception e)
{
System.out.println("Failed "+e.toString());
}
}


if(obj==FoodItem)
{
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

ResultSet rcordItem=null;
Connection ConItem=DriverManager.getConnection(URL,"","");
Statement stmtItem = ConItem.createStatement(rcordItem.TYPE_SCROLL_INSENSITIVE,rcordItem.CONCUR_UPDATABLE);

String StringItem = FoodItem.getSelectedItem().toString();


rcordItem = stmtItem.executeQuery("Select * from FoodItem where foodName = '" + StringItem + "'" );
rcordItem.absolute(1);
textServing1.setText(rcordItem.getString("foodServing"));
textGlucose1.setText(rcordItem.getString("foodGlucose"));
textCarbs1.setText(rcordItem.getString("foodCarbs"));
textCalories1.setText(rcordItem.getString("foodCalories"));

stmtItem.close();
ConItem.close();

}
catch(Exception e)
{
System.out.println("Failed "+e.toString());
}

}

if (obj==buttonAdd)
{

String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

ResultSet rcordAdd1=null;
Connection ConAdd=DriverManager.getConnection(URL,"","");
Statement stmtAdd1 = ConAdd.createStatement(rcordAdd1.TYPE_SCROLL_INSENSITIVE,rcordAdd1.CONCUR_UPDATABLE);

String foodName = FoodItem.getSelectedItem().toString();
String dayName = FoodDay.getSelectedItem().toString();

rcordAdd1 = stmtAdd1.executeQuery("Select ID from Days where dayName = '" + dayName + "'" );
rcordAdd1.absolute(1);
int dayID = rcordAdd1.getInt("ID");


rcordAdd1 = stmtAdd1.executeQuery("Select foodID from DailyRecord where dayID = " + dayID + "and userID=" + Global.UID);

ResultSet rcordAdd2=null;

Statement stmtAdd2 = ConAdd.createStatement(rcordAdd2.TYPE_SCROLL_INSENSITIVE,rcordAdd2.CONCUR_UPDATABLE);

ResultSet rcordFoodNew=null;

int totalCal = 0;
while (rcordAdd1.next())
{

rcordFoodNew = stmtAdd2.executeQuery("select foodCalories from FoodItem where ID=" + rcordAdd1.getString("foodID"));
rcordFoodNew.absolute(1);
totalCal = totalCal + rcordFoodNew.getInt("foodCalories");
}

rcordAdd1 = stmtAdd1.executeQuery("Select ID from Days where dayName = '" + dayName + "'" );
rcordAdd1.absolute(1);
dayID = rcordAdd1.getInt("ID");

rcordAdd1 = stmtAdd1.executeQuery("Select totalCal from totalNoMeals where uID=" + Global.UID );

rcordAdd1.absolute(1);
int amountCal = rcordAdd1.getInt("totalCal");


if (totalCal <= amountCal)
{

rcordAdd1 = stmtAdd1.executeQuery("Select * from FoodItem where foodName = '" + foodName + "'" );
rcordAdd1.absolute(1);
int foodID = rcordAdd1.getInt("ID");
int foodCal = rcordAdd1.getInt("foodCalories");
PreparedStatement stat = ConAdd.prepareStatement("Insert into DailyRecord(dayID,foodID,userID) values(" + dayID + "," + foodID + "," + Global.UID + ")");
stat.executeUpdate();
int totalCalAfter = totalCal + foodCal;

JOptionPane.showMessageDialog(null,"Add succsesful\n"+ "Your total allowed Calories= " + amountCal + "\nYour current daily calories= " + totalCalAfter);

}
else
{
JOptionPane.showMessageDialog(null, "You reach the total ammount for the daily calories!!");

}

stmtAdd1.close();
ConAdd.close();

}
catch(Exception e)
{
System.out.println("Faild " + e.toString());
}
}

if (obj==buttonResetAdd)
{

textServing1.setText("0");
textGlucose1.setText("0");
textCarbs1.setText("0");
textCalories1.setText("0");
}

// END ReviewPanel --------------------------------------------------------------------

// START CheckPanel --------------------------------------------------------------------
if (obj== buttonCompare)
{

String Serving = textServing.getText();
String Glucose = textGlucose.getText();


if (Serving.equals("") ||
Glucose.equals(""))
{

LabelFill.setVisible(true);

}
else
{
LabelFill.setVisible(false);
}

double G;
double TotalG;

G = Double.parseDouble(textGlucose.getText())/1000 ; // micro/dl to mg/dl
G = G * 100 ; //mg/dl to mg/ml in volume
G = G /1000 ; // mg/ml to g/ml
TotalG = Double.parseDouble(textServing.getText()) * G; // g/ml

match.setVisible(false);
nmatch.setVisible(false);

Double max = Double.parseDouble(textGlucose1.getText()) + 4; //g
Double min = Double.parseDouble(textGlucose1.getText()) - 4; //g


if ((TotalG >= min && TotalG <= max) &&
textServing.getText().equals(textServing1.getText()))
{
match.setVisible(true);
JOptionPane.showMessageDialog(null,"Note:Less or more than 4 gram doesn't affect\nbecause there's a lot of kind of the same food item");
}
else
{

nmatch.setVisible(true);
JOptionPane.showMessageDialog(null,"The food include added sugar or subtracted sugar");
}
}

if (obj==buttonResetCompare)
{
textServing.setText("0");
textGlucose.setText("0");
match.setVisible(false);
nmatch.setVisible(false);

}
// END CheckPanel --------------------------------------------------------------------

// START DailyEatenPanel --------------------------------------------------------------------

if(obj==DayTable )
{
model.setNumRows(1);

int totalCalories = 0;


{

String URL ="Jdbc:odbc:FoodExchangeLists";
try
{


Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

String selectedDay = DayTable.getSelectedItem().toString();


ResultSet rcordDay=null;
Connection ConDay=DriverManager.getConnection(URL,"","");
Statement stmtDay = ConDay.createStatement(rcordDay.TYPE_SCROLL_INSENSITIVE,rcordDay.CONCUR_UPDATABLE);

rcordDay = stmtDay.executeQuery("Select * from Days where dayName = '" + selectedDay + "'" );

rcordDay.absolute(1);
int dayID = rcordDay.getInt("ID");
String dayName = rcordDay.getString("dayName");
rcordDay = stmtDay.executeQuery("Select foodID from DailyRecord where dayID = " + dayID + "and userID=" + Global.UID);

while (rcordDay.next())
{


ResultSet rcordFood=null;
Connection ConFood=DriverManager.getConnection(URL,"","");
Statement stmtFood = ConFood.createStatement(rcordFood.TYPE_SCROLL_INSENSITIVE,rcordFood.CONCUR_UPDATABLE);


rcordFood = stmtFood.executeQuery("Select * from FoodItem where ID=" + rcordDay.getString("foodID"));

rcordFood.absolute(1);

String FN = rcordFood.getString("foodName");
String FT = rcordFood.getString("foodTypeID");
String FS = rcordFood.getString("foodServing");
String FG = rcordFood.getString("foodGlucose");
String FC = rcordFood.getString("foodCarbs");
String FCa = rcordFood.getString("foodCalories");

ResultSet rcordFoodType = null;
Connection ConFoodType=DriverManager.getConnection(URL,"","");
Statement stmtFoodType = ConFoodType.createStatement(rcordFoodType.TYPE_SCROLL_INSENSITIVE,rcordFoodType.CONCUR_UPDATABLE);


rcordFoodType = stmtFoodType.executeQuery("Select * from FoodType where ID=" + FT);
rcordFoodType.absolute(1);
String FTname = rcordFoodType.getString("typeName");



totalCalories = totalCalories + rcordFood.getInt("foodCalories");


String myItems[] = {FN,FTname,FS,FG,FC,FCa};

model.insertRow(rcordDay.getRow(), myItems );


stmtFoodType.close();
ConFoodType.close();


}
model.removeRow(0);
labelTotalCalories.setText("The total calories for" + dayName + " is " + totalCalories );





}
catch(Exception e)
{
System.out.println("Faild " + e.toString());
}
}

}

if(obj==buttonClearSelectedDay )
{

model.setNumRows(1);

int totalCalories = 0;
String URL ="Jdbc:odbc:FoodExchangeLists";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

String selectedDay = DayTable.getSelectedItem().toString();
ResultSet rcordDay=null;
Connection ConDay=DriverManager.getConnection(URL,"","");
Statement stmt = ConDay.createStatement(rcordDay.TYPE_SCROLL_INSENSITIVE,rcordDay.CONCUR_UPDATABLE);
rcordDay = stmt.executeQuery("Select * from Days where dayName = '" + selectedDay + "'" );
rcordDay.absolute(1);
int dayID = rcordDay.getInt("ID");

stmt.executeUpdate("DELETE from DailyRecord where dayID =" + dayID + "and userID =" + Global.UID );

JOptionPane.showMessageDialog(null, "Delete succsesful");




rcordDay = stmt.executeQuery("Select * from Days where dayName = '" + selectedDay + "'" );

rcordDay.absolute(1);

String dayName = rcordDay.getString("dayName");
rcordDay = stmt.executeQuery("Select foodID from DailyRecord where dayID = " + dayID + "and userID=" + Global.UID);

while (rcordDay.next())
{


ResultSet rcordFood=null;
Connection ConFood=DriverManager.getConnection(URL,"","");
Statement stmtFood = ConFood.createStatement(rcordFood.TYPE_SCROLL_INSENSITIVE,rcordFood.CONCUR_UPDATABLE);


rcordFood = stmtFood.executeQuery("Select * from FoodItem where ID=" + rcordDay.getString("foodID"));

rcordFood.absolute(1);

String FN = rcordFood.getString("foodName");
String FT = rcordFood.getString("foodTypeID");
String FS = rcordFood.getString("foodServing");
String FG = rcordFood.getString("foodGlucose");
String FC = rcordFood.getString("foodCarbs");
String FCa = rcordFood.getString("foodCalories");

ResultSet rcordFoodType = null;
Connection ConFoodType=DriverManager.getConnection(URL,"","");
Statement stmtFoodType = ConFoodType.createStatement(rcordFoodType.TYPE_SCROLL_INSENSITIVE,rcordFoodType.CONCUR_UPDATABLE);


rcordFoodType = stmtFoodType.executeQuery("Select * from FoodType where ID=" + FT);
rcordFoodType.absolute(1);
String FTname = rcordFoodType.getString("typeName");



totalCalories = totalCalories + rcordFood.getInt("foodCalories");


String myItems[] = {FN,FTname,FS,FG,FC,FCa};

model.insertRow(rcordDay.getRow(), myItems );

stmtFoodType.close();
ConFoodType.close();



}
model.removeRow(0);
labelTotalCalories.setText("The total calories for" + dayName + " is " + totalCalories );



}
catch(Exception e)
{
System.out.println("Faild " + e.toString());
}

// END DailyEatenPanel --------------------------------------------------------------------


}



}
}

}

