package queuetest3;


import java.util.Queue;//����Queue�����������������Լ��������в���������
import java.util.LinkedList;//����LinkedList����������������
import java.util.Random;//����Random�����������������


class Add_mul{//�ӳ˷�����
	int reg1;
	int reg2;
	int reg3;//��������Ĵ���
	
	public void Step1(int a,int b){//��һ���ӷ�
		int x=a;
		int y=b;
		reg1=x+y;
	}
	public void Step1_1(int a,int b){//��һ���˷�
		reg2=a*b;
	}
	public void Step2(){//�ڶ���
		reg2=0;
		if(reg1!=0){
		reg2=reg1;
		reg1=0;}
	}
	
	public void Step3(){//������
		reg3=0;
		if(reg2!=0){
		reg3=reg2;
		reg2=0;}
	}
	
	public int  Step5(){//���ļ�
		if(reg3!=0){
		int c=reg3;
		reg3=0;
		return c;}
		return 0;
		}
}





public class test3 {
	public static void main(String[] args){
		Queue<Integer> A=new LinkedList<Integer>();//����Ai����
		Queue<Integer> B=new LinkedList<Integer>();//����Bi����
		Queue<Integer> C=new LinkedList<Integer>();//�����ӷ�����
		Random rand=new Random();
		
		Add_mul add_mul=new Add_mul();//ʵ�����ӷ���
		
		
		for(int k=4;k<21;k++){//ѭ����n��4����20
		int count=0;
		int n=k;//���г��ȣ�
		System.out.println("n="+n);
		
		System.out.print("Ai:");
		for(int i=0;i<n;i++){//���A���У�����ʾA����Ԫ��
			int a=rand.nextInt(20)+1;//�������������ֵ�������е�Ԫ��
			A.offer(a);             
			
			System.out.print(a+"\t");
		}
		System.out.println();//����
		
		System.out.print("Bi:");
		for(int i=0;i<n;i++){//���B���У�����ʾB���е�Ԫ��
			int b=rand.nextInt(20)+1;//�������������ֵ�������е�Ԫ��
			B.offer(b);
			
			System.out.print(b+"\t");
		}
		System.out.println();//����
		

		
		
		while(true){//����
			if((C.size()==1)&&(add_mul.reg1==0)&&(add_mul.reg2==0)&&(add_mul.reg3==0)){
				break;
			}
			count++;
			
			int s=add_mul.Step5();//��ˮ�����������൱�ڻ�д
			add_mul.Step3();
			add_mul.Step2();
			if(A.size()>0){//����˷����в�Ϊ��ִ�г˷�
				int a=A.poll();
				int b=B.poll();
				add_mul.Step1_1(a, b);
			}else if((C.size()>=2)&&(A.size()==0)){//�˷�����Ϊ�գ��ӷ�����Ԫ�ظ������ڵ���2��ִ�мӷ�
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
		
		
		System.out.println("The sum is "+C.poll());//��ӡ���ս��
		System.out.println("The time is "+count);//��ӡʱ��
		System.out.println();//����
		}
		
	}

}

