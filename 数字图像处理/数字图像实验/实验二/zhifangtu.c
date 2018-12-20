#include<stdio.h>
#include<stdlib.h>
#include<windows.h>
/*��Ϊ4�ֽڶ���Ĵ���һ����ȥ�������ͼƬ�������⣬����ֻ��������������4�ı�����ͼƬ*/ 
typedef struct BITMAPFILEHEADER{
	WORD bfType;/* 2byte λͼ��𣬸��ݲ�ͬ�Ĳ���ϵͳ����ͬ����Windows�У����ֶε�ֵ��Ϊ��BM��*/
	DWORD bfSize;/* 4byte BMP�Ĵ�С */
	WORD bfReserved1;/* 2byte ��Ϊ0*/
	WORD bfReserved2;/*2byte ��Ϊ0*/
	DWORD bfOffBits; /*ͼ�����ݵ�ƫ�Ƶ�ַ���ȴ��ļ�bfOffBits֮������ݾ�����Ҫ��ͼ��������*/
	
};/*����ͷ�ļ��ṹ*/ 

typedef struct BITMAPINFOHEADER{
	DWORD biSize;/*4byte ���ṹ�Ĵ�С*/ 
	LONG biWidth; /* 4byte BMPͼ��Ŀ��*/ 
	LONG biHeight;/* 4byte BMPͼ��ĸ߶�*/ 
	WORD biPlanes;/* 2byte ��Ϊ0*/ 
	WORD biBitCount; /* 2byte BMPͼ���ɫ���һ�������ö���λ��ʾ��������1��4��8��16��24��32���ֱ��Ӧ��ɫ��16ɫ�� 256ɫ��16λ�߲�ɫ��24λ���ɫ��32λ��ǿ�����ɫ*/
	DWORD biCompression;/* 4byte ѹ����ʽ��0��ʾ��ѹ����1��ʾRLE8ѹ����2��ʾRLE4ѹ����3��ʾÿ������ֵ��ָ�����������*/
	DWORD biSizeImage;/* 4byte BMPͼ�����ݴ�С��������4�ı�����ͼ�����ݴ�С����4�ı���ʱ��0��䲹��*/
	LONG biXPelsPerMeter;/* 4byte ˮƽ�ֱ���*/
	LONG biYPelsPerMeter;/* 4byte ��ֱ�ֱ���*/
	DWORD biClrUsed;/* 4byte BMPͼ��ʹ�õ���ɫ��0��ʾʹ��ȫ����ɫ*/
	DWORD biClrImportant;/* 4byte ��Ҫ����ɫ������ֵΪ0ʱ������ɫ����Ҫ������ʹ�õ�ɫ���BMPͼ����˵�����Կ����ܹ���ʾ������ɫʱ����ֵ����������������ʾ��ɫ*/
};	/*������Ϣͷ�ṹ */

typedef struct RGB	{
	BYTE Blue;
    BYTE Green;
	BYTE Red;
}RGB;/*������ɫ�ṹ */

typedef struct bmp{
	BITMAPFILEHEADER file;
	BITMAPINFOHEADER info;
}bmp;/*����BMP�ļ��ṹ */

BITMAPFILEHEADER ReadHead();
BITMAPINFOHEADER ReadInfo();


