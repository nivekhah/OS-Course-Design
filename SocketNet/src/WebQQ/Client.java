package WebQQ;

import java.net.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
 
public class Client {
    public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		Socket socket = new Socket("localhost", 5432);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        final DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Frame myframe = new Frame("Socket������ power by ��װ�� ���� ����С��");
        Panel panelx = new Panel();
        final TextField input = new TextField(35);
        TextArea display = new TextArea(19, 35);
        panelx.add(display);
        panelx.add(input);
        myframe.add(panelx);
        new receiveThread(dis, display);    //��������������Ϣ���߳�
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dos.writeUTF(input.getText());    //��������
                    input.setText("");  //������������
                } catch (IOException e2) {    }
            }
        });
         
        myframe.setSize(350, 400);
        myframe.setVisible(true);
    }
}
 
//������Ϣ�߳�ѭ����ȡ������Ϣ����ʾ���ı���
class receiveThread extends Thread{
    DataInputStream dis;
    TextArea displayarea;
    public receiveThread(DataInputStream dis, TextArea m){
        this.dis = dis;
        displayarea = m;
        this.start();
    }
    public void run(){
        for(;;){
            try {
                String str = dis.readUTF();   //�����Է���������Ϣ
                displayarea.append(str + "\n");   //����Ϣ��ӵ��ı�����ʾ
            } catch (IOException e) {}
        }
    }
}
