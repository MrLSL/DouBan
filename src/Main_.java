
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
	//�������
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
		jp_down=new JPanel(); //������ʾ����
		jta=new JTextArea("",20,7);
		jta.setEditable(false);//ֻ����ʾ�������Ա༭��
		jpe=new JScrollPane(jta);//���û��֣�������ʾ������Ϣ
		jtf=new JTextField("���������ߡ�ISBN");//����������Ĭ��ֵ
		
		
		jtf.setBounds(150, 20, 240, 35);
		jtf.addMouseListener(this);//����ԭ��jtf.addActionListener(this)
		jb=new JButton("����");//����������ť
		jb.setBounds(395,20,80,35);
		jb.addActionListener(this);
	
		jp=new JPanel();//Ĭ����layout�����֡�
		
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
		this.setTitle("�������");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if(e.getSource()==jb){
			//���ö���api�����������Ϣ��
			//����java����json��ʽ�������ʾ��JTextArea��
			 if(jtf.getText().toString().equals("���������ߡ�ISBN")||jtf.getText().toString().equals(""))
				 return ;
			 jta.setText("");
			  temp=jtf.getText().trim().toString();//��ȥ�ո����ַ���  2023013
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
			 
			 //����jsonstr��json2java������
			 DouBan_Json dj=new DouBan_Json();
			// String t="["+result+"]";
			 dj.Json2java(result);
			 dblist=new ArrayList<DouBan_Book>();
			 dblist=dj.getBklist();
			 System.out.println(dblist.size());
			 if(dblist.size()==0)
			 {
				 jta.append("��������������");
			 }
			 else
			 {
			 for(int i=0;i<dblist.size();i++)
			 {
			  DouBan_Book book=dblist.get(i); 
			  jlb2=new  JLabel();
			  jlb2.setIcon(new ImageIcon(book.getImagePath()));
			  jta.add(jlb2);
			  jta.append(i+1+"��");
			  jta.append("������֣�"+book.getTitle()+"\r\n");
			  jta.append("���Isbn10��"+book.getIsbn10()+"\r\n");
			// jta.append("���Isbn13��"+book.getIsbn13()+"\r\n");
			  jta.append("���ҳ����"+book.getPages()+"\r\n");
			  jta.append("������ߣ�"+book.getAuthor()+"\r\n");
			  jta.append("װ����"+book.getBinding()+"\r\n");
			  jta.append("��ļ۸�"+book.getPrice()+"\r\n");
			  jta.append("����ʱ�䣺"+book.getPubdate()+"\r\n");
			  jta.append("�����磺"+book.getPublisher()+"\r\n");
			  jta.append("��Ľ��ܣ�"+"\r\n");
			  if(!book.getSummary().equals(""))
			  jta.append(book.getSummary()+"\r\n");
			  else
			  {
				  jta.append("�������޽��ܣ�������"+"\r\n");
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
		if(jtf.getText().toString().equals("���������ߡ�ISBN"))
		 jtf.setText("");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(jtf.getText().toString().equals(""))
		 jtf.setText("���������ߡ�ISBN");
	}

}