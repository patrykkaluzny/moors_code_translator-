#include "main.h"

xdata unsigned char UART_DATA[10];
xdata unsigned int save,licznik_UART=0;
xdata unsigned int flaga_stop=0, flaga_start=0,flaga_LCD=0,flaga_triak= 0;
xdata unsigned int TH1_ustaw,TL1_ustaw, triak_on=0,czas=0,TH0_ustaw=0,TL0_ustaw=0 ; 
xdata unsigned int licznik_timer=0, licznik_LCD=0,licznik_pom_LCD=0;



/////////////////////////Zakomentowane do testu czy dziala na diodzie//////////////////////////////
/*
void triak() interrupt 0 //przerwanie zewnatrzne 
{
	if(flaga_triak)
	{
	TH0=TH0_ustaw;
	TL0=TL0_ustaw;
	
	}
	TR0 = 1;
}
void Init_T0() interrupt 1 //przerwanie timer1
{
	Triak_On();
	TR0 = 0;
	delay(5);
	Triak_Off();
	
	
}
*/
///////////////////////////////////////////////////////////
void Timer1_interrupt() interrupt 3
{
			if(*WSK_TRANSLATED_MORS==36)		
			{
				flaga_LCD=1;
			}
			if(*WSK_TRANSLATED_MORS!='\0') 
			{
				if(*WSK_TRANSLATED_MORS=='-')
				{
					LedOn(LED_D5);	
					flaga_triak = 1 ; 
					if(licznik_timer<10)
					{
						licznik_timer++;
					}
					else{
						triak_on=1;
						licznik_timer=0;
						*WSK_TRANSLATED_MORS++;
					}
				}
				else if(*WSK_TRANSLATED_MORS=='.') 
				{
					LedOn(LED_D5);	
					flaga_triak = 1;
					if(licznik_timer<5)
					{
						licznik_timer++;
					}
					else{
						triak_on=1;
						licznik_timer=0;
						*WSK_TRANSLATED_MORS++;
					}
				}
				else if(*WSK_TRANSLATED_MORS==36||triak_on)  
				{
					LedOff(LED_D5);
					flaga_triak= 0;
					if(licznik_timer<2)
					{
						licznik_timer++;
					}
					else{
						triak_on=0;
						licznik_timer=0;
						*WSK_TRANSLATED_MORS++;
					}
				}
				
			}	
			else
			{
				*WSK_TRANSLATED_MORS = &TRANSLATED_MORS[0];
			}
			TH1=TH1_ustaw;
			TL1=TL1_ustaw;
}

void UART_interrupt() interrupt 4
{
	if(RI)
	{
		if(SBUF=='%')
		{
			save=0;
			flaga_stop = 1 ; 
		}
		if(save)
		{
			
			UART_DATA[licznik_UART]=SBUF;
 			licznik_UART++;
		}
		if(SBUF=='$')
		{
			licznik_UART=0;
			ARRAY_clear(&UART_DATA);	
			save=1;	
			flaga_start=1;
			TR1=0;
		}		
		RI=0;		
	}
}
int main(void)
{
	//Zgoda na Timer2 ma wiekkszy priorytet niz UART   !!!!
	// Zgoada na Timer1 ma mniejszy priorytet niz UART !!!! 
	//Zgody na obsluge przerwan
	EA=1;  //Zgoda na przerwanie 
	ES=1;	 //Zgoda na przerwanie od UART
	ET1=1; //Zgoda na przerwanie od Timer1 ;
	EX0 = 1; //Zgoda na przerwanie zewnetrzne
	IT0 = 0; //Definiwanie wyzolenia 
	ET0 = 1; // Zgoda na przerwanie od TIMER0
	//inicjalizacja ekrany
	UART_init();
	LCD_init();
	
 
		//inicjalizacja Timer0
	czas=liczczas(5);
	TL0_ustaw=T0_rejestr_wartosc_poczatkowa(czas);
	TH0_ustaw=T0_rejestr_wartosc_poczatkowa(czas)>>8;
	TR0 = 0 ; 
	
	//inicjalizacja Timer1
	TMOD = 0x11;  // timera1 - 16bit Timer  TIMER0 - 16bit Timer
	TL1_ustaw=T1_rejestr_wartosc_poczatkowa(50);
	TH1_ustaw=T1_rejestr_wartosc_poczatkowa(50)>>8;
	TR1=0;
	
	//ARRAY_clear(&UART_DATA);		
	SetBit(CFG831,0); //XRAM 
	
	while(1)
	{
		
		if(flaga_stop)
		{
			ARRAY_clear(&TRANSLATED_MORS);
			MORS_trans(UART_DATA); //Tlumaczenie 
			
			// Wysylanie morsa do javy  start			
			UART_putchar('$');
			UART_puts(TRANSLATED_MORS);
			UART_putchar('%');
			// Wysylanie morsa do javy  stop
			Display_clear(); //moze nie dzialac 
			
			LCD_GoTo(0,0); //moze nie dzialac 
			LCD_Send_str(UART_DATA); 
			LCD_Display_Cursor_shift(0,0); // moze nie dzialac
		
			*WSK_TRANSLATED_MORS = &TRANSLATED_MORS[0];
			licznik_timer=0;
			triak_on=0;
			TH1=TH1_ustaw;
			TL1=TL1_ustaw;
			TR1=1;
		flaga_stop=0;
		}
		if(flaga_LCD)
		{
			if(licznik_pom_LCD)
			{
				if(licznik_LCD>=licznik_UART)
				{
				licznik_LCD=0;
				}
				LCD_Display_Cursor_shift(licznik_LCD,0);
				licznik_LCD++;
				licznik_pom_LCD=0;
			}
			else{
				licznik_pom_LCD++;
			}
			flaga_LCD=0;
			
		}
		
			
		
	}
	return 0;
}


	//MODE COM3 19200,0,8,1
	//ASSIGN COM3 <SIN> SOUT