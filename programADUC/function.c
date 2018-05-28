#include "function.h"
#define DOT_TIME 500
#define LINE_TIME 1000
#define SPACE_TIME 2000

xdata unsigned int MORS_SIGNAL_TIME[50];
xdata unsigned char TRANSLATED_MORS[50];
xdata unsigned char *WSK_TRANSLATED_MORS = &TRANSLATED_MORS[0];

xdata unsigned char mors[][5]={     // TODO czemnu robisz 5 jak morse ma 4 znaki 
	
	{ ".-" },		//A	
	{ "-..." },		//B
	{ "-.-." },		//C
	{ "-.." },		//D
	{ "." },		//E
	{ "..-." },		//F
	{ "--." },		//G
	{ "...." },		//H
	{ ".." },		//I
	{ ".---" },		//J
	{ "-.-" },		//K
	{ ".-.." },		//L
	{ "--" },		//M
	{ "-." },		//N
	{ "---" },		//O
	{ ".--." },		//P
	{ "--.-" },		//Q
	{ ".-." },		//R
	{ "..." },		//S
	{ "-" },		//T
	{ "..-" },		//U
	{ "...-" },		//V
	{ ".--" },		//W
	{ "-..-" },		//X
	{ "-.--" },		//Y
	{ "--.." }		//Z
	
};
void delay(unsigned long c)
{
	int a = 0;
	int i =0;
	for(i; i<c;i++)
	{
	}
}


void delay_ms(int ms)
{
	uint32_t cycles = ((float)ms - 0.022)/0.016 + 0.5;
	delay(cycles);
}
void delay_us(int us)
{
	uint32_t cycles = ((float)us/1000.0  - 0.022)/0.016 + 0.5;
	delay(cycles);
}


// LCD //////////////////////////////////////////
void LCD_Send(char dane, bit RS, bit RW)
{
	LCDZgoda = 1;
	LCDRS = RS;
	LCDRW = RW;
	LCDDANE = dane;
	LCDZgoda = 0;
	delay(50);
	LCDZgoda = 1;
	delay(500);
}
void LCD_znak(char znak)
{
	LCD_Send(znak,1,0);
}

void LCD_Send_str(char *str)
{
	while(*str != '\0')
	{
		LCD_znak(*str);
		str++;
	}
}

void LCD_GoTo(unsigned char x, unsigned char y)
{
	LCD_Send(0x80 | (x + (0x40 * y)), 0 ,0);
}
void Function_set(int D, int N,int F)
{
	LCD_Send(0x20+0x10*D+0x8*N+0x4*F,0,0);
}

void Display_ON_OFF(int D, int C,int B)
{
	LCD_Send(0x08+0x4*D+0x2*C+0x1*B, 0,0);
}
void Display_clear(void)
{
	LCD_Send(0x01,0,0);
	delay_us(1540);
}
void Entry_mode(int I, int S )
{
	LCD_Send(0x04+0x2*I+0x1*S,0,0);
}
void LCD_init(void)
{
	I2CM = 1;
	MDE = 1;
	delay_ms(20);
	LCD_Send(0x30,0,0);
	delay_ms(5);
	LCD_Send(0x30,0,0);
	delay_ms(1);
	LCD_Send(0x30,0,0);
	Function_set(1,1,0);
	Display_ON_OFF(0,0,0); 
	Display_clear(); 
	delay_ms(1);
	Entry_mode(1,0); 
	Display_ON_OFF(1,0,0);
}

void LCD_Display_Cursor_shift(int S, int R)
{
	LCDZgoda = 1;
	LCDRS = 0;
	LCDRW = 0;
	LCDDANE=0x10+0x8*S+0x4*R;
	LCDZgoda = 0;
	delay_us(37);
}
// UART /////////////////////////////////////////
void UART_init(void)
{
	SM0=0; 
	SM1=1;
	REN=1; 
	T3CON=0x84;
	T3FD=0x08;
}

char UART_putchar (char c)
{
	SBUF=c;
	while(!TI);
	TI=0;
	return c;
}
void UART_puts(char* str)
{
	while (*str!='\0')
	{
		UART_putchar(*str);
		str++;
	}
}
/*
void UART_printf(const char * format,...)
{
	xdata char buffer[20];
	va_list args;
	int i=0;
	va_start (args, format);
	vsprintf(buffer,format, args);
	va_end(args);
	
	UART_puts(buffer);
	
}
*/
void ARRAY_clear(char* str)
{
	while (*str!='\0')
	{
		*str=0;
		str++;
	}
}
//MORS//////////////////////////////

void MORS_trans( unsigned char *tab)
{
	xdata int poz = -1;   //przeniesione do xdata prze brak dostepenj pamieci 
	xdata	int L = 0;			//przeniesione do xdata prze brak dostepenj pamieci 
	xdata int a=0;				//przeniesione do xdata prze brak dostepenj pamieci 
	while (*tab != '\0')
	{
		
		if (*tab > 91)   // Rozroznienie duzych i malych liter kodu ASCI
		{
			 a = *tab - 97;
		}
		else
		{
			a = *tab - 65;
		}
		L=0;
		while (mors[a][L] != '\0') 
		{
			poz++;
			TRANSLATED_MORS[poz] = mors[a][L];		// zapisanie znaku do tablicy wynikowej
			L++;
		}
		poz++;
		TRANSLATED_MORS[poz] = 32 ; // rozdzielenie (spacja) kodow poszczegolnych znakow
		tab++;
	}
}

/*void MORS_signal(){

	
	while(*WSK_TRANSLATED_MORS != '\0')
	{
	
		if(*WSK_TRANSLATED_MORS=='-')
		{
		LedOn(LED_D5);
		T1_ustaw_czas_ms(MORS_LINE);
			TR1 =1; // run timer 1 
			
		}
		if(*WSK_TRANSLATED_MORS=='.')
		{
		LedOn(LED_D5);
		T1_ustaw_czas_ms(MORS_DOT);
		TR1 =1; // run timer 1 
		}
	 else
	 {
		//przesuniecie kursora na LCD 
	 
	 }
	
	WSK_TRANSLATED_MORS++;
	}
}
*/

/*void MORS_SINGAL_TIME_ARRAY_FILL( )
{
	unsigned char *WSK_POM_TRANSLATED_MORS = &TRANSLATED_MORS[0];
	unsigned int poz_pom = 0 ; 
	ARRAY_clear(MORS_SIGNAL_TIME);
	while(*WSK_POM_TRANSLATED_MORS != '\0')
	{
	
		if(*WSK_POM_TRANSLATED_MORS=='-')
		{
		MORS_SINGAL_TIME[poz_pom]=LINE_TIME;
		poz_pom++;
			
		}
		else if(*WSK_POM_TRANSLATED_MORS=='.')
		{
			MORS_SINGAL_TIME[poz_pom]=DOT_TIME;
			poz_pom++;
		}
	 else
	 {
		 MORS_SINGAL_TIME[poz_pom]=SPACE_TIME;
			poz_pom++;
	 
	 }
	
	WSK_POM_TRANSLATED_MORS++;
	}
}

int CYCLE( unsigned int *MORS_SIGNAL_TIME_POM;)//przyjume wskaznik na elelment tablicy MORS_SIGNAL_TIME
{
return *MORS_ SIGNAL_TIME_POM / 50 ;
}
*/






