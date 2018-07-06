package WebQQ;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class Client {
	private static ReadThread readThread;
	private static DataOutputStream dos;
    public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		Socket socket = new Socket("localhost", 6666);
        dos = new DataOutputStream(socket.getOutputStream());
        JFrame frame = new JFrame("������by ��װ�� ���� ����С��");
        frame.setBounds(100, 100, 476, 657);
        frame.setLayout(null);
        JLabel label = new JLabel("��������");
		label.setFont(new Font("����", Font.PLAIN, 19));
        TextField input = new TextField(40);
        input.setBounds(26, 525, 295, 46);
        input.setFont(new Font("����", Font.PLAIN, 25));
        TextArea display = new TextArea(19, 40);
        display.setBounds(26, 46, 405, 466);
        display.setFont(new Font("����",Font.BOLD,25));
        JButton submits = new JButton("�ύ");
		submits.setBounds(335, 525, 96, 46);
		frame.add(submits);
        frame.add(input);
        frame.add(display);
        frame.add(label);
        submits.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
                    dos.writeUTF(input.getText());
                    input.setText("");
                } catch (IOException e2) {}
			}
		});
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dos.writeUTF(input.getText());
                    input.setText("");
                } catch (IOException e2) {}
            }
        });
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				readThread.interrupt();
			}
		});
        frame.setVisible(true);
        readThread =new ReadThread(new DataInputStream(socket.getInputStream()), display);
        readThread.start();
    }
}

