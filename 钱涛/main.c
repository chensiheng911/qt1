#include<reg51.h> 
#define uint unsigned int 
#define uchar unsigned char 
#define Time_RG 80; // ���� ��� ���̵� �� ʱ�� ���� ���� ��ʱ�� 0 ��ʱ 20S ʱ�� ��
#define Time_YE 30; // ���� �Ƶ���˸ ʱ����� 5S ʱ�䵽 
uint Time_Mod ; // ����ʱ����� ���� �л� ���̵�ģʽ 
uint Time; // ���� ʱ��Ƚϱ��� �������� ������ �� �Ƶ���˸ ʱ�䲻ͬ 
uchar Model; // ���� ���̵���ʾ ģʽ���� // 0�� �����̵��� �ϱ������ 1�� �����̵��� �����Ƶ���˸ �ϱ� ��� �� // 2�� ��������� �ϱ��̵��� 3�� ��������� �ϱ��̵��� �ϱ� �Ƶ��� ˸
uchar All_red=0x36; // ��� ȫ�� IO ����ֵ 
uchar DX_G_NB_R =0X1E; // ���� �̵��� �ϱ������ IO ����ֵ 
uchar DX_Y_NB_R =0X2E; // ���� �Ƶ��� �ϱ������ IO ����ֵ 
uchar DX_R_NB_G =0X33; // ���� ����� �ϱ��̵��� IO ����ֵ 
uchar DX_R_NB_Y =0X35; // ���� ����� �ϱ��Ƶ��� IO ����ֵ 
uchar DX_shanshuo,NB_shanshuo; // ���� �Ƶ���˸ ���� 
uchar Time_shanshuo; // ����Ƶ���˸ ʱ���� 
bit Flag_shanshuo=0; // ���� ��˸ �л���־λ //********* T0 ��ʱ�� ��ʼ�� ���� **********// 

void Time0_int() 
{ 
	TMOD |= 0X01; //ʹ�ö�ʱ�� T0 �Ķ�ʱ������ʽ�������ڹ�����ʽ 1 
	TH0 = 0x3C; //��ʱ�� T0 �ĸ� 8 λ��ֵ������ֵΪ 1 
	TL0 = 0xB0; //��ʱ�� T0 �ĵ� 8 λ��ֵ ��ʱ�� 0 ��ʱ 50ms ����ֵ 
	ET0 = 1; //���� ��ʱ�� 0 �ж� 
	EA = 1; //�����ж� 
	TR0 = 1; //������ʱ�� 0 
}

void Con_Shanshuo() { 

if((Model==1)||(Model==3)) //���̵��� 1 �� 3 ģʽ ��Ҫ�Ƶ���˸ ��ģʽ 
{ 
   Time=Time_YE; // �л���ʾģʽʱ��Ϊ �Ƶ���˸ģʽ 5S

}
else // ���ڻƵ���˸ ģʽ�� 
{ 
Time=Time_RG ; // �л���ʾģʽʱ��Ϊ ���̵� ����ʱ�� 20S 
}
if(Model==1) 
{ 

	if(Flag_shanshuo==0) { 
	DX_shanshuo=0X2E; 
}
else 
{ 
	DX_shanshuo=0XFE; 
} 
}
else if(Model==3) { 

if(Flag_shanshuo==0) { 

	NB_shanshuo=0X35;
 }
 else 
 { 
	NB_shanshuo=0XF7;
 } 
 } 
 }
 
 
 
void main() { 
P2=P0=All_red; // ��� ȫ�� 
Time0_int(); // ��ʱ�� 0 ��ʼ�� ���� 

while(1)
{ 
Con_Shanshuo(); 
	switch(Model) { 
	case 0 : { P2=P0=DX_G_NB_R ; // ���� �̵��� �ϱ� ����� 
	}break; 
	
	case 1 : { P2=P0= (DX_Y_NB_R | DX_shanshuo ); // ���� �Ƶ��� �ϱ������ 
	}break; 
	
	case 2 : { P2=P0=DX_R_NB_G; // ���� ����� �ϱ��̵��� 
	}break; 
	
	case 3 : { P2=P0=(DX_R_NB_Y | NB_shanshuo); // ���� ����� �ϱ� �Ƶ��� 
	}break; } } }//********* T0 ��ʱ���жϷ����� **********//

void timer0(void) interrupt 1 using 0 { 

Time_Mod++; // ��� �̵� ���� ��ʱ���� ++ 
if(Time_Mod>=Time) // ʱ�䵽 

{ 
	Model++; // ���̵� ģʽ ++ 
	if(Model>3)
	 { 
	 Model=0; 
	 }
	 Time_Mod=0; 
	 }
	
	if((Model==1)||(Model==3)) // �Ƶ���˸ģʽ 
	{ 
	  Time_shanshuo++; // ��˸ʱ���� ++ 
	if(Time_shanshuo>10) // 10*50ms ʱ�䵽 
    {
	 Flag_shanshuo=~Flag_shanshuo; // ��˸��־λ ȡ�� 
	 Time_shanshuo=0; 
	} 

}else

	Time_shanshuo=0; 
	TH0 = 0x3C; //��ʱ�� T0 �ĸ� 8 λ��ֵ������ֵΪ 1 
	TL0 = 0xB0; //��ʱ�� T0 �ĵ� 8 λ��ֵ ��ʱ�� 0 ��ʱ 50ms ����ֵ 
	TR0 = 1; //������ʱ�� 0 
}





