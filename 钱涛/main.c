#include<reg51.h> 
#define uint unsigned int 
#define uchar unsigned char 
#define Time_RG 80; // 定义 红灯 和绿灯 亮 时间 变量 用于 定时器 0 计时 20S 时间 到
#define Time_YE 30; // 定义 黄灯闪烁 时间变量 5S 时间到 
uint Time_Mod ; // 定义时间变量 用来 切换 红绿灯模式 
uint Time; // 定义 时间比较变量 用来区分 红绿亮 和 黄灯闪烁 时间不同 
uchar Model; // 定义 红绿灯显示 模式变量 // 0： 东西绿灯亮 南北红灯亮 1： 东西绿灯灭 东西黄灯闪烁 南北 红灯 亮 // 2： 东西红灯亮 南北绿灯亮 3： 东西红灯亮 南北绿灯灭 南北 黄灯闪 烁
uchar All_red=0x36; // 红灯 全亮 IO 口数值 
uchar DX_G_NB_R =0X1E; // 东西 绿灯亮 南北红灯亮 IO 口数值 
uchar DX_Y_NB_R =0X2E; // 东西 黄灯亮 南北红灯亮 IO 口数值 
uchar DX_R_NB_G =0X33; // 东西 红灯亮 南北绿灯亮 IO 口数值 
uchar DX_R_NB_Y =0X35; // 东西 红灯亮 南北黄灯亮 IO 口数值 
uchar DX_shanshuo,NB_shanshuo; // 定义 黄灯闪烁 变量 
uchar Time_shanshuo; // 定义黄灯闪烁 时间间隔 
bit Flag_shanshuo=0; // 定义 闪烁 切换标志位 //********* T0 定时器 初始化 函数 **********// 

void Time0_int() 
{ 
	TMOD |= 0X01; //使用定时器 T0 的定时工作方式，工作于工作方式 1 
	TH0 = 0x3C; //定时器 T0 的高 8 位赋值，计数值为 1 
	TL0 = 0xB0; //定时器 T0 的低 8 位赋值 定时器 0 定时 50ms 赋初值 
	ET0 = 1; //允许 定时器 0 中断 
	EA = 1; //开总中断 
	TR0 = 1; //启动定时器 0 
}

void Con_Shanshuo() { 

if((Model==1)||(Model==3)) //红绿灯在 1 和 3 模式 需要黄灯闪烁 的模式 
{ 
   Time=Time_YE; // 切换显示模式时间为 黄灯闪烁模式 5S

}
else // 不在黄灯闪烁 模式下 
{ 
Time=Time_RG ; // 切换显示模式时间为 红绿灯 点亮时间 20S 
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
P2=P0=All_red; // 红灯 全亮 
Time0_int(); // 定时器 0 初始化 程序 

while(1)
{ 
Con_Shanshuo(); 
	switch(Model) { 
	case 0 : { P2=P0=DX_G_NB_R ; // 东西 绿灯亮 南北 红灯亮 
	}break; 
	
	case 1 : { P2=P0= (DX_Y_NB_R | DX_shanshuo ); // 东西 黄灯亮 南北红灯亮 
	}break; 
	
	case 2 : { P2=P0=DX_R_NB_G; // 东西 红灯亮 南北绿灯亮 
	}break; 
	
	case 3 : { P2=P0=(DX_R_NB_Y | NB_shanshuo); // 东西 红灯亮 南北 黄灯亮 
	}break; } } }//********* T0 定时器中断服务函数 **********//

void timer0(void) interrupt 1 using 0 { 

Time_Mod++; // 红灯 绿灯 点亮 延时变量 ++ 
if(Time_Mod>=Time) // 时间到 

{ 
	Model++; // 红绿灯 模式 ++ 
	if(Model>3)
	 { 
	 Model=0; 
	 }
	 Time_Mod=0; 
	 }
	
	if((Model==1)||(Model==3)) // 黄灯闪烁模式 
	{ 
	  Time_shanshuo++; // 闪烁时间间隔 ++ 
	if(Time_shanshuo>10) // 10*50ms 时间到 
    {
	 Flag_shanshuo=~Flag_shanshuo; // 闪烁标志位 取反 
	 Time_shanshuo=0; 
	} 

}else

	Time_shanshuo=0; 
	TH0 = 0x3C; //定时器 T0 的高 8 位赋值，计数值为 1 
	TL0 = 0xB0; //定时器 T0 的低 8 位赋值 定时器 0 定时 50ms 赋初值 
	TR0 = 1; //启动定时器 0 
}





