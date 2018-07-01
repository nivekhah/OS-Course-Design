import java.awt.*;
import java.awt.event.*;
public class MouseAndKeyBoardDemo {
	
	private Frame f;
	private TextField tf;
	private Button button;
	public MouseAndKeyBoardDemo()
	{
		init();
	}
	public void init()
	{
		//��ʼ�����
		f=new Frame("�Ի���");
		f.setLayout(new FlowLayout());
		f.setBounds(400, 200, 500, 400);
		tf=new TextField(15);
		button=new Button("��ť");
		//������
		f.add(tf);
		f.add(button);
		//���ü����¼�
		MyEventer();
		f.setVisible(true);
	}
	private void MyEventer() {
		
		f.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		//����button������¼�����
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//tf.setText("�������");
			}
		});
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				super.mouseEntered(arg0);
				tf.setText("��������");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(e.getClickCount()==2)
					tf.setText("���˫��");
				else
					tf.setText("��굥��");
			}
		});
		//�����ı�����¼�����
		tf.addKeyListener(new KeyListener() {
			private StringBuffer s;
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyChar());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public static void main(String [] args)
	{
		new MouseAndKeyBoardDemo();
	}
}
