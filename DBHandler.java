
class DBHandler
{
int rno;
String sname;
int m1,m2,m3;
public DBHandler(){}

public DBHandler(int rno,String sname,int m1,int m2,int m3){
this.rno = rno;
this.sname = sname;
this.m1 = m1;
this.m2 = m2;
this.m3 = m3;
}
public void setrno(int rno){ 	this.rno = rno;}
public int getrno() { return rno;}
public void setsname(String sname){ this.sname = sname;}
public String getsname(){ return sname;}
public void setm1(int m1){ this.m1 = m1;}
public int getm1(){return m1;}
public void setm2(int m2){ this.m2 = m2;}
public int getm2(){return m2;}
public void setm3(int m3){ this.m3 = m3;}
public int getm3(){return m3;}


}