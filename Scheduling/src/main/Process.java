package main;
public class Process {
	public Integer ID; //���̱�ʶ
	public int PRIORITY; //����������
	public int CHIP;//ʱ��Ƭ��
	public int CPUTIME;//�Ѿ����õ�CPUʱ��;
	public int ALLTIME;//����ʣ������ʱ��
	public String STATUS; //����״̬ "R E"
	public Process(int id,int priority,int alltime)
	{
		this.ID=id;
		this.PRIORITY=priority;
		this.CHIP=0;
		this.ALLTIME=alltime;
		this.STATUS="R";
		this.CPUTIME=0;
	}
	public int getPriority()
	{
		return this.PRIORITY;
	}
}
