package queuetest4;

import java.util.*;

class LRU{
	int x;//实页数
	int LRUbingo=0;//统计命中数
	int LRUnotbingo=0;//统计未命中数
	Queue<Integer> LRUqueue=new LinkedList<Integer>();
	public void LRUwork(int b){
		if(LRUqueue.size()==0){//初始时LRU队列为空，缺页，未命中数加1，将当前页加入到队尾
			LRUqueue.offer(b);
			LRUnotbingo++;
		}else{                //LRU队列不为空，
			if(LRUqueue.contains(b)){//若当前页在队列中，命中数加1，把队列中的当前页移动到队尾，
				LRUbingo++;
				LRUqueue.remove(b);
				LRUqueue.offer(b);
			}else{			//当前页不在队列中，未命中数加1，把当前页加入到队尾
				LRUnotbingo++;
				if(LRUqueue.size()<x){//当队列长度小于实页数时，直接把当前页加入到队尾，否则移除队头元素，再将当前页加入到队尾
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
	int x;//实页数
	Queue<Integer> FIFOqueue=new LinkedList<Integer>();
	int FIFObingo=0;//统计命中数
	int FIFOnotbingo=0;//统计未命中数
	
	public void FIFOin(int a){
		if(FIFOqueue.size()==0){//初始时FIFO队列为空，缺页，未命中数加1，并且把当前页面加入到队尾
			FIFOqueue.offer(a);
			FIFOnotbingo++;
		}else{                 //队列不为空，判断队列中是否有当前页，有，则命中，没有，未命中，把当前页加入到队尾
			if(FIFOqueue.contains(a)){
				FIFObingo++;
			}else{
				FIFOnotbingo++;
				if(FIFOqueue.size()<x){//当队列长度小于实页数时，直接把当前页加入到队尾，否则移除队头元素，再将当前页加入到队尾
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
		 int[] Vqueue=new int[VqueueLength];//初始化虚页流长度
		 System.out.println("虚页流长度："+VqueueLength);
		 for(int k=0;k<VqueueLength;k++){   //初始化每个虚页，页号1-6，
			 Vqueue[k]=rand.nextInt(5)+1;
			 System.out.print(Vqueue[k]+" ");
		 }
		 System.out.println();
		 FIFO fifo=new FIFO();//实例化一个FIFO
		 for(int i=1;i<=5;i++){
			 System.out.println("实页数："+i);
			 fifo.FIFOqueue.clear();//清空队列
			 fifo.x=i;//设置实页数
			 fifo.FIFObingo=0;
			 fifo.FIFOnotbingo=0;//命中数和未命中数都清零
			 
			 for(int j=0;j<Vqueue.length;j++){//访问虚页流
				 fifo.FIFOin(Vqueue[j]);
			 }
			 System.out.println("FIFO命中数："+fifo.FIFObingo);
			 System.out.println("FIFO未命中数："+fifo.FIFOnotbingo);
			 time=fifo.FIFObingo*5+fifo.FIFOnotbingo*100;
			 System.out.println("FIFO时间"+time);
		 }
		 System.out.println();
		 LRU lru=new LRU();//实例化一个LRU
		 for(int n=1;n<=5;n++){
			 System.out.println("实页数："+n);
			 lru.LRUqueue.clear();//清空LRU队列
			 lru.x=n;//设置实页数
			 lru.LRUbingo=0;
			 lru.LRUnotbingo=0;//命中数和未命中数都清零
			 for(int m=0;m<Vqueue.length;m++){
				 lru.LRUwork(Vqueue[m]);
			 }
			 System.out.println("LRU命中数："+lru.LRUbingo);
			 System.out.println("LRU未命中数："+lru.LRUnotbingo);
			 time=lru.LRUbingo*5+lru.LRUnotbingo*100;
			 System.out.println("LUR时间"+time);
		 }
	 }
}
