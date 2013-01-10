
import java.awt.event.*;
import java.util.ArrayList;

//import org.apache.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.swing.*;

public class Main_ extends JFrame implements ActionListener,MouseListener {

	/**
	 * @param args
	 */
	//定义界面
	JLabel jlb,jlb2;
	JPanel jp_down=null;
	JTextArea jta =null;
	
	JTextField jtf=null;
	
	JButton jb=null;
	JPanel jp=null;
	
	String temp="";

	JScrollPane jpe=null;
	String result=null;
	ArrayList<DouBan_Book>dblist=null;
	
   // static TudouBookInfo book=null;
    
	public static void main(String[] args){
		// TODO Auto-generated method stub
             Main_ m=new Main_();
	}
	
	
	Main_(){
		
		jlb=new  JLabel(new ImageIcon("Image/douban.png"));
		//jlb.setBounds(0, 0, 200, 100);
		jp_down=new JPanel(); //定义显示界面
		jta=new JTextArea("",20,7);
		jta.setEditable(false);//只可显示，不可以编辑。
		jpe=new JScrollPane(jta);//设置滑轮，方便显示多条信息
		jtf=new JTextField("书名、作者、ISBN");//设置输入框和默认值
		
		
		jtf.setBounds(150, 20, 240, 35);
		jtf.addMouseListener(this);//错误原因：jtf.addActionListener(this)
		jb=new JButton("搜索");//设置搜索按钮
		jb.setBounds(395,20,80,35);
		jb.addActionListener(this);
	
		jp=new JPanel();//默认是layout流布局。
		
		jp.setLayout(null);

	
		jp.add(jtf);
		jp.add(jb);
		
		
		this.add(jlb,"North");
		
		this.add(jp,"Center");
		this.add(jpe,"South");
		
		this.setLocation(300, 200);
		this.setSize(600,500);
		this.setVisible(true);
		this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("豆瓣检索");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if(e.getSource()==jb){
			//调用豆瓣api，返回书的信息，
			//在用java解析json格式后输出显示在JTextArea中
			 if(jtf.getText().toString().equals("书名、作者、ISBN")||jtf.getText().toString().equals(""))
				 return ;
			 jta.setText("");
			  temp=jtf.getText().trim().toString();//出去空格后的字符串  2023013
			  System.out.println(temp);
			  String url="https://api.douban.com/v2/book/search?q="+temp+"&start=0&count=20";
			 try{
				 DefaultHttpClient client = new DefaultHttpClient();
                 HttpGet get = new HttpGet(url);   
                 HttpResponse response = client.execute(get);
                 result=EntityUtils.toString(response.getEntity()); 
                 System.out.println(result);
			 }catch(Exception re){	  
				 re.printStackTrace();
				 
			 } 
			 
			 //传入jsonstr给json2java函数。
			 DouBan_Json dj=new DouBan_Json();
			// String t="["+result+"]";
			 dj.Json2java(result);
			 dblist=new ArrayList<DouBan_Book>();
			 dblist=dj.getBklist();
			 System.out.println(dblist.size());
			 if(dblist.size()==0)
			 {
				 jta.append("暂无你搜索的书");
			 }
			 else
			 {
			 for(int i=0;i<dblist.size();i++)
			 {
			  DouBan_Book book=dblist.get(i); 
			  jlb2=new  JLabel();
			  jlb2.setIcon(new ImageIcon(book.getImagePath()));
			  jta.add(jlb2);
			  jta.append(i+1+"、");
			  jta.append("书的名字："+book.getTitle()+"\r\n");
			  jta.append("书的Isbn10："+book.getIsbn10()+"\r\n");
			// jta.append("书的Isbn13："+book.getIsbn13()+"\r\n");
			  jta.append("书的页数："+book.getPages()+"\r\n");
			  jta.append("书的作者："+book.getAuthor()+"\r\n");
			  jta.append("装订："+book.getBinding()+"\r\n");
			  jta.append("书的价格："+book.getPrice()+"\r\n");
			  jta.append("出版时间："+book.getPubdate()+"\r\n");
			  jta.append("出版社："+book.getPublisher()+"\r\n");
			  jta.append("书的介绍："+"\r\n");
			  if(!book.getSummary().equals(""))
			  jta.append(book.getSummary()+"\r\n");
			  else
			  {
				  jta.append("本书暂无介绍，梁绍锣"+"\r\n");
			  }
			 
			   }	
			 }
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
               
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
       	  
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(jtf.getText().toString().equals("书名、作者、ISBN"))
		 jtf.setText("");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(jtf.getText().toString().equals(""))
		 jtf.setText("书名、作者、ISBN");
	}

}
