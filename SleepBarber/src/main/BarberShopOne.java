package main;

import java.util.concurrent.Semaphore;
class Barber implements Runnable{
	private BarberShopOne object;
	private int index;
	public Barber(BarberShopOne barbershop,int i)
	{
		this.object=barbershop;
		index=i;
	}
	public void run()
	{
		try {
			object.RunWork(index);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
public class BarberShopOne {
	static int CustomerCount=0;//�˿���
	static int Max=6;
	static int WorkStatus=0;
	static Semaphore mutex=new Semaphore(1);
	public BarberShopUi ui;
	public static int sleeptime=2000;
	public BarberShopOne()
	{
		ui=new BarberShopUi();
		ui.setVisible(true);
	}
	public static void main(String[] args) throws InterruptedException {
		BarberShopOne bar=new BarberShopOne();
		for(int i=1;i<20;i++)
		{
			new Thread(new Barber(bar,i)).start();
			Thread.sleep(sleeptime);
		}
	}
	public synchronized boolean isEqual(int one,int two)
	{
		if(one==two)
		{
			return true;
		}
		return false;
	}
	public void RunWork(int index) throws InterruptedException
	{
		
		synchronized (this) {
			System.out.println("�˿�"+index+"����");
			ui.setStatus("�˿�"+index+"����");
			CustomerCount++;
		}
		if(isEqual(CustomerCount,Max))
		{
			ui.setDoorCome();
			System.out.println("û��������������");
			ui.setDoorLeave();
			ui.setStatus("û��������������");
			CustomerCount--;
		}
		else
		{
			if(isEqual(WorkStatus, 1))
			{
				synchronized (this) {
					ui.getSeet(index);
					System.out.println("�˿�"+index+"�ȴ���");
					ui.setStatus("�˿�"+index+"�ȴ���");
				}
			}
			mutex.acquire();
			synchronized (this) {
				while(WorkStatus==1)
				{
					wait();
				}
				synchronized(this)
				{
					ui.freeSeet(index);
					CustomerCount--;
					WorkStatus=1;
				}
			}
			synchronized (this) {
				System.out.println("�˿�"+index+"������");
				ui.setBarber(index);
			}
			Thread.sleep(sleeptime*3);
			synchronized (this) {
				System.out.println("�˿�"+index+"�뿪��");
				ui.setStatus("�˿�"+index+"������뿪��");
				ui.freeSeet(index);
			}
			synchronized (this) {
				WorkStatus=0;
				notify();
			}
			mutex.release();
			if(isEqual(CustomerCount,0))
			{
				System.out.println("û�й˿ͣ���ʼ˯��");
				ui.setStatus("û�й˿ͣ���ʼ˯��");
				ui.Sleep();
			}
		}
	}
}
