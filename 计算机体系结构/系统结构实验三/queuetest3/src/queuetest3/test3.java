package queuetest3;


import java.util.Queue;//插入Queue包，用来创建队列以及建立队列操作的引用
import java.util.LinkedList;//插入LinkedList包，用来创建队列
import java.util.Random;//插入Random包，用来生成随机数


class Add_mul{//加乘法器类
	int reg1;
	int reg2;
	int reg3;//三个级间寄存器
	
	public void Step1(int a,int b){//第一级加法
		int x=a;
		int y=b;
		reg1=x+y;
	}
	public void Step1_1(int a,int b){//第一级乘法
		reg2=a*b;
	}
	public void Step2(){//第二级
		reg2=0;
		if(reg1!=0){
		reg2=reg1;
		reg1=0;}
	}
	
	public void Step3(){//第三级
		reg3=0;
		if(reg2!=0){
		reg3=reg2;
		reg2=0;}
	}
	
	public int  Step5(){//第四级
		if(reg3!=0){
		int c=reg3;
		reg3=0;
		return c;}
		return 0;
		}
}





public class test3 {
	public static void main(String[] args){
		Queue<Integer> A=new LinkedList<Integer>();//创建Ai队列
		Queue<Integer> B=new LinkedList<Integer>();//创建Bi队列
		Queue<Integer> C=new LinkedList<Integer>();//创建加法队列
		Random rand=new Random();
		
		Add_mul add_mul=new Add_mul();//实例化加法器
		
		
		for(int k=4;k<21;k++){//循环，n从4——20
		int count=0;
		int n=k;//队列长度，
		System.out.println("n="+n);
		
		System.out.print("Ai:");
		for(int i=0;i<n;i++){//填充A队列，并显示A队列元素
			int a=rand.nextInt(20)+1;//生成随机数，赋值给队列中的元素
			A.offer(a);             
			
			System.out.print(a+"\t");
		}
		System.out.println();//换行
		
		System.out.print("Bi:");
		for(int i=0;i<n;i++){//填充B队列，并显示B队列的元素
			int b=rand.nextInt(20)+1;//生成随机数，赋值给队列中的元素
			B.offer(b);
			
			System.out.print(b+"\t");
		}
		System.out.println();//换行
		

		
		
		while(true){//运算
			if((C.size()==1)&&(add_mul.reg1==0)&&(add_mul.reg2==0)&&(add_mul.reg3==0)){
				break;
			}
			count++;
			
			int s=add_mul.Step5();//流水线输出结果，相当于回写
			add_mul.Step3();
			add_mul.Step2();
			if(A.size()>0){//如果乘法队列不为空执行乘法
				int a=A.poll();
				int b=B.poll();
				add_mul.Step1_1(a, b);
			}else if((C.size()>=2)&&(A.size()==0)){//乘法队列为空，加法队列元素个数大于等于2，执行加法
				int x=C.poll();
				int y=C.poll();
				add_mul.Step1(x, y);
			}else{
				add_mul.reg1=0;
			}
			
			if(s!=0){
				C.offer(s);
			}
		}
		
		
		System.out.println("The sum is "+C.poll());//打印最终结果
		System.out.println("The time is "+count);//打印时间
		System.out.println();//换行
		}
		
	}

}