int main(){
	printf("��Ϊ4�ֽڶ���Ĵ���һ����ȥ�������ͼƬ�����⣬����ֻ�ܴ�������4�ı�����ͼƬ.\n");	
 	printf("�������޸�Ҫ�����ͼƬ�Ŀ��Ϊ4�ı�����\nȻ��ͼƬ������Ϊsource.bmp���ƶ���D�̸�Ŀ¼��\n");
  	printf("������ϲ����"); 
  	system("pause");
  	
   bmp m;
   m.file=ReadHead();
   m.info=ReadInfo();
   int width=m.info.biWidth;/*��ȡͼƬ��� */ 
   int height=m.info.biHeight;/*��ȡͼƬ�߶� */ 
   int Allpoint=width*height;/*���������� */ 
   int BmpSize=m.file.bfSize;/*��ȡͼƬ��С */ 
   printf("ͼƬ��Ϣ\n");
   printf("ͼƬ��С��%dKB\n",BmpSize);
   printf("�߶ȣ�%d\t��ȣ�%d\t�����أ�%d\n",height,width,Allpoint);/*��ʾһЩ��Ϣ */ 
   RGB bmrgb[height][width];
   /*����һ����ά����洢ÿ�����ص�RGBֵ */ 
   int i;
   int j;
   int L=256;/*�Ҷȼ�����*/ 
   int minb,ming,minr; /*���صĻҶ�ֵ����С�Ҷ�ֵ*/ 
   int CDFBmin,CDFGmin,CDFRmin;/*�ۼƷֲ�������Сֵ*/ 
   int PointnumB[L]; 
   int PointnumG[L];
   int PointnumR[L];/*��������洢ÿ���Ҷȼ������ظ���*/
   int CDFB[L];
   int CDFG[L];
   int CDFR[L];/*��������洢�ۼ�ֱ��ͼ*/
   
   
   FILE *fpin=NULL;
   fpin=fopen("D:\\source.bmp","rb");
   FILE *fpout=NULL;
   fpout=fopen("D:\\out.bmp","wb");
   fwrite(&m.file,sizeof(BITMAPFILEHEADER),1,fpout);
   fwrite(&m.info,sizeof(BITMAPINFOHEADER),1,fpout);
   
   if(fpin==NULL){
   	printf("�ļ�������\n");
   }else{
 	 fseek(fpin,54L,0);
 	 for(i=0;i<height;i++){
 	 	for(j=0;j<width;j++){
	 	 	fread(&bmrgb[i][j],sizeof(RGB),1,fpin);
	 	 	bmrgb[i][j].Blue;
	 	 	bmrgb[i][j].Green;
	 	 	bmrgb[i][j].Red;
	 	 	
	 	 	PointnumB[bmrgb[i][j].Blue]++;
	 	 	PointnumG[bmrgb[i][j].Green]++;
	 	 	PointnumR[bmrgb[i][j].Red]++;
		  
	 	 }
       fseek(fpin,(3*width)%4,SEEK_CUR);
 	 }
 	 

        CDFB[0]=PointnumB[0];
   		CDFG[0]=PointnumG[0];
		CDFR[0]=PointnumR[0];
 	 for(i=1;i<L;i++){
		CDFB[i]=CDFB[i-1]+PointnumB[i];
   		CDFG[i]=CDFG[i-1]+PointnumG[i];
		CDFR[i]=CDFR[i-1]+PointnumR[i];
 	 	
 	 }/*�����ۼƷֲ�����*/ 
 	
	  for(i=0;i<L;i++){
         if(PointnumB[i]!=0){
         	minb=i;
         	CDFBmin=PointnumB[i];
         	break;
         }
 	 }/*����B��������С�ۼƷֲ�����ֵ*/
	  for(i=0;i<L;i++){
         if(PointnumG[i]!=0){
         	ming=i;
         	CDFGmin=PointnumG[i];
         	break;
         }
 	 }/*����G��������С�ۼƷֲ�����ֵ*/ 
	  for(i=0;i<L;i++){
         if(PointnumR[i]!=0){
         	minr=i;
         	CDFRmin=PointnumR[i];
         	break;
         }
 	 }/*����R��������С�ۼƷֲ�����ֵ*/ 
	  	 
 	 fseek(fpin,54L,0);
 	 for(i=0;i<height;i++){
 	 	for(j=0;j<width;j++){
	 	 	bmrgb[i][j].Blue=(L-1)*(CDFB[bmrgb[i][j].Blue]-CDFBmin)/(Allpoint-CDFBmin);
            bmrgb[i][j].Green=(L-1)*(CDFG[bmrgb[i][j].Green]-CDFGmin)/(Allpoint-CDFGmin);
	 	 	bmrgb[i][j].Red=(L-1)*(CDFR[bmrgb[i][j].Red]-CDFRmin)/(Allpoint-CDFRmin);

	 	 	
	 	 	
            fwrite(&bmrgb[i][j],sizeof(RGB),1,fpout);
	 	 }
       fseek(fpout,(3*width)%4,SEEK_CUR);
 	 }
 	 
   }

   fclose(fpin);
   fclose(fpout);
   printf("���\n������ͼƬ��D�̸�Ŀ¼��ͼƬ��Ϊout.bmp\n");
}


BITMAPFILEHEADER ReadHead(){
	bmp p;
	FILE *fp=NULL;
	fp=fopen("D:\\source.bmp","rb");
	if(fp==NULL){
		printf("�ļ�������\n");
	}else{
		fread(&p.file,sizeof(BITMAPFILEHEADER),1,fp);
	
	}
	return p.file;
}/*��ȡͷ�ļ�������*/ 

BITMAPINFOHEADER ReadInfo(){
	bmp q;
	FILE *fp=NULL;
	fp=fopen("D:\\source.bmp","rb");
	if(fp==NULL){
		printf("�ļ�������\n");
	}else{
		fseek(fp,sizeof(BITMAPFILEHEADER),0);
		fread(&q.info,sizeof(BITMAPINFOHEADER),1,fp);
	}
	return q.info;
}/*��ȡ��Ϣͷ*/

