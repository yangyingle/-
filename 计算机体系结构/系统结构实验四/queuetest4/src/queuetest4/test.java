package queuetest4;

import java.util.*;

class LRU{
	int x;//ʵҳ��
	int LRUbingo=0;//ͳ��������
	int LRUnotbingo=0;//ͳ��δ������
	Queue<Integer> LRUqueue=new LinkedList<Integer>();
	public void LRUwork(int b){
		if(LRUqueue.size()==0){//��ʼʱLRU����Ϊ�գ�ȱҳ��δ��������1������ǰҳ���뵽��β
			LRUqueue.offer(b);
			LRUnotbingo++;
		}else{                //LRU���в�Ϊ�գ�
			if(LRUqueue.contains(b)){//����ǰҳ�ڶ����У���������1���Ѷ����еĵ�ǰҳ�ƶ�����β��
				LRUbingo++;
				LRUqueue.remove(b);
				LRUqueue.offer(b);
			}else{			//��ǰҳ���ڶ����У�δ��������1���ѵ�ǰҳ���뵽��β
				LRUnotbingo++;
				if(LRUqueue.size()<x){//�����г���С��ʵҳ��ʱ��ֱ�Ӱѵ�ǰҳ���뵽��β�������Ƴ���ͷԪ�أ��ٽ���ǰҳ���뵽��β
					LRUqueue.offer(b);
				}else{
					LRUqueue.poll();
					LRUqueue.offer(b);
				}
			}
		}
	}
}

class FIFO{
	int x;//ʵҳ��
	Queue<Integer> FIFOqueue=new LinkedList<Integer>();
	int FIFObingo=0;//ͳ��������
	int FIFOnotbingo=0;//ͳ��δ������
	
	public void FIFOin(int a){
		if(FIFOqueue.size()==0){//��ʼʱFIFO����Ϊ�գ�ȱҳ��δ��������1�����Ұѵ�ǰҳ����뵽��β
			FIFOqueue.offer(a);
			FIFOnotbingo++;
		}else{                 //���в�Ϊ�գ��ж϶������Ƿ��е�ǰҳ���У������У�û�У�δ���У��ѵ�ǰҳ���뵽��β
			if(FIFOqueue.contains(a)){
				FIFObingo++;
			}else{
				FIFOnotbingo++;
				if(FIFOqueue.size()<x){//�����г���С��ʵҳ��ʱ��ֱ�Ӱѵ�ǰҳ���뵽��β�������Ƴ���ͷԪ�أ��ٽ���ǰҳ���뵽��β
					FIFOqueue.offer(a);
				}else{
					FIFOqueue.poll();
					FIFOqueue.offer(a);
				}
			}
		}
		
	}
}


public class test {
	 public static void main(String[] args){
		 int time;
		 Random rand=new Random();
		 int VqueueLength=rand.nextInt(5)+12;
		 int[] Vqueue=new int[VqueueLength];//��ʼ����ҳ������
		 System.out.println("��ҳ�����ȣ�"+VqueueLength);
		 for(int k=0;k<VqueueLength;k++){   //��ʼ��ÿ����ҳ��ҳ��1-6��
			 Vqueue[k]=rand.nextInt(5)+1;
			 System.out.print(Vqueue[k]+" ");
		 }
		 System.out.println();
		 FIFO fifo=new FIFO();//ʵ����һ��FIFO
		 for(int i=1;i<=5;i++){
			 System.out.println("ʵҳ����"+i);
			 fifo.FIFOqueue.clear();//��ն���
			 fifo.x=i;//����ʵҳ��
			 fifo.FIFObingo=0;
			 fifo.FIFOnotbingo=0;//��������δ������������
			 
			 for(int j=0;j<Vqueue.length;j++){//������ҳ��
				 fifo.FIFOin(Vqueue[j]);
			 }
			 System.out.println("FIFO��������"+fifo.FIFObingo);
			 System.out.println("FIFOδ��������"+fifo.FIFOnotbingo);
			 time=fifo.FIFObingo*5+fifo.FIFOnotbingo*100;
			 System.out.println("FIFOʱ��"+time);
		 }
		 System.out.println();
		 LRU lru=new LRU();//ʵ����һ��LRU
		 for(int n=1;n<=5;n++){
			 System.out.println("ʵҳ����"+n);
			 lru.LRUqueue.clear();//���LRU����
			 lru.x=n;//����ʵҳ��
			 lru.LRUbingo=0;
			 lru.LRUnotbingo=0;//��������δ������������
			 for(int m=0;m<Vqueue.length;m++){
				 lru.LRUwork(Vqueue[m]);
			 }
			 System.out.println("LRU��������"+lru.LRUbingo);
			 System.out.println("LRUδ��������"+lru.LRUnotbingo);
			 time=lru.LRUbingo*5+lru.LRUnotbingo*100;
			 System.out.println("LURʱ��"+time);
		 }
	 }
}
